package com.wzy.bbcnews.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.wzy.bbcnews.R;
import com.wzy.bbcnews.model.BBCNews;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        final ImageView img = (ImageView) holder.getView(R.id.im_bbc);
        TextView title = (TextView) holder.getView(R.id.tv_bbc);
        TextView txt = (TextView) holder.getView(R.id.txt_bbc);
        TextView time = (TextView) holder.getView(R.id.time_bbc);
        TextView url = (TextView) holder.getView(R.id.url_bbc);
        title.setText(relationsEntity.getContent().getShortName());
        txt.setText(relationsEntity.getContent().getSummary());
        url.setText(relationsEntity.getContent().getShareUrl());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = Long.parseLong(relationsEntity.getContent().getLastUpdated());
        String date = sdf.format(new Date(l));
        time.setText(date.toString());
//        Glide.with(img.getContext()).load(relationsEntity.getContent().getRelations().get(0).getContent().getHref())
//                .crossFade()
//                .into(img);
        Glide.with(img.getContext()).load(relationsEntity.getContent().getRelations().get(0).getContent().getHref())
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(img) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(img.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                img.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}
