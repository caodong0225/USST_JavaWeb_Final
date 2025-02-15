package usst.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import usst.web.dto.UserGeneralDTO;
import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
public interface UserMapper {
    @Select("""
            SELECT user.id,user.username,ur.role_name FROM user
            left join (select role_name,user_id from user_role) as ur on user.id = ur.user_id
            WHERE user.id = #{id}
            """)
    UserInfoDTO getUserById(Integer id);
    @Select("select user.id,user.username,user.password from user where username = #{username}")
    User selectUserByUsername(String username);
    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    boolean insertUser(User userAdd);
    @Delete("DELETE FROM user WHERE id = #{id}")
    boolean deleteUserById(Integer id);
    @Select("""
            SELECT user.id,user.username,ur.role_name,user.created_at,user.updated_at FROM user
            left join (select role_name,user_id from user_role) as ur on user.id = ur.user_id
            """)
    List<UserGeneralDTO> getUsers();

    @Update("""
        update user set user.password = #{password} where id = #{id}
    """)
    boolean updatePasswordById(Integer id,String password);
}
