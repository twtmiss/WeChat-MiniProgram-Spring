package wechat_shop.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeUtil {

    // 13位时间戳转日期 yyyy-MM-dd HH:mm:ss
    public static String TimestampToDatetime(long timestamp){
//        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
    }
}
