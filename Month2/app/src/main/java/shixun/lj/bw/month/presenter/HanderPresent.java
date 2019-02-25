package shixun.lj.bw.month.presenter;

import java.util.List;

import shixun.lj.bw.month.bean.Tou;
import shixun.lj.bw.month.model.Handlermodel;
import shixun.lj.bw.month.view.RegTou;

/*
  name:刘江
  data:2019
*/public class HanderPresent {

    private final Handlermodel handlermodel;
    private final RegTou regTou1;

    public HanderPresent(RegTou regTou) {
        handlermodel = new Handlermodel();
        regTou1 = regTou;

    }

    public void tou() {
        handlermodel.tou();
        handlermodel.setOnclickTou(new Handlermodel.OnclickTou() {
            @Override
            public void tou(List<Tou.ResultBean> list) {
                regTou1.Tou(list);
            }
        });
    }
}
