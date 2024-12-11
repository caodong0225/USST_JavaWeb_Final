package usst.web.service;

import usst.web.dto.UserInfoDTO;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
public interface IUserService {
    UserInfoDTO getUserById(Integer id);
}
