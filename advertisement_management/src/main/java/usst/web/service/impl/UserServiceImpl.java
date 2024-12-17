package usst.web.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import usst.web.annotation.CacheEvict;
import usst.web.dto.UserGeneralDTO;
import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;
import usst.web.entity.UserRole;
import usst.web.mapper.RoleMapper;
import usst.web.mapper.UserMapper;
import usst.web.service.IUserService;
import usst.web.util.SafetyUtils;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public UserInfoDTO getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean isUserExist(String username) {
        return userMapper.selectUserByUsername(username) != null;
    }

    @Override
    public User isCorrect(String username, String password) {
        User user = userMapper.selectUserByUsername(username);
        if(SafetyUtils.checkBCrypt(password, user.getPassword()))
        {
            return user;
        }
        return null;
    }

    @Override
    public boolean registerUser(User user) {
        user.setPassword(SafetyUtils.doBCrypt(user.getPassword()));
        return userMapper.insertUser(user);
    }

    @Override
    public List<UserGeneralDTO> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    @CacheEvict(key = "role::userId::", index = 0)
    public boolean deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    @CacheEvict(key = "role::userId::", index = 0)
    public boolean updateUserRoleById(Integer id, String roleName) {
        UserRole userRole = new UserRole();
        userRole.setUserId(id);
        userRole.setRoleName(roleName);
        if(roleMapper.getRoleByUserId(id) == null)
        {
            return roleMapper.insertRole(userRole);
        }
        else
        {
            return roleMapper.updateRoleByUserId(userRole);
        }
    }

    @Override
    public boolean deleteUserRoleById(Integer id) {
        return roleMapper.deleteRoleByUserId(id);
    }

    @Override
    public boolean updatePasswordById(Integer id, String password) {
        String hash = SafetyUtils.doBCrypt(password);
        return userMapper.updatePasswordById(id, hash);
    }
}
