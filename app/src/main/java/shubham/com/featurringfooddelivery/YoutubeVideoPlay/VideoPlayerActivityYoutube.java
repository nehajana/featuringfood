package shubham.com.featurringfooddelivery.YoutubeVideoPlay;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
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
import com.google.android.gms.cast.MediaStatus;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty.Casty;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty.MediaData;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty.ViewcountModel;

public class VideoPlayerActivityYoutube extends YouTubeBaseActivity implements IApiResponse,YouTubePlayer.OnInitializedListener {

    private ImageView img_back;
    private ImageView next_video_play;
    private ImageView previous_video_play;
    private TextView text_toolbar;
    private RelativeLayout rr_more;
    private RelativeLayout rr_videoview;
    private LinearLayout ll_video_next;
    private TextView skip_time;
    private TextView skip_ad;
    private TextView more_info;
    long timeleft;
    View view;
    // Declare variables
    ProgressDialog pDialog;
    CountDownTimer countDownTimer;
    VideoView videoview;
    ProgressBar progressbar;
    public static Casty casty;
    // Insert your Video URL
    // String VideoURL = "https://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    String VideoURL;
    String moreInfo;
    String media_id;
    YouTubePlayerView youTubePlayerView;
    MediaController mediacontroller;
    String vimeoUrl;

    String img_icon=null;
    String adName=null;
    String channelTitel=null;


    public int mIdleReason = MediaStatus.IDLE_REASON_NONE;

    /*TV url :

    http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8

    Advertisement url:

    http://sstv.ca/


    Radio url:

    http://sursagar24.dyndns.tv:8080/sstv/720p/index.m3u8

    Movie url:
    https://www.youtube.com/watch?v=sRlZ4HsVP1w*/

    public static final String API_KEY = "AIzaSyBxQDVDyvYuQK4qONK2KJowSCB4115SIJo";
    private String youtubeId ="";
    //mxPlayer

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        img_back = (ImageView) findViewById(R.id.img_back);
        previous_video_play = (ImageView) findViewById(R.id.previous_video_play);
        next_video_play = (ImageView) findViewById(R.id.next_video_play);
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
        rr_more = (RelativeLayout) findViewById(R.id.rr_more);
        ll_video_next = (LinearLayout) findViewById(R.id.ll_video_next);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);

        rr_videoview = (RelativeLayout) findViewById(R.id.rr_videoview);
        videoview = (VideoView) findViewById(R.id.VideoView);
        text_toolbar.setText("");

        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp){
                // invoke your activity here

                finish();

            }
        });

        progressbar=(ProgressBar)findViewById(R.id.progressbar);
        ProgressBar mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
        progressbar.setVisibility(View.VISIBLE);

 /*       if( getIntent().getExtras() != null) {

            VideoURL = getIntent().getExtras().getString("url");
      *//*      media_id = getIntent().getExtras().getString("media_id");
            moreInfo = getIntent().getExtras().getString("moreInfo");
            img_icon = getIntent().getExtras().getString("img");
            adName = getIntent().getExtras().getString("adName");
            channelTitel = getIntent().getExtras().getString("channelTitel");*//*

        }else{
            Toast.makeText(this, "Unable to load channel.", Toast.LENGTH_SHORT).show();
        }*/

        vimeoUrl = getIntent().getExtras().getString("vimeo");
        VideoURL = getIntent().getExtras().getString("webview");


        if (vimeoUrl!= null && !vimeoUrl.equalsIgnoreCase("")){
            String VimeoReplacedString = vimeoUrl.replace("https://vimeo.com/", "https://player.vimeo.com/video/");
            vimeoUrl = VimeoReplacedString;
            Vimeo(vimeoUrl);
            /*MainActivity.casty.setOnConnectChangeListener(new Casty.OnConnectChangeListener() {
                @Override
                public void onConnected() {
                    Vimeo(vimeoUrl);
                }
                @Override
                public void onDisconnected() {

                }
            });*/
            Vimeo(vimeoUrl);

        }else {
            WebView();
        }

