package com.example.startkotlin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    // variable for MultiPointerZoom
    private float mFirstTouchX;
    private float mFirstTouchY;
    private double firstPointerLength;
    private double secondPointerLength;
    // variable for 2FingerDoubleClick
    private static final int TIMEOUT = ViewConfiguration.getDoubleTapTimeout() + 100;
    private long mFirstDownTime = 0;
    private boolean mSeparateTouches = false;
    private byte mTwoFingerTapCount = 0;

    // mGestureDetector for 1FingerDoubleClick
    private GestureDetector mGestureDetector;
    // flag is user action
    private static boolean IS_USER_ACTION = false;

    public static boolean isUserAction() {
        return IS_USER_ACTION;
    }

    public static void setUserAction(boolean isUserAction) {
        IS_USER_ACTION = isUserAction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup gestureListenerGroup = findViewById(R.id.gesture_listen_layout);
        gestureListenerGroup.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, new SimpleOnGestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            v.performClick();
        }
        // 单指双击 https://www.jianshu.com/p/95b3fdc46b0f
        mGestureDetector.onTouchEvent(event);
        // 双指缩放 https://www.jianshu.com/p/b27e04b787c3
        isMultiPointerZoom(event);
        // 双指双击 https://stackoverflow.com/questions/12414680/how-to-implement-a-two-finger-double-click-in-android
        is2FingerDoubleClick(event);
        return true;
    }

    private void is2FingerDoubleClick(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (mFirstDownTime == 0 || event.getEventTime() - mFirstDownTime > TIMEOUT)
                    reset(event.getDownTime());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerCount() == 2)
                    mTwoFingerTapCount++;
                else
                    mFirstDownTime = 0;
                break;
            case MotionEvent.ACTION_UP:
                if (!mSeparateTouches)
                    mSeparateTouches = true;
                else if (mTwoFingerTapCount == 2 && event.getEventTime() - mFirstDownTime < TIMEOUT) {
                    mFirstDownTime = 0;
                    Toast.makeText(MainActivity.this, "双指双击事件", Toast.LENGTH_SHORT).show();
                    IS_USER_ACTION = true;
                }
        }
    }

    private void reset(long time) {
        mFirstDownTime = time;
        mSeparateTouches = false;
        mTwoFingerTapCount = 0;
    }

    // 双指缩放 双指先拖动再进行缩放操作 会认为仍然是双指缩放操作
    private void isMultiPointerZoom(MotionEvent event) {
        float mSecondTouchX;
        float mSecondTouchY;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //获取第一个点（A点）的位置
                mFirstTouchX = event.getX();
                mFirstTouchY = event.getY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getActionIndex() == 1) {
                    //获取第二个点（B点）的位置
                    mSecondTouchX = event.getX(1);
                    mSecondTouchY = event.getY(1);
                    //根据两点的位置获取两个触摸点之间的距离（AB）
                    float firstLengthX = Math.abs(mFirstTouchX - mSecondTouchX);
                    float firstLengthY = Math.abs(mFirstTouchY - mSecondTouchY);
                    firstPointerLength = Math.sqrt(Math.pow(firstLengthX, 2) + Math.pow(firstLengthY, 2));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() >= 2) {
                    //获取第一个点（A‘）的位置
                    float firstX = event.getX(0);
                    float firstY = event.getY(0);

                    //获取第二个点（B‘）的位置
                    float secondX = event.getX(1);
                    float secondY = event.getY(1);

                    //计算两点之间的距离（A'B'）
                    float secondLengthX = Math.abs(firstX - secondX);
                    float secondLengthY = Math.abs(firstY - secondY);
                    secondPointerLength = Math.sqrt(Math.pow(secondLengthX, 2) + Math.pow(secondLengthY, 2));
                }
                break;
            case MotionEvent.ACTION_UP:
                //最后一根手指离开时触发
                double zoomDistance = Math.abs(secondPointerLength - firstPointerLength);
                if (zoomDistance > 10) {
                    IS_USER_ACTION = true;
                    Toast.makeText(MainActivity.this, "缩放距离" + zoomDistance, Toast.LENGTH_SHORT).show();
                }
                mFirstTouchX = 0;
                mFirstTouchY = 0;
                firstPointerLength = 0;
                secondPointerLength = 0;
                break;
        }
    }

    // 单指双击 如果用户手指第二次点击后长时间没有离开屏幕 也会认为是双击事件
    class SimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            IS_USER_ACTION = true;
            Toast.makeText(MainActivity.this, "1 finger double click", Toast.LENGTH_SHORT).show();
            return super.onDoubleTap(e);
        }
    }

    public void jump(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}


