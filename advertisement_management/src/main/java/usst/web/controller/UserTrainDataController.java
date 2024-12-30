package usst.web.controller;

import usst.web.service.UserTrainDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-train-data")
public class UserTrainDataController {

    @Autowired
    private UserTrainDataService userTrainDataService;

    @PostMapping("/save")
    public String saveUserTrainData(@RequestBody String userTrainDataJson) {
        return userTrainDataService.saveUserTrainDataToDatabase(userTrainDataJson);
    }
}