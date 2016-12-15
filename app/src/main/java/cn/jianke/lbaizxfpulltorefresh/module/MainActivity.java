package cn.jianke.lbaizxfpulltorefresh.module;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import cn.jianke.lbaizxfpulltorefresh.R;
import cn.jianke.lbaizxfpulltorefresh.widget.JkPullToRefreshScrollView;
import cn.jianke.lbaizxfpulltorefresh.widget.ObservableScrollView;

/**
 * @className: MainActivity
 * @classDescription: 测试首页
 * @author: leibing
 * @createTime: 2016/12/15
 */
public class MainActivity extends Activity implements ObservableScrollView.ScrollViewListener{
    // 下拉刷新滑动布局实例
    private JkPullToRefreshScrollView pullUpSv;
    // 自定义Handler
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findView
        pullUpSv = (JkPullToRefreshScrollView) findViewById(R.id.sv_pull_up);
        // 设置居中
        pullUpSv.setGravity(Gravity.CENTER);
        // 设置下拉刷新方式
        pullUpSv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        // 设置滑动监听
        pullUpSv.getRefreshableView().setScrollViewListener(this);
        // 设置下拉事件监听
        pullUpSv.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener<ObservableScrollView>() {
            @Override
            public void onPullEvent(PullToRefreshBase<ObservableScrollView> refreshView,
                                    PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
                if (state == PullToRefreshBase.State.PULL_TO_REFRESH) {
                    // 刷新中
                } else if (state == PullToRefreshBase.State.RESET) {
                    // 刷新完毕
                }
            }
        });
        // 设置刷新事件
        pullUpSv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ObservableScrollView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ObservableScrollView> refreshView) {
                // 刷新事件
                if (mHandler != null){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 刷新完毕
                            if (pullUpSv != null)
                                pullUpSv.onRefreshComplete();
                        }
                    }, 3000);
                }
            }
        });
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

    }

    @Override
    public void onScrollToBottom() {
        Toast.makeText(MainActivity.this, "已经滑动到底部！",Toast.LENGTH_SHORT).show();
    }
}
