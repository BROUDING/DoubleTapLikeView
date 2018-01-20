package com.brouding.doubletaplikeview.extra;

import android.util.Log;

import com.brouding.doubletaplikeview.DoubleTapLikeView.OnTapListener;

public class SingleTapThread extends Thread {
    private long pressedTime;
    private int PRESS_TIME_GAP;
    private boolean isDoubleTapped;
    private OnTapListener mListener;

    public SingleTapThread(long pressedTime, int pressTimeGap, boolean isDoubleTapped, OnTapListener listener) {
        super();

        this.pressedTime    = pressedTime;
        this.PRESS_TIME_GAP = pressTimeGap;
        this.isDoubleTapped = isDoubleTapped;
        this.mListener      = listener;
    }

    @Override
    public void run() {
        while( !isInterrupted() ) {
            if( pressedTime +PRESS_TIME_GAP <= System.currentTimeMillis() ) {
                if( !isDoubleTapped ) {
                    // Due to Thread, if you want to change UI through "onTap()", you should use Activity.runOnUiThread()
                    if( mListener==null )
                        Log.e("onTap" , "setOnTapListener is missing");
                    else
                        mListener.onTap();
                    interrupt();
                }
            }
        }
    }
}
