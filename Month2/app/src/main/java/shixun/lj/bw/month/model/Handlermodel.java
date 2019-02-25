package shixun.lj.bw.month.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.month.bean.Tou;
import shixun.lj.bw.month.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Handlermodel {
    String url = "http://172.17.8.100/small/commodity/v1/bannerShow";

    public interface OnclickTou {
        void tou(List<Tou.ResultBean> list);
    }

    OnclickTou onclickTou;

    public void setOnclickTou(OnclickTou onclickTou) {
        this.onclickTou = onclickTou;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String s = (String) msg.obj;
                    Gson gson = new Gson();
                    Tou tou = gson.fromJson(s, Tou.class);
                    List<Tou.ResultBean> result = tou.getResult();
                    onclickTou.tou(result);


                    break;
            }
        }
    };

    public void tou() {
        OkHttpUtils.okHttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj = string;
                handler.sendMessage(message);
            }
        });
    }
}
