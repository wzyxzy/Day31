package com.wzy.day31.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.day31.R;
import com.wzy.day31.model.Model;

import java.util.List;

/**
 * Created by zy on 2016/3/28.
 */
public class MyAdapter extends TeachBaseAdapter<Model> {
    public MyAdapter(List<Model> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
    }

    @Override
    public void bindData(ViewHolder holder, Model model) {
        TextView title = (TextView) holder.getView(R.id.image_json);
        ImageView iamge = (ImageView) holder.getView(R.id.text_json);
        title.setText(model.getTitle());
    }
}
