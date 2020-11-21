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

public class CollectionsPageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String error = "";
    private JSONObject collections;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_page);

        toolbar = findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

    }

    /**
     * Get all collections from the database.
     *
     * @param v View
     */
    public void getAllCollections(View v) {
        HttpUtils.get("collections", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                error = "";
                collections = response;
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

    /**
     * Navigate to the page that displays all artworks of the selected collection.
     *
     * @param v View
     */
    public void enterCollection(View v) {
        String collectionName = "";
        //  ItemsPageActivity.collection = clickedCollection;
        Intent intent = new Intent(v.getContext(), ItemsPageActivity.class); // create new intent to navigate to the ItemsPage for the given collection
        intent.putExtra("ITEMS_PAGE_COLLECTION_NAME", collectionName);
        startActivity(intent);
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
