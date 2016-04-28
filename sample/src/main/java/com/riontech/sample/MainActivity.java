package com.riontech.sample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.riontech.sample.adapters.WordsAdapter;
import com.riontech.sample.utils.AppUtils;
import com.riontech.staggeredtextgridview.StaggeredTextGridView;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            JSONArray wordJsonArray = new JSONArray( AppUtils.loadJSONFromAsset(this, "word_power1"));
            StaggeredTextGridView gridview = (StaggeredTextGridView) findViewById(R.id.staggeredTextView);
            WordsAdapter adapter = new WordsAdapter(this, wordJsonArray);
            gridview.setmAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
