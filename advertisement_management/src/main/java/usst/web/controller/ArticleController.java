package usst.web.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import usst.web.pojo.Article;
import usst.web.service.impl.ArticleServiceImpl;
import usst.web.utils.FileUtils;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Value("${upload.dir}")
    private static String UPLOAD_DIR;

    private final ArticleServiceImpl articleService;

    @Autowired
    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/publish")
    @ResponseBody
    public JSONObject publishArticle(Article article) {
        boolean res = articleService.publishArticle(article);

        if(article.getId() > 0) {
            JSONObject response = new JSONObject();
            response.put("success", true);
            response.put("articleId", article.getId());
            return response;
        }
        JSONObject response = new JSONObject();
        response.put("success", false);
        return response;
//        if(res) {
//            return "success";
//        }
//        return "false";
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject updateArticle(Article article) {
        boolean res = articleService.updateArticle(article);

        if(article.getId() > 0) {
            JSONObject response = new JSONObject();
            response.put("success", true);
            response.put("articleId", article.getId());
            return response;
        }
        JSONObject response = new JSONObject();
        response.put("success", false);
        return response;
//        if(res) {
//            return "success";
//        }
//        return "false";
    }


    @RequestMapping("/image/upload")
    @ResponseBody
    public JSONObject imageUpload(@RequestParam("editormd-image-file") MultipartFile image) {
        JSONObject jsonObject = new JSONObject();
        if(image != null) {
            String path = FileUtils.uploadFile(image);
            System.out.println(path);
            jsonObject.put("url", path);
            jsonObject.put("success", 1);
            jsonObject.put("message", "upload success!");
            return jsonObject;
        }
        jsonObject.put("success", 0);
        jsonObject.put("message", "upload error!");
        return jsonObject;
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

    @RequestMapping("/get/{id}")
    public ModelAndView getArticleById(@PathVariable(name = "id")int id) {
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.getArticleById(id);
        modelAndView.setViewName("article");
        if(article == null) {
            modelAndView.addObject("article", new Article());
        }
        modelAndView.addObject("article", article);
        return modelAndView;
    }

}
