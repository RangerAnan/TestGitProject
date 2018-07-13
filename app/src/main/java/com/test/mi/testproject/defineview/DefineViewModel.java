package com.test.mi.testproject.defineview;

/**
 * Created by fcl on 18.4.2
 * desc:
 */

public class DefineViewModel {

    public static final int VIEW_TYPE_ONE = 1;
    public static final int VIEW_TYPE_TWO = 2;
    public static final int VIEW_TYPE_THREE = 3;

    private String title;
    private String content;
    private String icon;
    private int viewType;


    //构造方法
    public DefineViewModel(int type, String title, String content, String icon) {
        this.viewType = type;
        this.title = title;
        this.content = content;
        this.icon = icon;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
