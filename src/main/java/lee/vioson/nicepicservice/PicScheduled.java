package lee.vioson.nicepicservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时爬取数据
 */
//@Component
public class PicScheduled {
    private Logger logger = LoggerFactory.getLogger(PicScheduled.class);
//    @Autowired
//    private DataService dataService;

//    @Scheduled(cron = "0 0 0/2 * * ?")
    public void loadData() {
        logger.info("执行lee.vioson.nicepicservice.PicScheduled.loadData");
//        dataService.upAllDatas();
    }
}
