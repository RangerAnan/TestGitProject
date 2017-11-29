package com.test.mi.testproject.jsoup;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.mvp.presenter.QsPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by fcl on 2017/11/22
 * desc:
 */

public class JsoupPrensenter extends QsPresenter<TestJsoupActivity> {

    public String url = "http://home.meishichina.com/show-top-type-recipe.html";

    @ThreadPoint(ThreadType.WORK)
    public String getHtmlContent() {
        String result = "";

        try {
            Document document = Jsoup.connect(url).get();

            Elements elements = document.select("div.info");
            result = elements.select("a").attr("href");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @ThreadPoint(ThreadType.WORK)
    public String getHtmlTitle() {
        String result = "";

        try {
            Document document = Jsoup.connect(url).get();

            Elements elements = document.select("div.top-bar");
            result = elements.select("a").attr("title");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
