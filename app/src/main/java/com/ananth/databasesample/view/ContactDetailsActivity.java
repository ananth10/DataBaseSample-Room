package com.ananth.databasesample.view;

import android.app.AlertDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ananth.databasesample.R;
import com.ananth.databasesample.model.ContactModel;
import com.ananth.databasesample.utils.Utils;
import com.ananth.databasesample.viewmodel.ContactViewModel;

import java.util.List;

public class ContactDetailsActivity extends LifecycleActivity implements AppCompatCallback {

    private Toolbar toolbar;
    private ImageView mImage;
    private TextView mName;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mLocation;
    String mImageUri = "";
    private ContactViewModel viewModel;
    private AppCompatDelegate delegate;
    private ContactModel mContactModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_contact_details);
        mImage = (ImageView)delegate.findViewById(R.id.profile);
        mName = (TextView)delegate.findViewById(R.id.name);
        mEmail = (TextView)delegate.findViewById(R.id.email);
        mPhone = (TextView)delegate.findViewById(R.id.phone);
        mLocation = (TextView)delegate.findViewById(R.id.location);
        toolbar = (Toolbar)delegate.findViewById(R.id.toolbar);
        delegate.setSupportActionBar(toolbar);
        delegate.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        delegate.getSupportActionBar().setTitle("Contact Details");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Contact Details");
        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        viewModel.getSingleContact().observe(ContactDetailsActivity.this, new Observer<ContactModel>() {
            @Override
            public void onChanged(@Nullable ContactModel contactModel) {

                bindContactInfo(contactModel);
            }
        });
    }

    /**
     * Binding the contact info
     * */
    private void bindContactInfo(ContactModel contactModel) {

        if(contactModel!=null) {
            mName.setText(contactModel.getName());
            mEmail.setText(contactModel.getEmail());
            mPhone.setText(contactModel.getPhone());
            mLocation.setText(contactModel.getLocation());

            if (!TextUtils.isEmpty(contactModel.getImage())) {
                mImage.setImageURI(Uri.parse(contactModel.getImage()));
                mImageUri = contactModel.getImage();
            }
            this.mContactModel = contactModel;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = delegate.getMenuInflater();
        inflater.inflate(R.menu.detail_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.edit) {
            Intent intent = new Intent(ContactDetailsActivity.this, CreateContact.class);
            intent.putExtra("type", "edit");
            intent.putExtra("name", mName.getText().toString());
            intent.putExtra("email", mEmail.getText().toString());
            intent.putExtra("phone", mPhone.getText().toString());
            intent.putExtra("location", mLocation.getText().toString());
            intent.putExtra("image", mImageUri);
            startActivity(intent);

            return true;
        } else if (id == R.id.delete) {
            showDeleteDialog();

            return true;
        }
        return true;
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteContact)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                viewModel.deleteContact(mContactModel);
                                startActivity(new Intent(ContactDetailsActivity.this, ContactListActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();

                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub

                            }
                        });

        AlertDialog d = builder.create();
        d.setTitle("Delete Contact?");
        d.show();
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
