package usst.web.controller;

import usst.web.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<String> predictPreferences(@RequestBody String jsonInput) {
        try {
            String predictionResult = predictionService.predict(jsonInput);
            return ResponseEntity.ok().body(predictionResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("{\"code\": 400, \"message\": \"Bad Request\", \"data\": {}}");
        }
    }
}