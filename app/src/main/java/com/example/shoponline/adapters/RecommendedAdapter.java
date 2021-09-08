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
import com.example.shoponline.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHoler> {

    Context context;
    List<RecommendedModel> recommendedModelList;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendedAdapter.ViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommeded_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Glide.with(context).load(recommendedModelList.get(position).getImg_url()).into(holder.recImg);
        holder.name.setText(recommendedModelList.get(position).getName());
        holder.description.setText(recommendedModelList.get(position).getDescription());
        holder.rating.setText(recommendedModelList.get(position).getRating());
        holder.price.setText("Giá: " + recommendedModelList.get(position).getPrice() + " $");
    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView recImg;
        TextView name, description, rating, price;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            recImg = itemView.findViewById(R.id.rec_img);
            name = itemView.findViewById(R.id.rec_name);
            description = itemView.findViewById(R.id.rec_des);
            rating = itemView.findViewById(R.id.rec_rating);
            price = itemView.findViewById(R.id.rec_price);
        }
    }
}
