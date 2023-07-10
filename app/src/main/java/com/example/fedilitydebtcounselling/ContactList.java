package com.example.fedilitydebtcounselling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    //Initialize Variable
    RecyclerView recyclerView;
    ArrayList<ContactModel> arrayList = new ArrayList<ContactModel>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        //Assigning Variable
        recyclerView = findViewById(R.id.recycler_view);
        //Check permission
        checkPermission();
    }

    private void checkPermission() {
        //Check Condition
        if (ContextCompat.checkSelfPermission(ContactList.this
                , Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //when permission is not granted
            //request permission
            ActivityCompat.requestPermissions(ContactList.this
                    , new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }else {
            //When Permission Is Granted
            //Create Method
            getFedilityDebtCounselling2();
        }
    }

    private void getFedilityDebtCounselling2() {
        //Initialize uri;
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
         //Sort by ascending
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        //Intialize cursor
        Cursor cursor = getContentResolver().query(
                uri, null, null,null,sort
        );
        //check condition
        if (cursor.getCount() > 0){
            //when count is greater than 0
            //Use While loop
            while (cursor.moveToNext()){
                //Cursor move To Next
                //Get Contact id
                String id = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts._ID
                ));
                //Get Contact Name
                String Name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                ));
                //Initialize phone uri
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                //Initialize Selection
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                        +" =?";
                //Initialize Phone Cursor
                Cursor phoneCursor = getContentResolver().query(
                        uriPhone, null,
                        selection, new String[]{id}, null
                );
                //check condition
                if (phoneCursor.moveToNext()){
                    //when phone cursor move to next
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));
                    //Initialize Contact Model
                    ContactModel model = new ContactModel();
                    //set Name
                    model.setName(name);

                    //set Number
                    model.setName(number);
                    arrayList.add(model);
                    //close phone cursor
                    PhoneCursor.close();


                }
            }
            //close Cursor
            cursor.close();
        }
      //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Initialize Adapter
        adapter = new MainAdapter(this,arrayList);
        //set Adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check condition
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //when permission is granted
             //call method
            getFedilityDebtCounselling2();
        }else {
            //when permission is denied
            //Display Toast
            Toast.makeText(ContactList.this ,"permission Denied." ,
                    Toast.LENGTH_SHORT).show();
            //call check permission method
            checkPermission();

        }

    }
}