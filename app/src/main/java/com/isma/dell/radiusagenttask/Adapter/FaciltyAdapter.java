package com.isma.dell.radiusagenttask.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.isma.dell.radiusagenttask.Presenter.MainContract;
import com.isma.dell.radiusagenttask.R;
import com.isma.dell.radiusagenttask.RoomDb.FacilitiesTable;

import java.util.List;

public class FaciltyAdapter extends RecyclerView.Adapter<FaciltyAdapter.MyViewHolder> {
    Context context;
    List<FacilitiesTable> facilityModels;
    MainContract.RecyclerViewOnClick recyclerViewOnClick;

    public FaciltyAdapter(Context context, List<FacilitiesTable> facilityModels, MainContract.RecyclerViewOnClick recyclerViewOnClick){
        this.context=context;
        this.facilityModels=facilityModels;
        this.recyclerViewOnClick= recyclerViewOnClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.adapter_facility,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     FacilitiesTable facilityModel=facilityModels.get(position);
        holder.tvOptionName.setText(facilityModel.getOptionName());
        String optionIconName=facilityModel.getOptionIconName();

        switch (optionIconName){
            case "apartment":
                holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.apartment));
                break;
                case "boat":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.boat));

                    break;
                case "condo":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.condo));

                    break;
                case "garage":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.garage));

                    break;
                case "garden":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.garden));

                    break;
                case "land":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.land));
                    break;
                case "no-room":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.noroom));

                    break;
                case "rooms":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.rooms));
                    break;
                case "swimming":
                    holder.ivOptionIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.swimming));

                default:
                    break;
        }
        Glide.with(context)
                .load(optionIconName)
                .into(holder.ivOptionIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            recyclerViewOnClick.onClick(facilityModel);
            }
        });


    }


    @Override
    public int getItemCount() {
        return facilityModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivOptionIcon;
        AppCompatTextView tvOptionName;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivOptionIcon=itemView.findViewById(R.id.ivOptionIcon);
            tvOptionName=itemView.findViewById(R.id.tvOptionName);
        }
    }
}
