package usst.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import usst.web.entity.Advertisement;
import usst.web.service.AdvertisementService;

import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdPageController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/{adId}")
    public String getAdPage(@PathVariable int adId, Model model) throws JsonProcessingException {
        Advertisement advertisement = advertisementService.getAdvertisementById(adId);
        if (advertisement != null) {
            // 增加访问次数
            advertisement.setVisitCount(advertisement.getVisitCount() + 1);
            // 更新数据库中的广告信息
            advertisementService.updateAdvertisement(advertisement);

            model.addAttribute("ad", advertisement);
            model.addAttribute("imageUrls", new ObjectMapper().readValue(advertisement.getAdImageUrl(), List.class));
            return "adPage"; // 返回广告页面视图名称
        } else {
            return "errorPage"; // 返回错误页面视图名称
        }
    }
}
