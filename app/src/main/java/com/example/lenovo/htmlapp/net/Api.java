package com.example.lenovo.htmlapp.net;



import com.example.lenovo.htmlapp.bean.Bean;
import com.github.florent37.retrojsoup.annotations.Select;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by lenovo on 2018/1/12.
 */

public interface Api {
    @Select("li")
    Observable<Bean> getTitle();

    @Select("#beta-inner")
    Observable<Bean> getClaaify();

    @Select("li")
    Observable<Bean> getChildClass();

}
