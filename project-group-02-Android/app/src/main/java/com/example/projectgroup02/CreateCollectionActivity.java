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

public class CreateCollectionActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String error = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_collection_page);

        toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);
    }

    public void createCollection(View v){
        final TextView cName = (TextView) findViewById(R.id.collection_name);
        final TextView cDescription = (TextView) findViewById(R.id.collection_description);
        final TextView cImageURL = (TextView) findViewById(R.id.collection_image_URL);

        HttpUtils.post("create-collection/" + cName.getText().toString() + "?" + cDescription.getText().toString() + "&" + cImageURL.getText().toString(), new RequestParams(), new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                cName.setText("");
                cDescription.setText("");
                cImageURL.setText("");
                //MainActivity.username = response;
                Intent intent = new Intent(v.getContext(), CollectionsPageActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}
