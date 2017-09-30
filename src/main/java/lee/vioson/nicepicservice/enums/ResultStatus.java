package lee.vioson.nicepicservice.enums;

import org.omg.CORBA.UNKNOWN;

public enum ResultStatus {
    SUCCESSFUL(200, "成功"),
    UNLOGIN(101, "未登录"),
    ERROR_PARAMS(100, "参数错误"),
    UNKNOWN(-1, "未知错误");

    private Integer code;
    private String msg;

    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
