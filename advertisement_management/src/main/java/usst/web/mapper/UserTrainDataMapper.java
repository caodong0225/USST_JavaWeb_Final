package usst.web.mapper;

import usst.web.entity.UserTrainData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTrainDataMapper {

    @Insert("INSERT INTO user_train_data (age, gender, occupation, education, region, country, device) VALUES (#{age}, #{gender}, #{occupation}, #{education}, #{region}, #{country}, #{device})")
    void insertUserTrainData(UserTrainData userTrainData);
}