package cn.jianke.lbaizxfpulltorefresh.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.handmark.pulltorefresh.library.OverscrollHelper;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

/**
 * @className: JKPullToRefreshScrollView
 * @classDescription: 健客网上药店首页下拉刷新滑动布局
 * @author: leibing
 * @createTime: 2016/12/15
 */
public class JkPullToRefreshScrollView extends PullToRefreshBase<ObservableScrollView> {

    public JkPullToRefreshScrollView(Context context) {
        super(context);
    }

    public JkPullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JkPullToRefreshScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    public JkPullToRefreshScrollView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        // 横直滑动
        return Orientation.VERTICAL;
    }

    @Override
    protected LoadingLayout createLoadingLayout(Context context, Mode mode, TypedArray attrs) {
        // 触发下拉刷新操作
        if(mode == Mode.PULL_FROM_START){
            LoadingLayout layout =
                    new TweenAnimLoadingLayout(context,mode,
                            getPullToRefreshScrollDirection(),attrs);
            layout.setVisibility(View.INVISIBLE);
            return layout;
        }else{
            return super.createLoadingLayout(context, mode, attrs);
        }
    }

    @Override
    protected ObservableScrollView createRefreshableView(Context context, AttributeSet attrs) {
        ObservableScrollView scrollView;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            scrollView = new InternalScrollViewSDK9(context, attrs);
        } else {
            scrollView = new ObservableScrollView(context, attrs);
        }

        scrollView.setId(com.handmark.pulltorefresh.library.R.id.scrollview);
        return scrollView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        View scrollViewChild = mRefreshableView.getChildAt(0);
        if (null != scrollViewChild) {
            return mRefreshableView.getScrollY() >= (scrollViewChild.getHeight() - getHeight());
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return mRefreshableView.getScrollY() == 0;
    }

    @TargetApi(9)
    final class InternalScrollViewSDK9 extends ObservableScrollView {

        public InternalScrollViewSDK9(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                       int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                       int maxOverScrollY, boolean isTouchEvent) {
            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                    scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

            // Does all of the hard work...
            OverscrollHelper.overScrollBy(JkPullToRefreshScrollView.this, deltaX, scrollX, deltaY, scrollY,
                    getScrollRange(), isTouchEvent);

            return returnValue;
        }

        // Taken from the AOSP ScrollView source
        private int getScrollRange() {
            int scrollRange = 0;
            if (getChildCount() > 0) {
                View child = getChildAt(0);
                scrollRange = Math.max(0, child.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
            }
            return scrollRange;
        }
    }
}
