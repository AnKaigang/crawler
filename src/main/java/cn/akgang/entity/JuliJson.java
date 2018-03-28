package cn.akgang.entity;

/**
 * @author akgang
 * @date 2018/3/27.
 */
public class JuliJson {

    private Integer code;

    private String errMsg;

    private String data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JuliJson{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
