package usst.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
}
