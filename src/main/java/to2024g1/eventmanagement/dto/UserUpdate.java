package to2024g1.eventmanagement.dto;

import jakarta.persistence.Column;
import lombok.*;
import to2024g1.eventmanagement.entity.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdate {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String password;
    private List<Role> roles;
    private String currentPassword;
    private String newPassword;
    private String reNewPassword;
}
