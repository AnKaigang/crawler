package cn.akgang.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akgang on 2017/8/14.
 */
public class JsonResult {

    private Map<String, Object> data = new HashMap<String, Object>();

    private String msg = "";

    private Boolean success = true;

    public Map<String, Object> getData() {
        return data;
    }

    public JsonResult set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                '}';
    }
}
