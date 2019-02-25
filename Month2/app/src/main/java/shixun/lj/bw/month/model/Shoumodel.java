package shixun.lj.bw.month.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.month.bean.Data;
import shixun.lj.bw.month.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Shoumodel {
    public interface onclick {
        void show(List<Data.ResultBean> list);
    }

    onclick onclick;

    public void setOnclick(Shoumodel.onclick onclick) {
        this.onclick = onclick;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    Gson gson = new Gson();
                    Data data1 = gson.fromJson(data, Data.class);
                    List<Data.ResultBean> result = data1.getResult();
                    onclick.show(result);
                    break;
            }
        }
    };

    public void showm(String s, int page) {

        String url = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=" + s + "&page=" + page + "&&count=20";
        OkHttpUtils.doGet(url, new Callback() {
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
