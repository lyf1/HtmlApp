package com.example.lenovo.htmlapp.bookhoom.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.htmlapp.R;
import com.example.lenovo.htmlapp.adapter.ClassChildAdapter;
import com.example.lenovo.htmlapp.adapter.ClassGroupAdapater;
import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.bookhoom.presenter.ClassChildApi;
import com.example.lenovo.htmlapp.bookhoom.presenter.ClassChildPresenter;
import com.example.lenovo.htmlapp.bookhoom.presenter.ClassPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/1/13.
 */

public class ClassFragment extends Fragment implements ClassChildApi {

    @BindView(R.id.hf_child)
    RecyclerView mChild;
    Unbinder unbinder;
    @BindView(R.id.hf_group)
    ListView mGroup;
    private ClassGroupAdapater cga;
    private String url="http://www.ruanyifeng.com/blog/essays/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        unbinder = ButterKnife.bind(this, view);

        mChild.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ClassPresenter cp = new ClassPresenter(this);
        cp.getData();
        final ClassChildPresenter ccp=new ClassChildPresenter(this);
        ccp.getData(url);
        mGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               cga.press(position);
               Bean bean = (Bean) parent.getItemAtPosition(position);
                url= bean.url;
                Log.d("TAG",url);
                ccp.getData(url);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void showClassData(List<Bean> beanLis) {
        cga = new ClassGroupAdapater(beanLis, getActivity());
        mGroup.setAdapter(cga);
    }
    //获取子类的url


    @Override
    public void showChildCild(List<Bean> beanLis) {
        ClassChildAdapter cca = new ClassChildAdapter(beanLis, getActivity());
        mChild.setAdapter(cca);
    }
}
