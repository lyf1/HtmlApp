package com.example.lenovo.htmlapp.bookhoom.presenter;

import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.bookhoom.model.ClassModel;
import com.example.lenovo.htmlapp.bookshelf.Presenter.WebTitleApi;
import com.example.lenovo.htmlapp.net.OnNetApi;

import java.util.List;

/**
 * Created by lenovo on 2018/1/15.
 */

public class ClassPresenter {
    private ClassChildApi cca;
    private ClassModel cm;

    public ClassPresenter(ClassChildApi cca) {
        this.cca = cca;
        cm=new ClassModel();
    }
    public void getData(){
        cm.getData(new OnNetApi<Bean>() {
            @Override
            public void OnSuccess(List<Bean> t) {
                cca.showClassData(t);
            }

            @Override
            public void OnError(Exception e) {

            }
        });
    }
}
