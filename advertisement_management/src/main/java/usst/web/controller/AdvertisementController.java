package usst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usst.web.entity.Advertisement;
import usst.web.service.AdvertisementService;

import java.util.List;

@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping("/create")
    public void createAdvertisement(@RequestBody Advertisement advertisement) {
//        Advertisement advertisement = new Advertisement();
//        advertisement.setAdName(adTitle);
//        advertisement.setAdUrl(adUrl);
        advertisementService.createAdvertisement(advertisement);
    }

    @PutMapping("/update")
    public void updateAdvertisement(@RequestBody Advertisement advertisement) {
        advertisementService.updateAdvertisement(advertisement);
    }

    @DeleteMapping("/delete/{adId}")
    public void deleteAdvertisement(@PathVariable int adId) {
        advertisementService.deleteAdvertisement(adId);
    }

    @GetMapping("/{adId}")
    public Advertisement getAdvertisementById(@PathVariable int adId) {
        return advertisementService.getAdvertisementById(adId);
    }

    @GetMapping("/all")
    public List<Advertisement> getAllAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

    // 开放接口供其他网站跳转广告
    @GetMapping("/redirect/{adId}")
    public String redirectToAdvertisement(@PathVariable int adId) {
        Advertisement advertisement = advertisementService.getAdvertisementById(adId);
        if (advertisement != null && advertisement.getAdUrl() != null) {
            return "redirect:" + advertisement.getAdUrl();
        } else {
            // 广告不存在或URL为空时返回错误消息或默认页面
            return "Error: Advertisement not found or URL is empty.";
        }
    }
}