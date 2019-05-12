package com.my.sb_test.Util;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public class BindingAdapters {

    @BindingAdapter({"childViews"})
    public static void bindChildViews(View parentView, List<View> childViews) {
        if (childViews != null) {
            if (parentView instanceof GridLayout) {
                GridLayout gridLayout = (GridLayout) parentView;
                gridLayout.removeAllViews();
                for (View view : childViews) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    if (parent != null) {
                        parent.removeView(view);
                    }
                    gridLayout.addView(view);
                }
            }
        }
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void bindImageUrl(SimpleDraweeView simpleDraweeView,
                                    String imageUrl) {
        if (imageUrl != null) {
            Uri uri = Uri.parse(imageUrl);
            simpleDraweeView.setImageURI(uri);
        }
    }

}
