package com.target.dealbrowserpoc.dealbrowser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.widget.Toast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.api.Deal;
import java.util.List;


public class DealListItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Deal>     dealItems;
    private Context        context;

    public static DealListItemAdapter newInstance(Context context, List<Deal> items) {
        return new DealListItemAdapter(context, items);
    }

    protected DealListItemAdapter(Context ctx, List<Deal> items) {
        super();
        context = ctx;
        inflater = LayoutInflater.from(context);
        dealItems = items;
    }

    @Override
    public int getCount() {
        return dealItems.size();
    }

    @Override
    public Object getItem(int position){
        try {
            return dealItems.get(position);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null) {
            view = inflater.inflate(R.layout.deal_list_item_v2, parent, false);
            holder = new ViewHolder();
            holder.productImage = (ImageView)view.findViewById(R.id.deal_list_item_image_view);
            holder.title = (TextView)view.findViewById(R.id.deal_list_item_title);
            holder.price = (TextView)view.findViewById(R.id.deal_list_item_price);
            holder.loader = (ProgressBar)view.findViewById(R.id.progress_bar);
            holder.aisleNumber = (TextView)view.findViewById(R.id.deals_list_isle_number);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        Deal dealItem = dealItems.get(position);

        if (dealItem.image != null && !dealItem.image.isEmpty()){
            holder.loader.setVisibility(View.VISIBLE);
            final ProgressBar progressView = holder.loader;
            Picasso.with(context)
                   .load(dealItem.image)
                   .fit()
                   .into(holder.productImage, new Callback() {
                       @Override
                       public void onSuccess() {
                           progressView.setVisibility(View.GONE);
                       }

                       @Override
                       public void onError() {
                           // Do nothing
                       }
                   });
        } else {
            Toast.makeText(context, "The Image Done Goofed", Toast.LENGTH_SHORT).show();
        }

        holder.title.setText(dealItem.title);
        if (dealItem.salePrice != null && !dealItem.salePrice.isEmpty()) {
            holder.price.setText(dealItem.salePrice);
        } else {
            holder.price.setText(dealItem.price);
        }
        holder.aisleNumber.setText(dealItem.aisle.toUpperCase());

        return view;
    }

    private class ViewHolder {
        public ImageView productImage;
        public TextView title, price;
        public ProgressBar loader;
        public TextView aisleNumber;
    }
}
