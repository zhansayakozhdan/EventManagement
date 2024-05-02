package to2024g1.eventmanagement.dto;

import lombok.*;
import to2024g1.eventmanagement.entity.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreate {
    private String fullName;
    private String email;
    private  String password;
    private String rePassword;
}
