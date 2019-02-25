package shixun.lj.bw.month.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import shixun.lj.bw.month.Myadapter.Mlssadapter;
import shixun.lj.bw.month.Myadapter.Myadapter;
import shixun.lj.bw.month.Myadapter.Pzshadapter;
import shixun.lj.bw.month.Myadapter.Xinadapter;
import shixun.lj.bw.month.R;
import shixun.lj.bw.month.activity.MyLiner;
import shixun.lj.bw.month.bean.Data;
import shixun.lj.bw.month.bean.Tou;
import shixun.lj.bw.month.bean.Xinpin;
import shixun.lj.bw.month.presenter.HanderPresent;
import shixun.lj.bw.month.presenter.Shoupresenter;
import shixun.lj.bw.month.presenter.XinPresenter;
import shixun.lj.bw.month.view.RegTou;
import shixun.lj.bw.month.view.Regxin;
import shixun.lj.bw.month.view.Shou;

/*
  name:刘江
  data:2019
*/public class Fragment1 extends Fragment implements Shou, RegTou, Regxin {
    private int page = 1;
    private RecyclerView recyclerView;
    private Handler handler = new Handler();
    private Shoupresenter shoupresenter;
    private List<Data.ResultBean> list1;
    private FlyBanner flyBanner;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private LinearLayout linearLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        MyLiner myLiner = view.findViewById(R.id.shou);
        recyclerView = view.findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        shoupresenter = new Shoupresenter(this);
        flyBanner = view.findViewById(R.id.fly);
        linearLayout = view.findViewById(R.id.linear);
        //新品
        recyclerView1 = view.findViewById(R.id.recy1);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(staggeredGridLayoutManager);
        //魔力时尚
        recyclerView3 = view.findViewById(R.id.recy3);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView3.setLayoutManager(gridLayoutManager);
        //品质生活
        recyclerView4 = view.findViewById(R.id.recy4);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        recyclerView4.setLayoutManager(gridLayoutManager1);

        getxinpin();
        heander();
        molishishang();
        pinzhi();

        final SwipyRefreshLayout srl = view.findViewById(R.id.ser);
        srl.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        myLiner.setOnclick(new MyLiner.onclick1() {
            @Override
            public void showdata(final String s) {
                //隐藏
                linearLayout.setVisibility(View.GONE);

                shoupresenter.showp(s, page);

                srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh(int index) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                page = 1;
                                shoupresenter.showp(s, page);
                                srl.setRefreshing(false);
                            }
                        }, 2000);


                    }

                    @Override
                    public void onLoad(int index) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                page++;
                                shoupresenter.showp(s, page);
                                srl.setRefreshing(false);
                            }
                        }, 2000);
                    }
                });
            }
        });


        return view;
    }


    private void heander() {
        HanderPresent handerPresent = new HanderPresent(this);
        handerPresent.tou();

    }


    private void molishishang() {
        getxinpin();
    }

    private void pinzhi() {
        getxinpin();
    }

    private void getxinpin() {
        XinPresenter xinPresenter = new XinPresenter(this);
        xinPresenter.xin();

    }


    @Override
    public void Shou(List<Data.ResultBean> list) {
        if (page == 1) {
            list1 = new ArrayList<>();
        }
        list1.addAll(list);
        Myadapter myadapter = new Myadapter(list1, getActivity());
        recyclerView.setAdapter(myadapter);
        //显示当前页面
        recyclerView.scrollToPosition(list1.size() - (list.size()));
    }

    @Override
    public void Tou(List<Tou.ResultBean> list) {

        ArrayList<String> listimg = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String imageUrl = list.get(i).getImageUrl();
            listimg.add(imageUrl);
        }
        flyBanner.setImagesUrl(listimg);

    }


    @Override
    public void xin(Xinpin.ResultBean Xin) {
        //新品
        List<Xinpin.ResultBean.RxxpBean> xin = Xin.getRxxp();
        List<Xinpin.ResultBean.RxxpBean.CommodityListBean> list = xin.get(0).getCommodityList();
        Xinadapter xinadapter = new Xinadapter(list, getActivity());
        recyclerView1.setAdapter(xinadapter);
        //魔力时尚
        List<Xinpin.ResultBean.MlssBean> mlss = Xin.getMlss();
        List<Xinpin.ResultBean.MlssBean.CommodityListBeanXX> moli = mlss.get(0).getCommodityList();
        Mlssadapter mlssadapter = new Mlssadapter(moli, getActivity());
        recyclerView3.setAdapter(mlssadapter);
        //品质生活
        List<Xinpin.ResultBean.PzshBean> pzsh = Xin.getPzsh();
        List<Xinpin.ResultBean.PzshBean.CommodityListBeanX> pzsh1 = pzsh.get(0).getCommodityList();
        Pzshadapter pzshadapter = new Pzshadapter(pzsh1, getActivity());
        recyclerView4.setAdapter(pzshadapter);


    }
}
