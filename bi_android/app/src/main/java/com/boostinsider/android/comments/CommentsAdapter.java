package com.boostinsider.android.comments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostinsider.android.R;
import com.boostinsider.android.data.comment.Comment;
import com.boostinsider.android.util.CircleTransform;
import com.boostinsider.android.util.Constants;
import com.boostinsider.android.util.Font;
import com.boostinsider.android.util.ToastUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by qiongchen on 7/6/16.
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private ArrayList<Comment> comments;

    private View mFooterView;

    public CommentsAdapter(Context context) {
        this.context = context;
        comments = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comments, null);
            return new CommentsViewHolder(view);
        } else if (viewType == Constants.TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footerview, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mFooterView = view;
            return new FooterViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + ". Make sure your using types are correct.");
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return Constants.TYPE_FOOTER;
        } else {
            return Constants.TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentsViewHolder) {
            holder.setIsRecyclable(false);

            String avatarStr = comments.get(position).getUserAvatar();
            String nameStr = comments.get(position).getUserName();
            String timeStr = comments.get(position).getCreated();
            String contentStr = comments.get(position).getContent();
            final String imageStr = comments.get(position).getImage();

            if (avatarStr != null && !avatarStr.isEmpty()) {
                Picasso.with(context)
                        .load(avatarStr)
                        .transform(new CircleTransform())
                        .into(((CommentsViewHolder) holder).avatar);
            }

            ((CommentsViewHolder) holder).name.setText(nameStr);
            ((CommentsViewHolder) holder).time.setText(timeStr);

            if (contentStr != null && !contentStr.isEmpty()) {
                ((CommentsViewHolder) holder).content.setText(Html.fromHtml(contentStr));
            } else {
                ((CommentsViewHolder) holder).content.setVisibility(View.GONE);
            }

            if (imageStr != null && !imageStr.isEmpty()) {
                Picasso.with(context)
                        .load(imageStr)
                        .into(((CommentsViewHolder) holder).image);

                ((CommentsViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showImageDialog(imageStr);
                    }
                });
            } else {
                ((CommentsViewHolder) holder).image.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() + 1;
    }

    public void setAdapter(ArrayList<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
        this.notifyDataSetChanged();
    }

    public void updateAdapter(ArrayList<Comment> comments) {
        this.comments.addAll(comments);
        this.notifyItemRangeInserted(this.comments.size(), comments.size());
    }

    public int getPosition() {
        return comments.size();
    }

    public void setFooterVisible(boolean isVisible) {
        if (mFooterView != null) {
            mFooterView.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private class CommentsViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;

        ImageView image;

        TextView name;

        TextView time;

        TextView content;

        public CommentsViewHolder(View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.avatar);

            image = (ImageView) itemView.findViewById(R.id.image);

            name = (TextView) itemView.findViewById(R.id.name);

            time = (TextView) itemView.findViewById(R.id.time);

            content = (TextView) itemView.findViewById(R.id.content);

            setFont(name, time, content);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void setFont(TextView name, TextView time, TextView content) {
        Font.setAveniLig(name, context);

        Font.setOpenSansRegular(time, context);
        Font.setOpenSansRegular(content, context);
    }

    private void showImageDialog(String url) {
        final Dialog dialog=new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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

        Picasso.with(context)
                .load(url)
                .error(R.drawable.placeholder_icon)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageView.setImageBitmap(bitmap);
                        new PhotoViewAttacher(imageView);

                        downloadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast(context, "Image saved");
                                MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null);
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
