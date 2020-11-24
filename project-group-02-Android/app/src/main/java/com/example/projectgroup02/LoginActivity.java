package com.example.projectgroup02;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void login(View v) {
        final TextView tvUsername = (TextView) findViewById(R.id.loginUsername);
        final TextView tvPassword = (TextView) findViewById(R.id.loginPassword);

        // Setup the request parameters
        RequestParams params = new RequestParams();
        params.add("username", tvUsername.getText().toString());
        params.add("password", tvPassword.getText().toString());

        HttpUtils.post("user-login", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    MainActivity.username = tvUsername.getText().toString();
                    MainActivity.loggedIn = tvUsername.getText().toString() != null ? true : false;
                    tvUsername.setText("");
                    tvPassword.setText("");
                    Intent intent = new Intent(v.getContext(), BrowseCollectionsPageActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    error = e.getMessage();
                }
            }

            // returns the correct data here, but why in this method and not in onSuccess()?
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                try {
                    MainActivity.username = tvUsername.getText().toString();
                    MainActivity.loggedIn = tvUsername.getText().toString() != null ? true : false;
                    tvUsername.setText("");
                    tvPassword.setText("");
                    Intent intent = new Intent(v.getContext(), BrowseCollectionsPageActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }
        });
    }
}