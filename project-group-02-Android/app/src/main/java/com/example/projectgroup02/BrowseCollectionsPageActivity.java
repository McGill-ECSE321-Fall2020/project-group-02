package com.example.projectgroup02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Activity to browse collections.
 */
public class BrowseCollectionsPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    private JSONArray collections;
    private ListView listView;
    ArrayList<SubjectData> collectionsData = new ArrayList<SubjectData>();

    private static Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_browse_collections_page);

        // get the toolbar
        toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        // set the context for later use
        context = getApplicationContext();

        // Get and setup the list of collections to be displayed
        listView = (ListView) findViewById(R.id.listview_collections);

        // Retrieve the collections from the database
        getAllCollections();

        // Listen to which collection was clicked by the user
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ItemsPageActivity.class);
                intent.putExtra("ITEMS_PAGE_COLLECTION_NAME", collectionsData.get(position).getSubjectName());
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

                        // Add the collections data to the array list of collections
                        collectionsData.add(new SubjectData(collection.getString("collectionName"), collection.getString("pathToImage")));
                    }

                    CustomAdapter customAdapter = new CustomAdapter(context, collectionsData);
                    listView.setAdapter(customAdapter);

                } catch (JSONException e) {
                    error = e.getMessage();
                    Toast.makeText(BrowseCollectionsPageActivity.this, error,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    // display the error
                    error = errorResponse.get("message").toString();
                    Toast.makeText(BrowseCollectionsPageActivity.this, error,
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    error = e.getMessage();
                    Toast.makeText(BrowseCollectionsPageActivity.this, error,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Select options from the app header
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // home action
        if (id == R.id.home) {
            if (MainActivity.loggedIn) {
                Intent intent = new Intent(this, BrowseCollectionsPageActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            return true;
        }

        // go to shopping cart action
        if (id == R.id.shoppingcart) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        }

        // logout action
        if (id == R.id.logout) {
            logout();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    // logout activity
    private void logout() {
        try {
            HttpUtils.post("user-logout/" + MainActivity.username, new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        error = errorResponse.get("message").toString();
                        Toast.makeText(BrowseCollectionsPageActivity.this, error,
                                Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        error = e.getMessage();
                        Toast.makeText(BrowseCollectionsPageActivity.this, error,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }catch (Exception e) {
            error = e.getMessage();
            Toast.makeText(BrowseCollectionsPageActivity.this, error,
                    Toast.LENGTH_LONG).show();
        }
    }
}