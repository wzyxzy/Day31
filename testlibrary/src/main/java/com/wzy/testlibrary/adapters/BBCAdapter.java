package com.wzy.testlibrary.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wzy.testlibrary.R;
import com.wzy.testlibrary.model.BBCNews;

import java.util.List;

/**
 * Created by zy on 2016/3/29.
 */
public class BBCAdapter extends TeachBaseAdapter<BBCNews.RelationsEntity> {
    public BBCAdapter(List<BBCNews.RelationsEntity> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
    }

    @Override
    public void bindData(ViewHolder holder, BBCNews.RelationsEntity relationsEntity) {
        ImageView img = (ImageView) holder.getView(R.id.im_bbc);
        TextView txt = (TextView) holder.getView(R.id.tv_bbc);
        txt.setText(relationsEntity.getContent().getShortName());
        Glide.with(img.getContext()).load(relationsEntity.getContent().getRelations().get(0).getContent().getHref()).into(img);
    }
}
