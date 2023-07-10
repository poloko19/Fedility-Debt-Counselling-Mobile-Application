package com.example.fedilitydebtcounselling;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //Initialize variable
    Activity activity;
    ArrayList<ContactModel> arrayList;

    //Create Constructor
    public MainAdapter(Activity activity,ArrayList<ContactModel> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //Initialize view
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_contact,parent,false);

        //return view
        return  new ViewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel model = arrayList.get(position);
        //Set Name
        holder.tvName.setText(model.getName());
        //Set Number
        holder.tvNumber.setText(model.getNumber());


    }

    @Override
    public int getItemCount() {
        //Return array list Size
        return arrayList.size();
        //return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView tvName,tvNumber;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);

        }
    }
}
