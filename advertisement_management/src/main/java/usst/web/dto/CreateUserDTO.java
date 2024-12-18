package usst.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jyzxc
 * @since 2024-12-18
 */
@Getter
@Setter
public class CreateUserDTO {
    private String username;
    private String password;
}
