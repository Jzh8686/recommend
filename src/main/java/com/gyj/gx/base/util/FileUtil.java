package com.gyj.gx.base.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**
     * upload
     *
     * @param file     multipart file
     * @param path     store path
     * @param filename original filename
     * @return state of success or failure
     */
    public static String upload(MultipartFile file, String path, String filename) {
        String randomFilename = FileNameUtils.getFileName(filename);
        String realPath = path + "/" + randomFilename;

        File dest = new File(realPath);

        if (!dest.getParentFile().exists())
            dest.getParentFile().mkdir();

        try {
            file.transferTo(dest);
            return randomFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
