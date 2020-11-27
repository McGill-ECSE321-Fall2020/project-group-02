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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ItemsPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String error = "";
    public static String collection;
    private static Context context;
    private ListView listView;
    ArrayList<SubjectData> itemsData = new ArrayList<SubjectData>(); // contains all the items from the specified collection
    ArrayList<String> allUsernames = new ArrayList<String>(); // contains all usernames of the database in order to retrieve the artist

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_items_page);

        toolbar = findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);

        // set the context for later use
        context = getApplicationContext();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        collection = intent.getStringExtra("ITEMS_PAGE_COLLECTION_NAME");

        // Get and setup the list of collections to be displayed
        listView = (ListView) findViewById(R.id.listview_items_page);

        // Retrieve from the database the items inside the current collection
        getAllItems();

        // Set the name of the collection at the top of the items' page
        TextView tv = (TextView) findViewById(R.id.item_page_label);
        tv.setText(collection);

        // Listen to which item was clicked by the user
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addItemToShoppingCart(itemsData.get(position));

                if (itemsData.get(position).getInStock()) {
                    Intent intent = new Intent(context, ShoppingCartActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void getAllItems() {
        // Fetch all the users in order to find the artist
        HttpUtils.get("users", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                error = "";

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject user = response.getJSONObject(i); // get the item from the JSON data

                        // get the users' username
                        allUsernames.add(user.getString("username"));
                    }
                } catch (JSONException e) {
                    Toast.makeText(ItemsPageActivity.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString(); // display the error message
                    Toast.makeText(ItemsPageActivity.this, error,
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(ItemsPageActivity.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        // Fetch all the items
        HttpUtils.get(collection, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                error = "";

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item = response.getJSONObject(i); // get the item from the JSON data

                        SubjectData itemSubject;

                        if(item.getString("inStock").equals("true")) {
                            itemSubject = new SubjectData("Add to Shopping Cart", item.getString("pathToImage"));
                        } else {
                            itemSubject = new SubjectData("Out of stock", item.getString("pathToImage"));
                        }

                        String itemId = item.getString("itemId"); // get the item's id
                        itemSubject.setItemId(Integer.parseInt(itemId));
                        itemSubject.setInStock(item.getString("inStock"));

                        // set the item's artist by checking if the generated id is equal to the actual id of the item
                        for (String username : allUsernames) {
                            if ((username + item.getString("name")).hashCode() == Integer.parseInt(itemId)) {
                                itemSubject.setArtistUsername(username);
                            }
                        }
                         // add the item to the item arraylist (for every item)
                            itemsData.add(itemSubject);
                    }

                    CustomAdapter customAdapter = new CustomAdapter(context, itemsData);
                    listView.setAdapter(customAdapter);

                } catch (JSONException e) {
                    Toast.makeText(ItemsPageActivity.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                    Toast.makeText(ItemsPageActivity.this, error,
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    error = e.getMessage();
                    Toast.makeText(ItemsPageActivity.this, error,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addItemToShoppingCart(SubjectData item) {
        try {
            HttpUtils.post((MainActivity.username + "/shopping-cart/add-item/" + item.getSubjectName() + "/" + item.getArtistUsername()).replaceAll(" ", "%20"), new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    if(item.getInStock()) {
                        Intent intent = new Intent(context, ShoppingCartActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        error = errorResponse.get("message").toString();
                        Toast.makeText(ItemsPageActivity.this, error,
                                Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        error = e.getMessage();
                        Toast.makeText(ItemsPageActivity.this, error,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch(Exception e) {
          /*  Intent intent = new Intent(context, ShoppingCartActivity.class);
            startActivity(intent);*/
            error = e.getMessage();
            Toast.makeText(ItemsPageActivity.this, error,
                    Toast.LENGTH_LONG).show();
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
            HttpUtils.post("/checkout/" + MainActivity.username, new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        error += errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    /**
     * Helper method to generate Java hashcode
     * @param value
     * @return
     */
    public static int hashCode(byte[] value) {
        int h = 0;
        for (byte v : value) {
            h = 31 * h + (v & 0xff);
        }
        return h;
    }
}