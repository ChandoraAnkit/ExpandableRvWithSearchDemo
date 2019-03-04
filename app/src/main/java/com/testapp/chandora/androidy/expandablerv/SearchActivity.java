package com.testapp.chandora.androidy.expandablerv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText mSearchView;
    private RecyclerView mInfoRv;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_search);

        super.onCreate(bundle);

        mToolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSearchView = findViewById(R.id.search_view);

        mInfoRv = findViewById(R.id.info_rv);
        mInfoRv.setVisibility(View.VISIBLE);
        mInfoRv.setLayoutManager(new LinearLayoutManager(this));

        final InfoAdapter adapter = new InfoAdapter();
        mInfoRv.setAdapter(adapter);

        mSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not applicable
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Not applicable
            }
        });


        Intent intent = getIntent();


        if (intent != null && intent.hasExtra("INFO"))  {
            ArrayList<Info> infosList = intent.getParcelableArrayListExtra("INFO");
            adapter.setItems(infosList);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
