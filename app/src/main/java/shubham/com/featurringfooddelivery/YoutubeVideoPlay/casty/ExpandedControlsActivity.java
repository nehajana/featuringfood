package shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.media.widget.ExpandedControllerActivity;

import shubham.com.featurringfooddelivery.R;

/**
 * Fullscreen media controls
 */
public class ExpandedControlsActivity extends ExpandedControllerActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.casty_discovery, menu);
        CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.casty_media_route_menu_item);

        Button btn1 = new Button(this);

        btn1.setText("Button_text");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return true;
    }
}
