package com.example.settingapp.tiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingapp.R;

import java.util.ArrayList;

public class ItemAppAdapter extends RecyclerView.Adapter<ItemAppAdapter.MyViewHolder> {
    Context context;
    ArrayList<ItemApp> appArrayList;

    public ItemAppAdapter(Context context, ArrayList<ItemApp> appArrayList) {
        this.context = context;
        this.appArrayList = appArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_tiles,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_itemapp.setText(appArrayList.get(position).getTv_itemapp());
        holder.img_itemapp.setImageResource(appArrayList.get(position).getImg_itemapp());

    }

    @Override
    public int getItemCount() {
        return appArrayList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {
        TextView tv_itemapp;
        ImageView img_itemapp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_itemapp = itemView.findViewById(R.id.tv_itemapp);
            img_itemapp = itemView.findViewById(R.id.img_itemapp);
        }
    }
}
