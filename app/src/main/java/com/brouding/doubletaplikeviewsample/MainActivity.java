package com.brouding.doubletaplikeviewsample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.brouding.doubletaplikeview.DoubleTapLikeView;

/**
 * Created by John on 3/1/18.
 */

public class MainActivity extends AppCompatActivity {
    private Activity thisActivity;
    private DoubleTapLikeView mDoubleTapLikeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisActivity = this;

        // DoubleTapLikeView is an extension of RelativeLayout
        mDoubleTapLikeView = findViewById(R.id.layout_double_tap_like);


        // Possible Methods
        // mDoubleTapLikeView.disableAnimation(false);                              // You wouldn't use this...right?
        // mDoubleTapLikeView.setAdjustViewBounds(true);
        // mDoubleTapLikeView.setImageResource(R.drawable.ic_launcher_background);
        // mDoubleTapLikeView.setIconResource(R.drawable.ic_heart);
        // mDoubleTapLikeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        // mDoubleTapLikeView.setDoubleTapGap(200);                                // Keep this time short ( 200~400 )^

        // Set Image to imageView directly
        // Glide.with(this).load( IMAGE_URL ).into(mDoubleTapLikeView.imageView);

        // So basically, you can use ImageView methods into "mDoubleTapLikeView.imageView"


        mDoubleTapLikeView.setOnTapListener(new DoubleTapLikeView.OnTapListener() {
            @Override
            public void onDoubleTap(View view) {
                // If you want to customize animation, use method below and start your Animation here.
                // mDoubleTapLikeView.disableAnimation(false);

                Toast.makeText(thisActivity, "Double TAPPED !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTap() {
                // This method will be called if user didn't tap again after PRESS_TIME_TERM (default is 200)
                // So keep PRESS_TIME_GAP short ( 200~400 )^.
                // Due to Thread for single Tap, if you want to change UI through "onTap()", you should use Activity.runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(thisActivity, "Single TAPPED !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
