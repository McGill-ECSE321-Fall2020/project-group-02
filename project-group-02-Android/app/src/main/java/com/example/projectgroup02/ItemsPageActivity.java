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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ItemsPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    public static String collection;
    private JSONArray items;
    private static Context context;
    private ListView listView;
    ArrayList<SubjectData> items = new ArrayList<SubjectData>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_items_page);

        toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        // set the context for later use
        context = getApplicationContext();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        collection = intent.getStringExtra("ITEMS_PAGE_COLLECTION_NAME");

        // Get and setup the list of collections to be displayed
        listView = (ListView) findViewById(R.id.listview_collections);

        // Retrieve from the database the items inside the current collection
        getAllItems();

        // Set the name of the collection at the top of the items' page
        TextView tv = (TextView) findViewById(R.id.item_page_label);
        tv.setText(collection);
    }

    public void getAllItems() {
        HttpUtils.get(collection, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                error = "";

                try {
                    items = response.getJSONArray("items"); // get the array that represents the whole JSON data returned from the backend

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i); // get the item from the JSON data

                        // Extract item properties
                        itemsData.add(new SubjectData(item.getString("name"), item.getString("imageUrl"));

                        /*double itemHeight = item.getDouble("height");
                        double itemWidth = item.getDouble("width");
                        double itemBreadth = item.getDouble("breadth");
                        String itemCreationDate = item.getString("creationDate");
                        String itemDescription = item.getString("description");
                        double itemPrice = item.getDouble("price");
                        String itemImageUrl = item.getString("imageUrl");
                        boolean itemIsInStock = item.getBoolean("inStock");*/
                    }

                    CustomAdapter customAdapter = new CustomAdapter(context, itemsData);
                    listView.setAdapter(customAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }            }
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