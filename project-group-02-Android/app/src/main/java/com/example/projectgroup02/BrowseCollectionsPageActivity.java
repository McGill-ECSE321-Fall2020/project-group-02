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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class BrowseCollectionsPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    private JSONArray collections;
    private String[] collectionNames = new String[100];
    private String[] collectionDescriptions = new String[100];
    private String[] collectionImages = new String[100];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_browse_collections_page);

        toolbar = findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        // Retrieve the collections from the database
        getAllCollections();

        // Get and setup the list of collections to be displayed
        ListView listView = (ListView) findViewById(R.id.listview_collections);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, collectionNames);
        listView.setAdapter(adapter);

        // Listen to which collection was clicked by the user
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ItemsPageActivity.class);
                intent.putExtra("ITEMS_PAGE_COLLECTION_NAME", collectionNames[position]);
                startActivity(intent);
            }
        });

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

                        collectionNames = new String[] {"apple", "dragon fruit", "flydig"};
                        // collectionNames[i] = (collection.getString("collectionName"));
                        collectionDescriptions[i] = (collection.getString("description"));
                        collectionImages[i] = (collection.getString("pathToImage"));
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