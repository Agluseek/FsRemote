package com.agluseek.farsoon.farsoononline.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;

/**
 * Created by Farsoon on 2017/5/4.
 */

public class FS_3400GF_Nylon_detailsActivity extends AppCompatActivity {
    private TextView feature_content;
    private TextView apply_content;
    private TextView title_tv;
    private ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meterial_detail);

        Toolbar bar = (Toolbar) findViewById(R.id.metal_detail_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fs 3400GF");

        iv = (ImageView) findViewById(R.id.meterial_iv_fs3250);
        feature_content = (TextView) findViewById(R.id.feature_content);
        apply_content = (TextView) findViewById(R.id.apply_content);
        title_tv = (TextView) findViewById(R.id.title_tv);

        iv.setImageResource(R.mipmap.fs_3400gf);
        title_tv.setText("耐磨性能好的玻璃微珠复合尼龙材料FS 3400GF");
        feature_content.setText("易于加工，耐磨性能好，热变形温度高，良好的光泽性及优异的尺寸稳定性，表面光滑细腻。");
        apply_content.setText("适合于机械性能要求高的产品，零部件或利用粘结剂制造的大型件、复杂件与塑料模型，以及中小批量快速制造、电动工具、电器控制盒、汽车引擎盖、进气歧管等。");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
