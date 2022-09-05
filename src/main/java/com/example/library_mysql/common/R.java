package com.example.library_mysql.common;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Data
public class R<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.code = 200;
        r.msg = "Data request success at " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        r.data = object;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.code = 0;
        r.msg = msg + "; Data request fail at " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        return r;
    }

    public static <T> R<T> response(T object, String errorMsg) {
        if (Objects.isNull(object))
            return R.error(errorMsg);
        return R.success(object);
    }

    public static R<String> response(boolean res, String successMsg, String errorMsg) {
        return res ? R.success(successMsg) : R.error(errorMsg);
    }
}
