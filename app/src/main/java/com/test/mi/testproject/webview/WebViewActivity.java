package com.test.mi.testproject.webview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.qsmaxmin.qsbase.mvp.presenter.Presenter;
import com.test.mi.testproject.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by fcl on 18.1.3.
 */

@Presenter(WebviewPresenter.class)
public class WebViewActivity extends QsActivity<WebviewPresenter> {

    @Bind(R.id.webView)
    WebView webView;

    @Bind(R.id.webview_iv)
    ImageView imageView;


    String url_load = "file:///android_asset/demo.html";

    @Override
    public int layoutId() {
        return R.layout.activity_test_webview;
    }

    @Override
    public void initData(Bundle bundle) {
//        webView.loadUrl(url_load);
        Log.i(initTag(), "-------------");
        try {
            InputStream inputStream = getAssets().open("collect_face.jpg");
            byte[] bytes = readStream(inputStream);
            String s = new String(bytes, "UTF-8");

            Log.i(initTag(), EncodeBase64String(bytes));

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 将图片内容解析成字节数组
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    public static String EncodeBase64String(String value) {
        String strreturn = "";
        try {
            if (value.equals(""))
                return "";
            strreturn = android.util.Base64.encodeToString(value.getBytes(), Base64.DEFAULT).replaceAll("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strreturn;
    }

    public static String EncodeBase64String(byte[] value) {
        String strreturn = "";
        try {
            if (value.equals(""))
                return "";
            strreturn = android.util.Base64.encodeToString(value, Base64.DEFAULT).replaceAll("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strreturn;
    }
}
