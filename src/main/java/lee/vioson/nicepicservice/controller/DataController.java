package lee.vioson.nicepicservice.controller;

import lee.vioson.nicepicservice.results.Result;
import lee.vioson.nicepicservice.services.DataService;
import lee.vioson.nicepicservice.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/refreshData")
    public Result refreshData() {
        dataService.run();
        return ResultUtils.success();
    }

}
