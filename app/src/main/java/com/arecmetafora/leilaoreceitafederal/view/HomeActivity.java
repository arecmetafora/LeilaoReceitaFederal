package com.arecmetafora.leilaoreceitafederal.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arecmetafora.leilaoreceitafederal.R;
import com.arecmetafora.leilaoreceitafederal.databinding.ActivityHomeBinding;
import com.arecmetafora.leilaoreceitafederal.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private boolean mHasFilters = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.getData().observe(this, portal -> {
            mHasFilters = portal != null && portal.filters != null && portal.filters.length != 0;
            invalidateOptionsMenu();
        });

        mDrawerLayout = findViewById(R.id.home_filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        if(!mHasFilters) {
            menu.getItem(0).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_filter:
                mDrawerLayout.openDrawer(Gravity.END);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
