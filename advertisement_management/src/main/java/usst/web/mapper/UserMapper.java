package usst.web.mapper;

import org.apache.ibatis.annotations.Select;
import usst.web.dto.UserInfoDTO;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
public interface UserMapper {
    @Select("""
            SELECT user.id,user.username,user_role.role_name FROM user
            inner join user_role on user.id = user_role.user_id
            WHERE user.id = #{id}
            """)
    UserInfoDTO getUserById(Integer id);
}
