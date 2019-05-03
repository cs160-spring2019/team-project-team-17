package com.example.pantreasy;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter {
    private FirebaseManager mFirebaseManager;
    private Context mContext;
    private ArrayList<FoodItem> mFoodItems;
    private OnSuccessListener mSuccessListener;
    private List<Bitmap> bitmaps;

    public FoodItemAdapter(Context context, ArrayList<FoodItem> foodItems, List<Bitmap> bitmapList) {
        mContext = context;
        mFoodItems = foodItems;
        mFirebaseManager = new FirebaseManager((DonorAddActivity)mContext);
        this.bitmaps = bitmapList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // here, we specify what kind of view each cell should have. In our case, all of them will have a view
        View view = LayoutInflater.from(mContext).inflate(R.layout.donor_food_item, parent, false);
        return new FoodItemViewHolder(view);
    }


    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FoodItem foodItem = mFoodItems.get(position);
        Bitmap bm = bitmaps.get(position);
        ((FoodItemViewHolder) holder).bind(foodItem, bm);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFoodItems.size();
    }

    public void removeAt(int position) {
        mFoodItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mFoodItems.size());
    }

    class FoodItemViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public ConstraintLayout mFoodItemLayout;
        public TextView mFoodNameView;
        public TextView mExpDateView;
        public TextView mQuantityView;
        public TextView mPerishableView;
        public ImageView mFoodImage;
        public Button mDeleteButton;

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            mFoodItemLayout = itemView.findViewById(R.id.food_item);
            mFoodNameView = mFoodItemLayout.findViewById(R.id.food_name);
            mExpDateView = mFoodItemLayout.findViewById(R.id.expiration_text);
            mQuantityView = mFoodItemLayout.findViewById(R.id.quantity_text);
            mPerishableView = mFoodItemLayout.findViewById(R.id.perishable_text);
            mFoodImage = mFoodItemLayout.findViewById(R.id.food_image);
            mDeleteButton = mFoodItemLayout.findViewById(R.id.x_button);

            mDeleteButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeAt(getAdapterPosition());
                        }
                    }
            );

        }

        void bind(FoodItem foodItem, Bitmap bm) {
            mFoodImage.setClipToOutline(true);
            mFoodImage.setImageBitmap(bm);
            mFoodNameView.setText(foodItem.name);
            mExpDateView.setText(foodItem.expirationDate);
            mQuantityView.setText(foodItem.quantity);
            if (foodItem.perishable)
                mPerishableView.setText("Perishable");
            else
                mPerishableView.setText("Non-Perishable");
        }
    }
}

