package com.example.jay.setpayscale;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class OurAdapter extends RecyclerView.Adapter<OurAdapter.MyViewHolder> {

    private Context mContext;
    private List<DataForSearchEmp> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView empe_name, inst_id, specialisation, dob, ret_year;
        public MyViewHolder(View view) {
            super(view);
            empe_name = (TextView) view.findViewById(R.id.name_label);
            inst_id = (TextView) view.findViewById(R.id.instute_label);
            specialisation = (TextView) view.findViewById(R.id.specialization_label);
            dob = (TextView) view.findViewById(R.id.dob_label);
            ret_year = (TextView) view.findViewById(R.id.retirement_label);

        }
    }


    public OurAdapter(Context mContext, List<DataForSearchEmp> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emp_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        DataForSearchEmp album = albumList.get(position);
        holder.empe_name.setText(album.getEmpe_name());
        holder.inst_id.setText(album.getInst_id());
        holder.specialisation.setText(album.getSpecialisation());
        holder.dob.setText(album.getDob());
        holder.ret_year.setText(album.getRet_year());

        // loading album cover using Glide library
//        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}