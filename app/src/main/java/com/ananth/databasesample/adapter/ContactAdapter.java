package com.ananth.databasesample.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ananth.databasesample.R;
import com.ananth.databasesample.model.ContactModel;
import com.ananth.databasesample.utils.Utils;
import com.ananth.databasesample.view.ContactDetailsActivity;

import java.util.List;

/**
 * Created by Babu on 10/4/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.RecyclerViewHolder> {

    private List<ContactModel> mContactList;
    private Context mContext;

    public ContactAdapter(Context context, List<ContactModel> contactList) {
        this.mContactList = contactList;
        this.mContext=context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        try {
            final ContactModel mContact = mContactList.get(position);
            holder.mName.setText(mContact.getName());
            holder.mLocation.setText(mContact.getLocation());
            holder.mEmail.setText(mContact.getEmail());
            holder.mPhone.setText(mContact.getPhone());
            if (!TextUtils.isEmpty(mContact.getImage())) {
                holder.mImage.setImageURI(Uri.parse(mContact.getImage()));

            }

            holder.mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.mPhone=mContact.getPhone();
                    Intent in = new Intent(mContext, ContactDetailsActivity.class);
                    mContext.startActivity(in);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mLocation;
        private TextView mName;
        private TextView mEmail;
        private TextView mPhone;
        private ImageView mImage;
        private LinearLayout mParent;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mParent=(LinearLayout)itemView.findViewById(R.id.parent_lay);
            mName = itemView.findViewById(R.id.name);
            mLocation = itemView.findViewById(R.id.location);
            mEmail = itemView.findViewById(R.id.email);
            mPhone = itemView.findViewById(R.id.phone);
            mImage = itemView.findViewById(R.id.profile);
        }
    }

    public void addContact(List<ContactModel> contactList) {
        this.mContactList = contactList;
        notifyDataSetChanged();
    }
}
