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
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
}
