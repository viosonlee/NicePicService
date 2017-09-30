package lee.vioson.nicepicservice.utils;

import lee.vioson.nicepicservice.models.ListData;
import lee.vioson.nicepicservice.models.Pic;
import lee.vioson.nicepicservice.models.Type;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataHelper {
    private static Logger logger = LoggerFactory.getLogger(DataHelper.class);
    private static String url = "http://www.xiumeim.com";
//    @Value("${datasourceUrl}")
//    private static String url;

    /**
     * 存储类型
     *
     * @return
     */
    public static List<Type> getTypes() {
        try {
            Document document = Jsoup.connect(url).get();
            if (document != null) {
                Element bodywrap = document.getElementById("menu");
                Elements elements = bodywrap.select("a[href]");
                ArrayList<Type> types = new ArrayList<>();
                for (Element element : elements) {
                    String href = element.attr("href");
                    String title = element.attr("title");
                    if (!title.equals("首页") && !title.equals("赞助本站")) {
                        Type type = new Type();
                        type.setHref(href);
                        type.setTitle(title);
                        types.add(type);
                    }
                }
                return types;
            }
            return Collections.EMPTY_LIST;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }


//    public static List<ListData> getAllList() {
//        int page = 1;
//        boolean hasMore = true;
//        List<ListData> result = new ArrayList<>();
//        while (hasMore) {
//            String url = getAlbumsUrl(page);
//            logger.info(url);
//            List<ListData> listData = loadListData(url);
//            if (listData != null && listData.size() > 0) {
//                result.addAll(listData);
//                page++;
//            } else hasMore = false;
//        }
//        return result;
//    }

    /**
     * 首页分类下面的数据
     *
     * @param page
     * @return
     */
    private static String getAlbumsUrl(int page) {
        if (page == 1)
            return "http://www.xiumeim.com/";
        else return "http://www.xiumeim.com/albums/page-" + page + ".html";
    }


    /**
     * 获取列表数据
     *
     * @param page
     */
    public static List<ListData> getList(int page, Type type) {
        String url = completeUrl(page, type.getHref());
        logger.info("请求的url={}", url);
        return loadListData(url, type);
    }

    private static List<ListData> loadListData(String url, Type type) {
        try {
            Document document = Jsoup.connect(url).get();
            if (document != null) {
                Element bodywrap = document.getElementById("bodywrap");
                Elements elements = bodywrap.select("a[target]");
                ArrayList<ListData> listDatas = new ArrayList<>();
                for (Element element : elements) {
                    String href = element.attr("href");
                    Elements img = element.select("img");
                    if (img != null && !img.isEmpty()) {
                        String alt = img.attr("alt");
                        String src = img.attr("data-original");
                        ListData listData = new ListData();
                        listData.setTypeId(type.getId());
                        listData.setType(type.getTitle());
                        listData.setAlt(alt);
                        listData.setSrc(src);
                        listData.setHref(href);
                        listDatas.add(listData);
                    }
                }
                return listDatas;
            } else return Collections.EMPTY_LIST;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public static List<ListData> getListData(Type type) {
        int page = 1;
        boolean hasMore = true;
        List<ListData> listDatas = new ArrayList<>();
        while (hasMore) {
            List<ListData> list = getList(page, type);
            if (list != null && list.size() > 0) {
                listDatas.addAll(list);
                page++;
            } else {
                hasMore = false;
                page = 1;
            }
        }
        return listDatas;
    }

    /**
     * 获取详情
     *
     * @param listData
     */
    public static List<Pic> getDetail(ListData listData) {
        int page1 = 1;
        boolean hasMore = true;
        ArrayList<Pic> picDetails = new ArrayList<>();
        while (hasMore) {
            String url = completeDetailUrl(listData.getHref(), page1);
            try {
                Document document = Jsoup.connect(url).get();
                if (document != null) {
                    Elements bodywrap = document.getElementsByClass("mainbody");
                    Elements elements = bodywrap.select("img[src]");
                    for (Element element : elements) {
                        String style = element.attr("style");
                        if (!StringUtils.isEmpty(style))
                            continue;
                        Pic picDetail = new Pic();
                        picDetail.setListHref(listData.getHref());
                        picDetail.setType(listData.getType());
                        picDetail.setTypeId(listData.getTypeId());
                        picDetail.setSrc(element.attr("src"));
                        picDetail.setAlt(element.attr("alt"));
                        picDetails.add(picDetail);
                    }
                    page1++;
                } else {
                    hasMore = false;
                }
            } catch (IOException e) {
                hasMore = false;
                e.printStackTrace();
            }
        }
        return picDetails;
    }

    /**
     * 详情里面的url
     *
     * @param href
     * @param page
     * @return
     */
    private static String completeDetailUrl(String href, int page) {
        if (!StringUtils.isEmpty(href) && (href.contains("http://") || href.contains("https://")))
            return page == 1 ? href : (href.replace(".html", "-" + page + ".html"));
        else
            return page == 1 ? url + href : url + (href.replace(".html", "-" + page + ".html"));
    }

    /**
     * 获取列表数据用到分页，
     *
     * @param page
     * @param typeHref
     * @return 返回当前分页的url
     */
    private static String completeUrl(int page, String typeHref) {
        if (!StringUtils.isEmpty(typeHref) && typeHref.contains("http://")) {
            if (page == 1)
                return typeHref;
            else {
                typeHref = typeHref.replace(".html", "-" + page + ".html");
                return typeHref;
            }
        } else {
            return completeDetailUrl(typeHref, page);
        }
    }

    public static String getPicUrl(String picUrl) {
        return picUrl.contains("http://") ? picUrl : url + picUrl;
    }


}
