package usst.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
@Getter
@Setter
public class UserRole implements Serializable {
    private Integer id;
    private String roleName;
    private Integer userId;
}
