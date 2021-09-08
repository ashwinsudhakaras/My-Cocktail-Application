package com.ashwinsudhakar.mycocktailapplication.adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashwinsudhakar.mycocktailapplication.R;
import com.ashwinsudhakar.mycocktailapplication.models.ModelMainActivity;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

public class AdaptorMainActivity extends RecyclerView.Adapter<AdaptorMainActivity.MainActivityViewHolder> {

    public interface MainActivityItemInterface {

        void onItemClickListener(ModelMainActivity model, int position);
    }

    List<ModelMainActivity> modelMainActivityList;
    Context context;

    MainActivityItemInterface mainActivityItemInterface;

    public static class MainActivityViewHolder extends RecyclerView.ViewHolder {

        private final TextView productName;
        private final ImageView imageView;


        public MainActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            productName = itemView.findViewById(R.id.pName);

        }
    }

    public AdaptorMainActivity(Context context, List<ModelMainActivity> modelMainActivityList, MainActivityItemInterface listener) {
        this.modelMainActivityList = modelMainActivityList;
        this.context = context;
        this.mainActivityItemInterface = listener;
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_product_list, parent, false);

        return new MainActivityViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ModelMainActivity activity = modelMainActivityList.get(position);

        holder.productName.setText(activity.getpName());
        Glide.with(context).load(activity.getImageUrl()).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(holder.imageView);


        holder.itemView.setOnClickListener(v -> mainActivityItemInterface.onItemClickListener(activity, position));
    }

    @Override
    public int getItemCount() {
        return modelMainActivityList.size();
    }

    public void filterList(ArrayList<ModelMainActivity> filteredList) {
        modelMainActivityList = filteredList;
        notifyDataSetChanged();
    }
}

