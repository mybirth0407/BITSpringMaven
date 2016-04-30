package fileupload.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.Calendar;

@Controller
public class FileUploadController {
    private static final Log LOG = LogFactory.getLog(
        FileUploadController.class);
    private static final String SAVE_PATH = "/temp";

    @RequestMapping("/form")
    public String form() {
        return "../../fileupload/views/form";
    }

    @RequestMapping("/upload")
    public String upload(
        @RequestParam String email,
        @RequestParam("file") MultipartFile multipartFile,
        Model model) {
        LOG.debug("#### email: " + email);

        if (multipartFile.isEmpty() == false) {
            String originalFilename = multipartFile.getOriginalFilename();
            String extName = originalFilename.substring(
                originalFilename.lastIndexOf('.') + 1,
                originalFilename.length());
            String saveFileName = generateFileName(extName);
            Long size = multipartFile.getSize();

            writeFile(multipartFile, SAVE_PATH, saveFileName);

            String url = "/product-images/" + saveFileName;
            model.addAttribute("productImageURL", url);

            LOG.debug("#### file original name: " + originalFilename);
            LOG.debug("#### file size: " + size);
            LOG.debug("#### file extension: " + extName);
            LOG.debug("#### save file name: " + saveFileName);
        }
        return "../../fileupload/views/result";

    }

    private void writeFile(
        MultipartFile multipartFile, String savePath, String saveFileName) {
        FileOutputStream fileOutputStream = null;

        try {
            byte fileData[] = saveFileName.getBytes();
            fileOutputStream = new FileOutputStream(
                savePath + "/" + saveFileName);
            fileOutputStream.write(fileData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (Exception e) {
                }
            }
        }
    }

    private String generateFileName(String extName) {
        Calendar calendar = Calendar.getInstance();
        String fileName = "";
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += "." + extName;
        return fileName;
    }
}
