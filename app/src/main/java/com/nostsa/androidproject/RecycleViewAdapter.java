package com.nostsa.androidproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends  RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

private Context mContext;
private List<User> mUser;
private List<Address> mAddress;
private List<Company> mCompany;
private List<Geo> mGeo;
private MyViewHolder myViewHolder;

    public RecycleViewAdapter(Context context, List<User> user, List<Address> address, List<Company> company, List<Geo> geo) {
        mContext = context;
        mUser = user;
        mAddress = address;
        mCompany = company;
        mGeo = geo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        LayoutInflater inflater = LayoutInflater.from(mContext);

        view=inflater.inflate(R.layout.custom_list_layout, parent, false);

        myViewHolder = new MyViewHolder(view);

        myViewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentContext();
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mUser.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        LinearLayout view_container;

        public MyViewHolder(View itemView){
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.txtName);

        }
    }

    private void intentContext(){
        Intent i = new Intent(mContext,DetailssActivity.class);
        i.putExtra("user_id",mUser.get(getAdapterPosition()).getId());
        i.putExtra("user_name",mUser.get(getAdapterPosition()).getName());
        i.putExtra("user_username",mUser.get(getAdapterPosition()).getUsername());
        i.putExtra("user_email",mUser.get(getAdapterPosition()).getEmail());
        i.putExtra("user_phone",mUser.get(getAdapterPosition()).getPhone());
        i.putExtra("user_website",mUser.get(getAdapterPosition()).getWebsite());

        i.putExtra("address_street",mAddress.get(getAdapterPosition()).getStreet());
        i.putExtra("address_suite",mAddress.get(getAdapterPosition()).getSuite());
        i.putExtra("address_city",mAddress.get(getAdapterPosition()).getCity());
        i.putExtra("address_zipcode",mAddress.get(getAdapterPosition()).getZipcode());

        i.putExtra("geo_lat",mGeo.get(getAdapterPosition()).getLat());
        i.putExtra("geo_lng",mGeo.get(getAdapterPosition()).getLng());

        i.putExtra("company_name",mCompany.get(getAdapterPosition()).getName());
        i.putExtra("company_catchPhrase",mCompany.get(getAdapterPosition()).getCatchPhrase());
        i.putExtra("company_bs",mCompany.get(getAdapterPosition()).getBs());


        mContext.startActivity(i);
    }

    private int getAdapterPosition(){
        return myViewHolder.getAdapterPosition();
    }

    

}
