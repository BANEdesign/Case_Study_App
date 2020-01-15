package com.target.dealbrowserpoc.dealbrowser;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.target.dealbrowserpoc.dealbrowser.api.Deal;
import com.target.dealbrowserpoc.dealbrowser.api.DealsResponse;
import com.target.dealbrowserpoc.dealbrowser.api.RetrofitInterface;
import com.target.dealbrowserpoc.dealbrowser.deals.DealContent;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DealListFragment extends ListFragment implements
    DealListItemAdapter.OnItemClickListener {

    private      OnFragmentInteractionListener mListener;
    private      RetrofitInterface             retrofitInterface;
    private      Retrofit                      retrofit;
    private      DealsViewModel                viewModel;
    private      List<Deal>                    dealsList;
    static final String                        BASE_URL = "http://target-deals.herokuapp.com/api/";
    private      Context                       context;

    private Observer dealsObserver = new Observer<DealsResponse>() {
        @Override
        public void onChanged(
            @Nullable DealsResponse dealsResponse
            ) {
            if (dealsResponse != null) {
                dealsList = dealsResponse.data;
                setupAdapter();
            } else {
                new AlertDialog.Builder(getContext())
                    .setMessage(R.string.error)
                    .show();
            }
        }
    };

    public static DealListFragment newInstance() { return new DealListFragment(); }

    public DealListFragment() {
    }

    @Override
    public void onItemClick(Deal dealItem) {
        Fragment fragment = DealDetailFragment.newInstance(dealItem.salePrice, dealItem.price, dealItem.description,
            dealItem.title, dealItem.image);
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction()
                                .add(R.id.container, fragment)
                                .commitNowAllowingStateLoss();
        }
    }

    public void setupAdapter() {
        setListAdapter(new DealListItemAdapter(getActivity(), dealsList, this));
    }

    public void setupNetwork() {

        Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNetwork();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        viewModel = ViewModelProviders.of(this).get(DealsViewModel.class);
        viewModel.dealsResponseLiveData.observe(this, dealsObserver);
        viewModel.getDeals(retrofitInterface);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            mListener.onFragmentInteraction(DealContent.ITEMS.get(position).id);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}