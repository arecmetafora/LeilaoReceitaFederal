package com.arecmetafora.leilaoreceitafederal.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arecmetafora.leilaoreceitafederal.R;
import com.arecmetafora.leilaoreceitafederal.databinding.FragmentInFocusBinding;
import com.arecmetafora.leilaoreceitafederal.model.to.InFocus;

public class InFocusFragment extends Fragment {

    private static final String ARG_IN_FOCUS_INFO = "IN_FOCUS_INFO";

    private OnFragmentInteractionListener mListener;

    public InFocusFragment() {
    }

    public static InFocusFragment newInstance(InFocus inFocus) {
        InFocusFragment fragment = new InFocusFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_IN_FOCUS_INFO, inFocus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assert getArguments() != null;
        InFocus inFocus = (InFocus) getArguments().getSerializable(ARG_IN_FOCUS_INFO);
        FragmentInFocusBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_in_focus, container, false);
        binding.setData(inFocus);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onInFocusSelected(InFocus inFocus);
    }
}
