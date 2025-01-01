package com.news.dao;

import com.news.model.UserTag;

import java.util.List;

public interface UserTagDao {
    boolean addUserTag(UserTag userTag);

    List<String> getTagsByUsername(String username);

    boolean updateUserTag(UserTag userTag);

    UserTag getUserTagByUsernameAndTag(String username, String tag);

}
