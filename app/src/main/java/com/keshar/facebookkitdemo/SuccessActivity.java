package com.keshar.facebookkitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

public class SuccessActivity extends AppCompatActivity {
    private Button btnLogout;
    private EditText edtUserId,edtUserPhone,edtUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        btnLogout=findViewById(R.id.logout);
        edtUserEmail=findViewById(R.id.email);
        edtUserPhone=findViewById(R.id.phone);
        edtUserId=findViewById(R.id.user_id);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountKit.logOut();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                edtUserId.setText(String.format("User Id %s",account.getId()));
                edtUserEmail.setText(String.format("User Phone Number %s",account.getPhoneNumber()==null?"":account.getPhoneNumber().toString()));
                edtUserPhone.setText(String.format("User Email %s",account.getEmail()));
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }
}
