package cn.jianke.lbaizxfpulltorefresh.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @className: ObservableScrollView
 * @classDescription: 自定义ScrollView（监听滑动状态）
 * @author: leibing
 * @createTime: 2016/12/15
 */
public class ObservableScrollView extends ScrollView {
    // 监听是否滑动底部检测时间间隔
    private final static int BOTTOM_DETECT_TIME_DIV = 100;
    // 滑动监听
    private ScrollViewListener mScrollViewListener;
    // 监听是否滑动到底部
    private Handler mHandler = new Handler();
    private Runnable mDetectYRunnable = new Runnable() {
        @Override
        public void run() {
            if (getChildAt(0)  != null
                    && getChildAt(0) .getMeasuredHeight() <= getScrollY() + getHeight()) {
                // 滑动到顶部
                if(mScrollViewListener != null){
                    mScrollViewListener.onScrollToBottom();
                }
                if (mHandler != null && mDetectYRunnable != null){
                    mHandler.removeCallbacks(mDetectYRunnable);
                    return;
                }
            }
            // 每过100ms检测一次是否滑动到底部
            if (mHandler != null)
                mHandler.postDelayed(mDetectYRunnable, BOTTOM_DETECT_TIME_DIV);
        }
    };

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置滑动监听
     * @author leibing
     * @createTime 2016/12/15
     * @lastModify 2016/12/15
     * @param mScrollViewListener 滑动监听
     * @return
     */
    public void setScrollViewListener(ScrollViewListener mScrollViewListener) {
        this.mScrollViewListener = mScrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        // 滑动变化监听
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                if (getChildAt(0)  != null && getChildAt(0) .getMeasuredHeight() <= getScrollY() + getHeight()) {
                    // 滑动到底部
                    if(mScrollViewListener != null){
                        mScrollViewListener.onScrollToBottom();
                    }
                }else if(getChildAt(0)  != null && getChildAt(0) .getMeasuredHeight() > getScrollY() + getHeight()){
                    // 检测是否滑动到底部
                    if (mHandler != null){
                        mHandler.post(mDetectYRunnable);
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * @interfaceName: ScrollViewListener
     * @interfaceDescription: 滑动监听
     * @author: leibing
     * @createTime: 2016/12/15
     */
    public interface ScrollViewListener {
        // 滑动变化监听
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
        // 监听是否滑动到底部
        void onScrollToBottom();
    }
}
