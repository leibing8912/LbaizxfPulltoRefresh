package cn.jianke.lbaizxfpulltorefresh.widget.jd;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.jianke.lbaizxfpulltorefresh.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * @className: 仿京东下拉刷新Header
 * @classDescription:
 * @author: leibing
 * @createTime: 2016/12/16
 */
public class JdRefreshHeader extends FrameLayout implements PtrUIHandler {
    // 重置
    public static final int STATE_RESET = -1;

    // 准备刷新
    public static final int STATE_PREPARE = 0;

    // 开始刷新
    public static final int STATE_BEGIN = 1;

    // 结束刷新
    public static final int STATE_FINISH = 2;

    // 右外边距
    public static final int MARGIN_RIGHT = 100;

    // 刷新布局容器
    private RelativeLayout jdRefreshRly;

    // 提醒文本
    private TextView mRemindTv;

    // 快递员logo
    private ImageView mManIv;

    // 商品logo
    private ImageView mGoodsIv;

    // 状态识别
    private int mState;

    // 动画
    private AnimationDrawable mAnimation;

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param context 页面实例
     * @return
     */
    public JdRefreshHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param context 页面实例
     * @param attrs
     * @return
     */
    public JdRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param context 页面实例
     * @param attrs
     * @param defStyleAttr
     * @return
     */
    public JdRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化view
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param mContext 页面实例
     * @return
     */
    private void initView(Context mContext) {
        // 构建布局
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.jd_refresh_header_view, this, false);
        // 初始化刷新布局容器
        jdRefreshRly = (RelativeLayout) view.findViewById(R.id.rly_jd_refresh);
        // 初始化提醒文本
        mRemindTv = (TextView) view.findViewById(R.id.tv_remain);
        // 初始化快递员
        mManIv = (ImageView) view.findViewById(R.id.iv_man);
        // 初始化货物
        mGoodsIv = (ImageView) view.findViewById(R.id.iv_goods);
        // 添加布局到当前控件
        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        // 状态重置
        mState = STATE_RESET;
        // 设置刷新布局容器不可见
        if (jdRefreshRly != null)
            jdRefreshRly.setVisibility(GONE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        // 准备刷新
        mState = STATE_PREPARE;
        // 设置刷新布局容器可见
        if (jdRefreshRly != null)
            jdRefreshRly.setVisibility(VISIBLE);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        // 开始刷新
        mState = STATE_BEGIN;
        // 隐藏商品logo
        mGoodsIv.setVisibility(View.GONE);
        // 开启跑步动画
        mManIv.setBackgroundResource(R.drawable.jd_refresh_header_anim);
        mAnimation = (AnimationDrawable) mManIv.getBackground();
        if (!mAnimation.isRunning()) {
            mAnimation.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        // 结束刷新
        mState = STATE_FINISH;
        // 显示商品Logo
        mGoodsIv.setVisibility(View.VISIBLE);
        // 停止跑步动画
        if (mAnimation.isRunning()) {
            mAnimation.stop();
        }
        // 显示跑步完快递员
        mManIv.setBackgroundResource(R.drawable.a2a);
        // 设置刷新布局容器不可见
        if (jdRefreshRly != null)
            jdRefreshRly.setVisibility(GONE);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        // 处理提醒字体
        switch (mState) {
            case STATE_PREPARE:
                // 准备刷新
                mManIv.setAlpha(ptrIndicator.getCurrentPercent());
                mGoodsIv.setAlpha(ptrIndicator.getCurrentPercent());
                LayoutParams mIvManLayoutParams = (LayoutParams) mManIv.getLayoutParams();
                if (ptrIndicator.getCurrentPercent() <= 1) {
                    mManIv.setScaleX(ptrIndicator.getCurrentPercent());
                    mManIv.setScaleY(ptrIndicator.getCurrentPercent());
                    mGoodsIv.setScaleX(ptrIndicator.getCurrentPercent());
                    mGoodsIv.setScaleY(ptrIndicator.getCurrentPercent());
                    int marginRight = (int) (MARGIN_RIGHT - MARGIN_RIGHT * ptrIndicator.getCurrentPercent());
                    mIvManLayoutParams.setMargins(0, 0, marginRight, 0);
                    mManIv.setLayoutParams(mIvManLayoutParams);
                }
                if (ptrIndicator.getCurrentPercent() < 1.2) {
                    mRemindTv.setText("下拉刷新...");
                } else {
                    mRemindTv.setText("松开刷新...");
                }
                break;
            case STATE_BEGIN:
                // 开始刷新
                mRemindTv.setText("更新中...");
                break;
            case STATE_FINISH:
                // 结束刷新
                mRemindTv.setText("加载完成...");
                break;
        }
    }
}
