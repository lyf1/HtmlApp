package com.example.lenovo.htmlapp.net;

import com.github.florent37.retrojsoup.RetroJsoup;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by lenovo on 2018/1/12.
 */

public class RetrofitUtils {

    private static OkHttpClient okHttpClient;
    static {
       InitOkHttp();
    }

    private static void InitOkHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(okHttpClient==null){
            synchronized (RetrofitUtils.class){
                if(okHttpClient==null){
                    okHttpClient=new OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
    private static <T> T creatApi(Class<T> tClass,String webUrl){
        return  new RetroJsoup.Builder()
                .url(webUrl)
                 .client(okHttpClient)
                .build()
                .create(tClass);
    }
    public static  Api getWebApi(){
        return creatApi(Api.class,"http://www.ruanyifeng.com/blog/archives.html");
    }
     public static  Api getClassApi(String url){
        return creatApi(Api.class,url);
     }
 }
