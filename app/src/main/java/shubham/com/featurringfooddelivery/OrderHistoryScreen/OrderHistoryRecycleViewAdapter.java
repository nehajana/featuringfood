package shubham.com.featurringfooddelivery.OrderHistoryScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.PurchesHistoryDataModel;
import shubham.com.featurringfooddelivery.R;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class OrderHistoryRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<PurchesHistoryDataModel> modelList;

    private OnItemClickListener mItemClickListener;


    public OrderHistoryRecycleViewAdapter(Context context, ArrayList<PurchesHistoryDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<PurchesHistoryDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.mycontact_item_recycler_list, viewGroup, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final PurchesHistoryDataModel model = getItem( position );
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_address.setText( model.getAddress() );
            genericViewHolder.txt_order_id_number.setText( model.getItemNumber() );

            float y1 = Float.valueOf(model.getItemPrice());
            DecimalFormat df = new DecimalFormat("#.00");
            y1 = Float.valueOf(df.format(y1));
            genericViewHolder.txt_price.setText( "$"+y1+"0");

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private PurchesHistoryDataModel getItem(int position) {
        return modelList.get( position );
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, PurchesHistoryDataModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private TextView txt_address;
        private TextView txt_order_id_number;
        private TextView txt_price;


        // @BindView(R.id.img_user)
        // ImageView imgUser;
        // @BindView(R.id.item_txt_title)
        // TextView itemTxtTitle;
        // @BindView(R.id.item_txt_message)
        // TextView itemTxtMessage;
        // @BindView(R.id.radio_list)
        // RadioButton itemTxtMessage;
        // @BindView(R.id.check_list)
        // CheckBox itemCheckList;
        public ViewHolder(final View itemView) {
            super( itemView );

            this.txt_name = (TextView) itemView.findViewById( R.id.txt_name );
            this.txt_address = (TextView) itemView.findViewById( R.id.txt_address );
            this.txt_order_id_number = (TextView) itemView.findViewById( R.id.txt_order_id_number );
            this.txt_price = (TextView) itemView.findViewById( R.id.txt_price );



            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick( itemView, getAdapterPosition(), modelList.get( getAdapterPosition() ) );

                }
            } );

        }
    }

}

