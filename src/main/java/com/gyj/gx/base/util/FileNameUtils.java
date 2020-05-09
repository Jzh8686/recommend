package com.gyj.gx.base.util;

public class FileNameUtils {

    /**
     * get suffix
     * @param filename original filename
     * @return suffix of the filename
     */
    public static String getSuffix(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * get filename
     * @param filename original filename
     * @return get random filename
     */
    public static String getFileName(String filename){
        return UUIDUtils.getUUID()+getSuffix(filename);
    }
}
