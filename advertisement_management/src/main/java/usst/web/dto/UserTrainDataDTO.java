package usst.web.dto;

import lombok.Data;

@Data
public class UserTrainDataDTO {
    private String userName;
    private Integer age=25;
    private String gender="不愿透露";
    private String occupation="不愿透露";
    private String education_level="不愿透露";
    private String region="不愿透露";
    private String country="不愿透露";
    private String device="不愿透露";
    private String preference;
    // Getters and setters
}