package usst.web.service;

import usst.web.dto.UserGeneralDTO;
import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
public interface IUserService {
    UserInfoDTO getUserById(Integer id);
    boolean isUserExist(String username);
    User isCorrect(String username, String password);
    boolean registerUser(User user);
    List<UserGeneralDTO> getUsers();
    boolean deleteUserById(Integer id);
}
