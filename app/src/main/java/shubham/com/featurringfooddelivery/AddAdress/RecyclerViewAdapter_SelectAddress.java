package shubham.com.featurringfooddelivery.AddAdress;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.featurringfooddelivery.AddAdress.ApiModel.DeleteAddressModel;
import shubham.com.featurringfooddelivery.AddAdress.ApiModel.NewAddressDataModel;
import shubham.com.featurringfooddelivery.AddAdress.ApiModel.NewAddressModel;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class RecyclerViewAdapter_SelectAddress extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IApiResponse{

    int pos = 0;
    private Context mContext;
    private ArrayList<NewAddressDataModel> modelList;
    private OnItemClickListener mItemClickListener;

    boolean  isDefault =  true;


    public RecyclerViewAdapter_SelectAddress(Context context, ArrayList<NewAddressDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<NewAddressDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_add_address, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final NewAddressDataModel model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;
            pos = position;

            if(isDefault)
            {

                for (int i = 0; i < modelList.size(); i++) {
                    modelList.get(i).setSelected(false);
                }
                modelList.get(position).setSelected(true);
                isDefault =  false;
                genericViewHolder.img_right.setImageResource(R.drawable.location_red_icon);
                genericViewHolder.img_delete.setImageResource(R.drawable.remove_icon_red);
                genericViewHolder.img_edit.setImageResource(R.drawable.edit_icon_red);

                genericViewHolder.txt_name.setTextColor(Color. RED);
                genericViewHolder.txt_city.setTextColor(Color.RED);
                genericViewHolder.address.setTextColor(Color.RED);
                genericViewHolder.address_apartment.setTextColor(Color.RED);
                genericViewHolder.address_state.setTextColor(Color.RED);

                Preference.save(mContext,Preference.KEY_ZipCode,model.getZipcode());

                Preference.save(mContext,Preference.KEY_Address,model.getAddressDetails());
                Preference.save(mContext,Preference.KEY_Address_Id,model.getAddressId());

                String address=Preference.get(mContext,Preference.KEY_Address);
                String Zipcode=Preference.get(mContext,Preference.KEY_ZipCode);
              //  HomeBottomActivity.txt_title_address.setText(Zipcode+","+address);
                HomeBottomActivity.txt_title_address.setText(address);

            }else if( model.getSelected()){

                genericViewHolder.img_right.setImageResource(R.drawable.location_red_icon);
                genericViewHolder.img_delete.setImageResource(R.drawable.remove_icon_red);
                genericViewHolder.img_edit.setImageResource(R.drawable.edit_icon_red);

                genericViewHolder.txt_name.setTextColor(Color.RED);
                genericViewHolder.txt_city.setTextColor(Color.RED);
                genericViewHolder.address.setTextColor(Color.RED);
                genericViewHolder.address_apartment.setTextColor(Color.RED);
                genericViewHolder.address_state.setTextColor(Color.RED);

                Preference.save(mContext,Preference.KEY_ZipCode,model.getZipcode());

                Preference.save(mContext,Preference.KEY_Address,model.getAddressDetails());
                Preference.save(mContext,Preference.KEY_Address_Id,model.getAddressId());

                String address=Preference.get(mContext,Preference.KEY_Address);
                String Zipcode=Preference.get(mContext,Preference.KEY_ZipCode);
            //    HomeBottomActivity.txt_title_address.setText(Zipcode+","+address);
                HomeBottomActivity.txt_title_address.setText(address);
            }else
            {
                genericViewHolder.img_right.setImageResource(R.drawable.location_black_icon);
                genericViewHolder.img_delete.setImageResource(R.drawable.remove_icon);
                genericViewHolder.img_edit.setImageResource(R.drawable.edit_icon_black);

                genericViewHolder.txt_name.setTextColor(Color. BLACK);
                genericViewHolder.txt_city.setTextColor(Color.BLACK);
                genericViewHolder.address.setTextColor(Color.BLACK);
                genericViewHolder.address_apartment.setTextColor(Color.BLACK);
                genericViewHolder.address_state.setTextColor(Color.BLACK);

            }

            genericViewHolder.address.setText(model.getAddressDetails());
            genericViewHolder.address_apartment.setText(model.getApartment());
            genericViewHolder.address_state.setText(model.getState());
            genericViewHolder.txt_name.setText( model.getZipcode() );
            genericViewHolder.txt_city.setText( model.getCity() );

            genericViewHolder.img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(mContext,EditAddress.class)
                            .putExtra("AddressDretails",model.getAddressDetails())
                            .putExtra("ZipCode",model.getZipcode())
                            .putExtra("City",model.getCity())
                            .putExtra("state",model.getState())
                            .putExtra("Apartment",model.getApartment())
                            .putExtra("Address_id",model.getAddressId());
                         mContext.startActivity(intent);
                }
            });

            genericViewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Button cancel_btn,logout_btn;
                    final Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setCancelable(false);
                    dialog.show();
                    dialog.setContentView(R.layout.delete_dialog);
                    cancel_btn=dialog.findViewById(R.id.dialog_cancel_button);
                    logout_btn=dialog.findViewById(R.id.exit_button);
                    cancel_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                        }
                    });
                    logout_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            modelList.remove(position);
                            notifyDataSetChanged();

                            removeAddress(model.getAddressId());

                            Toast.makeText(mContext, "Delete this item", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                }
            });

   /*  genericViewHolder.RR_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show();

                    Preference.save(mContext,Preference.KEY_Address,model.getAddressDetails());

                    HomeBottomActivity.txt_title_address.setText(model.getAddressDetails());

                    Toast.makeText(mContext, ""+model.getAddressDetails(), Toast.LENGTH_SHORT).show();

                }
            });*/

         /*   genericViewHolder.txt_name.setText( model.getFirstName()+" "+model.getLastname() );
            genericViewHolder.address.setText( model.getAddressDetails() );
            genericViewHolder.pincode.setText( model.getPincode() );
            genericViewHolder.mobile.setText( model.getMobile() );

            final String UserId = Preference.get( mContext,Preference.KEY_UserId);

            genericViewHolder.dialog_button_edit.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, DeliveriAddressActivity.class)
                    .putExtra("AddressType","update_adress")
                    .putExtra("FirstName",model.getFirstName())
                    .putExtra("LastName",model.getLastname())
                    .putExtra("pincode",model.getPincode())
                    .putExtra("mobile",model.getMobile())
                    .putExtra("address_details",model.getAddressDetails())
                    .putExtra("Lnadmark",model.getLandMark())
                    .putExtra("address_Id",model.getAddressId());
                    mContext.startActivity(i);
                }
            } );

            genericViewHolder.remove_button.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Button cancel_btn,logout_btn;
                    final Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
                    dialog.setCancelable(false);
                    dialog.show();
                    dialog.setContentView(R.layout.reomve_address );
                    cancel_btn=dialog.findViewById(R.id.dialog_cancel_button);
                    logout_btn=dialog.findViewById(R.id.exit_button);
                    cancel_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();

                        }
                    });
                    logout_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            modelList.remove(position);

                            removeAddress(UserId,model.getAddressId());

                            notifyDataSetChanged();

                            dialog.dismiss();
                        }
                    });

                }
            } );*/
        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

   public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private NewAddressDataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, NewAddressDataModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView txt_name;
        private TextView address;
        private TextView address_apartment;
        private TextView address_state;
        private TextView txt_city;
        private TextView mobile;
        public ImageView img_right;
        private RelativeLayout RR_img;
        private Button dialog_button_edit;
        private Button remove_button;
        ImageView img_edit;
        ImageView img_delete;


        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);
           /* this.dialog_button_edit = (Button) itemView.findViewById(R.id.dialog_button_edit);
            this.remove_button = (Button) itemView.findViewById(R.id.remove_button);*/
            this.img_right = (ImageView) itemView.findViewById(R.id.img_right);
            this.RR_img = (RelativeLayout) itemView.findViewById(R.id.RR_img);
           this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
            this.txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            this.txt_city = (TextView) itemView.findViewById(R.id.txt_city);
            this.address = (TextView) itemView.findViewById(R.id.address);
            this.address_apartment = (TextView) itemView.findViewById(R.id.address_apartment);
            this.address_state = (TextView) itemView.findViewById(R.id.address_state);
            this.img_edit = (ImageView) itemView.findViewById(R.id.img_edit);
            this.img_delete = (ImageView) itemView.findViewById(R.id.img_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < modelList.size(); i++) {
                        modelList.get(i).setSelected(false);
                    }
                    modelList.get(getAdapterPosition()).setSelected(true);
                    notifyDataSetChanged();
                 // mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }

  public void removeAddress(String Address_id){

      String User_Id = Preference.get(mContext,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",User_Id);

        map.put("Address_id",Address_id);

        ApiRequest apiRequest = new ApiRequest(mContext,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_DeleteAddress, Constants.USER_DeleteAddress,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_DeleteAddress.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                DeleteAddressModel finalArray = new Gson().fromJson(response,new TypeToken<DeleteAddressModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                  Toast.makeText(mContext, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }else
                {

                  Toast.makeText(mContext, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(mContext, "Please Enter Check..", Toast.LENGTH_SHORT).show();
    }
}

