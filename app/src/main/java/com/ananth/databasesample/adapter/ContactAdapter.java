package com.ananth.databasesample.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.ananth.databasesample.databinding.ContactsListItemBinding;
import com.ananth.databasesample.model.ContactModel;
import com.ananth.databasesample.utils.Utils;
import com.ananth.databasesample.view.ClickHandler;
import com.ananth.databasesample.view.ContactDetailsActivity;

import java.util.List;

/**
 * Created by Babu on 10/4/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.RecyclerViewHolder> implements ClickHandler {

    private List<ContactModel> mContactList;
    private Context mContext;

    public ContactAdapter(Context context, List<ContactModel> contactList) {
        this.mContactList = contactList;
        this.mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactsListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.contacts_list_item,
                parent, false);
        return new RecyclerViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        ContactModel contactModel = mContactList.get(position);
        holder.binding.setContact(contactModel);
        holder.binding.setClickHandler(this);
    }


    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    @Override
    public void onClicked(String phone) {
        Utils.mPhone = phone;
        Intent in = new Intent(mContext, ContactDetailsActivity.class);
        mContext.startActivity(in);
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ContactsListItemBinding binding;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public void addContact(List<ContactModel> contactList) {
        this.mContactList = contactList;
        notifyDataSetChanged();
    }
}
