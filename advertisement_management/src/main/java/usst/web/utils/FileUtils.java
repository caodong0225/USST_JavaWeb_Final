package usst.web.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具类
 */
public class FileUtils {

    // private static final String prePath = new File(System.getProperty("user.dir"), "src/main/webapp/upload/").getAbsolutePath();
    private static final String prePath = "C:\\Users\\tianqianjia\\Desktop\\USST_JavaWeb_ADTool\\advertisement_management\\src\\main\\resources\\uploads\\";

    /**
     * 上传文件
     * @param file
     * @return 返回文件路径（以相对路径放回）
     */
    public static String uploadFile(MultipartFile file) {
        if(file.isEmpty()) {
            return "";
        }
        // 获取原文件名
        String originFileName = file.getOriginalFilename();
        // 我们通过UUID 来重新重组文件名
        String uid = UUID.randomUUID().toString();
        assert originFileName != null;
        String suffix = originFileName.substring(originFileName.lastIndexOf('.') + 1);
        String path = prePath + uid + "." + suffix;
        String returnPath = "/advertisements/images/" + uid + "." + suffix;
        File newFile = new File(path);
        if(newFile.getParentFile() != null && !newFile.getParentFile().exists()) {
            System.out.println("创建目录ing");
            // 上面的 newFile.getParentFile() 已经保证了不为null.
            if(newFile.getParentFile().mkdirs()) {
                System.out.println("创建目录成功");
            }else {
                System.out.println("创建目录失败");
                return "";
            }
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return returnPath;
    }

}
