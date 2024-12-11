package usst.web.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import usst.web.dto.UserInfoDTO;
import usst.web.mapper.UserMapper;
import usst.web.service.IUserService;

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
}
