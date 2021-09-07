package com.example.shoponline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoponline.R;
import com.example.shoponline.models.HomeCategory;


import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHoler>  {
    private Context context;
    private List<HomeCategory> homeCategoryList;

    public HomeAdapter(Context context, List<HomeCategory> homeCategoryList) {
        this.context = context;
        this.homeCategoryList = homeCategoryList;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
Glide.with(context).load(homeCategoryList.get(position).getImg_url()).into(holder.catImg);
holder.name.setText(homeCategoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return homeCategoryList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView name;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            catImg = itemView.findViewById(R.id.home_cat_img);
            name = itemView.findViewById(R.id.home_cat_name);

        }
    }
}
