package com.target.dealbrowserpoc.dealbrowser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * created by bryonnabaines on 2020-01-15
 */
public class DealDetailFragment extends Fragment {

  static final String SALE_PRICE = "sale_price";
  static final String REG_PRICE = "reg_price";
  static final String DESC = "description";
  static final String TITLE = "title";
  static final String IMAGE = "image_url";

  private String[] bundleKeys = {SALE_PRICE, REG_PRICE, DESC, TITLE, IMAGE};
  private String salePrice = "", regPrice = "", description = "", title = "", imageUrl = "";
  private String[] bundleStrings = {salePrice, regPrice, description, title, imageUrl};

  public static DealDetailFragment newInstance(String salePrice, String regPrice, String description,
                                               String title, String image_url) {
    DealDetailFragment fragment = new DealDetailFragment();
    Bundle bundle = new Bundle();
    bundle.putString(SALE_PRICE, salePrice);
    bundle.putString(REG_PRICE, regPrice);
    bundle.putString(DESC, description);
    bundle.putString(TITLE, title);
    bundle.putString(IMAGE, image_url);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    for (String key : bundleKeys) {
      if (getArguments() != null && getArguments().containsKey(key)) {
        switch (key) {
          case SALE_PRICE:
            salePrice = getArguments().getString(key);
            break;
          case REG_PRICE:
            regPrice = getArguments().getString(key);
            break;
          case DESC:
            description = getArguments().getString(key);
            break;
          case TITLE:
            title = getArguments().getString(key);
            break;
          case IMAGE:
            imageUrl = getArguments().getString(key);
            break;
        }
      }
    }
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState
  ) {
    return inflater.inflate(R.layout.deal_detail_fragment, container, false);
  }

  @Override
  public void onViewCreated(
      @NonNull View view,
      @Nullable Bundle savedInstanceState
  ) {
    super.onViewCreated(view, savedInstanceState);
    TextView  salePriceTextView = (TextView) view.findViewById(R.id.detailViewSalePrice);
    TextView  regPriceTextView = (TextView) view.findViewById(R.id.detailViewRegularPrice);
    TextView  titleTextView = (TextView) view.findViewById(R.id.detailViewTitle);
    TextView  descTextView = (TextView) view.findViewById(R.id.detailViewDescription);
    ImageView productImage = (ImageView) view.findViewById(R.id.detailImageView);
    ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

    salePriceTextView.setText(salePrice);
    regPriceTextView.setText(regPrice);
    titleTextView.setText(title);
    descTextView.setText(description);

    if (imageUrl != null && !imageUrl.isEmpty()) {
      progressBar.setVisibility(View.VISIBLE);
      Picasso.with(getContext())
             .load(imageUrl)
             .fit()
             .into(productImage, new Callback() {
               @Override
               public void onSuccess() {
                 progressBar.setVisibility(View.GONE);
               }

               @Override
               public void onError() {
                  // Do nothing
               }
             });
    }
  }
}
