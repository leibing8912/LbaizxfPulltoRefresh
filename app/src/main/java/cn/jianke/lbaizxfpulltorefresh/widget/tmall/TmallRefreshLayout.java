package cn.jianke.lbaizxfpulltorefresh.widget.tmall;

import android.content.Context;
import android.util.AttributeSet;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @className: TmallRefreshLayout
 * @classDescription: 仿天猫下拉刷新view
 * @author: leibing
 * @createTime: 2016/12/16
 */

public class TmallRefreshLayout extends PtrFrameLayout {
    // HeaderView
    private TmallRefreshHeader mHeaderView;

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param context 页面实例
     * @return
     */
    public TmallRefreshLayout(Context context) {
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
    public TmallRefreshLayout(Context context, AttributeSet attrs) {
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
     * @param defStyle
     * @return
     */
    public TmallRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        // 初始化Header
        mHeaderView = new TmallRefreshHeader(mContext);
        // 设置Header
        setHeaderView(mHeaderView);
        // 添加PtrUIHandler实例
        addPtrUIHandler(mHeaderView);
    }
}
