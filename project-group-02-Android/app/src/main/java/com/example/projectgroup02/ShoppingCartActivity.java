package com.example.projectgroup02;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ShoppingCartActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    private JSONArray shoppingCartItems;
    private JSONArray allUsers;
    private ArrayList<String> itemNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getAllUserItems();
      //  retrieveItemArtist();
    }
    /*
    Retrieve all of the items in the user's shopping cart
     */
    public void getAllUserItems() {

        HttpUtils.get(MainActivity.username + "/shopping-cart", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                error = "";
                try {
                    shoppingCartItems = response;

                    for (int i = 0; i < shoppingCartItems.length(); i++) {
                        JSONObject item = shoppingCartItems.getJSONObject(i);

                        itemNames.add(item.getString("name"));
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

    /*
    Associate an item to the artist selling it
     */

    public void retrieveItemArtist() {

        HttpUtils.get("users", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                error = "";
                try {
                    allUsers = response;

                    for (int i = 0; i < shoppingCartItems.length(); i++) {
                        JSONObject item = shoppingCartItems.getJSONObject(i);

                        for(int j=0; j < allUsers.length(); j++){
                            JSONObject user = allUsers.getJSONObject(j);

                            String username = user.getString("username");

                            String itemName = item.getString("name");

                            int itemID = item.getInt("itemId");
                        }

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

    /*
    Remove an item from the user's shopping cart
     */

    public void removeFromShoppingCart() {

        HttpUtils.get(MainActivity.username + "/shopping-cart", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                error = "";
                try {
                    shoppingCartItems = response;

                    for (int i = 0; i < shoppingCartItems.length(); i++) {
                        JSONObject item = shoppingCartItems.getJSONObject(i);

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

}