package shubham.com.featurringfooddelivery.OrderBooking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.TimePeriedModel;
import shubham.com.featurringfooddelivery.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class RecyclerViewAdapterCooseTime extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    public ArrayList<TimePeriedModel> modelList;
     ViewHolder genericViewHolder;
    private OnItemClickListener mItemClickListener;


    public RecyclerViewAdapterCooseTime(Context context, ArrayList<TimePeriedModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<TimePeriedModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_choosetime, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final TimePeriedModel model = getItem(position);
                genericViewHolder = (ViewHolder) holder;

           genericViewHolder.radio_time.setText(model.getTimePeriod());

            if(modelList.get(position).getChecked()){
                genericViewHolder.radio_time.setChecked(true);
            }else{
                genericViewHolder.radio_time.setChecked(false);
            }
        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private TimePeriedModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, ArrayList<TimePeriedModel> modelList, RadioButton radio_time);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_quatity;
        private TextView txt_ordername;
        private RadioButton radio_time;



        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);
            this.radio_time = (RadioButton) itemView.findViewById(R.id.radio_time);
          //  this.txt_ordername = (TextView) itemView.findViewById(R.id.txt_ordername);
         //   this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList,genericViewHolder.radio_time);

                }
            });
        }
    }


}

