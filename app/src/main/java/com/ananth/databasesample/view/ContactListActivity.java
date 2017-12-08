package com.ananth.databasesample.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ananth.databasesample.R;
import com.ananth.databasesample.adapter.ContactAdapter;
import com.ananth.databasesample.db.AppDatabase;
import com.ananth.databasesample.model.ContactModel;
import com.ananth.databasesample.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactListActivity extends LifecycleActivity {

    private RecyclerView mList;
    //    DBHelper myDb;
    ArrayList<HashMap<String, String>> mContactList = new ArrayList<>();
    private ContactAdapter mAdapter;
    private ContactViewModel viewModel;
    private LinearLayout mNoResult;
    private TextView mCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (RecyclerView) findViewById(R.id.recyclerView);
        mNoResult = (LinearLayout) findViewById(R.id.no_result_lay);
        mCreate = (TextView) findViewById(R.id.create_contact);
        mList.setVisibility(View.VISIBLE);
        mNoResult.setVisibility(View.GONE);

        /**
         * Set the adapter for recyclerview
         * */
        mAdapter = new ContactAdapter(ContactListActivity.this, new ArrayList<ContactModel>());
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);

        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        viewModel.getLiveContactList().observe(ContactListActivity.this, new Observer<List<ContactModel>>() {
            @Override
            public void onChanged(@Nullable List<ContactModel> contactModels) {
                mAdapter.addContact(contactModels);
                handleViewState();
            }
        });


        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent io = new Intent(ContactListActivity.this, CreateContact.class);
                io.putExtra("type", "create");
                startActivity(io);
            }
        });

        handleViewState();
    }


    private void handleViewState() {
        if (mList.getAdapter().getItemCount() > 0) {
            mList.setVisibility(View.VISIBLE);
            mNoResult.setVisibility(View.GONE);
        } else {
            mList.setVisibility(View.GONE);
            mNoResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.create) {
            Intent io = new Intent(ContactListActivity.this, CreateContact.class);
            io.putExtra("type", "create");
            startActivity(io);
            return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_contact, menu);
        return true;
    }

}
