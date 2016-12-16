package cn.jianke.lbaizxfpulltorefresh.widget.jd;

import android.content.Context;
import android.util.AttributeSet;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @className: JdRefreshLayout
 * @classDescription: 仿京东下拉刷新View
 * @author: leibing
 * @createTime: 2016/12/16
 */
public class JdRefreshLayout extends PtrFrameLayout {
    // HeaderView
    private JdRefreshHeader mHeaderView;

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param context 页面实例
     * @return
     */
    public JdRefreshLayout(Context context) {
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
    public JdRefreshLayout(Context context, AttributeSet attrs) {
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
    public JdRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    /**
     * 初始化view
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param
     * @return
     */
    private void initView(Context mContext) {
        // 初始化Header
        mHeaderView = new JdRefreshHeader(mContext);
        // 设置Header
        setHeaderView(mHeaderView);
        // 添加PtrUIHandler实例
        addPtrUIHandler(mHeaderView);
    }
}
