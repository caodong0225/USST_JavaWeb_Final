package usst.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import usst.web.entity.UserTrainData;
import usst.web.mapper.UserTrainDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserTrainDataService {

    @Autowired
    private UserTrainDataMapper userTrainDataMapper;

    // 存储用户偏好的字典
    private Map<String, Double> userPreferences = new HashMap<>();

    public String saveUserTrainDataToDatabase(String userTrainDataJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserTrainData userTrainData = mapper.readValue(userTrainDataJson, UserTrainData.class);

            // Set default values if any field is null
            if (userTrainData.getAge() == null) userTrainData.setAge(25);
            if (userTrainData.getGender() == null || userTrainData.getGender().isEmpty()) userTrainData.setGender("男");
            if (userTrainData.getOccupation() == null || userTrainData.getOccupation().isEmpty()) userTrainData.setOccupation("白领");
            if (userTrainData.getEducation() == null || userTrainData.getEducation().isEmpty()) userTrainData.setEducation("高中");
            if (userTrainData.getRegion() == null || userTrainData.getRegion().isEmpty()) userTrainData.setRegion("上海");
            if (userTrainData.getCountry() == null || userTrainData.getCountry().isEmpty()) userTrainData.setCountry("中国");
            if (userTrainData.getDevice() == null || userTrainData.getDevice().isEmpty()) userTrainData.setDevice("智能手机");
            if (userTrainData.getPreference() == null || userTrainData.getPreference().isEmpty()) {
                userTrainData.setPreference(initTag());
            }

            userTrainDataMapper.insertUserTrainData(userTrainData);
            return "{\"code\": 200, \"message\": \"User train data saved successfully\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"code\": 500, \"message\": \"Internal Server Error\"}";
        }
    }

    public String getPreferences(String userTrainDataJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserTrainData userTrainData = mapper.readValue(userTrainDataJson, UserTrainData.class);

            // Check if preferences already exist in the database
            String existingPreferences = userTrainDataMapper.findPreferencesByUserName(userTrainData.getUserName());
            if (existingPreferences != null) {
                userPreferences = mapper.readValue(existingPreferences, Map.class);
            } else {
                userPreferences = initDefaultPreferences();
            }

            // Update preferences based on recent activity
            updateTagAccess(userTrainData.getPreference());

            // Save updated preferences to database
            saveUpdatedPreferences(userTrainData);

            // Prepare response
            String responseJson = mapper.writeValueAsString(userTrainData);
            return "{\"code\": 200, \"message\": \"Preferences updated\", \"data\": " + responseJson + "}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"code\": 500, \"message\": \"Internal Server Error\"}";
        }
    }

    private void updateTagAccess(String tag) {
        userPreferences.put(tag, userPreferences.getOrDefault(tag, 0.0) + 1);
        normalizePreferences();
    }

    private void saveUpdatedPreferences(UserTrainData userTrainData) {
        String preferencesJson = convertMapToJson(userPreferences);
        userTrainDataMapper.updateUserPreference(userTrainData.getUserName(), preferencesJson);
    }

    private String convertMapToJson(Map<String, Double> map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private void normalizePreferences() {
        double sum = userPreferences.values().stream().mapToDouble(Double::doubleValue).sum();
        userPreferences.forEach((key, value) -> userPreferences.put(key, value / sum));
        userPreferences.replaceAll((key, value) -> Math.min(value, 0.5));
    }

    private String initTag() throws JsonProcessingException {
        // Initialize tag with default values
        String[] tags = {"Fashion", "Technology", "Gourmet", "Home", "Travel", "Sports", "Parenting", "Festival", "Eco", "Luxury", "Education", "Pets", "Art", "Cars", "Finance", "Healthcare", "Real Estate", "Digital", "Entertainment", "Leisure"};
        HashMap<String, Double> defaultPreferences = new HashMap<>();
        for (String tag : tags) {
            defaultPreferences.put(tag, 1.0 / tags.length);
        }
        return new ObjectMapper().writeValueAsString(defaultPreferences);
    }

    private Map<String, Double> initDefaultPreferences() {
        String[] tags = {"Fashion", "Technology", "Gourmet", "Home", "Travel", "Sports", "Parenting", "Festival", "Eco", "Luxury", "Education", "Pets", "Art", "Cars", "Finance", "Healthcare", "Real Estate", "Digital", "Entertainment", "Leisure"};
        Map<String, Double> defaultPreferences = new HashMap<>();
        for (String tag : tags) {
            defaultPreferences.put(tag, 1.0 / tags.length);
        }
        return defaultPreferences;
    }
}