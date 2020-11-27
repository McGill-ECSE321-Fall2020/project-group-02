package com.example.projectgroup02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ShoppingCartActivity extends AppCompatActivity {

    private String error = "";
    private JSONArray items;
    private ListView listView;
    private ArrayList<SubjectData> shoppingCartItems = new ArrayList<SubjectData>();
    private Double totalPrice = 0.0;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        Toolbar toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        // set the context for later use
        context = getApplicationContext();

        // get and setup the list of items in the shopping cart to be displayed
        listView = (ListView) findViewById(R.id.listview_shopping_cart);

        totalPrice = 0.0;


        //Retrieve all of the items in the user's shopping cart
        getAllUserItems();


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
                    items = response;

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);

                        if(item.getString("inStock").equals("true")) {
                            shoppingCartItems.add(new SubjectData(item.getString("name"), item.getString("pathToImage") ));
                        }
                    }

                    CustomAdapter customAdapter = new CustomAdapter(context, shoppingCartItems);
                    listView.setAdapter(customAdapter);

                } catch (JSONException e) {
                    error = e.getMessage();
                    Toast.makeText(ShoppingCartActivity.this, error, Toast.LENGTH_LONG).show();
                }

                try {
                    calculateTotal();
                } catch (JSONException e) {
                    error = e.getMessage();
                    Toast.makeText(ShoppingCartActivity.this, error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {

                    error = errorResponse.get("message").toString();
                    Toast.makeText(ShoppingCartActivity.this, error, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {

                    error = e.getMessage();
                    Toast.makeText(ShoppingCartActivity.this, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void calculateTotal() throws JSONException {

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);

            totalPrice += item.getDouble("price");
        }
        TextView text = (TextView) findViewById(R.id.totalPrice);
        text.setText("Total Price: " + totalPrice.toString() + "$");
    }

    public void goToCheckout(View v) {
        Intent intent = new Intent(v.getContext(), CheckoutActivity.class);
        startActivity(intent);
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.errorcheckout);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

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
            if(MainActivity.loggedIn) {
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
            HttpUtils.post("user-logout/" + MainActivity.username, new RequestParams(), new JsonHttpResponseHandler() {
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