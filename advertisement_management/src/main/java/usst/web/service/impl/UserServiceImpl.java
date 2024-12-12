package usst.web.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;
import usst.web.mapper.UserMapper;
import usst.web.service.IUserService;
import usst.web.util.SafetyUtils;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfoDTO getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean isUserExist(String username) {
        return userMapper.selectUserByUsername(username) != null;
    }

    @Override
    public UserInfoDTO isCorrect(String username, String password) {
        User user = userMapper.selectUserByUsername(username);
        if(SafetyUtils.checkBCrypt(password, user.getPassword()))
        {
            return this.getUserById(user.getId());
        }
        return null;
    }

    @Override
    public boolean registerUser(User user) {
        user.setPassword(SafetyUtils.doBCrypt(user.getPassword()));
        return userMapper.insertUser(user);
    }
}
