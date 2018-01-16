package com.example.lenovo.htmlapp.bookshelf.model;



import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.net.OnNetApi;
import com.example.lenovo.htmlapp.net.RetrofitUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/1/12.
 */

public class WebTitleModel {
    public void getData(final OnNetApi<Bean> onNetApi){
        Observable<Bean> title = RetrofitUtils.getWebApi().getTitle();
           title.toList()
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribeOn(Schedulers.newThread())
                   .subscribe(new Consumer<List<Bean>>() {
                       @Override
                       public void accept(List<Bean> beans) throws Exception {
                                 onNetApi.OnSuccess(beans);
                       }
                   });






    }
}
