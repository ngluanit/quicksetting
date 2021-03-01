package com.example.blacklist;

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

public class BlacklistAdapter  extends RecyclerView.Adapter<BlacklistAdapter.MyViewHolder>{
    Context context;
    ArrayList<ItemBlacklist> itemBlacklists;

    public BlacklistAdapter(Context context, ArrayList<ItemBlacklist> itemBlacklists) {
        this.context = context;
        this.itemBlacklists = itemBlacklists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blacklist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_app_bl.setText(itemBlacklists.get(position).getTv_app_bl());
        holder.img_app_bl.setImageDrawable(itemBlacklists.get(position).getImg_app_bl());

    }

    @Override
    public int getItemCount() {
       return itemBlacklists.size();
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder {
        TextView tv_app_bl;
        ImageView img_app_bl,check_bl;
        private boolean check_blShown= true;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_app_bl = itemView.findViewById(R.id.tv_app_bl);
            img_app_bl = itemView.findViewById(R.id.img_app_bl);
            check_bl = itemView.findViewById(R.id.check_bl);

            check_bl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((check_bl != null) && (check_blShown)){
                        check_bl.setImageResource(R.drawable.ic_checkbox);
                        check_blShown = false;
                    }
                    else {
                        if (check_bl != null) check_bl.setImageResource(R.drawable.ic_check_empty);
                        check_blShown = true;
                    }
                }
            });
        }
    }
}
