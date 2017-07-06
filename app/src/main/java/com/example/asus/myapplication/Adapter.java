package com.example.asus.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Data> dataList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.name.setText(data.getName());
        holder.count.setText(String.valueOf(data.getCount()));
        holder.price.setText(String.valueOf(data.getPrice()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public Adapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, count, price;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_rec);
            count = (TextView) itemView.findViewById(R.id.count_rec);
            price = (TextView) itemView.findViewById(R.id.price_rec);
        }
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }
}
