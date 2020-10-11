package spm.app.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;

public class MultipartFileToFile {
    public static File execute(MultipartFile multipartFile) {
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file=File.createTempFile(filename[0],filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}