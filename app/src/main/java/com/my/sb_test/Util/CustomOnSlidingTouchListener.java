package com.my.sb_test.Util;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.synnapps.carouselview.CarouselView;

/**
 * Created by G-Man garethlye on 2019-05-12
 */
public class CustomOnSlidingTouchListener implements CarouselView.OnTouchListener {

    private final GestureDetector gestureDetector;

    public CustomOnSlidingTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private final String TAG = GestureListener.class.getSimpleName();

        private static final int SLIDE_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            onLongClick();
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            try {
                float deltaY = e2.getY() - e1.getY();
                float deltaX = e2.getX() - e1.getX();

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > SLIDE_THRESHOLD) {
                        if (deltaX > 0) {
                            //sliding right gesture
                            return onSlideRight();
                        }
                        else {
                            //sliding left gesture
                            return onSlideLeft();
                        }
                    }
                }
                else {
                    if (Math.abs(deltaY) > SLIDE_THRESHOLD) {
                        if (deltaY > 0) {
                            //sliding down gesture
                            return onSlideDown();
                        }
                        else {
                            //sliding up gesture
                            return onSlideUp();
                        }
                    }
                }
            } catch (Exception exception) {
                Log.e(TAG, exception.getMessage());
            }

            return false;
        }
    }

    public boolean onClick() {
        return false;
    }

    public boolean onLongClick() {
        return false;
    }

    public boolean onSlideRight() {
        return false;
    }

    public boolean onSlideLeft() {
        return false;
    }

    public boolean onSlideUp() {
        return true;
    }

    public boolean onSlideDown() {
        return false;
    }

}
