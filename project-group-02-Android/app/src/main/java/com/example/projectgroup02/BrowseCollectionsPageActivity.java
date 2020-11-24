package com.example.projectgroup02;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class BrowseCollectionsPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    private JSONArray collections;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_browse_collections_page);

        toolbar = findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        getAllCollections();
    }
    /**
     * Get all collections from the database.
     *
     */
    public void getAllCollections() {
        HttpUtils.get("collections", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                error = "";
                try {
                    collections = response;

                    for (int i = 0; i < collections.length(); i++) {
                        JSONObject collection = collections.getJSONObject(i);

                        String collectionName = collection.getString("collectionName");
                        String collectionDescription = collection.getString("description");
                        String collectionPathToImage = collection.getString("pathToImage");
                        JSONArray items = collection.getJSONArray("items");

                       /* For testing -> Getting the Collection Works!
                       if (collectionName.equalsIgnoreCase("Picasso Nightmares")) {
                            TextView tv = (TextView) findViewById(R.id.collection_name_1);
                            tv.setText(collectionName);
                        }*/
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    public void clickCollection(View v) {
        String collectionName = "";
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