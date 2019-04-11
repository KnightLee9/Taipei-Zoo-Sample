package com.knight.taiepizoo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.databinding.ActivityMainBinding;
import com.knight.taiepizoo.ui.arealist.AreaListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        commitFragment(new AreaListFragment(), "", true, null);
    }

    private void commitFragment(Fragment fragment, String name, boolean isRoot, Bundle argument) {
        if (argument != null) {
            fragment.setArguments(argument);
        }
        boolean firstToCommit = getSupportFragmentManager().getFragments() == null ? true : getSupportFragmentManager().getFragments().size() > 0 ? false : true;
        if (isRoot) {
            clearBackstack();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        if (isRoot | firstToCommit) {
            ft.replace(R.id.container, fragment)
                .commitAllowingStateLoss();
        } else {
            ft.replace(R.id.container, fragment)
                .addToBackStack(name)
                .commitAllowingStateLoss();
        }
    }

    private void clearBackstack() {
        if (getSupportFragmentManager().getFragments() == null || getSupportFragmentManager().getBackStackEntryCount() <= 0)
            return;
        FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
            0);
        getSupportFragmentManager().popBackStack(entry.getId(),
            FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showLoading(boolean show) {
        dataBinding.loadingProgressBar.loadingProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
