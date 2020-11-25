package com.example.projectgroup02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BrowseCollectionsPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    private JSONArray collections;
    private ArrayList<String> collectionNames = new ArrayList<>();
    private ArrayList<String> collectionDescriptions = new ArrayList<>();
    private ArrayList<String> collectionImages = new ArrayList<>();
    private ListView listView;

    private static Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_browse_collections_page);

        // set the context for later use
        context = getApplicationContext();

        // Get and setup the list of collections to be displayed
        listView = (ListView) findViewById(R.id.listview_collections);

        // Retrieve the collections from the database
        getAllCollections();


        //listView.setAdapter

        // Listen to which collection was clicked by the user
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ItemsPageActivity.class);
                intent.putExtra("ITEMS_PAGE_COLLECTION_NAME", collectionNames.get(position));
                startActivity(intent);
            }
        });

    }
    /**
     * Get all collections from the database.
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

                        collectionNames.add(collection.getString("collectionName"));
                        collectionDescriptions.add(collection.getString("description"));
                        collectionImages.add(collection.getString("pathToImage"));

                     /* API test

                      if (collection.getString("collectionName").equalsIgnoreCase("Picasso Nightmares")) {
                            TextView tv = (TextView) findViewById(R.id.collections_page_title);
                            tv.setText(collectionNames.get(0));
                        }*/
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.unitary_item_layout, R.id.collection_name_label, collectionNames);
                    listView.setAdapter(adapter);

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