package com.example.jay.setpayscale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jay on 27/3/17.
 */

public class CustomAdapter extends ArrayAdapter {

    List<Data> list = new ArrayList<>();
    public CustomAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Data object) {
        list.add(object);
        Log.v("aseem","asdkjfh");
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        //Holder holder;
        //if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
        Data data = (Data) this.getItem(position);
        TextView empe_id,empe_name,empe_gender,inst_id,perf,ret_year;
        empe_id = (TextView) row.findViewById(R.id.empe_id);
        empe_name = (TextView) row.findViewById(R.id.empe_name);
        empe_gender = (TextView) row.findViewById(R.id.empe_gender);
        inst_id = (TextView) row.findViewById(R.id.inst_id);
        perf = (TextView) row.findViewById(R.id.perf);
        ret_year = (TextView) row.findViewById(R.id.ret_year);
        empe_id.setText(data.getEmpe_id());
        empe_name.setText(data.getEmpe_name());
        empe_gender.setText(data.getEmpe_gender());
        inst_id.setText(data.getInst_id());
        perf.setText(data.getPerf());
        ret_year.setText(data.getRet_year());
       /*     holder = new Holder();
            holder.empe_id = (TextView) row.findViewById(R.id.empe_id);
            holder.empe_name = (TextView) row.findViewById(R.id.empe_name);
            holder.empe_gender = (TextView) row.findViewById(R.id.empe_gender);
            holder.inst_id = (TextView) row.findViewById(R.id.inst_id);
            holder.perf = (TextView) row.findViewById(R.id.perf);
            holder.ret_year = (TextView) row.findViewById(R.id.ret_year);
            row.setTag(holder);
        }else {
            holder = (Holder) row.getTag();
        }

        Data data = (Data) this.getItem(position);
        holder.empe_id.setText(data.getEmpe_id());
        holder.empe_name.setText(data.getEmpe_name());
        holder.empe_gender.setText(data.getEmpe_gender());
        holder.inst_id.setText(data.getInst_id());
        holder.perf.setText(data.getPerf());
        holder.ret_year.setText(data.getRet_year());*/

        return row;
    }

    static class Holder {
        TextView empe_id,empe_name,empe_gender,inst_id,perf,ret_year;
    }

}
