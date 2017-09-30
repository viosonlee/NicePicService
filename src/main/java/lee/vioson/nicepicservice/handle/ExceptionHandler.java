package lee.vioson.nicepicservice.handle;

import lee.vioson.nicepicservice.enums.ResultStatus;
import lee.vioson.nicepicservice.exceptions.LoginException;
import lee.vioson.nicepicservice.results.Result;
import lee.vioson.nicepicservice.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        if (e instanceof LoginException) {
            return ResultUtils.error(((LoginException) e).getResultStatus());
        }
        logger.error("【系统错误】", e);
        return ResultUtils.error(ResultStatus.UNKNOWN);
    }

}
