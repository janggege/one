package shixun.lj.bw.month.presenter;

import shixun.lj.bw.month.bean.Xinpin;
import shixun.lj.bw.month.model.Xinmodel;
import shixun.lj.bw.month.view.Regxin;

/*
  name:刘江
  data:2019
*/public class XinPresenter {

    private final Xinmodel xinmodel;
    private final Regxin regxin1;

    public XinPresenter(Regxin regxin) {
        xinmodel = new Xinmodel();
        regxin1 = regxin;

    }

    public void xin() {
        xinmodel.xin();
        xinmodel.setOnXinpinclick(new Xinmodel.OnXinpinclick() {
            @Override
            public void xinpin(Xinpin.ResultBean Xin) {
                regxin1.xin(Xin);
            }
        });
    }
}
