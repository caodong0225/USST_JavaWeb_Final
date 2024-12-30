package usst.web.service;

import usst.web.entity.UserTrainData;
import usst.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usst.web.mapper.UserTrainDataMapper;

@Service
public class UserTrainDataService {

    @Autowired
    private UserTrainDataMapper userTrainDataMapper;

    public String saveUserTrainDataToDatabase(String userTrainDataJson) {
        try {
            UserTrainData userTrainData = convertJsonToUserTrainData(userTrainDataJson);
            userTrainDataMapper.insertUserTrainData(userTrainData);
            return "{\"code\": 200, \"message\": \"User train data saved successfully\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"code\": 500, \"message\": \"Internal Server Error\"}";
        }
    }

    private UserTrainData convertJsonToUserTrainData(String userTrainDataJson) {
        // Convert JSON string to UserTrainData object
        // Use a JSON parsing library like Jackson or Gson
        return new UserTrainData();
    }
}