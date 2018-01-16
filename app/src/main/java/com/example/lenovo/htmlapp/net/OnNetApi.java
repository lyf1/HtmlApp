package com.example.lenovo.htmlapp.net;

import com.example.lenovo.htmlapp.bean.Bean;

import java.util.List;

/**
 * Created by lenovo on 2018/1/12.
 */

public interface OnNetApi<T> {
    void OnSuccess(List<T> t);
    void OnError(Exception e);
}
