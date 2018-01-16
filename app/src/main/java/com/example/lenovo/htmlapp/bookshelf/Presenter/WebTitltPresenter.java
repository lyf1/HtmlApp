package com.example.lenovo.htmlapp.bookshelf.Presenter;

import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.bookshelf.model.WebTitleModel;
import com.example.lenovo.htmlapp.net.OnNetApi;

import java.util.List;

/**
 * Created by lenovo on 2018/1/13.
 */

public class WebTitltPresenter {
    private WebTitleApi wa;
    private WebTitleModel wm;

    public WebTitltPresenter(WebTitleApi wa) {
        this.wa = wa;
        wm=new WebTitleModel();
    }

    public void getData(){
        wm.getData(new OnNetApi<Bean>() {
            @Override
            public void OnSuccess(List<Bean> t) {
                wa.showData(t);
            }

            @Override
            public void OnError(Exception e) {

            }
        });
    }
}
