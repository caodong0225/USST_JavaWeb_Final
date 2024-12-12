package usst.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Getter
@Setter
public class UserGeneralDTO {
    private Integer id;
    private String username;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
