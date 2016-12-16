package cn.jianke.lbaizxfpulltorefresh.widget.tmall;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.jianke.lbaizxfpulltorefresh.R;
import cn.jianke.lbaizxfpulltorefresh.common.ImageLoader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * @className: TmallRefreshHeader
 * @classDescription:仿天猫下拉刷新Header
 * @author: leibing
 * @createTime: 2016/12/16
 */
public class TmallRefreshHeader extends FrameLayout implements PtrUIHandler {
    // 重置
    public static final int STATE_RESET = -1;

    // 准备刷新
    public static final int STATE_PREPARE = 0;

    // 开始刷新
    public static final int STATE_BEGIN = 1;

    // 结束刷新
    public static final int STATE_FINISH = 2;

    // 刷新布局容器
    private LinearLayout tmRefreshLy;

    // 提醒文本
    private TextView mRemindTv;

    // 状态识别
    private int mState;

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param context 页面实例
     * @return
     */
    public TmallRefreshHeader(Context context) {
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
    public TmallRefreshHeader(Context context, AttributeSet attrs) {
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
    public TmallRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
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
                R.layout.tmall_refresh_header_view, this, false);
        // 初始化刷新布局容器
        tmRefreshLy = (LinearLayout) view.findViewById(R.id.ly_tm_refresh);
        // 初始化提醒文本
        mRemindTv = (TextView) view.findViewById(R.id.tv_remind);
        // 初始化logo
        ImageView logoIv = (ImageView) view.findViewById(R.id.iv_logo);
        // 设置ImageView ScaleType
        logoIv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        // 加载Logo资源
        ImageLoader.getInstance().load(mContext, logoIv,
                ImageLoader.getDrawableSource(mContext, R.drawable.tm_mui_bike));
        // 添加布局到当前控件
        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        // 状态重置
        mState = STATE_RESET;
        // 设置刷新布局容器不可见
        if (tmRefreshLy != null)
            tmRefreshLy.setVisibility(GONE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        // 准备刷新
        mState = STATE_PREPARE;
        // 设置刷新布局容器不可见
        if (tmRefreshLy != null)
            tmRefreshLy.setVisibility(VISIBLE);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        // 开始刷新
        mState = STATE_BEGIN;
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        // 结束刷新
        mState = STATE_FINISH;
        // 设置刷新布局容器不可见
        if (tmRefreshLy != null)
            tmRefreshLy.setVisibility(GONE);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        // 处理提醒文本
        switch (mState) {
            case STATE_PREPARE:
                if (ptrIndicator.getCurrentPercent() < 1) {
                    mRemindTv.setText("下拉刷新");
                } else {
                    mRemindTv.setText("松开立即刷新");
                }
                break;
            case STATE_BEGIN:
                mRemindTv.setText("正在刷新...");
                break;
            case STATE_FINISH:
                mRemindTv.setText("加载完成...");
                break;
        }
    }
}
