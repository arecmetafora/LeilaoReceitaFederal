package com.arecmetafora.leilaoreceitafederal.view;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.arecmetafora.leilaoreceitafederal.R;
import com.arecmetafora.leilaoreceitafederal.databinding.ActivityHomeBinding;
import com.arecmetafora.leilaoreceitafederal.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private boolean mHasFilters = false;
    private HomeViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setLifecycleOwner(this);
        binding.setViewModel(mViewModel);

        mViewModel.getData().observe(this, portal -> {
            mHasFilters = portal != null && portal.filters != null && portal.filters.length != 0;
            invalidateOptionsMenu();
        });

        mDrawerLayout = findViewById(R.id.home_filter);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                mViewModel.applyFilters();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        if(!mHasFilters) {
            menu.findItem(R.id.action_filter).setVisible(false);
        }

        // Configuring the search view
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        assert searchManager != null;
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(() -> {
            mViewModel.search(null);
            return false;
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_filter:
                if(mDrawerLayout.isDrawerOpen(Gravity.END)) {
                    mDrawerLayout.closeDrawer(Gravity.END);
                } else {
                    mDrawerLayout.openDrawer(Gravity.END);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
