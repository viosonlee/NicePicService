package lee.vioson.nicepicservice.services;

import lee.vioson.nicepicservice.models.ListData;
import lee.vioson.nicepicservice.models.Pic;
import lee.vioson.nicepicservice.models.Type;
import lee.vioson.nicepicservice.repository.ListDataRepository;
import lee.vioson.nicepicservice.repository.PicRepository;
import lee.vioson.nicepicservice.repository.TypeRepository;
import lee.vioson.nicepicservice.utils.DataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private PicRepository picRepository;
    @Autowired
    private ListDataRepository listDataRepository;

    private Logger logger = LoggerFactory.getLogger(DataService.class);

//    public void upDateType() {
//        List<Type> types = DataHelper.getTypes();
//        if (types != null && types.size() > 0) {
//            typeRepository.deleteAll();
//            typeRepository.save(types);
//        } else logger.error("types is empty");
//    }

    public void upAllDatas() {
        List<Type> types = DataHelper.getTypes();
        typeRepository.deleteAll();
        typeRepository.save(types);
        types = typeRepository.findAll();
        if (types != null && types.size() > 0) {
            for (Type type : types) {
                List<Type> listType = typeRepository.findOneByHref(type.getHref());
                if (listType == null || listType.size() == 0) {
                    typeRepository.save(type);
                    logger.info("存入:" + type.toString());
                } else logger.info("跳过:" + type.toString());
                List<ListData> listData = DataHelper.getListData(type);
                if (listData != null && listData.size() > 0) {
                    for (ListData listDatum : listData) {
                        List<ListData> oneBySrc = listDataRepository.findOneBySrc(listDatum.getSrc());
                        if (oneBySrc == null || oneBySrc.size() == 0) {
                            listDataRepository.save(listDatum);
                            logger.info("存入:" + listDatum.toString());
                        } else logger.info("跳过:" + listDatum.toString());
                        List<Pic> detail = DataHelper.getDetail(listDatum);
                        if (detail != null && detail.size() > 0) {
                            for (Pic pic : detail) {
                                List<Pic> oneBySrc1 = picRepository.findOneBySrc(pic.getSrc());
                                if (oneBySrc1 == null || oneBySrc1.size() == 0) {
                                    picRepository.save(pic);
                                    logger.info("存入:" + pic.toString());
                                } else logger.info("跳过:" + pic.toString());
                            }
                        } else logger.error("detail pic of " + listDatum.getHref() + " is empty");
                    }
                } else logger.error("listData of " + type.getHref() + " is empty");
            }
        } else logger.error("types is empty");

    }

    public void run() {
        new Thread(this::upAllDatas).start();
    }

//    public void refreshAllDatas() {
//        List<ListData> listData = DataHelper.getAllList();
//        if (listData != null && listData.size() > 0) {
//            for (ListData listDatum : listData) {
//                List<ListData> oneBySrc = listDataRepository.findOneBySrc(listDatum.getSrc());
//                if (oneBySrc == null || oneBySrc.size() == 0)
//                    listDataRepository.save(listDatum);
//                else logger.info("跳过:" + listDatum.toString());
//                List<Pic> detail = DataHelper.getDetail(listDatum.getHref());
//                if (detail != null && detail.size() > 0) {
//                    for (Pic pic : detail) {
//                        List<Pic> oneBySrc1 = picRepository.findOneBySrc(pic.getSrc());
//                        if (oneBySrc1 == null || oneBySrc1.size() == 0)
//                            picRepository.save(pic);
//                        else logger.info("跳过:" + pic.toString());
//                    }
//                } else logger.error("detail pic of " + listDatum.getHref() + " is empty");
//            }
//        } else logger.error("listData is empty");
//    }
}
