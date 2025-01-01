package usst.web.dto;

import lombok.Data;

@Data
public class UserTrainDataDTO {
    private String userName;
    private Integer age;
    private String gender;
    private String occupation;
    private String educationLevel;
    private String region;
    private String country;
    private String device;
    private String preference;
    // Getters and setters
}