package com.example.user.newsapp_fase1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    // article OPERATION_URL_EXTRA And OPERATION_SEARCH_LOADER
    private static String REQUEST_URL = "https://content.guardianapis.com/search?section=film&order-by=newest&&show-tags=contributor&q=cinema&api-key=4f03c7ba-df38-456a-9c29-ca693c79622b";
    boolean isConnected;
    @BindView(R.id.list)
    ListView newsListView;
    @BindView(R.id.empty_view)
    TextView mEmptyStateTextView;
    @BindView(R.id.no_internet)
    TextView mNoInternetTextView;
    private int NEWS_LOADER_ID = 1;
    private LoaderManager loaderManager;
    private NewsAdapter mAdapter;
    private ProgressBar mProgressBar;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check connection
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        //Identify and binding of Layout and Views - activity main xml -
        ButterKnife.bind(this);
        Button refreshButton = (Button) findViewById(R.id.refresh_button);
        mProgressBar = findViewById(R.id.loading_spinner);

        //Default progressBar -> invisibile
        mProgressBar.setVisibility(GONE);

        //array List creation and passing it to adapter
        final ArrayList<News> newsArray = new ArrayList<>();
        mAdapter = new NewsAdapter(this, newsArray);
        //linking adapter to listview
        newsListView.setAdapter(mAdapter);


        // Get a reference to the LoaderManager, in order to interact with loaders.
        loaderManager = getLoaderManager();


        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(NEWS_LOADER_ID, null, this).forceLoad();

        refreshButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                /*Checks connection status everytime button is clicked in case if connection status
                 *has changed since OnCreate method was called
                 */
                activeNetwork = cm.getActiveNetworkInfo();
                isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                mProgressBar.setVisibility(View.VISIBLE);

                // Get a reference to the LoaderManager, in order to interact with loaders.
                loaderManager = getLoaderManager();

                // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                // because this activity implements the LoaderCallbacks interface).
                loaderManager.initLoader(NEWS_LOADER_ID, null, MainActivity.this);

            }
        });
    }
    // The following 2 are methods that helps us inflate our Activity. THe first useful to inflate menu_main.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

        // this adds items to the action bar if it is present.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //To determine which item was selected and what action to take, call getItemId, which returns the unique ID for the menu item (defined by the android:id attribute in the menu resource).
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {

        mAdapter.clear();

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }

        //Checks if there is connection to internet and shows appropriate msg in case of empty list of results or lack of connection
        if (isConnected) {
            mEmptyStateTextView.setText(R.string.no_news);
            mProgressBar.setVisibility(GONE);
            mNoInternetTextView.setVisibility(GONE);
            newsListView.setEmptyView(mEmptyStateTextView);
        } else {
            mProgressBar.setVisibility(GONE);
            mEmptyStateTextView.setVisibility(GONE);
            mNoInternetTextView.setText(R.string.no_internet);
            mNoInternetTextView.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
