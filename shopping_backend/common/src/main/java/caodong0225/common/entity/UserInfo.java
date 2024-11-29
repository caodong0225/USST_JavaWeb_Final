package caodong0225.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author jyzxc
 * @since 2024-11-17
 */
@Data
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "主键ID", example = "1")
    private Integer id;
    @Schema(description = "用户名", example = "admin")
    private String userName;
    @Schema(description = "昵称", example = "张三")
    private String nickName;
}
