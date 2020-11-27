package com.example.projectgroup02;

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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CheckoutActivity extends AppCompatActivity {
    public String deliveryMethod = "HOMEDELIVERY";
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Toolbar toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        Button checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();
            }
        });

        Switch deliverySwitchButton = (Switch) findViewById(R.id.delivery);

        deliverySwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    deliverySwitchButton.setText("In-Store Pickup");
                    deliveryMethod = "INSTOREPICKUP";
                } else {
                    deliverySwitchButton.setText("Home Delivery");
                    deliveryMethod = "HOMEDELIVERY";
                }
            }
        });
    }

    private void checkout() {
        try {
            HttpUtils.post(MainActivity.username + "/checkout/" + deliveryMethod, new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    refreshErrorMessage();
                    startActivity(new Intent(CheckoutActivity.this, BrowseCollectionsPageActivity.class));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    startActivity(new Intent(CheckoutActivity.this, BrowseCollectionsPageActivity.class));
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
            if (MainActivity.loggedIn) {
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