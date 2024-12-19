package usst.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import usst.web.entity.Advertisement;
import usst.web.service.AdvertisementService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@MultipartConfig
@RequestMapping("/advertisements")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    @PostMapping("/create")
    public void createAdvertisement(@RequestParam("adName") String adName,
                                    @RequestParam("adImages") MultipartFile[] adImages,
                                    @RequestParam("adFeature") String adFeature) throws IOException {
        List<String> imageUrls = new ArrayList<>();

        // 保存文件并记录URL
        for (MultipartFile adImage : adImages) {
            if (!adImage.isEmpty()) {
                String fileName = UUID.randomUUID().toString() + "-" + adImage.getOriginalFilename();
                String filePath = UPLOAD_DIR + fileName;
                adImage.transferTo(Paths.get(filePath));
                imageUrls.add("/advertisements/images/" + fileName);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonImageUrls = objectMapper.writeValueAsString(imageUrls);
        String jsonKeywords = objectMapper.writeValueAsString(new ArrayList<>()); // 假设初始关键词为空列表
        String jsonPlacementLocation = objectMapper.writeValueAsString(new ArrayList<>()); // 假设初始投放位置为空列表

        // 创建广告对象
        Advertisement advertisement = new Advertisement();
        advertisement.setAdName(adName);
        advertisement.setAdImageUrl(jsonImageUrls); // 设置JSON字符串
        advertisement.setKeywords(jsonKeywords);     // 设置JSON字符串
        advertisement.setPlacementLocation(jsonPlacementLocation); // 设置JSON字符串
        advertisement.setAdFeature(adFeature);

        // 保存到数据库
        advertisementService.createAdvertisement(advertisement);
    }

    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
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