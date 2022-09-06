package com.example.library_mysql.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Data
@ApiModel(value = "R", description = "加工后的数据返回对象")
public class R<T> {

    @ApiModelProperty("编码：200成功，0和其它数字为失败")
    private Integer code;

    @ApiModelProperty("附带信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;

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
