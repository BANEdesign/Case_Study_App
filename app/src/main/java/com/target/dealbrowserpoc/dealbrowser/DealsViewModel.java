package com.target.dealbrowserpoc.dealbrowser;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.target.dealbrowserpoc.dealbrowser.api.DealsResponse;
import com.target.dealbrowserpoc.dealbrowser.api.RetrofitInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * created by bryonnabaines on 2020-01-14
 */
public class DealsViewModel extends ViewModel {
  public MutableLiveData<DealsResponse> dealsResponseLiveData = new MutableLiveData<>();

  public void getDeals(RetrofitInterface api) {
    api.getDeals()
       .subscribeOn(Schedulers.io())
       .observeOn(AndroidSchedulers.mainThread())
       .subscribe(new Consumer<DealsResponse>() {
         @Override
         public void accept(DealsResponse dealsResponse) throws Exception {
           dealsResponseLiveData.setValue(dealsResponse);
         }
       });
  }
}
