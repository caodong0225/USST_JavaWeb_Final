package usst.web.mapper;

import org.apache.ibatis.annotations.*;
import usst.web.entity.UserTrainData;

@Mapper
public interface UserTrainDataMapper {

    @Insert("INSERT INTO user_train_data (\n" +
            "            user_name, age, gender, occupation, education_level, region, country, device,\n" +
            "            Fashion, Art, Entertainment, Education, Pets, Eco, Weather, Technology, Politics, Economy\n" +
            "        ) VALUES (\n" +
            "            #{userName}, #{age}, #{gender}, #{occupation}, #{educationLevel}, #{region}, #{country}, #{device},\n" +
            "            #{Fashion}, #{Art}, #{Entertainment}, #{Education}, #{Pets}, #{Eco}, #{Weather}, #{Technology}, #{Politics}, #{Economy}\n" +
            "        )")
    void insertUserTrainData(UserTrainData userTrainData);

    @Select("SELECT * FROM user_train_data WHERE user_name = #{username}")
    UserTrainData findPreferencesByUserName(@Param("username") String username);

    @Update({
            "<script>",
            "UPDATE user_train_data",
            "SET",
            "  <choose>",
            "    <when test='preference == \"Fashion\"'>Fashion = Fashion + 1</when>",
            "    <when test='preference == \"Art\"'>Art = Art + 1</when>",
            "    <when test='preference == \"Entertainment\"'>Entertainment = Entertainment + 1</when>",
            "    <when test='preference == \"Education\"'>Education = Education + 1</when>",
            "    <when test='preference == \"Pets\"'>Pets = Pets + 1</when>",
            "    <when test='preference == \"Eco\"'>Eco = Eco + 1</when>",
            "    <when test='preference == \"Weather\"'>Weather = Weather + 1</when>",
            "    <when test='preference == \"Technology\"'>Technology = Technology + 1</when>",
            "    <when test='preference == \"Politics\"'>Politics = Politics + 1</when>",
            "    <when test='preference == \"Economy\"'>Economy = Economy + 1</when>",
            "  </choose>",
            "WHERE user_name = #{username}",
            "</script>"
            })
    void updatePreference(@Param("username") String username, @Param("preference") String preference);
}