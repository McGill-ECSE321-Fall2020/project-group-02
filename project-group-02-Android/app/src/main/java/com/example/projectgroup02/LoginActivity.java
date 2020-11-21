package com.example.projectgroup02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String error = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);
    }

    public void login(View v) {
        final TextView tvUsername = (TextView) findViewById(R.id.editTextUsername);
        final TextView tvPassword = (TextView) findViewById(R.id.editTextPassword);

        HttpUtils.post("user-login/" + tvUsername.getText().toString() + "/" + tvPassword.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                tvUsername.setText("");
                tvPassword.setText("");
                //MainActivity.username = response;
                Intent intent = new Intent(v.getContext(), CollectionsPageActivity.class);
                startActivity(intent);
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

}
