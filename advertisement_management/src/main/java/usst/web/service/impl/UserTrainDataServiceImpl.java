package usst.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usst.web.dto.UserTrainDataDTO;
import usst.web.entity.Advertisement;
import usst.web.entity.UserTrainData;
import usst.web.mapper.UserTrainDataMapper;
import usst.web.service.AdvertisementService;
import usst.web.service.ITagService;
import usst.web.vo.UserTrainDataVO;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserTrainDataServiceImpl {
    @Resource
    private UserTrainDataMapper userTrainDataMapper;
    @Resource
    private ITagService tagService;

//    @Resource
//    private AdvertisementServiceImpl advertisementService;

    @Autowired
    private AdvertisementService advertisementService;

    private final String pythonCmd = "python";
    private final String pythonPath = "C:\\Users\\tianqianjia\\Desktop\\USST_JavaWeb_ADTool\\py\\";


    public UserTrainDataVO getPreferences(UserTrainDataDTO userTrainDataDTO1 , HttpServletRequest request) {
        UserTrainDataDTO userTrainDataDTO = setDtoNotNull(userTrainDataDTO1);
        Map<String, Double> userPreferences = defaultPreferences(userTrainDataDTO);//模型偏好预测
        boolean isTourist =isTourist(userTrainDataDTO.getUserName());//是否是游客
        if(userTrainDataDTO.getPreference() == null || userTrainDataDTO.getPreference().isEmpty()) {
            // 获取服务器 IP 和端口号
            //初始化用户偏好map，取默认值
            Map<String, Integer> preferencesByUsername = getPreferencesByUsername(userTrainDataDTO.getUserName());

            Map<String,Double> result = new HashMap<>();
            int times=sumData(preferencesByUsername);

            Map<String,Double> tempDB=normalizePreferences(preferencesByUsername);//数据库偏好
            if(0<=times && times<= 10 &&isTourist) {
                result.put("Fashion",userPreferences.get("Fashion")*(1-0.03*times)+tempDB.get("Fashion")*(0.03*times));
                result.put("Art",userPreferences.get("Art")*(1-0.03*times)+tempDB.get("Art")*(0.03*times));
                result.put("Entertainment",userPreferences.get("Entertainment")*(1-0.03*times)+tempDB.get("Entertainment")*(0.03*times));
                result.put("Education",userPreferences.get("Education")*(1-0.03*times)+tempDB.get("Education")*(0.03*times));
                result.put("Pets",userPreferences.get("Pets")*(1-0.03*times)+tempDB.get("Pets")*(0.03*times));
                result.put("Eco",userPreferences.get("Eco")*(1-0.03*times)+tempDB.get("Eco")*(0.03*times));
                result.put("Weather",userPreferences.get("Weather")*(1-0.03*times)+tempDB.get("Weather")*(0.03*times));
                result.put("Technology",userPreferences.get("Technology")*(1-0.03*times)+tempDB.get("Technology")*(0.03*times));
                result.put("Politics",userPreferences.get("Politics")*(1-0.03*times)+tempDB.get("Politics")*(0.03*times));
                result.put("Economy",userPreferences.get("Economy")*(1-0.03*times)+tempDB.get("Economy")*(0.03*times));
            }
            if(times>10&&isTourist){
                result.put("Fashion",userPreferences.get("Fashion")*(1-0.03*10)+tempDB.get("Fashion")*(0.03*10));
                result.put("Art",userPreferences.get("Art")*(1-0.03*10)+tempDB.get("Art")*(0.03*10));
                result.put("Entertainment",userPreferences.get("Entertainment")*(1-0.03*10)+tempDB.get("Entertainment")*(0.03*10));
                result.put("Education",userPreferences.get("Education")*(1-0.03*10)+tempDB.get("Education")*(0.03*10));
                result.put("Pets",userPreferences.get("Pets")*(1-0.03*10)+tempDB.get("Pets")*(0.03*10));
                result.put("Eco",userPreferences.get("Eco")*(1-0.03*10)+tempDB.get("Eco")*(0.03*10));
                result.put("Weather",userPreferences.get("Weather")*(1-0.03*10)+tempDB.get("Weather")*(0.03*10));
                result.put("Technology",userPreferences.get("Technology")*(1-0.03*10)+tempDB.get("Technology")*(0.03*10));
                result.put("Politics",userPreferences.get("Politics")*(1-0.03*10)+tempDB.get("Politics")*(0.03*10));
                result.put("Economy",userPreferences.get("Economy")*(1-0.03*10)+tempDB.get("Economy")*(0.03*10));
            }
            if (times>=0&&times<=10&&!isTourist){
                result.put("Fashion",userPreferences.get("Fashion")*(1-0.07*times)+tempDB.get("Fashion")*(0.07*times));
                result.put("Art",userPreferences.get("Art")*(1-0.07*times)+tempDB.get("Art")*(0.07*times));
                result.put("Entertainment",userPreferences.get("Entertainment")*(1-0.07*times)+tempDB.get("Entertainment")*(0.07*times));
                result.put("Education",userPreferences.get("Education")*(1-0.07*times)+tempDB.get("Education")*(0.07*times));
                result.put("Pets",userPreferences.get("Pets")*(1-0.07*times)+tempDB.get("Pets")*(0.07*times));
                result.put("Eco",userPreferences.get("Eco")*(1-0.07*times)+tempDB.get("Eco")*(0.07*times));
                result.put("Weather",userPreferences.get("Weather")*(1-0.07*times)+tempDB.get("Weather")*(0.07*times));
                result.put("Technology",userPreferences.get("Technology")*(1-0.07*times)+tempDB.get("Technology")*(0.07*times));
                result.put("Politics",userPreferences.get("Politics")*(1-0.07*times)+tempDB.get("Politics")*(0.07*times));
                result.put("Economy",userPreferences.get("Economy")*(1-0.07*times)+tempDB.get("Economy")*(0.07*times));
            }
            if (times>10&&!isTourist){
                result.put("Fashion",userPreferences.get("Fashion")*(1-0.07*10)+tempDB.get("Fashion")*(0.07*10));
                result.put("Art",userPreferences.get("Art")*(1-0.07*10)+tempDB.get("Art")*(0.07*10));
                result.put("Entertainment",userPreferences.get("Entertainment")*(1-0.07*10)+tempDB.get("Entertainment")*(0.07*10));
                result.put("Education",userPreferences.get("Education")*(1-0.07*10)+tempDB.get("Education")*(0.07*10));
                result.put("Pets",userPreferences.get("Pets")*(1-0.07*10)+tempDB.get("Pets")*(0.07*10));
                result.put("Eco",userPreferences.get("Eco")*(1-0.07*10)+tempDB.get("Eco")*(0.07*10));
                result.put("Weather",userPreferences.get("Weather")*(1-0.07*10)+tempDB.get("Weather")*(0.07*10));
                result.put("Technology",userPreferences.get("Technology")*(1-0.07*10)+tempDB.get("Technology")*(0.07*10));
                result.put("Politics",userPreferences.get("Politics")*(1-0.07*10)+tempDB.get("Politics")*(0.07*10));
                result.put("Economy",userPreferences.get("Economy")*(1-0.07*10)+tempDB.get("Economy")*(0.07*10));
            }


            Integer id = tagService.getRecommendationUri(result);
            Advertisement advertisement = advertisementService.getAdvertisementById(id);

            String serverIp = request.getServerName(); // 获取服务器 IP 或主机名
            int serverPort = request.getServerPort(); // 获取端口号
            UserTrainDataVO userTrainDataVO = new UserTrainDataVO();
            userTrainDataVO.setAdImgUrl("http://" + serverIp + ":" + serverPort + "/ad/images/" + id);
            userTrainDataVO.setAdName(""+advertisement.getAdName());
            userTrainDataVO.setAdUrl("http://" + serverIp + ":" + serverPort + "/ad/" + id);
            return userTrainDataVO;

        }
        if (!isUserPreferencesExist(userTrainDataDTO.getUserName())) {
            initPreferences(userTrainDataDTO);
        }
        else {
            updatePreference(userTrainDataDTO);
        }


        UserTrainDataVO userTrainDataVO = new UserTrainDataVO();
        userTrainDataVO.setAdImgUrl("Success!");
        return userTrainDataVO;
}

    public static boolean isTourist(String username) {
        if (username == null) {
            return false; // 如果用户名为 null，默认不是游客
        }
        return username.startsWith("Tourist_");
    }


    private Integer sumData(Map<String, Integer> preferencesByUsername) {
        Integer sum = 0;
        for (Map.Entry<String, Integer> entry : preferencesByUsername.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }


    private  UserTrainDataDTO setDtoNotNull(UserTrainDataDTO userTrainDataDTO) {
        if (userTrainDataDTO.getAge() == null) userTrainDataDTO.setAge(25);
        if (userTrainDataDTO.getGender() == null || userTrainDataDTO.getGender().isEmpty()) userTrainDataDTO.setGender("不愿透露");
        if (userTrainDataDTO.getOccupation() == null || userTrainDataDTO.getOccupation().isEmpty()) userTrainDataDTO.setOccupation("不愿透露");
        if (userTrainDataDTO.getEducation_level() == null || userTrainDataDTO.getEducation_level().isEmpty()) userTrainDataDTO.setEducation_level("不愿透露");
        if (userTrainDataDTO.getRegion() == null || userTrainDataDTO.getRegion().isEmpty()) userTrainDataDTO.setRegion("不愿透露");
        if (userTrainDataDTO.getCountry() == null || userTrainDataDTO.getCountry().isEmpty()) userTrainDataDTO.setCountry("不愿透露");
        if (userTrainDataDTO.getDevice() == null || userTrainDataDTO.getDevice().isEmpty()) userTrainDataDTO.setDevice("不愿透露");
        return userTrainDataDTO;
    }

    public UserTrainDataVO train(HttpServletRequest request) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(pythonCmd, pythonPath+"train.py");
        processBuilder.redirectErrorStream(true); // 将错误流和输出流合并
        Process process = processBuilder.start();
        UserTrainDataVO userTrainDataVO = new UserTrainDataVO();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "GBK")
        );
        String line;
        while ((line = reader.readLine()) != null) {
            if(line.startsWith("3")) {
                userTrainDataVO.setAdImgUrl("Success! The journey of a thousand predictions begins with a single model!");
                return userTrainDataVO;
            }
        }
            userTrainDataVO.setAdImgUrl("Failed!");
            return userTrainDataVO;
    }


    private Map<String, Double> defaultPreferences(UserTrainDataDTO userTrainDataDTO) {
        Map<String, Double> preferences = new HashMap<>();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    pythonCmd,
                    pythonPath+"predict.py",
                    String.valueOf(userTrainDataDTO.getAge()),
                    userTrainDataDTO.getGender(),
                    userTrainDataDTO.getOccupation(),
                    userTrainDataDTO.getEducation_level(),
                    userTrainDataDTO.getRegion(),
                    userTrainDataDTO.getCountry(),
                    userTrainDataDTO.getDevice()
            );
            processBuilder.redirectErrorStream(true); // 将错误流和输出流合并

            Process process = processBuilder.start();

            // 读取 Python 脚本的输出，指定 GBK 编码
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "gbk")
            );

            String line;
            while ((line = reader.readLine()) != null) {
                // 检查是否包含用户偏好比例
                if (line.startsWith("用户偏好比例:")) {
                    // 移除前缀 "用户偏好比例: "
                    String jsonStr = line.substring("用户偏好比例: ".length());

                    // 将 Python 字典格式的字符串转换为 JSON 格式
                    jsonStr = jsonStr.replace("'", "\"");

                    // 使用 Jackson 解析 JSON 字符串
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Double> preferenceMap = objectMapper.readValue(jsonStr, Map.class);

                    // 将 Double 类型的值转换为 Integer
                    for (Map.Entry<String, Double> entry : preferenceMap.entrySet()) {
                        preferences.put(entry.getKey(), entry.getValue().doubleValue());
                    }
                }
            }

            // 等待进程结束
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Python 脚本执行失败，退出码: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("运行 Python 脚本时发生错误", e);
        }

        return preferences;
    }

    private void updatePreference(UserTrainDataDTO userTrainDataDTO) {
        String username = userTrainDataDTO.getUserName();
        String preference = userTrainDataDTO.getPreference();
        System.out.println("Updating preference for username: " + username + ", preference: " + preference); // 添加日志
        // 调用 Mapper 方法更新数据库
        userTrainDataMapper.updatePreference(username, preference);
    }

    private boolean isUserPreferencesExist(String username) {
        return userTrainDataMapper.findPreferencesByUserName(username) != null;
    }
    public void initPreferences(UserTrainDataDTO userTrainDataDTO) {
        // 将 DTO 转换为实体类
        UserTrainData userTrainData = new UserTrainData();
        userTrainData.setUserName(userTrainDataDTO.getUserName());
        userTrainData.setAge(userTrainDataDTO.getAge());
        userTrainData.setGender(userTrainDataDTO.getGender());
        userTrainData.setOccupation(userTrainDataDTO.getOccupation());
        userTrainData.setEducationLevel(userTrainDataDTO.getEducation_level());
        userTrainData.setRegion(userTrainDataDTO.getRegion());
        userTrainData.setCountry(userTrainDataDTO.getCountry());
        userTrainData.setDevice(userTrainDataDTO.getDevice());

        // 初始化偏好浏览次数为 0
        userTrainData.setFashion(1);
        userTrainData.setArt(1);
        userTrainData.setEntertainment(1);
        userTrainData.setEducation(1);
        userTrainData.setPets(1);
        userTrainData.setEco(1);
        userTrainData.setWeather(1);
        userTrainData.setTechnology(1);
        userTrainData.setPolitics(1);
        userTrainData.setEconomy(1);


        // 插入数据库
        userTrainDataMapper.insertUserTrainData(userTrainData);
    }
    private Map<String, Double> normalizePreferences(Map<String, Integer> userPreferences) {
        // 1. 将点击次数转换为 Double 类型
        Map<String, Double> normalizedPreferences = new HashMap<>();
        userPreferences.forEach((key, value) -> normalizedPreferences.put(key, value.doubleValue()));

        // 2. 计算所有偏好值的总和
        double sum = normalizedPreferences.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Sum of preferences: " + sum); // 添加日志
        // 3. 归一化：将每个偏好值除以总和
        normalizedPreferences.replaceAll((key, value) -> value / sum);

        // 4. 限制最大值，并将超出部分均分给其他元素
        double maxThreshold = 0.5; // 最大阈值
        double excessSum = 0.0; // 超出部分的总和
        int excessCount = 0; // 超出阈值的元素数量

        // 计算超出部分的总和和数量
        for (Map.Entry<String, Double> entry : normalizedPreferences.entrySet()) {
            if (entry.getValue() > maxThreshold) {
                excessSum += entry.getValue() - maxThreshold;
                entry.setValue(maxThreshold);
                excessCount++;
            }
        }

        // 将超出部分均分给其他元素
        if (excessSum > 0) {
            double distributeValue = excessSum / (normalizedPreferences.size() - excessCount);
            normalizedPreferences.replaceAll((key, value) -> {
                if (value < maxThreshold) {
                    return value + distributeValue;
                }
                return value;
            });
        }
        System.out.println("Normalized preferences: " + normalizedPreferences); // 添加日志
        return normalizedPreferences;
    }



    public Map<String, Integer> getPreferencesByUsername(String username) {
        // 查询数据库
        UserTrainData userTrainData = userTrainDataMapper.findPreferencesByUserName(username);

        // 转换为 Map<String, Integer>
        Map<String, Integer> preferences = new HashMap<>();
        if (userTrainData != null) {
            preferences.put("Fashion", userTrainData.getFashion());
            preferences.put("Art", userTrainData.getArt());
            preferences.put("Entertainment", userTrainData.getEntertainment());
            preferences.put("Education", userTrainData.getEducation());
            preferences.put("Pets", userTrainData.getPets());
            preferences.put("Eco", userTrainData.getEco());
            preferences.put("Weather", userTrainData.getWeather());
            preferences.put("Technology", userTrainData.getTechnology());
            preferences.put("Politics", userTrainData.getPolitics());
            preferences.put("Economy", userTrainData.getEconomy());
        }
        return preferences;
    }
