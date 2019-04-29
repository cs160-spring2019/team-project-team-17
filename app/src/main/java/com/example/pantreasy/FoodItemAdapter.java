package com.example.pantreasy;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<FoodItem> mFoodItems;

    public FoodItemAdapter(Context context, ArrayList<FoodItem> foodItems) {
        mContext = context;
        mFoodItems = foodItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // here, we specify what kind of view each cell should have. In our case, all of them will have a view
        View view = LayoutInflater.from(mContext).inflate(R.layout.food_item, parent, false);
        return new FoodItemViewHolder(view);
    }


    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FoodItem foodItem = mFoodItems.get(position);
        ((FoodItemViewHolder) holder).bind(foodItem);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFoodItems.size();
    }
}

class FoodItemViewHolder extends RecyclerView.ViewHolder {

    // each data item is just a string in this case
    public ConstraintLayout mFoodItemLayout;
    public TextView mFoodNameView;
    public TextView mExpDateView;
    public EditText mQuantityView;

    public FoodItemViewHolder(View itemView) {
        super(itemView);
        mFoodItemLayout = itemView.findViewById(R.id.food_item);
        mFoodNameView = mFoodItemLayout.findViewById(R.id.food_name);
        mExpDateView = mFoodItemLayout.findViewById(R.id.expiration_date);
        mQuantityView = mFoodItemLayout.findViewById(R.id.quantity_value);
    }

    void bind(FoodItem foodItem) {
        //TODO
        // Below is for reference
//        mUsernameTextView.setText(comment.username);
//        mDateTextView.setText("posted " + comment.elapsedTimeString() + " ago");
//        mCommentTextView.setText(comment.text);
    }
}