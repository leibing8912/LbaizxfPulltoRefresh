package cn.jianke.lbaizxfpulltorefresh.module;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import cn.jianke.lbaizxfpulltorefresh.R;

/**
 * @className: HomeActivity
 * @classDescription: 首页
 * @author: leibing
 * @createTime: 2016/12/16
 */
public class HomeActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // onClick
        findViewById(R.id.btn_jk).setOnClickListener(this);
        findViewById(R.id.btn_jd).setOnClickListener(this);
        findViewById(R.id.btn_tmall).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_jk:
                // 仿健客页
                turnToNewPage(JkActivity.class);
                break;
            case R.id.btn_jd:
                // 仿京东页
                turnToNewPage(JdActivity.class);
                break;
            case R.id.btn_tmall:
                // 仿天猫页
                turnToNewPage(TmallActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 跳转新页面
     * @author leibing
     * @createTime 2016/12/16
     * @lastModify 2016/12/16
     * @param newCls 页面类名
     * @return
     */
    private void turnToNewPage(Class newCls){
        Intent intent = new Intent();
        intent.setClass(this, newCls);
        startActivity(intent);
    }
}
