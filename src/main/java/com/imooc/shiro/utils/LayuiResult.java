package com.imooc.shiro.utils;


/**
 * 通用的返回的类
 */
public class LayuiResult {
    private int code;
    private String msg;
    private Long count;
    private Object data;

    public static LayuiResult success(String msg) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    public static LayuiResult success(Integer code, String msg) {
        LayuiResult result = new LayuiResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static LayuiResult success(Long count, Object data) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("加载成功");
        result.setCount(count);
        result.setData(data);
        return result;
    }

    public static LayuiResult success(String msg, Object data) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static LayuiResult success(Object data) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("加载成功");
        result.setData(data);
        return result;
    }


    public static LayuiResult fail() {
        LayuiResult result = new LayuiResult();
        result.setCode(1);
        result.setMsg("处理失败！");
        return result;
    }

    public static LayuiResult fail(String mssge) {
        LayuiResult result = new LayuiResult();
        result.setCode(1);
        result.setMsg(mssge);
        return result;
    }

    public static LayuiResult fail(Integer code, String mssge) {
        LayuiResult result = new LayuiResult();
        result.setCode(code);
        result.setMsg(mssge);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
