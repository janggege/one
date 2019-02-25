package shixun.lj.bw.month.Myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shixun.lj.bw.month.R;
import shixun.lj.bw.month.bean.Xinpin;

/*
  name:刘江
  data:2019
*/public class Mlssadapter extends RecyclerView.Adapter<Mlssadapter.Myiewholder> {
    List<Xinpin.ResultBean.MlssBean.CommodityListBeanXX> list;
    Context context;

    public Mlssadapter(List<Xinpin.ResultBean.MlssBean.CommodityListBeanXX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myiewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.moli, null, false);
        Myiewholder myiewholder = new Myiewholder(view);
        return myiewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull Myiewholder myiewholder, int i) {
        myiewholder.textView.setText(list.get(i).getCommodityName());
        myiewholder.textView1.setText("￥"+list.get(i).getPrice());
        Glide.with(context).load(list.get(i).getMasterPic()).into(myiewholder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myiewholder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final TextView textView1;

        public Myiewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.moi1);
            textView = itemView.findViewById(R.id.mot1);
            textView1 = itemView.findViewById(R.id.mot2);
        }
    }
}
