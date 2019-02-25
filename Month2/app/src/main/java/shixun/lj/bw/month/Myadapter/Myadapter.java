package shixun.lj.bw.month.Myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shixun.lj.bw.month.R;
import shixun.lj.bw.month.bean.Data;
import shixun.lj.bw.month.bean.ShowData;


/*
  name:刘江
  data:2019
*/public class Myadapter extends RecyclerView.Adapter<Myadapter.Myviewholder> {
    List<Data.ResultBean> list;
    Context context;

    public Myadapter(List<Data.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.iteam, null, false);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        myviewholder.textView.setText(list.get(i).getCommodityName());
        Glide.with(context).load(list.get(i).getMasterPic()).into(myviewholder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.i);
            textView = itemView.findViewById(R.id.t);
        }
    }
}
