package wechat_shop.Util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候，如果是null的对象，key也会消失


public class Result<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private Result(){}

    private Result(int status){
        this.status = status;
    }

    private Result(int status, T data){
        this.status = status;
        this.data = data;
    }

    private Result(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private Result(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public String getMsg(){
        return msg;
    }

    public static <T> Result<T> resultSuccess(){
        return new Result<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> Result<T> resultSuccessMessage(String msg){
        return new Result<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> resultSuccess(T data){
        return new Result<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> Result<T> resultSuccess(String msg, T data){
        return new Result<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> resultError(){
        return new Result<T>(ResponseCode.ERROR.getCode());
    }

    public static <T> Result<T> resultErrorMessage(String errorMessage){
        return new Result<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> Result<T> resultErrorCodeMessage(int errorCode, String errorMessage){
        return new Result<T>(errorCode, errorMessage);
    }

//
//    /**
//     * @param requestCode
//     * @param msg
//     * @param data
//     * @return Map<String,Object>
//     * @throws
//     * @Description:构建统一格式返回对象
//     */
//
//    // 返回代码，消息，数据
//    private static Map<String, Object> toResponsObject(int requestCode, String msg, Object data) {
//        Map<String, Object> obj = new HashMap<String, Object>();
//        obj.put("no", requestCode);
//        obj.put("msg", msg);
//        if (data != null)
//            obj.put("data", data);
//        return obj;
//    }
//
//    // 返回 消息
//    public static Map<String, Object> toResponsSuccess(String msg) {
//        if(msg.equals("") || msg == null){
//            return toResponsObject(1, "执行成功",null);
//        }else{
//            return toResponsObject(1, msg, null);
//        }
//    }
//
//    public static Map<String, Object> toResponsMsgSuccess(String msg, Object data) {
//        if(msg.equals("") || msg == null){
//            return toResponsObject(1, "执行成功", data);
//        }else{
//            return toResponsObject(1, msg, data);
//        }
//    }
//
//    public static Map<String, Object> toResponsFail(String msg) {
//        return toResponsObject(0, msg, null);
//    }
}
