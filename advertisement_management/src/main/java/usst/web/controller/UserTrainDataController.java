package usst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usst.web.service.UserTrainDataService;

@RestController
@RequestMapping("/user-predict")
public class UserTrainDataController {

    @Autowired
    private UserTrainDataService userTrainDataService;

    @PostMapping("/save")
    public String saveUserTrainData(@RequestBody String userTrainDataJson) {
        return userTrainDataService.saveUserTrainDataToDatabase(userTrainDataJson);
    }

    @PostMapping("/get-preferences")
    public String getPreferences(@RequestBody String userTrainDataJson) {
        return userTrainDataService.getPreferences(userTrainDataJson);
    }
}