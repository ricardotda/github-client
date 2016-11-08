package com.app.githubclient.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.githubclient.R;
import com.app.githubclient.fragments.SearchFragment;
import com.app.githubclient.values.Repo;
import com.app.githubclient.values.User;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        SearchFragment.OnListFragmentInteractionListener {

    public static final int DEFAULT_SELECTED_ITEM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(DEFAULT_SELECTED_ITEM).setChecked(true);

        showSearchFragment(SearchFragment.SEARCH_USER);
    }

    private void showSearchFragment(int searchType) {
        SearchFragment searchFragment = SearchFragment.newInstance(searchType);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, searchFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user) {
            showSearchFragment(SearchFragment.SEARCH_USER);
        } else if (id == R.id.nav_repository) {
            showSearchFragment(SearchFragment.SEARCH_REPO);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onUserSelection(User user) {
        Toast.makeText(this, "Uuário: " + user.getUserName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRepoSelection(Repo repo) {
        Toast.makeText(this, "Repositório: " + repo.getName(), Toast.LENGTH_SHORT).show();
    }
}
