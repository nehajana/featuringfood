package shubham.com.featurringfooddelivery.DeliveryOrderOne;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.featurringfooddelivery.DeliveryOrderOne.ApiModel.SubHomeModerImage;
import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.GetCartListDataModel;
import shubham.com.featurringfooddelivery.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class RecyclerViewAdapterIngredients extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<SubHomeModerImage> modelList;

    private OnItemClickListener mItemClickListener;


    public RecyclerViewAdapterIngredients(Context context, ArrayList<SubHomeModerImage> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<SubHomeModerImage> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_post_question, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final SubHomeModerImage model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            //genericViewHolder.txt_quatity.setText(model.getQuantity());
            genericViewHolder.moreitem_check_name.setText(model.getIngredientsName());
            genericViewHolder.txt_price.setText("$ "+model.getIngredientsPrice());

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private SubHomeModerImage getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, SubHomeModerImage model , CheckBox moreitem_check_name);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox moreitem_check_name;
        private TextView txt_ordername;
        private TextView txt_price;

        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);
            this.moreitem_check_name = (CheckBox) itemView.findViewById(R.id.moreitem_check_name);
            // this.txt_ordername = (TextView) itemView.findViewById(R.id.txt_ordername);
            this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()), moreitem_check_name);

                }
            });
        }
    }

}

