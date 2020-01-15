package com.target.dealbrowserpoc.dealbrowser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.target.dealbrowserpoc.dealbrowser.api.Deal;

public class MainActivity extends AppCompatActivity implements DealListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadFragment(DealListFragment.newInstance());
        }
    }

    private void loadFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                                       .addToBackStack(null)
                                       .replace(R.id.container, fragment)
                                       .commit();
        } catch (Exception e ) {

        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Don't create the menu for now
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Deal dealItem) {
        Fragment fragment = DealDetailFragment.newInstance(dealItem.salePrice, dealItem.price, dealItem.description,
            dealItem.title, dealItem.image);
        loadFragment(fragment);
    }
}
