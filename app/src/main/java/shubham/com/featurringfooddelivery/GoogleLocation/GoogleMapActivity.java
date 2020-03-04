package shubham.com.featurringfooddelivery.GoogleLocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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
import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AddressComponent;
import com.seatgeek.placesautocomplete.model.AddressComponentType;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.R;

public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    RelativeLayout rll_arrow;
    //RelativeLayout rll_search;
    RelativeLayout rll_next;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    CameraUpdate cameraUpdate = null;
    String Drop_location;
    EditText edt_location;
    double editLatitude;
    double editLongitude;
    LatLng latLngnew;

    //Placeholder
    @BindView(R.id.autocomplete)
    PlacesAutocompleteTextView mAutocomplete;

    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findview();

        //Drop_location= edt_location.getText().toString();

        ButterKnife.bind(this);

        mAutocomplete.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                mAutocomplete.getDetailsFor(place, new DetailsCallback() {
                    @Override
                    public void onSuccess(final PlaceDetails details) {

                        Drop_location = details.name.toString();

                        Log.d("test", "success " + details);

                        Geocoder coder = new Geocoder(GoogleMapActivity.this);
                        List<Address> address;

                        try {
                            address = coder.getFromLocationName(Drop_location, 5);

                            Address location = address.get(0);
                            editLatitude = location.getLatitude();
                            editLongitude = location.getLongitude();
                            System.out.println("editLatitude : " + editLatitude);
                            System.out.println("editLongitude : " + editLongitude);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        latLngnew = new LatLng(editLatitude, editLongitude);

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngnew));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngnew,15));



                        mMap.addMarker(new MarkerOptions()
                                .position(latLngnew)
                                .title("second pin")
                                .icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.marker)));

                        Toast.makeText(GoogleMapActivity.this, ""+details.name, Toast.LENGTH_SHORT).show();

                        hideKeyboard(GoogleMapActivity.this);
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


        final LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //Toast.makeText(this, "GPS is disable!", Toast.LENGTH_LONG).show();
        }

        if (ActivityCompat.checkSelfPermission(GoogleMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GoogleMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            mMap.setMyLocationEnabled(true);
            Location loc = mMap.getMyLocation();
            if (loc != null) {
                LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
                cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                mMap.animateCamera(cameraUpdate);


            }
        }
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
        mMap.setOnMapLongClickListener(this);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        LatLng latLng = new LatLng(22.7180544,75.9005184);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(1));

        Toast.makeText(this, "asdfsadfds", Toast.LENGTH_SHORT).show();
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
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;


        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
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
}
