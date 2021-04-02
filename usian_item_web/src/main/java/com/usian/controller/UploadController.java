package com.usian.controller;
import com.usian.config.OssFileUtils;
import com.usian.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 图片上传
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");

    /**
     * 图片上传
     */
    @RequestMapping("/upload")
    public Result fileUpload(MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            // 校验文件的类型
            String contentType = file.getContentType();
            if (!CONTENT_TYPES.contains(contentType)){
                // 文件类型不合法，直接返回
                return Result.error("文件类型不合法:"+originalFilename);
            }
            // 校验文件的内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                return Result.error("文件内容不合法：" + originalFilename);
            }
            // 保存到服务器
           // file.transferTo(new File("E:\\images\\" + originalFilename));
            String ext = StringUtils.substringAfterLast(originalFilename,".");
            String url = OssFileUtils.uploadFile(UUID.randomUUID() + "." + ext, file);
            // 生成url地址，返回
            return Result.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("服务器内部错误");
    }
}