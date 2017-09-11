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

public class TPU_X92A_2_Nylon_detailsActivity extends AppCompatActivity {
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
        getSupportActionBar().setTitle("TPU X92A-f2");

        iv = (ImageView) findViewById(R.id.meterial_iv_fs3250);
        feature_content = (TextView) findViewById(R.id.feature_content);
        apply_content = (TextView) findViewById(R.id.apply_content);
        title_tv = (TextView) findViewById(R.id.title_tv);

        iv.setImageResource(R.mipmap.tpu_x92a_2);
        title_tv.setText("弹性优异，高耐磨性的TPU粉末X92A-f2");
        feature_content.setText("具有高耐磨性， 硬度范围广，随着硬度的增加，其产品仍保持良好的弹性，TPU制品的承载能力、抗冲击性及减震性能突出，耐油、耐水、耐霉菌，且加工性能好，操作温度低，易于加工，可重复利用性高。");
        apply_content.setText("弹性材料，可用于小批量及中等批量产品的直接制造，运动装备，如运动鞋，头盔内衬，医疗领域，如假肢，工业应用中弹性功能部件，如软管，密封件，垫圈。");
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
