package usst.web.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import usst.web.annotation.Cacheable;
import usst.web.mapper.RoleMapper;
import usst.web.service.IRoleService;

/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    @Cacheable(key = "role::userId::#userId")
    public String getRoleNameByUserId(Integer userId) {
        return roleMapper.getRoleByUserId(userId);
    }
}
