package usst.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usst.web.dto.UserTrainDataDTO;
import usst.web.service.impl.UserTrainDataServiceImpl;
import usst.web.vo.UserTrainDataVO;

import java.io.IOException;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user-predict")
public class UserTrainDataController {

    @Autowired
    private UserTrainDataServiceImpl userTrainDataService;

    @PostMapping("/get-preferences")
    public UserTrainDataVO getPreferences(@RequestBody UserTrainDataDTO userTrainDataDTO, HttpServletRequest request) {
        return userTrainDataService.getPreferences(userTrainDataDTO, request);
    }

    @GetMapping("/train")
    public UserTrainDataVO train(HttpServletRequest request) throws IOException {
        return userTrainDataService.train(request);
    }
}