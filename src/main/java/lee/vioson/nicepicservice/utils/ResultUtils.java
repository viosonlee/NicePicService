package lee.vioson.nicepicservice.utils;


import lee.vioson.nicepicservice.enums.ResultStatus;
import lee.vioson.nicepicservice.results.Result;

public class ResultUtils {

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setData(null);
        result.setMessage(msg);
        result.setCode(code);
        return result;
    }

    public static Result error(ResultStatus resultStatus) {
        return error(resultStatus.getCode(), resultStatus.getMsg());
    }

}
