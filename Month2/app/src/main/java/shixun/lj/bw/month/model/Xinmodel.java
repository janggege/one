package shixun.lj.bw.month.model;

import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.month.bean.Xinpin;
import shixun.lj.bw.month.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Xinmodel {
    String url = "http://172.17.8.100/small/commodity/v1/commodityList";

    public interface OnXinpinclick {
        void xinpin(Xinpin.ResultBean Xin);
    }

    OnXinpinclick onXinpinclick;

    public void setOnXinpinclick(OnXinpinclick onXinpinclick) {
        this.onXinpinclick = onXinpinclick;
    }

    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    Gson gson = new Gson();
                    Xinpin xinpin = gson.fromJson(data, Xinpin.class);
                    Xinpin.ResultBean result = xinpin.getResult();
                    onXinpinclick.xinpin(result);
                    break;

            }
        }
    };

    public void xin() {
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
