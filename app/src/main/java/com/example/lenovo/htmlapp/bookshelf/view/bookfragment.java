package com.example.lenovo.htmlapp.bookshelf.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.lenovo.htmlapp.R;
import com.example.lenovo.htmlapp.adapter.NoNetTitleAdapter;
import com.example.lenovo.htmlapp.adapter.PopuAdapter;
import com.example.lenovo.htmlapp.adapter.TitleAdapter;
import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.bean.DaoSession;
import com.example.lenovo.htmlapp.bean.User;
import com.example.lenovo.htmlapp.bean.UserDao;
import com.example.lenovo.htmlapp.bookshelf.Presenter.WebTitleApi;
import com.example.lenovo.htmlapp.bookshelf.Presenter.WebTitltPresenter;
import com.example.lenovo.htmlapp.bookshelf.utils.GlideImageLoader;
import com.example.lenovo.htmlapp.bookshelf.utils.MyApp;
import com.youth.banner.Banner;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/1/13.
 */

public class bookfragment extends Fragment implements WebTitleApi {
    @BindView(R.id.b_rv)
    RecyclerView mRv;
    Unbinder unbinder;
    @BindView(R.id.b_ss)
    EditText bSs;
    @BindView(R.id.b_banner)
    Banner bBanner;
    @BindView(R.id.b_flipper)
    ViewFlipper bFlipper;
    @BindView(R.id.b_image)
    ImageView mImage;


    private List<String> images = new ArrayList<>();
    private List testList;
    private int count;
    private Query<User> userQuery;
    private UserDao userDao;
    private List<User> listdao;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        //轮播
        //设置图片加载器
        setBanner();
        bFlipperMethod();
        //数据库

        DaoSession daoSession = ((MyApp) getActivity().getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
        listdao = userQuery.list();
        //判断网络
        if (isNetworkAvailable(getActivity())) {
            Toast.makeText(getActivity(), "您有网络,将显示网络数据", Toast.LENGTH_SHORT).show();
            WebTitltPresenter wp = new WebTitltPresenter(this);
            wp.getData();
        } else {
            Toast.makeText(getActivity(), "您当前没有网络,将显示本地数据", Toast.LENGTH_SHORT).show();
            NoNetTitleAdapter adapter = new NoNetTitleAdapter(listdao, getActivity());
            mRv.setAdapter(adapter);
        }
        bSs.addTextChangedListener(new TextWatcher() {

            private PopupWindow window;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s)) {

                } else {
                    popuwindowMethod(s);
                }
            }

            //popuwindow显示
            private void popuwindowMethod(CharSequence s) {
                String s1 = s.toString();
                List<User> users = queryByTitle(s1);
                //popuwindow显示
                if (users.size() == 0) {
                    Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_LONG).show();
                } else {
                    View popupView = getActivity().getLayoutInflater().inflate(R.layout.popuwindow, null);
                    RecyclerView mrv = (RecyclerView) popupView.findViewById(R.id.popu_iv);
                    mrv.setLayoutManager(new LinearLayoutManager(getContext()));
                    PopuAdapter pa = new PopuAdapter(users, getActivity());
                    mrv.setAdapter(pa);
                    window = new PopupWindow(popupView, 600, 400);
                    window.setAnimationStyle(R.style.popup_window_anim);
                    // TODO: 2016/5/17 设置背景颜色
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                    // TODO: 2016/5/17 设置可以获取焦点
                    window.setFocusable(true);
                    // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
                    window.setOutsideTouchable(true);
                    // TODO：更新popupwindow的状态
                    window.update();
                    // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
                    window.showAsDropDown(bSs, 0, 10);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }



    private void setBanner() {
        images.add("http://img4.imgtn.bdimg.com/it/u=1134454037,3183470517&fm=27&gp=0.jpg");
        images.add("http://img1.imgtn.bdimg.com/it/u=1942857029,928048256&fm=27&gp=0.jpg");
        images.add("http://img4.imgtn.bdimg.com/it/u=1750751996,1022563196&fm=27&gp=0.jpg");
        images.add("http://img2.imgtn.bdimg.com/it/u=3276521399,2038008043&fm=27&gp=0.jpg");
        bBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        bBanner.start();
    }

    private void bFlipperMethod() {
        testList = new ArrayList();
        testList.add(0, "学贵有疑，小疑则小进，大疑则大进");
        testList.add(1, "尽信书，莫如无书");
        testList.add(2, "书，如细雨过后的梧桐，洗去了浮华，透出了青绿");
        testList.add(3, "悠闲读书，追求的是一种气质、一种涵养");
        testList.add(4, "读书需要一种心境，不是什么时候都可以读的");
        count = testList.size();
        for (int i = 0; i < count; i++) {
            final View ll_content = View.inflate(getActivity(), R.layout.item_flipper, null);
            TextView tv_content = (TextView) ll_content.findViewById(R.id.tv_content);
            ImageView iv_closebreak = (ImageView) ll_content.findViewById(R.id.iv_closebreak);
            tv_content.setText(testList.get(i).toString());
            iv_closebreak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //对当前显示的视图进行移除
                    bFlipper.removeView(ll_content);
                    count--;
                    //当删除后仅剩 一条 新闻时，则取消滚动
                    if (count == 1) {
                        bFlipper.stopFlipping();
                    }
                }
            });
            bFlipper.addView(ll_content);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showData(List<Bean> beanLis) {
         if(listdao.size()==0){
             for (int i = 0; i < beanLis.size(); i++) {
                 User user = new User(null, beanLis.get(i).title, beanLis.get(i).url, beanLis.get(i).time);
                 userDao.insert(user);
             }
         }
        TitleAdapter ta = new TitleAdapter(beanLis, getContext());
        mRv.setAdapter(ta);
    }

    /* 检测当的网络（WLAN、3G/2G）状态
    * @param context Context
    * @return true 表示网络可用
    */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    //查询的方法
    private List<User> queryByTitle(String name) {
        QueryBuilder<User> builder = userDao.queryBuilder();
        Query<User> query = builder
                .where(UserDao.Properties.Title.like("%" + name + "%"))
                .build();
        List<User> list = query.list();
        return list;
    }

    @OnClick(R.id.b_image)
    public void onViewClicked() {

    }
}
