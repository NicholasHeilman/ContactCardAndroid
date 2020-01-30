package com.example.contactcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Edit extends AppCompatActivity {


    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY_NAME ="com.example.android.contactcard.extra.REPLY_NAME";
    public static final String EXTRA_REPLY_PHONE ="com.example.android.contactcard.extra.REPLY_PHONE";
    public static final String EXTRA_REPLY_EMAIL ="com.example.android.contactcard.extra.REPLY_EMAIL";
    public static final String EXTRA_REPLY_WEB ="com.example.android.contactcard.extra.REPLY_WEB";
    public static final String EXTRA_REPLY_DESCRIPTION ="com.example.android.contactcard.extra.REPLY_DESCRIPTION";

    // Init of View variables
    private EditText nameEdit;
    private EditText phoneEdit;
    private EditText emailEdit;
    private EditText webEdit;
    private EditText descriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Connect View variables to id of object
        nameEdit = findViewById(R.id.edit_name);
        phoneEdit = findViewById(R.id.edit_phone);
        emailEdit = findViewById(R.id.edit_email);
        webEdit = findViewById(R.id.edit_web);
        descriptionEdit = findViewById(R.id.edit_description);
    }

     public void saveCard(View view){
        //Get reply message for text input
         String nameReply = nameEdit.getText().toString();
         String phoneReply = phoneEdit.getText().toString();
         String emailReply = emailEdit.getText().toString();
         String webReply = webEdit.getText().toString();
         String descriptionReply = descriptionEdit.getText().toString();

         //connect
         Intent intent = new Intent();
         intent.putExtra(EXTRA_REPLY_NAME, nameReply);
         intent.putExtra(EXTRA_REPLY_PHONE, phoneReply);
         intent.putExtra(EXTRA_REPLY_EMAIL, emailReply);
         intent.putExtra(EXTRA_REPLY_WEB, webReply);
         intent.putExtra(EXTRA_REPLY_DESCRIPTION, descriptionReply);
         setResult(RESULT_OK, intent);

         //Save user data changes
         SharedPreferences.Editor editor = getSharedPreferences( Constants.USER_PREF, Context.MODE_PRIVATE).edit();

         //
         editor.putString(Constants.NAME, nameReply);
         editor.putString(Constants.EMAIL, emailReply);
         editor.putString(Constants.PHONE, phoneReply);
         editor.putString(Constants.WEB, webReply);
         editor.putString(Constants.DESCRIPTION, descriptionReply);
         editor.apply();

         finish();

     }

}
