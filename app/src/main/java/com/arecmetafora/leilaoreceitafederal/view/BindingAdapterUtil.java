package com.arecmetafora.leilaoreceitafederal.view;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arecmetafora.leilaoreceitafederal.BR;

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
}
