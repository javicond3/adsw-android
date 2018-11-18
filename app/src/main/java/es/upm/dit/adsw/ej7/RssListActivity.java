package es.upm.dit.adsw.ej7;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RssListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_list);

        List<RssItem> items = FilteredRssFeed.getEntries();
        RssArrayAdapter adapter =
                new RssArrayAdapter(this, items);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                RssItem item = (RssItem) parent.getAdapter().getItem(position);
                Intent browserIntent = new Intent (Intent.ACTION_VIEW, Uri.parse(item.link));
                startActivity(browserIntent);

            }
        });




    }




}
