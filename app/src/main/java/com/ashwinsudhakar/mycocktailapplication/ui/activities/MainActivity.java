package com.ashwinsudhakar.mycocktailapplication.ui.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.ashwinsudhakar.mycocktailapplication.R;
import com.ashwinsudhakar.mycocktailapplication.adaptors.AdaptorMainActivity;
import com.ashwinsudhakar.mycocktailapplication.models.ModelMainActivity;
import com.ashwinsudhakar.mycocktailapplication.ui.customViews.MyDialogSheet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView search;
    private EditText editText;
    private ProgressDialog pDialog;

    private final List<ModelMainActivity> lProductList = new ArrayList<>();

    private AdaptorMainActivity adaptorMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initClicks();
        showSettingsDialog();
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


    }

    @Override
    protected void onResume() {
        super.onResume();
        lProductList.clear();

        if (isNetworkConnected()) {
            fetchListResponse();
        }

    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("No Internet Connection");
        builder.setMessage("This app needs internet connection to use this feature. You can grant them in from settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
            startActivity(intent);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();

    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void initClicks() {

        search.setOnClickListener(v -> filter(editText.getText().toString()));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                filter(s.toString());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);

        editText = findViewById(R.id.edtProducts);
        search = findViewById(R.id.searchView);

    }


    private void fetchListResponse() {
        showDialog();
        JSONObject jsonParam = new JSONObject();

        String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL, jsonParam,
                response -> {
                    hideDialog();
                    Log.d("Response", response.toString());
                    try {
                        JSONArray jsonArray = response.getJSONArray("drinks");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            String strDrink = jsonArray.getJSONObject(i).getString("strDrink");

                            String strDrinkThumb = jsonArray.getJSONObject(i).getString("strDrinkThumb");
                            String strInstructions = jsonArray.getJSONObject(i).getString("strInstructions");
                            String strGlass = jsonArray.getJSONObject(i).getString("strGlass");


                            lProductList.add(new ModelMainActivity(strDrinkThumb, strDrink, strInstructions, strGlass));
                        }

                        initRecyclerView();

                        adaptorMainActivity.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    hideDialog();
                    MyDialogSheet dialogSheet = new MyDialogSheet(MainActivity.this);
                    dialogSheet.setTitle(getString(R.string.dialog_title_error_loading_data))
                            .setMessage(error.getMessage())
                            .setPositiveButton(getString(R.string.dialog_button_retry),
                                    v -> fetchListResponse())
                            .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                            .setButtonsColorRes(R.color.colorAccent)
                            .setNegativeButton(getString(R.string.dialog_button_cancel),
                                    null);
                    dialogSheet.show();

                });
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(jsonObjectRequest);

    }

    private void initRecyclerView() {

        adaptorMainActivity = new AdaptorMainActivity(getApplicationContext(), lProductList, (model, position) -> startActivity(new Intent(MainActivity.this, MainActivityDetails.class)
                .putExtra("NAME", model.getpName())
                .putExtra("IMAGE", model.getImageUrl())
                .putExtra("INSTRUCTIONS", model.getsInstructions())
                .putExtra("GLASS", model.getStrGlass())));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2
        );
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptorMainActivity);
    }

    private void filter(String text) {
        ArrayList<ModelMainActivity> filteredList = new ArrayList<>();
        for (ModelMainActivity item : lProductList) {
            if (item.getpName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adaptorMainActivity.filterList(filteredList);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
