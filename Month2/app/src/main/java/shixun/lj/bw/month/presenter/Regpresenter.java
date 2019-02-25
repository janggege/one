package shixun.lj.bw.month.presenter;

import java.util.Map;

import shixun.lj.bw.month.activity.Regactivity;
import shixun.lj.bw.month.model.Regmodel;
import shixun.lj.bw.month.view.Regutils;

/*
  name:刘江
  data:2019
*/public class Regpresenter {

    private final Regmodel regmodel;
    private final Regutils regutils1;

    public Regpresenter(Regutils regutils) {
        regmodel = new Regmodel();
        regutils1 = regutils;



    }


    public void reg(Map<String, String> map) {
        regmodel.Regdata(map);
        regmodel.setRegclick(new Regmodel.Regclick() {
            @Override
            public void reg(String status) {
                regutils1.Regutils(status);
            }
        });

    }
}
