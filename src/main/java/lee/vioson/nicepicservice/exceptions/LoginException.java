package lee.vioson.nicepicservice.exceptions;

import lee.vioson.nicepicservice.enums.ResultStatus;

public class LoginException extends RuntimeException {
    private int code;
    private ResultStatus resultStatus;

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public LoginException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMsg();
    }


    private String msg;

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


}
