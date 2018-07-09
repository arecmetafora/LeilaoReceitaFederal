package com.arecmetafora.leilaoreceitafederal.view;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arecmetafora.leilaoreceitafederal.BR;

public final class BindingAdapterUtil {

    @BindingAdapter({"entries", "layout"})
    public static <T> void setEntries(ViewGroup viewGroup,
                                      T[] entries, int layoutId) {
        viewGroup.removeAllViews();

        if (entries != null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            for (int i = 0; i < entries.length; i++) {
                T entry = entries[i];
                ViewDataBinding binding = DataBindingUtil
                        .inflate(inflater, layoutId, viewGroup, true);
                binding.setVariable(BR.data, entry);
            }
        }
    }
}
