package com.zxk.study.utils;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/7  13:12
 */
@Component
public class Utils {

    public static final JsonMapper jsonMapper = new JsonMapper();

    public static final String LOCKTYPE1 = "LOCKTYPE1";

    public static final String ID_PREFIX = "ID_PREFIX";

    public static final long LOCK_OUT_TIME = 10L;

    public static final long CACHE_OUT_TIME = 6000L;

    public static final int RETRYLOCK_MAX = 10;

    public static final String USERID_PREFIX ="USERID_PREFIX";
}