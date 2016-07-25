package com.boostinsider.android.campaigndetail;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.boostinsider.android.R;
import com.boostinsider.android.data.campaign.Photo;
import com.boostinsider.android.util.ToastUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by qiongchen on 6/7/16.
 */
public class ImageAdapter extends PagerAdapter {

    private ArrayList<Photo> photos;

    private Context mContext;

    private LayoutInflater mInflater;

    public ImageAdapter(ArrayList<Photo> photos, Context mContext) {
        this.photos = photos;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.viewpager_item_image, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog(position);
            }
        });

        ImageView imageView = (ImageView) view.findViewById(R.id.viewpager_item_image);
        if (photos != null && photos.size() != 0) {
            Picasso.with(mContext)
                    .load(photos.get(position).getImageUrl())
                    .into(imageView);
        }

//        TextView textView = (TextView) view.findViewById(R.id.viewpager_item_indicator);
//        textView.setText((position + 1) + "/" + photos.size());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    private void showImageDialog(int position) {
        final Dialog dialog=new Dialog(mContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.image_dialog);

        ImageButton closeButton = (ImageButton) dialog.findViewById(R.id.image_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final ImageButton downloadButton = (ImageButton) dialog.findViewById(R.id.image_download_button);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.image);
        Picasso.with(mContext)
                .load(photos.get(position).getImageUrl())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageView.setImageBitmap(bitmap);
                        new PhotoViewAttacher(imageView);

                        downloadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast(mContext, "Image saved");
                                MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, null, null);
                            }
                        });
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        dialog.show();
    }
}
