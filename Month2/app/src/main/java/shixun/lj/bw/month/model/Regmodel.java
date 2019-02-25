package shixun.lj.bw.month.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;
import shixun.lj.bw.month.bean.Reg;
import shixun.lj.bw.month.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Regmodel {
    String url = "http://172.17.8.100/small/user/v1/register";

    //接口回调
    public interface Regclick {
        void reg(String status);
    }

    Regclick regclick;

    public void setRegclick(Regclick regclick) {
        this.regclick = regclick;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String s = (String) msg.obj;
                    Gson gson = new Gson();
                    Reg reg = gson.fromJson(s, Reg.class);
                    String status = reg.getStatus();
                    if (regclick != null) {
                        regclick.reg(status);
                    }
                    break;
            }

        }
    };


    public void Regdata(Map<String, String> map) {
        OkHttpUtils.okHttpUtils.doPost(url, map, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.obj = string;
                message.what = 1;
                handler.sendMessage(message);


            }
        });

    }
}
