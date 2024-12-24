package usst.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import usst.web.entity.Advertisement;
import usst.web.pojo.Article;
import usst.web.service.AdvertisementService;
import usst.web.service.impl.ArticleServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdPageController {

    @Autowired
    private AdvertisementService advertisementService;

    private ArticleServiceImpl articleService;
    @Autowired
    public AdPageController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{adId}")
    public ModelAndView getAdPage(@PathVariable int adId, Model model) throws JsonProcessingException {
        Advertisement advertisement = advertisementService.getAdvertisementById(adId);
        if (advertisement != null) {
            // 增加访问次数
            advertisement.setVisitCount(advertisement.getVisitCount() + 1);
            // 更新数据库中的广告信息
            advertisementService.updateAdvertisement(advertisement);

            ModelAndView modelAndView = new ModelAndView();
            Article article = articleService.getArticleById(advertisement.getArticleId());
            modelAndView.setViewName("article");
            if(article == null) {
                modelAndView.addObject("article", new Article());
            }
            modelAndView.addObject("article", article);
            return modelAndView;

//            model.addAttribute("ad", advertisement);
//            model.addAttribute("imageUrls", new ObjectMapper().readValue(advertisement.getAdImageUrl(), List.class));
//            return "adPage"; // 返回广告页面视图名称
        } else {
            return null; // 返回错误页面视图名称
        }
    }
}
