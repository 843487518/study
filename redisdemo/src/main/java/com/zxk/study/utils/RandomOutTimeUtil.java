package com.zxk.study.utils;

import java.util.Random;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/8  14:33
 */

public class RandomOutTimeUtil {
    public static long getRandomOutTime(){
        Random random = new Random();
        return (long)(random.nextInt(8000));
    }
}
