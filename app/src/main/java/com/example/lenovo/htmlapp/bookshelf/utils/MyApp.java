package com.example.lenovo.htmlapp.bookshelf.utils;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.example.lenovo.htmlapp.bean.DaoMaster;
import com.example.lenovo.htmlapp.bean.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lenovo on 2018/1/13.
 */

public class MyApp extends Application {

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper(this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
