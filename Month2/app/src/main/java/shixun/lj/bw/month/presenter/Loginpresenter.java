package shixun.lj.bw.month.presenter;

import java.util.Map;

import shixun.lj.bw.month.activity.MainActivity;
import shixun.lj.bw.month.model.Logimodel;
import shixun.lj.bw.month.view.Regutils;

/*
  name:刘江
  data:2019
*/public class Loginpresenter {

    private final Logimodel logimodel;
    private final Regutils regutils1;

    public Loginpresenter(Regutils regutils) {
        logimodel = new Logimodel();
        regutils1 = regutils;

    }

    public void Login(Map<String, String> map) {
        logimodel.Logindata(map);
        logimodel.setLoginclick(new Logimodel.loginclick() {
            @Override
            public void reg(String status) {
                regutils1.Regutils(status);
            }
        });


    }

}
