package cn.jianke.lbaizxfpulltorefresh.module;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import cn.jianke.lbaizxfpulltorefresh.R;
import cn.jianke.lbaizxfpulltorefresh.widget.jd.JdRefreshLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @className: JdActivity
 * @classDescription: 仿京东页面
 * @author: leibing
 * @createTime: 2016/12/16
 */
public class JdActivity extends Activity {
    // 下拉刷新
    private JdRefreshLayout mPullUpJdly;
    // 自定义Handler
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd);
        // findView
        mPullUpJdly = (JdRefreshLayout) findViewById(R.id.jdly_pull_up);
        // 设置刷新监听
        mPullUpJdly.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // 刷新操作
                if (mHandler != null)
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 刷新完毕
                            if (mPullUpJdly != null)
                                mPullUpJdly.refreshComplete();
                        }
                    }, 3000);
            }
        });
    }
}
