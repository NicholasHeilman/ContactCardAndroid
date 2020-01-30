package com.example.contactcard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


//import static com.example.contactcard.Edit.EXTRA_REPLY_DESCRIPTION;
//import static com.example.contactcard.Edit.EXTRA_REPLY_EMAIL;
//import static com.example.contactcard.Edit.EXTRA_REPLY_NAME;
//import static com.example.contactcard.Edit.EXTRA_REPLY_PHONE;
//import static com.example.contactcard.Edit.EXTRA_REPLY_WEB;



public class MainActivity extends AppCompatActivity {

    // Declare variables
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;
    private TextView nameSetReply;
    private TextView emailSetReply;
    private TextView phoneSetReply;
    private TextView webSetReply;
    private TextView descriptionSetReply;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameSetReply = findViewById(R.id.main_name);
        emailSetReply = findViewById(R.id.main_email);
        phoneSetReply = findViewById(R.id.main_phone);
        webSetReply = findViewById(R.id.main_web);
        descriptionSetReply = findViewById(R.id.main_description);

        //Initialize preferences
        preferences = getSharedPreferences( Constants.USER_PREF, Context.MODE_PRIVATE);
        String name = preferences.getString(Constants.NAME, "");
        String phone = preferences.getString(Constants.PHONE, "");
        String email = preferences.getString(Constants.EMAIL,"");
        String web = preferences.getString(Constants.WEB,"");
        String description = preferences.getString(Constants.DESCRIPTION,"");


        if(!TextUtils.isEmpty(name)) {
            nameSetReply.setText(name);
            emailSetReply.setText(email);
            phoneSetReply.setText(phone);
            webSetReply.setText(web);
            descriptionSetReply.setText(description);
        }
    }

    public void editCard(View view) {
//            Log.d(TAG, "Send Btn Click");
        Intent intent = new Intent(this, Edit.class);
//        String message = mMessageEditText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String nameReply = data.getStringExtra(Edit.EXTRA_REPLY_NAME);
                String phoneReply = data.getStringExtra(Edit.EXTRA_REPLY_PHONE);
                String emailReply = data.getStringExtra(Edit.EXTRA_REPLY_EMAIL);
                String webReply = data.getStringExtra(Edit.EXTRA_REPLY_WEB);
                String descriptionReply = data.getStringExtra(Edit.EXTRA_REPLY_DESCRIPTION);
                System.out.println("Result OK!!!");
                System.out.println(nameReply);


                // Set the reply
                nameSetReply.setText(nameReply);
                emailSetReply.setText(emailReply);
                phoneSetReply.setText(phoneReply);
                webSetReply.setText(webReply);
                descriptionSetReply.setText(descriptionReply);
            }
        }
    }
}
