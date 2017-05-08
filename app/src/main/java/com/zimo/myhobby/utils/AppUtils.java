package com.zimo.myhobby.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${Zimo} on 2017/5/8.
 * title:
 */

public class AppUtils {



    public static boolean stringFilter(String str) {
        // 只允许字母、数字和汉字
        String regEx = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    /**
     * @prama: str 要判断是否包含特殊字符的目标字符串
     */

    public static boolean compileExChar(String str) {

        String limitEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);

        if (m.find()) {
            return false;
        }
        return true;
    }


}
