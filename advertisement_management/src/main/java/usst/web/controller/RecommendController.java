package usst.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usst.web.dto.TagDTO;
import usst.web.service.ITagService;


/**
 * @author jyzxc
 * @since 2025-1-1
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController {
    @Resource
    ITagService tagService;
    @PostMapping("/get-recommendation")
    public String getRecommendation(@RequestBody TagDTO tagDTO, HttpServletRequest request) {
//        Integer id = tagService.getRecommendationUri(userPreferences);
        int id = 1; //test
        // 获取服务器 IP 和端口号
        String serverIp = request.getServerName(); // 获取服务器 IP 或主机名
        int serverPort = request.getServerPort(); // 获取端口号
        return "http://" + serverIp + ":" + serverPort + "/ad/" + id;
    }
}
