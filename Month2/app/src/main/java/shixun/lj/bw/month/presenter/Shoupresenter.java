package shixun.lj.bw.month.presenter;

import android.content.Context;

import java.util.List;

import shixun.lj.bw.month.activity.MyLiner;
import shixun.lj.bw.month.bean.Data;
import shixun.lj.bw.month.model.Shoumodel;
import shixun.lj.bw.month.view.Shou;

/*
  name:刘江
  data:2019
*/public class Shoupresenter {

    private final Shoumodel shoumodel;
    private final Shou shou1;


    public Shoupresenter(Shou shou) {
        shoumodel = new Shoumodel();
        shou1 = shou;

    }

    public void showp(String s,int page) {
        shoumodel.showm(s,page);
        shoumodel.setOnclick(new Shoumodel.onclick() {
            @Override
            public void show(List<Data.ResultBean> list) {
                shou1.Shou(list);

            }
        });
    }


}
