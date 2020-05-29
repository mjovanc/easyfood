package com.example.easyfood.view.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;

import java.text.DecimalFormat;
import java.util.List;


public class BasketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products;
    private Context context;
    private OnRemoveEateryListener onRemoveEateryListener;

    /**
     * Basket Adapter Constructor
     *
     * @param context : Context
     * @param products : List<Product>
     * @param onRemoveEateryListener: OnRemoveEateryListener
     */
    BasketAdapter(Context context, List<Product> products, OnRemoveEateryListener onRemoveEateryListener) {
        this.products = products;
        this.context = context;
        this.onRemoveEateryListener = onRemoveEateryListener;
    }

    /**
     * Sets products
     *
     * @param products : List<Product>
     */
    void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_basket, parent, false);
        return new BasketAdapter.ViewHolder(view, onRemoveEateryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BasketAdapter.ViewHolder)holder).name.setText(products.get(position).getName());
        ((BasketAdapter.ViewHolder)holder).desc.setText(products.get(position).getDescription());
        double price = products.get(position).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("0.#####");
        String result = decimalFormat.format(Double.valueOf(price));

        ((BasketAdapter.ViewHolder)holder).price.setText(result + " :-");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     * Represents a View Holder
     */
    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView desc;
        private TextView price;
        private OnRemoveEateryListener onRemoveEateryListener;

        ViewHolder(@NonNull View itemView, OnRemoveEateryListener onRemoveEateryListener) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.product_price);
            this.onRemoveEateryListener = onRemoveEateryListener;
            Button button = itemView.findViewById(R.id.remove_product_button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        onRemoveEateryListener.onRemoveEateryClick(getAdapterPosition());
        }
    }

    /**
     * Remove Eatery Listener Interface
     */
    public interface OnRemoveEateryListener {
        void onRemoveEateryClick(int position);
    }
}
