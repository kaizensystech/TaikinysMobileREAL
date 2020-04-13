package java.com.kaizenmax.taikinys_icl.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.CommonConstants;
import com.kaizenmax.taikinys_icl.util.DemoL3ListItem;
import com.kaizenmax.taikinys_icl.view.DemoL3_InProgressActivity;

import java.util.List;

public class MyAdapter_ForDemoL3IdList extends RecyclerView.Adapter<MyAdapter_ForDemoL3IdList.ViewHolder> {

    private List<com.kaizenmax.taikinys_icl.util.DemoL3ListItem> listItems;
    private Context context;
    private int position;




    public MyAdapter_ForDemoL3IdList(List<com.kaizenmax.taikinys_icl.util.DemoL3ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.demol3_id_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        com.kaizenmax.taikinys_icl.util.DemoL3ListItem listItem = listItems.get(position);
        holder.dateOfActivity_textView.setText(listItem.getDateOfActivity());
       // holder.lastUpdatedOn_textView.setText("Last updated on: "+listItem.getLastUpdatedOn());
        holder.farmerName_textView.setText(", "+listItem.getFarmerName());
        holder.villageName_textView.setText(", "+listItem.getVillageName());
        if(listItem.getDemoL3PermanentId()!=null) {
            holder.tempId_textView.setText("PermId: " + listItem.getDemoL3PermanentId());
            holder.textViewPermanentId_textView.setText(", TempId: " + listItem.getDemoL3TempId());


        }
        else {
            holder.tempId_textView.setText("TempId: " + listItem.getDemoL3TempId());
            holder.textViewPermanentId_textView.setText("");

        }

           this.position =position ;

            holder.bind(listItems.get(position));

    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tempId_textView;
        public TextView dateOfActivity_textView;
      //  public TextView lastUpdatedOn_textView;
        public TextView farmerName_textView;
        public TextView villageName_textView;
        public TextView textViewPermanentId_textView;



        public ViewHolder(View itemView) {
            super(itemView);
            tempId_textView = (TextView) itemView.findViewById(R.id.tempId);
            dateOfActivity_textView = itemView.findViewById(R.id.dateOfActivityId);
           //
            // lastUpdatedOn_textView = itemView.findViewById(R.id.lastUpdatedOnId);
            farmerName_textView = itemView.findViewById(R.id.farmerNameId);;
            villageName_textView = itemView.findViewById(R.id.villageNameId);;
            textViewPermanentId_textView = (TextView) itemView.findViewById(R.id.permanentId);
         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DemoL3_InProgressActivity.getInstance(), "RAM RAm", Toast.LENGTH_SHORT).show();

                   String s =  listItems.get(position).getDemoL3TempId();

                    Toast.makeText(DemoL3_InProgressActivity.getInstance(), " " +s, Toast.LENGTH_SHORT).show();
                }
            });
*/
        }

        public void bind(final DemoL3ListItem item) {
            //name.setText(item.name);
            //  Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(DemoL3_InProgressActivity.getInstance(), ""+item.getDemoL3TempId(), Toast.LENGTH_SHORT).show();

                    DemoL3ActivityPresenterInterface demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();

                    Integer tempId = Integer.valueOf(item.getDemoL3TempId());
                    String stage = null;
                    try {
                        stage = demoL3ActivityPresenterInterface.getStage(tempId);


                   // Toast.makeText(DemoL3_InProgressActivity.getInstance(), "stage "+stage, Toast.LENGTH_SHORT).show();

                   if(stage.equals(com.kaizenmax.taikinys_icl.util.CommonConstants.PROTOCOL.toString()))
                    {


                        DemoL3_InProgressActivity.getInstance().protocol_Intent(item.getDemoL3TempId());


                    }

                    else if(stage.equals(com.kaizenmax.taikinys_icl.util.CommonConstants.EXECUTION.toString()))
                    {

                        DemoL3_InProgressActivity.getInstance().execution_Intent(item.getDemoL3TempId());
                    }

                    else if (stage.equals(com.kaizenmax.taikinys_icl.util.CommonConstants.RESULT_INTERIM.toString()))
                    {
                        DemoL3_InProgressActivity.getInstance().interim_Intent(item.getDemoL3TempId());
                    }

                    else if (stage.equals(CommonConstants.RESULT_YIELD.toString()))
                    {
                        DemoL3_InProgressActivity.getInstance().yield_Intent(item.getDemoL3TempId());
                    }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        }


    }
}