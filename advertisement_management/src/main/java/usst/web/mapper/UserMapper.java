package usst.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;

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
    @Select("select user.id,user.username,user.password from user where username = #{username}")
    User selectUserByUsername(String username);
    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    boolean insertUser(User userAdd);
    @Delete("DELETE FROM user WHERE id = #{id}")
    boolean deleteUserById(Integer id);
}
