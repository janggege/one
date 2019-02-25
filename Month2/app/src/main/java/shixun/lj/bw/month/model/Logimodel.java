package shixun.lj.bw.month.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;
import shixun.lj.bw.month.bean.Login;
import shixun.lj.bw.month.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Logimodel {
    private String url = "http://172.17.8.100/small/user/v1/login";

    //接口回调
    public interface loginclick {
        void reg(String status);
    }

    loginclick loginclick;

    public void setLoginclick(Logimodel.loginclick loginclick) {
        this.loginclick = loginclick;
    }


    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String s = (String) msg.obj;
                    Gson gson = new Gson();
                    Login login = gson.fromJson(s, Login.class);
                    String status = login.getStatus();
                    if (loginclick != null) {
                        loginclick.reg(status);
                    }

                    break;
            }
        }
    };

    public void Logindata(Map<String, String> map) {
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
