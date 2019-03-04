package com.testapp.chandora.androidy.expandablerv;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;

    private ArrayList<Info> infosList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();
        mExpandableView = findViewById(R.id.expandableView);


        for (Feed feed : Utils.loadFeeds(this.getApplicationContext())) {
            mExpandableView.addView(new HeadingView(mContext, feed.getHeading()));
            infosList.addAll(feed.getInfoList());
            for (Info info : feed.getInfoList()) {
                mExpandableView.addView(new InfoView(mContext, info));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.search:
                startSearchActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void startSearchActivity() {

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putParcelableArrayListExtra("INFO", infosList);
        startActivity(intent);
    }
}
