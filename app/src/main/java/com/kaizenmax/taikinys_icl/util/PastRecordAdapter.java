package com.kaizenmax.taikinys_icl.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.pojo.PastRecord;

import java.util.List;

public class PastRecordAdapter  extends RecyclerView.Adapter<PastRecordAdapter.MyViewHolder> {

    private List<PastRecord> pastRecordList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date_of_activity,village,district,crop_category,farmerName,farmermobile,row_id,header_1,header_2,header_3,header_4,header_5;

        public MyViewHolder(View view) {
            super(view);
            farmerName = (TextView) view.findViewById(R.id.fa_name);
            date_of_activity = (TextView) view.findViewById(R.id.date_of_activity);
            farmermobile = (TextView) view.findViewById(R.id.fa_mobile);
            crop_category = (TextView) view.findViewById(R.id.crop_category);
            village = (TextView) view.findViewById(R.id.village);
            district = (TextView) view.findViewById(R.id.district);
            row_id = (TextView) view.findViewById(R.id.row_id);
            header_1 = (TextView) view.findViewById(R.id.header_1);
            header_2 = (TextView) view.findViewById(R.id.header_2);
            header_3 = (TextView) view.findViewById(R.id.header_3);
            header_4 = (TextView) view.findViewById(R.id.header_4);
            header_5 = (TextView) view.findViewById(R.id.header_5);


        }
    }


    public PastRecordAdapter(List<PastRecord> pastRecordList) {
        this.pastRecordList = pastRecordList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.past_record_card_recycler_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PastRecord pastRecord = pastRecordList.get(position);
        holder.farmerName.setText(pastRecord.getFarmerName());
        holder.date_of_activity.setText(pastRecord.getDate_of_activity());
        holder.farmermobile.setText(pastRecord.getFarmermobile_formatted());
        holder.crop_category.setText(pastRecord.getCrop_category_formatted());
        holder.village.setText(pastRecord.getVillage_formatted());
        holder.district.setText(pastRecord.getDistrict_formatted());
        holder.row_id.setText(pastRecord.getRow_id());
        holder.header_1.setText(pastRecord.getHeader_1());
        holder.header_2.setText(pastRecord.getHeader_2());
        holder.header_3.setText(pastRecord.getHeader_3());
        holder.header_4.setText(pastRecord.getHeader_4());
        holder.header_5.setText(pastRecord.getHeader_5());
    }

    @Override
    public int getItemCount() {
        return pastRecordList.size();
    }
}