package usst.web.entity;

import lombok.Data;

@Data
public class UserTrainData {
    private String userName;
    private Integer age;
    private String gender;
    private String occupation;
    private String education;
    private String region;
    private String country;
    private String device;
    private String preference;
    // Getters and setters
}