/*        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) videoview.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        skip_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView();
            }
        });
        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countDownTimer.cancel();
                videoview.pause();  /*
                final Dialog dialog = new Dialog(VideoPlayerActivity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.ad_dialog);
                dialog.show();
                dialog.setCancelable(false);
                WebView webview_ad = (WebView) dialog.findViewById(R.id.webview1);
                webview_ad.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                webview_ad.loadUrl(moreInfo);
                *//*WebView webview_ad = (WebView) dialog.findViewById(R.id.webview1);
                webview_ad.getSettings().setJavaScriptEnabled(true);
                webview_ad.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webview_ad.loadUrl(moreInfo);*//*
                ImageView cancel_ad = (ImageView) dialog.findViewById(R.id.cancel_ad);
                cancel_ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        videoview.start();
                        CountDown(timeleft);


                    }
                });
*/
               /* WebView webview = (WebView) findViewById(R.id.webview);
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

                showPopUp(view);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
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
    public void showPopUp(final View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(VideoPlayerActivityYoutube.this);
       //Create a custom layout for the dialog box
        LayoutInflater inflater = (LayoutInflater)VideoPlayerActivityYoutube.this.getSystemService(LAYOUT_INFLATER_SERVICE);
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

    private void WebView(){
        youtubeId = getYouTubeId(VideoURL);
        if (youtubeId != null && !youtubeId.equalsIgnoreCase("") && !youtubeId.equalsIgnoreCase("error")){
            youTubePlayerView.setVisibility(View.VISIBLE);
          //  ll_video_next.setVisibility(View.VISIBLE);
            if (videoview.getVisibility() == View.VISIBLE){
                videoview.setVisibility(View.GONE);
                rr_more.setVisibility(View.GONE);
            }
            //mVideoPlayerWidget.setVisibility(View.GONE);
            text_toolbar.setVisibility(View.VISIBLE);
            youTubePlayerView.initialize(API_KEY, this);
            // youtubeId = getYouTubeId("https://www.youtube.com/watch?v=Hxy8BZGQ5Jo");
            // youtubeId = getYouTubeId(VideoURL);
            progressbar.setVisibility(View.GONE);


                    next_video_play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //HomeFragment.favriteModelList
                            Toast.makeText(VideoPlayerActivityYoutube.this, "next", Toast.LENGTH_SHORT).show();

                        }
                    });
                      previous_video_play.setOnClickListener(new View.OnClickListener() {
                         @Override
                            public void onClick(View v) {
                             Toast.makeText(VideoPlayerActivityYoutube.this, "previous", Toast.LENGTH_SHORT).show();
                    
                }
            });
                                        



        }

        else {

            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

            // mVideoPlayerWidget.mFullscreenButton.performClick();

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

        }

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
    private void CountDown(long millitimeleft){
        countDownTimer=  new CountDownTimer(millitimeleft, 1000) {

            public void onTick(long millisUntilFinished) {
                timeleft=millisUntilFinished;
                skip_time.setText("Skip in " + millisUntilFinished/1000);
                //here you can have your logic to set text to edittext
            }
            public void onFinish() {
                skip_ad.setVisibility(View.VISIBLE);
                skip_time.setVisibility(View.GONE);
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
                   /* if (casty.isConnected()){
                        CastVideo(videoUrl,adName,"");
                    }
                    else {*/
                        mediacontroller = new MediaController(VideoPlayerActivityYoutube.this);
                        mediacontroller.setAnchorView(videoview);
                        //specify the location of media file
                        // videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
                        videoview.setVisibility(View.VISIBLE);
                        // mVideoPlayerWidget.setVisibility(View.GONE);
                        videoview.setVideoURI(Uri.parse(videoUrl));
                        youTubePlayerView.setVisibility(View.GONE);
                        //Setting MediaController and URI, then starting the videoView
                        // videoview.setMediaController(mediacontroller);
                       // videoview.setMediaController(null);
                        videoview.requestFocus();
                        videoview.start();

                        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                //play video... video is ready to play
                                progressbar.setVisibility(View.GONE);
                                //rr_more.setVisibility(View.VISIBLE);
                              //  CountDown(6000);
                            }
                        });
                        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                            /*MainActivity.casty.setOnConnectChangeListener(new Casty.OnConnectChangeListener() {
                                @Override
                                public void onConnected() {
                                    WebView();
                                }
                                @Override
                                public void onDisconnected() {

                                }
                            });*/
                                WebView();
                            }
                        });
                /*    }
*/

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
            else {
                WebView();
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

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(null== player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(youtubeId);
        }
        //player = player;
        player.loadVideo(youtubeId);

        // Hiding player controls
       //  player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        //player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        // Add listeners to YouTubePlayer instance
        player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onAdStarted() { }
            @Override
            public void onError(YouTubePlayer.ErrorReason arg0) { }
            @Override
            public void onLoaded(String arg0) { }
            @Override
            public void onLoading() { }
            @Override
            public void onVideoEnded() {

                finish();
            }
            @Override
            public void onVideoStarted() { }
        });


        player.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onBuffering(boolean arg0) { }
            @Override
            public void onPaused() { }
            @Override
            public void onPlaying() { }
            @Override
            public void onSeekTo(int arg0) { }
            @Override
            public void onStopped() { }
        });
    }
    private void  Vimeo(String vimeoUrl){

        HashMap<String, String> map = new HashMap<>();
        ApiRequest apiRequest = new ApiRequest(VideoPlayerActivityYoutube.this,this);
        apiRequest.postRequest(vimeoUrl+Preference.GET_VIMEO, Preference.GET_VIMEO,map, Request.Method.GET);

    }
    private void  AddViewCount(){

        HashMap<String, String> map = new HashMap<>();
        map.put("media_id",media_id);
        ApiRequest apiRequest = new ApiRequest(VideoPlayerActivityYoutube.this,this);
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
    public void CastVideo(final String finalVideoUrl, String titel, String img){

        casty.getPlayer().loadMediaAndPlay( createSampleMediaData(finalVideoUrl,titel,img));




    }
    private MediaData createSampleMediaData(String url, String titel, String thumbImg) {
        return new MediaData.Builder(url)
                .setStreamType(MediaData.STREAM_TYPE_BUFFERED)
                .setContentType("videos/mp4")
                .setMediaType(MediaData.MEDIA_TYPE_MOVIE)
                .setTitle(titel)
                .addPhotoUrl(thumbImg)
                .build();
    }
}
