package com.example.lenovo.htmlapp.bookhoom.model;

import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.net.OnNetApi;
import com.example.lenovo.htmlapp.net.RetrofitUtils;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lenovo on 2018/1/15.
 */

public class ClassChildModel {
    public void getData(String url, final OnNetApi<Bean> onNetApi){
        Observable<Bean> childClass = RetrofitUtils.getClassApi(url).getChildClass();
        childClass.toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Bean>>() {
                    @Override
                    public void accept(List<Bean> beans) throws Exception {
                        onNetApi.OnSuccess(beans);
                    }
                });
    }
}
