package denggao.wechat.util;

import java.util.HashMap;
import java.util.Map;

public class toResponse {

    /**
     * @param requestCode
     * @param msg
     * @param data
     * @return Map<String,Object>
     * @throws
     * @Description:构建统一格式返回对象
     */
    public Map<String, Object> toResponsObject(int requestCode, String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("no", requestCode);
        obj.put("msg", msg);
        if (data != null)
            obj.put("data", data);
        return obj;
    }

    public Map<String, Object> toResponsSuccess(String msg) {
        if(msg.equals("") || msg == null){
            return toResponsObject(1, "执行成功",null);
        }else{
            return toResponsObject(1, msg, null);
        }
    }

    public Map<String, Object> toResponsMsgSuccess(String msg, Object data) {
        if(msg.equals("") || msg == null){
            return toResponsObject(1, "执行成功", data);
        }else{
            return toResponsObject(1, msg, data);
        }
    }

    public Map<String, Object> toResponsFail(String msg) {
        return toResponsObject(0, msg, null);
    }
}
