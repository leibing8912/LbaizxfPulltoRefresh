package cn.jianke.lbaizxfpulltorefresh.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import cn.jianke.lbaizxfpulltorefresh.R;

/**
 * @className: TweenAnimLoadingLayout
 * @classDescription: Tween动画加载布局
 * @author: leibing
 * @createTime: 2016/12/15
 */
public class TweenAnimLoadingLayout extends LoadingLayout {
    // 名称
    private final TextView mSubHeaderText;
    // 动画
    private AnimationDrawable animationDrawable;

    /**
     * constructor
     * @author leibing
     * @createTime 2016/12/15
     * @lastModify 2016/12/15
     * @param context 上下文
     * @param mode 模式
     * @param scrollDirection 滑动方向
     * @param attrs 样式
     * @return
     */
    public TweenAnimLoadingLayout(Context context, PullToRefreshBase.Mode mode,
                                  PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        // 设置动画
        mHeaderImage.setImageResource(R.drawable.pull_to_refresh_loading);
        animationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
        // 设置动画布局
        FrameLayout mInnerLayout = (FrameLayout) findViewById(com.handmark.pulltorefresh.library.R.id.fl_inner);
        mInnerLayout.setPadding(24, 0 , 24, 0);
        // 设置动画文字提示
        mSubHeaderText = (TextView) mInnerLayout.findViewById(com.handmark.pulltorefresh.library.R.id.pull_to_refresh_subtext);
        mSubHeaderText.setText("正在加载");
    }

    @Override
    protected int getDefaultDrawableResId() {
        // 默认图片
        return R.drawable.refresh_gif0000;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
    }

    @Override
    protected void pullToRefreshImpl() {
        // 下拉以刷新
        mHeaderImage.setVisibility(View.VISIBLE);
        mSubHeaderText.setVisibility(View.GONE);
    }

    @Override
    protected void refreshingImpl() {
        // 正在刷新时回调
        // 播放帧动画
        animationDrawable.start();
        mSubHeaderText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void releaseToRefreshImpl() {
        // 释放以刷新
        mSubHeaderText.setVisibility(View.GONE);
    }

    @Override
    protected void resetImpl() {
        // 重新设置
        mHeaderImage.clearAnimation();
        mHeaderImage.setVisibility(View.GONE);
    }
}
