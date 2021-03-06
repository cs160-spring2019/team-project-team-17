package com.example.pantreasy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ValueEventRegistration;

import java.util.ArrayList;
import java.util.List;

public class DonationItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<DonationItem> mDonations;

    public DonationItemAdapter(Context context, List<DonationItem> donationItems) {
        mContext = context;
        ArrayList<DonationItem> l = new ArrayList<>();
        for (int i = 0; i < donationItems.size(); i++) {
            DonationItem item = donationItems.get(i);
            if (item.getAvailableFoodItems().size() > 0)
                l.add(item);
        }
        mDonations = l;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pantry_donate_item, parent, false);
        return new DonationItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DonationItem donationItem = mDonations.get(position);
        ((DonationItemViewHolder) holder).bind(donationItem);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDonations.size();
    }

    class DonationItemViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public ConstraintLayout mDonationItemLayout;
        public TextView mDonorName;
        public ImageView mDonorImage;
        public TextView mTime;
        public TextView mItemList;
        public TextView mDistance;
        public View mItemView;

        public DonationItemViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mDonationItemLayout = itemView.findViewById(R.id.donation_item);
            mDonorName = mDonationItemLayout.findViewById(R.id.donor_name);
            mDonorImage = mDonationItemLayout.findViewById(R.id.donor_image);
            mTime = mDonationItemLayout.findViewById(R.id.pickup_dropoff_time);
            mItemList = mDonationItemLayout.findViewById(R.id.food_items_list);
            mDistance = mDonationItemLayout.findViewById(R.id.distance_text);
        }

        void bind(final DonationItem donationItem) {
            mDonorName.setText(donationItem.profileName);
            mTime.setText(donationItem.time);
            mDistance.setText("0m away");
            mItemList.setText(donationItem.foodListAsString());
            Profile p = ((Pantreasy) mContext.getApplicationContext()).allProfiles.get(donationItem.profileName);
            mDonorImage.setImageBitmap(((Pantreasy) mContext.getApplicationContext()).mAllPictures.get(p.imageName));

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToSecondActivityIntent = new Intent(mContext, PantryViewDonation.class);
                    goToSecondActivityIntent.putExtra("donation_UUID", donationItem.UUID);
                    mContext.startActivity(goToSecondActivityIntent);
                }
            });
        }
    }
}

