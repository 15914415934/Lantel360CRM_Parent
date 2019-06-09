package com.lantel.common.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.lantel.common.list.model.MediaModel;
import com.lantel.homelibrary.R;
import com.lantel.homelibrary.app.Config;
import com.xiao360.baselibrary.image.GlideUtils;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class DetailAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<MediaModel> datas;

    public DetailAdapter(Context context, List<MediaModel> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return null == datas ? 0 : datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        if (object instanceof ThumbnailHolder) {
            return view == ((ThumbnailHolder) object).itemView;
        }
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int viewType = getItemViewType(position);
         if (viewType == Config.PHOTO) {
            View v = inflater.inflate(R.layout.item_photo,null);
            PhotoViewHolder vh = new PhotoViewHolder(v);
            container.addView(vh.itemView);
            onBindPhotoViewHolder(vh, position);
            return vh;
        }else if(viewType == Config.VIDEO){

         }
        return null;
    }

    private void onBindPhotoViewHolder(PhotoViewHolder vh, int position) {
        MediaModel photoModel = datas.get(position);
        GlideUtils.loadImageView(context,photoModel.getFile_url(),vh.thumbnail);
    }

    private int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        int viewType = getItemViewType(position);
        if (viewType == Config.PHOTO) {
            if (object instanceof PhotoViewHolder) {
                PhotoViewHolder photoViewHolder = (PhotoViewHolder) object;
                container.removeView(photoViewHolder.itemView);
            }
        }
    }

    private abstract class ThumbnailHolder {
        final View itemView;
        ImageView thumbnail;

        public ThumbnailHolder(@NonNull View itemView) {
            this.itemView = itemView;
        }
    }

    private class PhotoViewHolder extends ThumbnailHolder {
        public PhotoViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.detail_photo);
            ((GestureImageView) thumbnail).getController().getSettings()
                    .setMinZoom(0.05f)
                    .setMaxZoom(5f)
                    .enableGestures()
                    .setPanEnabled(true)
                    .setZoomEnabled(true)
                    .setDoubleTapEnabled(true)
                    .setRotationEnabled(true)
                    .setRestrictRotation(true)
                    .setFillViewport(true);
            thumbnail.setBackgroundColor(context.getResources().getColor(R.color.black));
        }
    }
}
