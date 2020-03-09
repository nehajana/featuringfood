package shubham.com.featurringfooddelivery.GoogleLocation;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AddressComponent;
import com.seatgeek.placesautocomplete.model.AddressComponentType;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shubham.com.featurringfooddelivery.AddAdress.AddAdressActivity;
import shubham.com.featurringfooddelivery.AddAdress.ApiModel.AddAddressModel;
import shubham.com.featurringfooddelivery.AddAdress.ApiModel.SelcetedAddressModel;
import shubham.com.featurringfooddelivery.GPSTracker;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapLongClickListener,IApiResponse {

    private GoogleMap mMap;
    RelativeLayout rll_arrow;
    //RelativeLayout rll_search;
    RelativeLayout rll_next;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    CameraUpdate cameraUpdate = null;
    String Drop_location="";
    EditText edt_location;
    double editLatitude;
    double editLongitude;
    LatLng latLngnew;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    //Placeholder
    @BindView(R.id.autocomplete)
    PlacesAutocompleteTextView mAutocomplete;
    public double latitude;
    public double longitude;
    String address;
    GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findview();



        // check if GPS enabled
        gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {
            latitude = gpsTracker.latitude;
            longitude = gpsTracker.longitude;

/*         String country = gpsTracker.getCountryName(this);
            String city = gpsTracker.getLocality(this);
            String postalCode = gpsTracker.getPostalCode(this);
            String addressLine = gpsTracker.getAddressLine(this);*/

        }
        else
        {
            gpsTracker.showSettingsAlert();
        }




        //Drop_location= edt_location.getText().toString();

        ButterKnife.bind(this);

        mAutocomplete.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                mAutocomplete.getDetailsFor(place, new DetailsCallback() {
                    @Override
                    public void onSuccess(final PlaceDetails details) {
                        double lat = details.geometry.location.lat;
                        double lon = details.geometry.location.lng;

                        hideKeyboard(GoogleMapActivity.this);

                        String area = "",city="",zipcode="",country="";

                        Drop_location = details.name.toString();

                        for (AddressComponent component : details.address_components) {
                            for (AddressComponentType type : component.types) {
                                switch (type) {
                                    case STREET_NUMBER:
                                        break;
                                    case ROUTE:
                                        break;
                                    case NEIGHBORHOOD:
                                        break;
                                    case SUBLOCALITY_LEVEL_1:
                                        break;
                                    case SUBLOCALITY:
                                        break;
                                    case LOCALITY:
                                        city=component.long_name.toString();
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_1:
                                        area=component.short_name.toString();
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_2:
                                        break;
                                    case COUNTRY:
                                        country=component.short_name.toString();
                                        break;
                                    case POSTAL_CODE:
                                        zipcode=component.short_name.toString();
                                        break;
                                    case POLITICAL:
                                        break;
                                }
                            }
                        }

                        System.out.println("Address :"+Drop_location+"city :"+city+"Zipcode :"+zipcode+"City :"+country+"area :"+area);

                        AddAddressMethod(Drop_location,city,zipcode,city,area);

                        Preference.save(GoogleMapActivity.this,Preference.KEY_ZipCode,zipcode);


                        if(Drop_location !=null && !Drop_location.equalsIgnoreCase(""))
                        {
                            Preference.save(GoogleMapActivity.this,Preference.KEY_Address,Drop_location);
                            // HomeBottomActivity.txt_title_address.setText(Drop_location);
                        }

                        Log.d("test", "success " + details);
                        Geocoder coder = new Geocoder(GoogleMapActivity.this);
                        List<Address> address;


                        mMap.clear();

                        latLngnew = new LatLng(lat, lon);

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngnew));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngnew,15));

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLngnew);
                        markerOptions.title("Current Position");
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        mCurrLocationMarker = mMap.addMarker(markerOptions);

                    }
                    @Override
                    public void onFailure(final Throwable failure) {
                        Log.d("test", "failure " + failure);
                    }
                });
            }
        });




        rll_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rll_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Drop_location.equalsIgnoreCase(""))
                {
                    Toast.makeText(GoogleMapActivity.this, "Please Enter Location..", Toast.LENGTH_SHORT).show();

                }else {

                    Intent myIntent = new Intent( GoogleMapActivity.this, HomeBottomActivity.class );
                    startActivity( myIntent );
                }

            }
        });

    }

    private void findview()
    {
        rll_arrow=(RelativeLayout) findViewById(R.id.rll_arrow);
        rll_next=(RelativeLayout) findViewById(R.id.rll_next);
        //rll_search=(RelativeLayout) findViewById(R.id.rll_search);
        edt_location=(EditText) findViewById(R.id.edt_location);
        mAutocomplete=(PlacesAutocompleteTextView) findViewById(R.id.autocomplete);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                // mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
                //gpsTracker.showSettingsAlert();
            }
        }
        else {
            buildGoogleApiClient();
            //mMap.setMyLocationEnabled(true);
        }

    }


    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(GoogleMapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
   /*     mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);*/
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {


        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //Place current location marker

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mCurrLocationMarker = mMap.addMarker(markerOptions);


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void AddAddressMethod(String AddressDetails,String city,String Pincode,String apartment,String state){

        String user_id = Preference.get(GoogleMapActivity.this,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);
        map.put("country","india");
        map.put("AddressDetails",AddressDetails);
        map.put("city",city);
        map.put("zipcode",Pincode);
        map.put("apartment",apartment);
        map.put("state",state);

        ApiRequest apiRequest = new ApiRequest(GoogleMapActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_AddAddress, Constants.USER_AddAddress,map, Request.Method.POST);
    }

    public void selectedAddressApi(String Address_id){

        String User_Id = Preference.get(GoogleMapActivity.this,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",User_Id);

        map.put("Address_id",Address_id);

        ApiRequest apiRequest = new ApiRequest(GoogleMapActivity.this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_selectAddress, Constants.USER_selectAddress,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_AddAddress.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                NewAddAddressModel finalArray = new Gson().fromJson(response,new TypeToken<NewAddAddressModel>(){}.getType());

                String status= finalArray.getStatus();

                if (status.equalsIgnoreCase("success")){

                  //  Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    selectedAddressApi(finalArray.getAddressId().toString());


                }else
                {
                    Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }else if (Constants.USER_selectAddress.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                SelcetedAddressModel finalArray = new Gson().fromJson(response,new TypeToken<SelcetedAddressModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                   // Toast.makeText(GoogleMapActivity.this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();



                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network..", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }
}
