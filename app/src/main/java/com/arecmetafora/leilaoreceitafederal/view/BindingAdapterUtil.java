package com.arecmetafora.leilaoreceitafederal.view;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arecmetafora.leilaoreceitafederal.BR;
import com.arecmetafora.leilaoreceitafederal.R;
import com.arecmetafora.leilaoreceitafederal.model.to.InFocus;
import com.squareup.picasso.Picasso;

public final class BindingAdapterUtil {

    @BindingAdapter({"entries", "layout", "parent", "viewModel"})
    public static <T, P> void setEntries(ViewGroup viewGroup,
                                         T[] entries, int layoutId, P parent, ViewModel viewModel) {
        viewGroup.removeAllViews();

        if (entries != null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            for (T entry : entries) {
                ViewDataBinding binding = DataBindingUtil
                        .inflate(inflater, layoutId, viewGroup, true);
                binding.setVariable(BR.data, entry);
                binding.setVariable(BR.parent, parent);
                binding.setVariable(BR.viewModel, viewModel);
            }
        }
    }

    @BindingAdapter({"entries", "layout", "viewModel"})
    public static <P, T> void setEntries(ViewGroup viewGroup,
                                      T[] entries, int layoutId, ViewModel viewModel) {
        setEntries(viewGroup, entries, layoutId, null, viewModel);
    }

    @BindingAdapter({"fragmentManager", "entries"})
    public static void setEntries(ViewPager viewPager, FragmentManager fm, InFocus[] entries) {
        if(entries != null) {
            viewPager.setAdapter(new InFocusPagerAdapter(fm, entries));
        } else {
            viewPager.setAdapter(new InFocusPagerAdapter(fm, new InFocus[0]));
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(imageUrl == null || imageUrl.isEmpty()) {
            view.setImageResource(R.drawable.no_image);
        } else {
            imageUrl = view.getContext().getString(R.string.base_url_images) + imageUrl;
            Picasso.get().load(imageUrl)
                    .placeholder(R.drawable.no_image)
                    .into(view);
        }
    }
}
