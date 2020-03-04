package shubham.com.featurringfooddelivery.YoutubeVideoPlay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerWidget;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty.ExpandedControlsActivity;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty.MediaData;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty.ViewcountModel;


public class YouVideoPlayerActivity extends AppCompatActivity implements IApiResponse {

    private ImageView img_back;
    private TextView text_toolbar;
    private RelativeLayout rr_more;
    private RelativeLayout rr_videoview;
    LinearLayout ll_video_next;
    private TextView skip_time;
    private TextView skip_ad;
    private TextView more_info;
    ImageView ad_show;
    View view;
    TextView txt_ads;
    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;
    ProgressBar progressbar;
    // Insert your Video URL
    // String VideoURL = "https://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    String VideoURL;
    ArrayList<String> adList = new ArrayList<>();
    String media_id;
    MediaController mediacontroller;
    CountDownTimer countDownTimer;
    long timeleft;
    String moreInfo;
    MxVideoPlayerWidget mVideoPlayerWidget ;
    private SensorManager mSensorManager;
    private MxVideoPlayer.MxAutoFullscreenListener mSensorEventListener;
    String vimeoUrl = "";
    /*TV url :
    http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8
    Advertisement url:
    http://sstv.ca/
    Radio url:
    http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8
    Movie url:
    https://www.youtube.com/watch?v=sRlZ4HsVP1w*/

