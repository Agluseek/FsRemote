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

public class FS_CoCrMoW_Metal_detailsActivity extends AppCompatActivity {
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
        getSupportActionBar().setTitle("钴铬合金粉末 FS CoCrMoW");

        iv = (ImageView) findViewById(R.id.meterial_iv_fs3250);
        feature_content = (TextView) findViewById(R.id.feature_content);
        apply_content = (TextView) findViewById(R.id.apply_content);
        title_tv = (TextView) findViewById(R.id.title_tv);

        iv.setImageResource(R.mipmap.fs_cocrmow);
        title_tv.setText("3D打印钴铬合金义齿");
        feature_content.setText("力学性能优异，具有优异的生物相容性，抗腐蚀性强，钴铬合金烤瓷牙经济且耐用，性价比较高。");
        apply_content.setText("主要应用于医用领域、义齿制作等，是最具性价比的医用牙科材料，还可用于发动机部件以及时装、珠宝行业等。" );

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
