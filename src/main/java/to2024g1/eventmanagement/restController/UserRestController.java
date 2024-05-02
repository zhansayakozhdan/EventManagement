package to2024g1.eventmanagement.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import to2024g1.eventmanagement.dto.UserCreate;
import to2024g1.eventmanagement.dto.UserDto;
import to2024g1.eventmanagement.dto.UserUpdate;
import to2024g1.eventmanagement.entity.User;
import to2024g1.eventmanagement.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("{id}")
    public User getUserView(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public String createUser(@RequestBody UserCreate userCreate){
        return  userService.createUser(userCreate);
    }

    @PutMapping
    public UserDto editUser(@RequestBody UserUpdate userUpdate){
        return userService.editUser(userUpdate);
    }
}
