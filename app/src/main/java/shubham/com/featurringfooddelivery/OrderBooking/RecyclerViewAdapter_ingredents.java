package shubham.com.featurringfooddelivery.OrderBooking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.GetCartListDataModel;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class RecyclerViewAdapter_ingredents extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements IApiResponse {

    private Context mContext;
    private ArrayList<GetCartListDataModel> modelList;

    private OnItemClickListener mItemClickListener;

    public static RelativeLayout RR_remove_item;


    public RecyclerViewAdapter_ingredents(Context context, ArrayList<GetCartListDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<GetCartListDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_intgredietns_one, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final GetCartListDataModel model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_quatity.setText(model.getQuantity());
            genericViewHolder.txt_ordername.setText(model.getProductName());
            genericViewHolder.txt_price.setText("$"+model.getPrice());

            RR_remove_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private GetCartListDataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, GetCartListDataModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_quatity;
        private TextView txt_ordername;
        private TextView txt_price;


        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);
            this.txt_quatity = (TextView) itemView.findViewById(R.id.txt_quatity);
            this.txt_ordername = (TextView) itemView.findViewById(R.id.txt_ordername);
            this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);

            RR_remove_item = (RelativeLayout) itemView.findViewById(R.id.RR_remove_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }



  /*  public void getCardMethod(){

        String user_id = Preference.get(getActivity(),Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getCartList, Constants.USER_getCartList,map, Request.Method.POST);
    }
*/


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

}

