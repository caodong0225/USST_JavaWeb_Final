package com.news.service;

import com.news.dao.UserTagDao;
import com.news.dao.impl.UserTagDaoImpl;
import com.news.model.UserTag;

import java.util.List;

public class UserTagService {
    private static UserTagService instance;
    public static UserTagService getInstance() {
        if (instance == null) {
            instance = new UserTagService();
            instance.init();
        }
        return instance;
    }
    private UserTagDao userTagDao;
    private void init() {
        userTagDao = new UserTagDaoImpl();
    }

    public boolean setUserTag(UserTag userTag) {
        if (userTagDao.getUserTagByUsernameAndTag(userTag.getUsername(), userTag.getTag()) == null) {
            return userTagDao.addUserTag(userTag);
        } else {
            return userTagDao.updateUserTag(userTag);
        }
    }
    public List<String> getTagsByUsername(String username) {
        return userTagDao.getTagsByUsername(username);
    }
}
