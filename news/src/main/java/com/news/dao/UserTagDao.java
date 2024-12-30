package com.news.dao;

import com.news.model.UserTag;

import java.util.List;

public interface UserTagDao {
    public boolean addUserTag(UserTag userTag);
    public List<String> getTagsByUsername(String username);
    public boolean updateUserTag(UserTag userTag);
    public UserTag getUserTagByUsernameAndTag(String username, String tag);

}
