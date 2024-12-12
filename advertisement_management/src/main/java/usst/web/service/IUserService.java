package usst.web.service;

import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
public interface IUserService {
    UserInfoDTO getUserById(Integer id);
    boolean isUserExist(String username);
    UserInfoDTO isCorrect(String username, String password);
    boolean registerUser(User user);
}
