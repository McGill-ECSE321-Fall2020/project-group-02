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
    ArrayList<SubjectData> collectionsData = new ArrayList<SubjectData>();

    private static Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_browse_collections_page);

        toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

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

                        // Add the collections data to the array list of collections
                        collectionsData.add(new SubjectData(collection.getString("collectionName"), collection.getString("pathToImage")));
                    }

                    // Test with hard-coded images
                    //collectionsData.add(new SubjectData("JAVA", "https://www.tutorialspoint.com/java/images/java-mini-logo.jpg"));
                    //collectionsData.add(new SubjectData("Python", "https://www.tutorialspoint.com/python/images/python-mini.jpg"));
                    //collectionsData.add(new SubjectData("Javascript", "https://www.tutorialspoint.com/javascript/images/javascript-mini-logo.jpg"));

                    CustomAdapter customAdapter = new CustomAdapter(context, collectionsData);
                    listView.setAdapter(customAdapter);

                   /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.unitary_item_layout, R.id.collection_name_label, collectionNames);
                    listView.setAdapter(adapter);
*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            if (loggedIn) {
                Intent intent = new Intent(this, BrowseCollectionsPageActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.shoppingcart) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            logout();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        try {
            HttpUtils.post("/checkout/" + MainActivity.username, new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    refreshErrorMessage();
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
        }catch (Exception e) {

        }
    }
}