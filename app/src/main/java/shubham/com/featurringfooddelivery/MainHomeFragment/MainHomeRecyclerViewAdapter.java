package shubham.com.featurringfooddelivery.MainHomeFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryDataModelslider;
import shubham.com.featurringfooddelivery.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class MainHomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<CategoryList> modelList;

    private OnItemClickListener mItemClickListener;


    public MainHomeRecyclerViewAdapter(Context context, ArrayList<CategoryList> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<CategoryList> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_main_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final CategoryList model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            //genericViewHolder.imgUser.setImageResource(model.getImg());

            if(model.getProduct_image() != null) {
                Picasso.with(mContext).load(model.getProduct_image())
                        .placeholder(R.drawable.no_image_icon)
                        .into(genericViewHolder.ImgCat);
            }else{
                Picasso.with(mContext).load(R.drawable.no_image_icon)
                        .placeholder(R.drawable.no_image_icon)
                        .into(genericViewHolder.ImgCat);
            }
            //genericViewHolder.txt_priceSymbol.setText("$"+model.getPrice());
            genericViewHolder.txt_title.setText(model.getCategoryName());
            //genericViewHolder.txt_description.setText(model.getDescription());

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private CategoryList getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, CategoryList model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ImgCat;
        private TextView txt_title;
        private TextView txt_priceSymbol;
        private TextView txt_description;


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
            super(itemView);

            // ButterKnife.bind(this, itemView);

         this.ImgCat = (ImageView) itemView.findViewById(R.id.ImgCat);
          //this.txt_priceSymbol = (TextView) itemView.findViewById(R.id.txt_priceSymbol);
         this.txt_title = (TextView) itemView.findViewById(R.id.txt_title);
         //this.txt_description = (TextView) itemView.findViewById(R.id.txt_description);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });

        }
    }

}

