package cn.jianke.lbaizxfpulltorefresh.module;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import cn.jianke.lbaizxfpulltorefresh.R;
import cn.jianke.lbaizxfpulltorefresh.widget.tmall.TmallRefreshLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @className: TmallActivity
 * @classDescription:仿天猫页面
 * @author: leibing
 * @createTime: 2016/12/16
 */
public class TmallActivity extends Activity {
    // 下拉刷新
    private TmallRefreshLayout mPullUpTmly;
    // 自定义Handler
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmall);
        // findView
        mPullUpTmly = (TmallRefreshLayout) findViewById(R.id.tmly_pull_up);
        // 设置刷新监听
        mPullUpTmly.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // 刷新操作
                if (mHandler != null)
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 刷新完毕
                            if (mPullUpTmly != null)
                                mPullUpTmly.refreshComplete();
                        }
                    }, 1500);
            }
        });
    }

}
