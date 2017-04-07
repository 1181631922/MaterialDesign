package com.fanyafeng.materialdesign.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Toast;

import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.util.DpPxConvert;


public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {
    private static final String TAG = "StickyNavLayout";

    private boolean canPullDown = true;


    public boolean isCanPullDown() {
        return canPullDown;
    }

    public void setCanPullDown(boolean canPullDown) {
        this.canPullDown = canPullDown;
        if (!canPullDown && mTopViewHeight != 0) {
            mDeltaY = mTopViewHeight;
            scrollTo(0, mTopViewHeight);
        }
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
    }

    @Override
    public void onStopNestedScroll(View target) {
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        //down - //up+
        if (getScrollY() >= mTopViewHeight) {
            return false;
        }
        fling((int) velocityY);
        return true;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    private View mTop;
    private View mNav;
    private ViewPager mViewPager;

    private int mTopViewHeight;

    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaximumVelocity, mMinimumVelocity;
    private GestureDetector mGestureDetector;

    public StickyNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);

        mScroller = new OverScroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

        mGestureDetector = new GestureDetector(context, new TopViewGestureListener());
        mGestureDetector.setIsLongpressEnabled(false);

        Log.d(TAG, "getTop:" + this.getTop());

    }

    int mLastY = 0;
    int mDeltaY = 0;
    boolean isMoveUp;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.id_stickynavlayout_topview);
        mTop.setOnTouchListener(new OnTouchListener() {
            float y = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y = event.getY();
                        Log.d(TAG, "point MotionEvent.ACTION_DOWN; now y point:" + event.getY());
                        mLastY = (int) event.getRawY();
                        Log.d(TAG, "mLastY:" + mLastY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int currentY = (int) event.getRawY();
//                        Log.d(TAG, "Raw Y:" + event.getRawY());
//                        Log.d(TAG, "point MotionEvent.ACTION_MOVE;now y point:" + event.getY());
                        int deltaY = currentY - mLastY;
                        Log.d(TAG, "deltaY:" + deltaY);
//                        mDeltaY = mDeltaY - deltaY;
                        if (mDeltaY < 0) {
                            mDeltaY = 0;
                        }
                        if (mDeltaY > mTopViewHeight) {
                            mDeltaY = mTopViewHeight;
                        }
                        if (mDeltaY >= 0 && mDeltaY <= mTopViewHeight) {
//                            scrollTo(0, mDeltaY);

                        }

                        Log.d(TAG, "total offset:" + mDeltaY);

//                        mLastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
//                        Log.d(TAG, "point MotionEvent.ACTION_UP; now y point:" + event.getY());
//                        if (event.getY() - y < 0) {
//                            Log.d(TAG, "MotionEvent.ACTION_UP <0");//move up
//                            isMoveUp = true;
//                        } else if (event.getY() - y > 0) {
//                            Log.d(TAG, "MotionEvent.ACTION_UP >=0");//move down
//                            isMoveUp = false;
//                        }
                        break;
                }
                return false;
            }
        });
        mNav = findViewById(R.id.id_stickynavlayout_indicator);
        View view = findViewById(R.id.id_stickynavlayout_viewpager);
        if (!(view instanceof ViewPager)) {
            throw new RuntimeException(
                    "id_stickynavlayout_viewpager show used by ViewPager !");
        }
        mViewPager = (ViewPager) view;
    }

    class TopViewGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent ev) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mDeltaY = (int) (mDeltaY - e2.getY() + e1.getY());
            scrollTo(0, mDeltaY);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "e2.y-e1.y:" + (e2.getY() - e1.getY()));
            if (e2.getY() - e1.getY() == 0 || e2.getY() - e1.getY() < 0) {
                scrollTo(0, mTopViewHeight);
            }
            return true;
        }

        @Override
        public boolean onDown(MotionEvent ev) {
            Log.d(TAG, "ontouch onDown");
            return true;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //不限制顶部的高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = getMeasuredHeight() - mNav.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(), mTop.getMeasuredHeight() + mNav.getMeasuredHeight() + mViewPager.getMeasuredHeight());
        mTopViewHeight = mTop.getMeasuredHeight();//fix bug if version >7.0
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTop.getMeasuredHeight();//topview height
    }


    public void fling(int velocityY) {//when fling to cover headerview
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        Log.d(TAG, "mTopViewHeight:" + mTopViewHeight);


        if (canPullDown) {
            if (y > mTopViewHeight) {
                y = mTopViewHeight;
                mDeltaY = y;
            }
            if (y != getScrollY()) {
                mDeltaY = y;
                super.scrollTo(x, y);
            }
        } else {
            if (y > mTopViewHeight) {
                y = mTopViewHeight;
                mDeltaY = y;
                super.scrollTo(x, y);
            } else {
                y = mTopViewHeight;
                mDeltaY = y;
                super.scrollTo(x, y);
            }
        }
        Log.d(TAG, "scroll total offset:" + mDeltaY);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }


}
