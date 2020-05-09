package com.gyj.gx.base.util;

import java.util.UUID;

public class UUIDUtils {

    /**
     * generate a uuid
     *
     * @return a random uuid without "-"
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
