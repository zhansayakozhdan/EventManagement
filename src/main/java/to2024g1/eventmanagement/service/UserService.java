package to2024g1.eventmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import to2024g1.eventmanagement.dto.UserCreate;
import to2024g1.eventmanagement.dto.UserDto;
import to2024g1.eventmanagement.dto.UserUpdate;
import to2024g1.eventmanagement.entity.Role;
import to2024g1.eventmanagement.entity.User;
import to2024g1.eventmanagement.mapper.UserMapper;
import to2024g1.eventmanagement.repository.RoleRepository;
import to2024g1.eventmanagement.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return (User) authentication.getPrincipal();
        }
    }
    public String createUser(UserCreate userCreate) {
        Optional<User> optionalUser = userRepository.findByEmail(userCreate.getEmail());
        if(optionalUser.isPresent()){
            return "EMAIL IS BUSY!";
        }

        if(!userCreate.getPassword().equals(userCreate.getRePassword())){
            return "PASSWORDS ARE NOT SAME!";
        }

        final String encodedPassword = passwordEncoder.encode(userCreate.getPassword());
        userCreate.setPassword(encodedPassword);

        User user = UserMapper.INSTANCE.toEntity(userCreate);
        Role roleUser = roleRepository.findRoleUser();
        user.setRoles(Collections.singletonList(roleUser));
        //List.of(role1, role2, role3)
        //Collections.singletonList(userRole) если мы знаем что по дефолту толька одна роль будет задана

        userRepository.save(user);
        return "Account created successfully!";
    }

    public UserDto editUser(UserUpdate userUpdate) {
        User user = getUserById(userUpdate.getId());
        if(user == null){
            return null;
        } else {
            user = UserMapper.INSTANCE.toUpdatedUser(userUpdate);
        }
        User saved = userRepository.save(user);
        return UserMapper.INSTANCE.toDtoView(saved);
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }


    public UserDto changeUserPassword(UserUpdate userUpdate) {
        User user = getUserById(userUpdate.getId());
        if(user.getPassword().equals(userUpdate.getCurrentPassword())){
            if(userUpdate.getNewPassword().equals(userUpdate.getReNewPassword())){
                user = UserMapper.INSTANCE.toUpdatedUser(userUpdate);
            }
            else {
                throw new RuntimeException("NEW PASSWORDS ARE NOT SAME!");
            }
        } else {
            throw new RuntimeException("OLD PASSWORDS ARE NOT SAME!");
        }
        User saved = userRepository.save(user);
        return UserMapper.INSTANCE.toDtoView(saved);
    }
}
