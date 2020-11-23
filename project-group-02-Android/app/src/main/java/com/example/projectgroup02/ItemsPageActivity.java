package com.example.projectgroup02;

import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ItemsPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    public static String collection;
    private Object[] items;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_items_page);

        toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        collection = intent.getStringExtra("ITEMS_PAGE_COLLECTION_NAME");
    }

    public void getAllItems(View v) {
        HttpUtils.get(collection + "/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                error = "";
                //items = response;
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

    public void addItemToShoppingCart(View v) {
        Object item = ""; // selected item
        final JSONObject allUsers; // all the users in the database used to find the artist using the hashcode

        HttpUtils.get("/users", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                error = "";
            /*    allUsers = response;

                for (Item item: items) {
                    for (User user : allUsers) {
                        if ((user.username + item.name).hashCode() == item.itemId) {
                            item.artist = user;
                        }
                    }
                }*/

             /*   HttpUtils.get("/" + MainActivity.username + "/shopping-cart/add-item/" + item.getName() + "/" + item.artist, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        error = "";
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            error = errorResponse.get("message").toString();
                        } catch (JSONException e) {
                            error = e.getMessage();
                        }
                    }
                });*/
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

   /* private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }*/
}