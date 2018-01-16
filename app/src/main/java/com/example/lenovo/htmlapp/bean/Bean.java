package com.example.lenovo.htmlapp.bean;

import com.github.florent37.retrojsoup.annotations.JsoupHref;
import com.github.florent37.retrojsoup.annotations.JsoupText;

/**
 * Created by lenovo on 2018/1/12.
 */

public class Bean  {
    @JsoupText("li")
    public String time;

    @JsoupText("a")
    public String title;

    @JsoupHref("a")
    public String url;

    public  boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", checked=" + checked +
                '}';
    }
}
