package com.example.lenovo.htmlapp.bookhoom.presenter;

import android.view.View;
import android.widget.AdapterView;

import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.bookhoom.model.ClassChildModel;
import com.example.lenovo.htmlapp.bookhoom.model.ClassModel;
import com.example.lenovo.htmlapp.bookhoom.view.ClassFragment;
import com.example.lenovo.htmlapp.bookshelf.Presenter.WebTitleApi;
import com.example.lenovo.htmlapp.net.OnNetApi;

import java.util.List;

/**
 * Created by lenovo on 2018/1/15.
 */

public class ClassChildPresenter {
    private ClassChildApi cca;
    private ClassChildModel cm;

    public ClassChildPresenter(ClassFragment cca) {
        this.cca = cca;
        cm=new ClassChildModel();
    }

    public void getData(String url){
        cm.getData(url,new OnNetApi<Bean>() {
            @Override
            public void OnSuccess(List<Bean> t) {
                cca.showChildCild(t);
            }

            @Override
            public void OnError(Exception e) {

            }
        });
    }
}
