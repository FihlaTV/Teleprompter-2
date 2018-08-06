package com.and.ibrahim.teleprompter.modules.listContents;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;

import com.and.ibrahim.teleprompter.R;
import com.and.ibrahim.teleprompter.base.BaseActivity;
import com.and.ibrahim.teleprompter.data.Contract;
import com.and.ibrahim.teleprompter.callback.FragmentEditListRefreshListener;
import com.and.ibrahim.teleprompter.modules.setting.SettingsActivity;

import java.util.Objects;

import butterknife.BindView;

@SuppressWarnings("ALL")
public class ListContentsActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ListContentsActivity";
    //@BindView(R.id.et_search)
    // protected AppCompatEditText mEtSearch;
    //@BindView(R.id.img_search)
    // protected ImageView mImgSearch;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    @BindView(R.id.list_contents_collapsing_toolbar)
    protected
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private FragmentEditListRefreshListener fragmentEditListRefreshListener;

    private boolean ischecked;


    private Fragment mContentListFragment;

    @BindView(R.id.list_contents_toolbar)
    protected Toolbar mToolbar;
    int mFlag;
    private FragmentEditListRefreshListener getFragmentEditListRefreshListener() {
        return fragmentEditListRefreshListener;
    }
    public void setFragmentEditListRefreshListener(FragmentEditListRefreshListener fragmentEditListRefreshListener) {
        this.fragmentEditListRefreshListener = fragmentEditListRefreshListener;
    }
    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        mContentListFragment = new ListContentsFragment();
        if (savedInstanceState != null) {
            mContentListFragment =  getSupportFragmentManager().getFragment(savedInstanceState, Contract.EXTRA_FRAGMENT);

        }
        mCollapsingToolbarLayout.setTitleEnabled(false);

        setupSearchToolbar();
        // mImgSearch.setOnClickListener(this);
        boolean isVisible = false;
        ischecked=false;
        /*SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
*/

    }



    @Override
    public int getResourceLayout() {
        return R.layout.activity_list_ccontents;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void init() {

        initFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.content_list_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(Objects.requireNonNull(searchManager).getSearchableInfo(getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu);
        return  super.onCreateOptionsMenu(menu);


    }
    private void setupSearchToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            mToolbar.setTitle(getResources().getString(R.string.app_name));

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                // Not implemented here
             break;
            case R.id.action_select:
                ischecked=true;
                if(getFragmentEditListRefreshListener()!=null){
                    getFragmentEditListRefreshListener().onRefresh();
                }
                break;

            case R.id.action_setting:
                Intent intent=(new Intent(ListContentsActivity.this, SettingsActivity.class));
                startActivity(intent);

            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setListener() {

     /*   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // searchFor(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
               // filterSearchFor(query);
                return true;
            }
        });*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, Contract.EXTRA_FRAGMENT, mContentListFragment);

    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
    }


    private void initFragment(){
        Bundle bundle = new Bundle();

        bundle.putString(Contract.EXTRA_TEXT, getResources().getString(R.string.mytest));

        bundle.putBoolean(Contract.EXTRA_SELECTED, false);

        mContentListFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contents_container, mContentListFragment)
                .commit();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public boolean isTablet() {
        return (ListContentsActivity.this.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
