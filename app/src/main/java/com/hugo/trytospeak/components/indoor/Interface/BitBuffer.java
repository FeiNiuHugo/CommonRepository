package com.hugo.trytospeak.components.indoor.Interface;

import android.graphics.Bitmap;

import com.hugo.trytospeak.components.indoor.Adapter.BitAdapter;
import com.hugo.trytospeak.components.indoor.unit.PathUnit;

import java.util.List;

/**
 * Created by karonl on 16/4/1.
 * 为绘制canvas供应getBitBuffer和路径
 */
public interface BitBuffer {

    List<PathUnit> getPathUnit();

    Bitmap getBitBuffer();

    void setOnAdapterListener(BitAdapter.AttrListener listener);
}
