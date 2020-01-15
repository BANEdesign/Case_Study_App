package com.target.dealbrowserpoc.dealbrowser;

import android.app.AlertDialog;
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
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.container, DealListFragment.newInstance())
                                       .commit();
        }
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, DealListFragment.newInstance())
                                   .commitNowAllowingStateLoss();
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

            getSupportFragmentManager().beginTransaction()
            .add(R.id.container, fragment)
            .commitNowAllowingStateLoss();
    }
}
