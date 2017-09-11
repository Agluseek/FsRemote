package com.agluseek.farsoon.farsoononline.Interface;

import android.view.View;

/**
 * Created by Farsoon on 2017/4/24.
 */

public interface RecyclerViewOnItemClickListener {
    void setOnItemClick(View view, int position, int id);
    void setOnItemLongClick(View view, int position);
}
