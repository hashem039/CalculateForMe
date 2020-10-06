package com.hmeng.calculateforme.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hmeng.calculateforme.Data.BalconyRadiusOperationTask;
import com.hmeng.calculateforme.Data.ServiceModel;
import com.hmeng.calculateforme.R;
import com.hmeng.calculateforme.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity context;
    List<BalconyRadiusOperationTask> serviceModelsList;
    public RecyclerViewAdapter(Activity context, List<BalconyRadiusOperationTask> userArrayList) {
        this.context = context;
        this.serviceModelsList = userArrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.prev_balcony_radius_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BalconyRadiusOperationTask service = serviceModelsList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        viewHolder.txtView_width_value.setText(String.valueOf(service.getFirstAttribute()));
        viewHolder.txtView_length_value.setText(String.valueOf(service.getSecondAttribute()));
        viewHolder.txtView_result_value.setText(String.valueOf(service.getResult()));
        viewHolder.txtView_date.setText(service.getTaskDate());

    }

    @Override
    public int getItemCount() {
        return serviceModelsList != null? serviceModelsList.size() : 0;
    }
    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_icon;
        TextView txtView_width_value;
        TextView txtView_length_value;
        TextView txtView_result_value;
        TextView txtView_date;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView_icon = itemView.findViewById(R.id.imageview_serviceIcon);
            txtView_width_value = itemView.findViewById(R.id.tv_balcony_radius_width_value);
            txtView_length_value = itemView.findViewById(R.id.tv_balcony_radius_length_value);
            txtView_result_value = itemView.findViewById(R.id.tv_balcony_radius_result_value);
            txtView_date = itemView.findViewById(R.id.tv_balcony_radius_date_value);


        }
    }
}
