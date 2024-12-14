package usst.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import usst.web.entity.UserRole;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
public interface RoleMapper {
    @Select("select user_role.role_name from user_role where user_id = #{userId}")
    String getRoleByUserId(Integer userId);
    @Insert("insert into user_role (role_name, user_id) values (#{roleName}, #{userId})")
    boolean insertRole(UserRole role);
    @Delete("delete from user_role where user_id = #{userId}")
    boolean deleteRoleByUserId(Integer userId);
    @Update("update user_role set role_name = #{roleName} where user_id = #{userId}")
    boolean updateRoleByUserId(UserRole role);
}
