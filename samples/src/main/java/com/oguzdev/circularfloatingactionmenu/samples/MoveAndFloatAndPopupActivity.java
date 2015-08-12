package com.oguzdev.circularfloatingactionmenu.samples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jalen_pc.moveviewlibrary.FloatingViewListener;
import com.example.jalen_pc.moveviewlibrary.FloatingViewManager;

public class MoveAndFloatAndPopupActivity extends Activity implements View.OnClickListener, FloatingViewListener {
    private static final String TAG = "MoveAndFloatAndPopupActivity";

    private Button btnShow;
    private FloatingViewManager mFloatViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_and_float_and_popup);
        btnShow = (Button) this.findViewById(R.id.btn_show_floating);
        btnShow.setOnClickListener(this);
    }

    /**
     * 显示悬浮按钮
     */
    private void showFloatView() {
        // 1. 获取屏幕密度
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        // 2. 加载iconView
        ImageView iconView = (ImageView) LayoutInflater.from(this).inflate(R.layout.widget_chathead, null, false);
        iconView.setOnClickListener(this);
        // 3. FloatViewManager配置（监听、删除按钮所用图片、iconView图片、）
        mFloatViewManager = new FloatingViewManager(this, this);
        mFloatViewManager.setFixedTrashIconImage(R.drawable.ic_trash_fixed);
        mFloatViewManager.setActionTrashIconImage(R.drawable.ic_trash_action);
        mFloatViewManager.addViewToWindow(iconView, FloatingViewManager.SHAPE_CIRCLE, (int) (18 * metrics.density));
        //
        // 4. 配置展开菜单
//        initOptionsMenu(iconView, 0, 90);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show_floating:
                showFloatView();
                break;
            case R.id.info_text:
                Toast.makeText(getApplicationContext(), "icon onclick", Toast.LENGTH_SHORT).show();
//                initOptionsMenu(v, 0, 90);
                break;
        }
    }

    @Override
    public void onFinishFloatingView() {
        Toast.makeText(getApplicationContext(), "onFinishFloatingView()被调用", Toast.LENGTH_SHORT).show();
    }

}