//    @Autowired
//    private UserTrainDataMapper userTrainDataMapper;
//
//    // 存储用户偏好的字典
//    private Map<String, Double> userPreferences = new HashMap<>();
//
//    public String saveUserTrainDataToDatabase(String userTrainDataJson) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            UserTrainDataDTO userTrainDataDTO = mapper.readValue(userTrainDataJson, UserTrainDataDTO.class);
//
//            // Set default values if any field is null
//            if (userTrainDataDTO.getAge() == null) userTrainDataDTO.setAge(25);
//            if (userTrainDataDTO.getGender() == null || userTrainDataDTO.getGender().isEmpty()) userTrainDataDTO.setGender("男");
//            if (userTrainDataDTO.getOccupation() == null || userTrainDataDTO.getOccupation().isEmpty()) userTrainDataDTO.setOccupation("白领");
//            if (userTrainDataDTO.getEducation() == null || userTrainDataDTO.getEducation().isEmpty()) userTrainDataDTO.setEducation("高中");
//            if (userTrainDataDTO.getRegion() == null || userTrainDataDTO.getRegion().isEmpty()) userTrainDataDTO.setRegion("上海");
//            if (userTrainDataDTO.getCountry() == null || userTrainDataDTO.getCountry().isEmpty()) userTrainDataDTO.setCountry("中国");
//            if (userTrainDataDTO.getDevice() == null || userTrainDataDTO.getDevice().isEmpty()) userTrainDataDTO.setDevice("智能手机");
//            if (userTrainDataDTO.getPreference() == null || userTrainDataDTO.getPreference().isEmpty()) {
//                userTrainDataDTO.setPreference(initTag());
//            }
//
//            userTrainDataMapper.insertUserTrainData(userTrainDataDTO);
//            return "{\"code\": 200, \"message\": \"User train data saved successfully\"}";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "{\"code\": 500, \"message\": \"Internal Server Error\"}";
//        }
//    }
//
//    public UserTrainDataVO getPreferences(UserTrainDataDTO userTrainDataDTO) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            // Check if preferences already exist in the database
//            String existingPreferences = userTrainDataMapper.findPreferencesByUserName(userTrainDataDTO.getUserName());
//            if (existingPreferences != null) {
//                userPreferences = mapper.readValue(existingPreferences, Map.class);
//            } else {
//                userPreferences = initDefaultPreferences();
//            }
//
//            // Update preferences based on recent activity
//            updateTagAccess(userTrainDataDTO.getPreference());
//
//            // Save updated preferences to database
//            saveUpdatedPreferences(userTrainDataDTO);
//
//            // Prepare response
//            UserTrainDataVO response = new UserTrainDataVO();
//            response.setPreference(mapper.writeValueAsString(userPreferences));
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // In case of error, you can return a default or error state UserTrainDataVO
//            UserTrainDataVO errorResponse = new UserTrainDataVO();
//            errorResponse.setPreference("Error occurred");
//            return errorResponse;
//        }
//    }
//
//    private void updateTagAccess(String tag) {
//        userPreferences.put(tag, userPreferences.getOrDefault(tag, 0.0) + 1);
//        normalizePreferences();
//    }
//
//    private void saveUpdatedPreferences(UserTrainDataDTO userTrainDataDTO) {
//        String preferencesJson = convertMapToJson(userPreferences);
//        userTrainDataMapper.updateUserPreference(userTrainDataDTO.getUserName(), preferencesJson);
//    }
//
//    private String convertMapToJson(Map<String, Double> map) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.writeValueAsString(map);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "{}";
//        }
//    }
//
//    private void normalizePreferences() {
//        double sum = userPreferences.values().stream().mapToDouble(Double::doubleValue).sum();
//        userPreferences.forEach((key, value) -> userPreferences.put(key, value / sum));
//        userPreferences.replaceAll((key, value) -> Math.min(value, 0.5));
//    }
//
//    private String initTag() throws JsonProcessingException {
//        // Initialize tag with default values
//        String[] tags = {"Fashion", "Technology", "Gourmet", "Home", "Travel", "Sports", "Parenting", "Festival", "Eco", "Luxury", "Education", "Pets", "Art", "Cars", "Finance", "Healthcare", "Real Estate", "Digital", "Entertainment", "Leisure"};
//        HashMap<String, Double> defaultPreferences = new HashMap<>();
//        for (String tag : tags) {
//            defaultPreferences.put(tag, 1.0 / tags.length);
//        }
//        return new ObjectMapper().writeValueAsString(defaultPreferences);
//    }
//
//    private Map<String, Double> initDefaultPreferences() {
//        String[] tags = {"Fashion", "Technology", "Gourmet", "Home", "Travel", "Sports", "Parenting", "Festival", "Eco", "Luxury", "Education", "Pets", "Art", "Cars", "Finance", "Healthcare", "Real Estate", "Digital", "Entertainment", "Leisure"};
//        Map<String, Double> defaultPreferences = new HashMap<>();
//        for (String tag : tags) {
//            defaultPreferences.put(tag, 1.0 / tags.length);
//        }
//        return defaultPreferences;
//    }
}