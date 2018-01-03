package com.brouding.doubletaplikeview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by John on 30/12/17.
 */

public class DoubleTapLikeView extends RelativeLayout implements View.OnClickListener {
    public  ImageView imageView, iconView;
    private OnTapListener mListener=null;

    private int  PRESS_TIME_GAP=200;
    private long pressedTime;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
    private boolean disableAnimation=false, isDoubleTapped=false;

    public DoubleTapLikeView(Context context) {
        super(context);

        initView(context, null);
    }

    public DoubleTapLikeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public DoubleTapLikeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.setOnClickListener(this);

        int NO_IMAGE=-1, imageId=NO_IMAGE, iconId=NO_IMAGE;
        boolean adjustViewBounds=true;

        int attrsCount = attrs.getAttributeCount();
        for(int i=0 ; i<attrsCount ; i++) {
            if( attrs.getAttributeName(i).equals("src") ) {
                imageId = attrs.getAttributeResourceValue(i, 0);
            }

            if( attrs.getAttributeName(i).equals("icon") ) {
                iconId = attrs.getAttributeResourceValue(i, 0);
            }

            if( attrs.getAttributeName(i).equals("disableAnimation") ) {
                disableAnimation = attrs.getAttributeBooleanValue(i, false);
            }

            if( attrs.getAttributeName(i).equals("doubleTapIn") ) {
                PRESS_TIME_GAP = attrs.getAttributeIntValue(i, 0);
            }

            if( attrs.getAttributeName(i).equals("adjustViewBounds") ) {
                adjustViewBounds = attrs.getAttributeBooleanValue(i, true);
            }

            if( attrs.getAttributeName(i).equals("scaleType") ) {
                mScaleType = getScaleType( attrs.getAttributeValue(i) );
            }
        }

        // Image view
        LayoutParams imageViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        imageView = new ImageView(context);
        imageView.setLayoutParams(imageViewParams);
        if( imageId!=NO_IMAGE )
            imageView.setImageResource(imageId);
        imageView.setScaleType(mScaleType);
        imageView.setAdjustViewBounds(adjustViewBounds);
        this.addView(imageView);

        // Icon view
        LayoutParams iconViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        iconViewParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        iconView = new ImageView(context);
        iconView.setLayoutParams(iconViewParams);
        if( iconId!=NO_IMAGE )
            iconView.setImageResource(iconId);
        iconView.setVisibility(View.INVISIBLE);
        iconView.setTag("iconView");
        this.addView(iconView);
    }

    public DoubleTapLikeView setImageResource(@DrawableRes int resId) {
        imageView.setImageResource(resId);
        return this;
    }

    public DoubleTapLikeView setIconResource(@DrawableRes int resId) {
        iconView.setImageResource(resId);
        return this;
    }

    public DoubleTapLikeView disableAnimation(boolean disable) {
        disableAnimation = disable;
        return this;
    }

    public DoubleTapLikeView setDoubleTapGap(int milliSecond) {
        PRESS_TIME_GAP = milliSecond;
        return this;
    }

    public DoubleTapLikeView setScaleType(ImageView.ScaleType scaleType) {
        imageView.setScaleType(scaleType);
        return this;
    }

    public DoubleTapLikeView setAdjustViewBounds(boolean set) {
        imageView.setAdjustViewBounds(set);
        return this;
    }

    public DoubleTapLikeView setOnTapListener(OnTapListener listener) {
        this.mListener = listener;
        return this;
    }

    private void animateIcon(final ImageView view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 0.95f, 0.0f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation( 0.5f, 1.0f );
        AnimationSet firstSet = new AnimationSet(false);
        firstSet.addAnimation(scaleAnimation);
        firstSet.addAnimation(alphaAnimation);
        firstSet.setDuration(180);
        firstSet.setFillAfter(true);
        view.startAnimation(firstSet);

        firstSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.95f, 0.8f, 0.95f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(220);
                view.startAnimation(scaleAnimation);

                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ScaleAnimation shivalAnimation = new ScaleAnimation(0.8f, 0.84f, 0.8f, 0.84f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shivalAnimation.setDuration(100);
                        shivalAnimation.setFillAfter(true);
                        view.startAnimation(shivalAnimation);

                        shivalAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {}
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                ScaleAnimation scaleAnimation = new ScaleAnimation(0.84f, 0.0f, 0.84f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                                AlphaAnimation alphaAnimation = new AlphaAnimation( 1.0f, 0.0f );

                                AnimationSet secondAnim = new AnimationSet(true);
                                secondAnim.addAnimation(scaleAnimation);
                                secondAnim.addAnimation(alphaAnimation);
                                secondAnim.setDuration(150);
                                secondAnim.setFillAfter(true);
                                secondAnim.setStartOffset(180);
                                view.startAnimation(secondAnim);
                            }
                            @Override
                            public void onAnimationRepeat(Animation animation) {}
                        });
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    final Thread singleTapThread = new Thread() {
        @Override
        public void run() {
            while( !isInterrupted() ) {
                if( pressedTime +PRESS_TIME_GAP <= System.currentTimeMillis() ) {
                    if( !isDoubleTapped ) {
                        // Due to Thread, if you want to change UI through "onTap()", you should use Activity.runOnUiThread()
                        mListener.onTap();
                        interrupt();
                    }
                }
            }
        }
    };

    @Override
    public void onClick(final View view) {
        isDoubleTapped = false;
        if (pressedTime +PRESS_TIME_GAP > System.currentTimeMillis()) {
            isDoubleTapped = true;
            if( singleTapThread.isAlive() )
                singleTapThread.interrupt();

            // heart Image animation
            if( !disableAnimation )
                animateIcon( (ImageView) view.findViewWithTag("iconView") );

            if( mListener==null )
                Log.e("onDoubleTap" , "setOnDoubleTapClickListener is missing");
            else
                mListener.onDoubleTap(view);
        }
        pressedTime = System.currentTimeMillis();

        if( !isDoubleTapped && !singleTapThread.isAlive() )
            singleTapThread.start();
    }

    private ImageView.ScaleType getScaleType(String scaleType) {
        switch (scaleType) {
            case "center":
                return ImageView.ScaleType.CENTER;
            case "centerCrop":
                return ImageView.ScaleType.CENTER_CROP;
            case "centerInside":
                return ImageView.ScaleType.CENTER_INSIDE;
            case "fitCenter":
            default:
                return ImageView.ScaleType.FIT_CENTER;
            case "fitEnd":
                return ImageView.ScaleType.FIT_END;
            case "fitStart":
                return ImageView.ScaleType.FIT_START;
            case "fitXY":
                return ImageView.ScaleType.FIT_XY;
            case "matrix":
                return ImageView.ScaleType.MATRIX;
        }
    }

    public interface OnTapListener {
        void onDoubleTap(View view);
        void onTap();
    }
}
