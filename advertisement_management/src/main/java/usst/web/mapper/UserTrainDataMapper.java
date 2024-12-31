package usst.web.mapper;

import org.apache.ibatis.annotations.*;
import usst.web.entity.UserTrainData;

@Mapper
public interface UserTrainDataMapper {

    @Insert("INSERT INTO user_train_data (user_name, age, gender, occupation, education, region, country, device, preference) VALUES (#{userName}, #{age}, #{gender}, #{occupation}, #{education}, #{region}, #{country}, #{device}, #{preference})")
    void insertUserTrainData(UserTrainData userTrainData);

    @Select("SELECT preference FROM user_train_data WHERE user_name = #{userName}")
    String findPreferencesByUserName(String userName);

    @Update("UPDATE user_train_data SET preference = #{preference} WHERE user_name = #{userName}")
    void updateUserPreference(@Param("userName") String userName, @Param("preference") String preference);

}