    public static final String API_KEY = "AIzaSyBxQDVDyvYuQK4qONK2KJowSCB4115SIJo";
    public static String youtubeId ="";
    String adUrl=null;
    String img_icon=null;
    String adName;
    String channelTitel;
    ImageView imgNext;
    ImageView previous_video_play;
    ImageView next_video_play;
    ArrayList<HomeNewResult> videolList = new ArrayList<>();
    int pos = 0;
    int type;
    WebView webview,webview_ad;
    //mxPlayer

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mx_player);
        img_back = (ImageView) findViewById(R.id.img_back);
        ad_show = (ImageView) findViewById(R.id.ad_show);
        txt_ads = (TextView) findViewById(R.id.txt_ads);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        text_toolbar = (TextView) findViewById(R.id.text_toolbar);
        skip_ad = (TextView) findViewById(R.id.skip_ad);
        skip_time = (TextView) findViewById(R.id.skip_time);
        more_info = (TextView) findViewById(R.id.more_info);
        rr_more = (RelativeLayout) findViewById(R.id.rr_more1);
        rr_videoview = (RelativeLayout) findViewById(R.id.rr_videoview);
        ll_video_next = (LinearLayout) findViewById(R.id.ll_video_next);
         webview = (WebView) findViewById(R.id.webview);
        imgNext = (ImageView) findViewById(R.id.ImgNext);
        previous_video_play = (ImageView) findViewById(R.id.previous_video_play);
        next_video_play = (ImageView) findViewById(R.id.next_video_play);


       // HomeFragment obj =new HomeFragment();

     //   videolList = HomeFragment.objFavouriteInterface.getVideoLinksList();

        videoview=(VideoView)findViewById(R.id.VideoView1);
        progressbar=(ProgressBar)findViewById(R.id.progressbar);
        ProgressBar mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
        mVideoPlayerWidget  = (MxVideoPlayerWidget) findViewById(R.id.mpw_video_player);
        progressbar.setVisibility(View.VISIBLE);

        text_toolbar.setText("");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new MxVideoPlayer.MxAutoFullscreenListener();
         type = Preference.getInt(YouVideoPlayerActivity.this, Preference.TYPE);
        //adList=getIntent().getStringArrayListExtra("ad");
        if( getIntent().getExtras() != null) {
            VideoURL = getIntent().getExtras().getString("url");
            media_id = getIntent().getExtras().getString("media_id");
            moreInfo = getIntent().getExtras().getString("moreInfo");
            img_icon = getIntent().getExtras().getString("img");
            adName = getIntent().getExtras().getString("adName");
            channelTitel = getIntent().getExtras().getString("channelTitel");
            pos = getIntent().getIntExtra("pos",0);
        }else{
            Toast.makeText(this, "Unable to load channel.", Toast.LENGTH_SHORT).show();
        }
        vimeoUrl = getIntent().getExtras().getString("adUrl");
        if (vimeoUrl!= null){
            String VimeoReplacedString = vimeoUrl.replace("https://vimeo.com/", "https://player.vimeo.com/video/");
            vimeoUrl = VimeoReplacedString;
            Vimeo(vimeoUrl);

        }else {
            mxPlayer();
        }



    /*   for (int i = 0; i <videolList.size(); i++) {
            String youtubeId = getYouTubeId(videolList.get(pos).getLink());
            if (youtubeId.equalsIgnoreCase(null))
            {

            }
            else {

            }
        }*/
        int size = videolList.size();
        final int totalSize=size-1;
        next_video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalSize > pos ) {
                    for (int i = pos; i <totalSize ; i++) {
                        pos = pos + 1;
                        String youtubeId = getYouTubeId(videolList.get(pos).getLink());
                        if (youtubeId.equalsIgnoreCase("error")){
                            String nexturl = videolList.get(pos).getLink();
                            mVideoPlayerWidget.autoStartPlay(nexturl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                           /* System.out.println("nexturl = " + nexturl);
                            mVideoPlayerWidget.autoStartPlay(nexturl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");*/
                        }
                        break;

                    }
                }
            }
        });
        previous_video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos>0) {
                    for (int i = pos; i >0 ; i--) {
                        pos = pos - 1;
                        String youtubeId = getYouTubeId(videolList.get(pos).getLink());
                        if (youtubeId.equalsIgnoreCase("error")){

                            String nexturl = videolList.get(pos).getLink();
                            mVideoPlayerWidget.autoStartPlay(nexturl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                           /* System.out.println("nexturl = " + nexturl);
                            mVideoPlayerWidget.autoStartPlay(nexturl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");*/
                            mVideoPlayerWidget.autoStartPlay(nexturl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                        }
                        break;
                    }
                }
            }
        });

        skip_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mxPlayer();
            }
        });
        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                videoview.pause();
                showPopUp( view);

               /* final Dialog dialog = new Dialog(YouVideoPlayerActivity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.activity_vimeo);
                dialog.show();
                dialog.setCancelable(false);
                Button paid_btn=(Button) dialog.findViewById(R.id.paid_btn);
                webview_ad = (WebView) dialog.findViewById(R.id.webview1);
                webview_ad.getSettings().setJavaScriptEnabled(true);
                webview_ad.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webview_ad.loadUrl(moreInfo);
                ImageView cancel_ad = (ImageView) dialog.findViewById(R.id.cancel_ad);
                cancel_ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        CountDown(timeleft);
                        videoview.start();

                    }
                });*/
               /* Intent in=new Intent(YouVideoPlayerActivity.this, VimeoActivity.class);
                in.putExtra("moreInfo",moreInfo);
                startActivity(in);*/



                 /*WebView webview = (WebView) findViewById(R.id.webview);
                webview.setVisibility(View.VISIBLE);
                text_toolbar.setVisibility(View.VISIBLE);
                rr_more.setVisibility(View.GONE);
                videoview.setVisibility(View.GONE);
                webview.setWebViewClient(new AppWebViewClient());
                webview.getSettings().setJavaScriptEnabled(true);
                webview.getSettings().setUseWideViewPort(true);
                webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                webview.getSettings().setPluginState(WebSettings.PluginState.ON);
                webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
                webview.setWebChromeClient(new WebChromeClient());
                //   webview.loadUrl("http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8");
                //  webview.loadUrl("http://www.youtube.com/embed/" + "sRlZ4HsVP1w" + "?autoplay=1&vq=small");
                webview.loadUrl(moreInfo);*/
            }
        });
    }
    public void showPopUp(View view) {
 /*       final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        //this is custom dialog
        //custom_popup_dialog contains textview only
        View customView = layoutInflater.inflate(R.layout.ad_dialog, null);
        builder.setCancelable(false);
        // reference the textview of custom_popup_dialog
        WebView webview_ad = (WebView) customView.findViewById(R.id.webview1);
        webview_ad.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        webview_ad.loadUrl(moreInfo);
        ImageView cancel_ad = (ImageView) customView.findViewById(R.id.cancel_ads);
        cancel_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoview.start();
                CountDown(timeleft);
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //this textview is from the adapter
        builder.setView(customView);
        builder.create();
        builder.show();*/
        AlertDialog.Builder dialog = new AlertDialog.Builder(YouVideoPlayerActivity.this);
        //Create a custom layout for the dialog box
        LayoutInflater inflater = (LayoutInflater)YouVideoPlayerActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.ad_dialog, null);

        WebView webview_ad = (WebView) layout.findViewById(R.id.webview1);
        final ProgressBar ads_progressbar = (ProgressBar) layout.findViewById(R.id.ads_progressbar);
        ImageView cancel_ad = (ImageView) layout.findViewById(R.id.cancel_ads);
        dialog.setView(layout);

        final AlertDialog alertDialog = dialog.create();
        alertDialog.setCancelable(false);
        // reference the textview of custom_popup_dialog

        webview_ad.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                ads_progressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ads_progressbar.setVisibility(View.GONE);
            }
        });

        webview_ad.loadUrl(moreInfo);

        cancel_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
                alertDialog.dismiss();
                videoview.start();
                CountDown(timeleft);
            }
        });
        alertDialog.show();


    }
    private void mxPlayer(){
        if (VideoPlayerActivityYoutube.casty.isConnected()){
            CastVideo(VideoURL,channelTitel,img_icon);
            videoview.setVisibility(View.GONE);
            rr_more.setVisibility(View.GONE);
            ad_show.setVisibility(View.GONE);
            txt_ads.setVisibility(View.GONE);
            Intent intent =new Intent(YouVideoPlayerActivity.this, ExpandedControlsActivity.class);
            startActivity(intent);
            finish();
        }
        else if (type ==3){
            videoview.setVisibility(View.GONE);
            rr_more.setVisibility(View.GONE);
            ad_show.setVisibility(View.GONE);
            txt_ads.setVisibility(View.GONE);
            mVideoPlayerWidget.setVisibility(View.GONE);
            webview.setVisibility(View.VISIBLE);
            webview.setWebViewClient(new AppWebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setUseWideViewPort(true);
            webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webview.getSettings().setPluginState(WebSettings.PluginState.ON);
           // webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
            webview.setWebChromeClient(new WebChromeClient());
            //   webview.loadUrl("http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8");
            //  webview.loadUrl("http://www.youtube.com/embed/" + "sRlZ4HsVP1w" + "?autoplay=1&vq=small");
            webview.loadUrl(VideoURL);
        }

        else if(type!=3) {

            if (videoview.getVisibility() == View.VISIBLE){
                videoview.setVisibility(View.GONE);
                rr_more.setVisibility(View.GONE);
                ad_show.setVisibility(View.GONE);
                txt_ads.setVisibility(View.GONE);
            }

        /*videoview.setVisibility(View.GONE);
        rr_more.setVisibility(View.GONE);*/


            mVideoPlayerWidget.setVisibility(View.VISIBLE);
            ll_video_next.setVisibility(View.VISIBLE);
            mVideoPlayerWidget .autoStartPlay(VideoURL, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");



            //mVideoPlayerWidget .autoStartPlay(String.valueOf(Uri.parse(VideoURL)),MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
            progressbar.setVisibility(View.GONE);
            mVideoPlayerWidget.mFullscreenButton.performClick();



            if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_STATE_OFF) {
                System.out.println("Arpit : "+"SCREEN_STATE_OFF");
                ll_video_next.setVisibility(View.VISIBLE);
            } else  if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_STATE_ON) {
                System.out.println("Arpit : "+"SCREEN_STATE_ON");

            } else  if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_LAYOUT_LIST) {
                System.out.println("Arpit : "+"SCREEN_LAYOUT_LIST");
            } else  if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_LAYOUT_NORMAL) {
                System.out.println("Arpit : "+"SCREEN_LAYOUT_NORMAL");
            } else if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_WINDOW_FULLSCREEN) {
                System.out.println("Arpit : "+"SCREEN_WINDOW_FULLSCREEN");
            } else  if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_WINDOW_TINY) {
                System.out.println("Arpit : "+"SCREEN_WINDOW_TINY");
            }  if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.FULL_SCREEN_NORMAL_DELAY) {
                System.out.println("Arpit : "+"FULL_SCREEN_NORMAL_DELAY");
            } else  if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.FULLSCREEN_ORIENTATION) {
                System.out.println("Arpit : "+"FULLSCREEN_ORIENTATION");
            } else if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SYSTEM_UI_FLAG_FULLSCREEN) {
                System.out.println("Arpit : "+"SYSTEM_UI_FLAG_FULLSCREEN");
            }else if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN) {
                System.out.println("Arpit : "+"SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN");
            }else if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.CLICK_QUIT_FULLSCREEN_TIME) {
                System.out.println("Arpit : "+"CLICK_QUIT_FULLSCREEN_TIME");
            }

            if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_WINDOW_FULLSCREEN) {
                ll_video_next.setVisibility(View.VISIBLE);
            } else if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_LAYOUT_LIST ||
                    mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_LAYOUT_NORMAL) {
                ll_video_next.setVisibility(View.VISIBLE);
            }
            else if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_WINDOW_TINY) {
              //  ll_video_next.setVisibility(View.GONE);
            }

            if (mVideoPlayerWidget.mCurrentScreen == mVideoPlayerWidget.SCREEN_STATE_OFF) {
                System.out.println("Arpit : "+"SCREEN_STATE_OFF");
                ll_video_next.setVisibility(View.VISIBLE);

            }



        }



            /*mediacontroller = new FullScreenMediaController(VideoPlayerActivity.this);
            mediacontroller.setAnchorView(videoview);

            //specify the location of media file
            // videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
            videoview.setVideoURI(Uri.parse(VideoURL));
            //Setting MediaController and URI, then starting the videoView
            // videoview.setMediaController(mediacontroller);
            videoview.setMediaController(mediacontroller);
            videoview.requestFocus();
            videoview.start();
            progressbar.setVisibility(View.GONE);
            // CountDown();
            videoview.setOnCompletionListener ( new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    WebView();
                }
            });


            String fullScreen =  getIntent().getStringExtra("fullScreenInd");
            if("y".equals(fullScreen)){

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            }
            else {

               setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }*/

        /* String vUrl = null;

         *//* videoview.setVisibility(View.GONE);
            rr_more.setVisibility(View.GONE);
            text_toolbar.setVisibility(View.VISIBLE);

            WebView webview = (WebView) findViewById(R.id.webview);
            webview.setVisibility(View.VISIBLE);
            webview.setWebViewClient(new AppWebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setUseWideViewPort(true);
            webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webview.getSettings().setPluginState(WebSettings.PluginState.ON);
            webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
            webview.setWebChromeClient(new WebChromeClient());
            //   webview.loadUrl("http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8");
            //  webview.loadUrl("http://www.youtube.com/embed/" + "sRlZ4HsVP1w" + "?autoplay=1&vq=small");
            webview.loadUrl(VideoURL);*//*


           rr_more.setVisibility(View.GONE);
            text_toolbar.setVisibility(View.VISIBLE);
            String fullScreen =  getIntent().getStringExtra("fullScreenInd");
            if("y".equals(fullScreen)){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            else {


                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }


            MediaController mediacontroller = new FullScreenMediaController(VideoPlayerActivity.this);
            mediacontroller.setAnchorView(videoview);

            //specify the location of media file
            // videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
            videoview.setVideoURI(Uri.parse(vUrl));
            //Setting MediaController and URI, then starting the videoView
            videoview.setMediaController(mediacontroller);
            videoview.requestFocus();
            videoview.start();*/



    }
    @Override
    public void onBackPressed() {

        if (VideoPlayerActivityYoutube.casty.isConnected())
        {
            if (videoview.isPlaying()){

            }
        }
        else if (type == 3){
           webview.destroy();
            super.onBackPressed();
        }
        else if(type != 3 ) {
            if ((MxVideoPlayer.backPress())) {
                return;
            }
            super.onBackPressed();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        rr_more.setVisibility(View.GONE);
        videoview.start();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //play video... video is ready to play
                progressbar.setVisibility(View.GONE);
                rr_more.setVisibility(View.VISIBLE);
                CountDown(6000);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (type != 3){
            mSensorManager.unregisterListener(mSensorEventListener);
            MxVideoPlayer.releaseAllVideos();
        }

    }
    private void CountDown(long millisInFuture){
        countDownTimer=  new CountDownTimer(millisInFuture, 1000) {

            public void onTick(long millisUntilFinished) {
                timeleft = millisUntilFinished;
                skip_time.setText("Skip in " + millisUntilFinished/1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                skip_time.setVisibility(View.GONE);
                skip_ad.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if(tag_json_obj.equalsIgnoreCase(Preference.GET_VIMEO)) {
            if(response != null) {
                String videoUrl = null;

                try {
                    JSONObject mainObject = new JSONObject(response);
                    JSONObject requestObj = mainObject.getJSONObject("request");
                    JSONObject filesObj = requestObj.getJSONObject("files");
                    JSONArray mArray = filesObj.getJSONArray("progressive");
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject jsonObject = mArray.getJSONObject(i);
                        videoUrl = jsonObject.getString("url");
                    }
                    if (VideoPlayerActivityYoutube.casty.isConnected()){
                        ad_show.setVisibility(View.VISIBLE);
                        txt_ads.setVisibility(View.VISIBLE);
                        CastVideo(videoUrl,adName,"");

                        mediacontroller = new MediaController(YouVideoPlayerActivity.this);
                        mediacontroller.setAnchorView(videoview);
                        //specify the location of media file
                        // videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
                        videoview.setVisibility(View.VISIBLE);
                        // mVideoPlayerWidget.setVisibility(View.GONE);
                        videoview.setVideoURI(Uri.parse(videoUrl));
                        //Setting MediaController and URI, then starting the videoView
                        // videoview.setMediaController(mediacontroller);
                        videoview.setMediaController(null);
                        videoview.requestFocus();

                        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                //play video... video is ready to play
                                mp.setVolume(0f,0f);
                                videoview.start();



                                progressbar.setVisibility(View.GONE);
                                rr_more.setVisibility(View.VISIBLE);
                                CountDown(6000);
                            }
                        });

                        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mxPlayer();
                                ad_show.setVisibility(View.GONE);
                                txt_ads.setVisibility(View.GONE);
                            }
                        });

                    }
                    else {
                        mediacontroller = new MediaController(YouVideoPlayerActivity.this);
                        mediacontroller.setAnchorView(videoview);
                        //specify the location of media file
                        // videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
                        videoview.setVisibility(View.VISIBLE);
                        // mVideoPlayerWidget.setVisibility(View.GONE);
                        videoview.setVideoURI(Uri.parse(videoUrl));
                        //Setting MediaController and URI, then starting the videoView
                        // videoview.setMediaController(mediacontroller);
                        videoview.setMediaController(null);
                        videoview.requestFocus();

                        String position = String.valueOf(videoview.getCurrentPosition());
                        txt_ads.setVisibility(View.GONE);
                        ad_show.setVisibility(View.GONE);
                        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                //play video... video is ready to play
                                videoview.start();

                                progressbar.setVisibility(View.GONE);
                                rr_more.setVisibility(View.VISIBLE);
                                CountDown(6000);

                            }
                        });

                        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mxPlayer();
                                ad_show.setVisibility(View.GONE);
                                txt_ads.setVisibility(View.GONE);
                            }
                        });
                    }

           /* try {
                // Start the MediaController
                MediaController mediacontroller = new MediaController(
                        VideoPlayerActivity.this);
                mediacontroller.setAnchorView(videoview);
                // Get the URL from String VideoURL
                Uri video = Uri.parse("http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8");
                videoview.setMediaController(mediacontroller);
                videoview.setVideoURI(video);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            videoview.requestFocus();
            videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Close the progress bar and play the video
                public void onPrepared(MediaPlayer mp) {
                    //  pDialog.dismiss();
                    progressbar.setVisibility(View.GONE);

                    videoview.start();
                }
            });*/


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        if(tag_json_obj.equalsIgnoreCase(Preference.ADD_VIEW_COUNT)) {
            if(response != null) {

                ViewcountModel finalArray = new Gson().fromJson(response,new TypeToken<ViewcountModel>(){}.getType());
                if (finalArray.getStatus() == true){

                    int view = finalArray.getViewCount();
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
    public void CastVideo(final String finalVideoUrl, String titel, String img){

        VideoPlayerActivityYoutube.casty.getPlayer().loadMediaAndPlay(createSampleMediaData(finalVideoUrl,titel,img));



    }
    private static MediaData createSampleMediaData(String url, String titel, String thumbImg) {
        return new MediaData.Builder(url)
                .setStreamType(MediaData.STREAM_TYPE_BUFFERED)
                .setContentType("videos/mp4")
                .setMediaType(MediaData.MEDIA_TYPE_MOVIE)
                .setTitle(titel)
                .addPhotoUrl(thumbImg)
                .build();
    } private static MediaData SampleMediaData(String url, String thumbImg) {
        return new MediaData.Builder(url)
                .setStreamType(MediaData.STREAM_TYPE_BUFFERED)
                .setContentType("videos/mp4")
                .setMediaType(MediaData.MEDIA_TYPE_MOVIE)
                .setTitle("Sample title")
                .setSubtitle("Sample subtitle")
                .addPhotoUrl(thumbImg)
                .build();
    }

    public class AppWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);

            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //Page load finished
            super.onPageFinished(view, url);
            progressbar.setVisibility(View.GONE);
        }
    }


    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()){
            vId = matcher.group(1);
        }
        return vId;

    }

    public void VideoTime(String videoFile){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//use one of overloaded setDataSource() functions to set your data source
        retriever.setDataSource(YouVideoPlayerActivity.this, Uri.fromFile(new File(videoFile)));
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInMillisec = Long.parseLong(time );

        retriever.release();
    }
    private void  Vimeo(String mainUrl){

        HashMap<String, String> map = new HashMap<>();
        ApiRequest apiRequest = new ApiRequest(YouVideoPlayerActivity.this,this);
        apiRequest.postRequest(mainUrl+Preference.GET_VIMEO, Preference.GET_VIMEO,map, Request.Method.GET);
    }
    private void  AddViewCount(){

        HashMap<String, String> map = new HashMap<>();
        map.put("media_id",media_id);
        ApiRequest apiRequest = new ApiRequest(YouVideoPlayerActivity.this,this);
        apiRequest.postRequest(Constants.BASE_URL + Preference.ADD_VIEW_COUNT, Preference.ADD_VIEW_COUNT,map, Request.Method.POST);

    }
    private String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }
    public boolean onTouchEvent(MotionEvent me) {
        return true;
    }
}
