package es.upm.dit.adsw.ej7;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RssArrayAdapter extends ArrayAdapter<RssItem>{

    Activity context;
    public RssArrayAdapter (Activity context, List<RssItem> rssItems){
        super(context,0,rssItems);
        this.context = context;

    }

    @Override

    public View getView(int position, View view, ViewGroup parent){
        RssItem rssItem = getItem(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.activity_rss_item_row,null);

            TextView rssItemTitle = (TextView) item.findViewById(R.id.rssItemTitle);
            TextView rssItemDate = (TextView) item.findViewById(R.id.rssItemDate);
            TextView rssItemDescription = (TextView) item.findViewById(R.id.rssItemDescription);
            rssItemTitle.setText(rssItem.title);
            rssItemDate.setText(rssItem.date);
            rssItemDescription.setText(Html.fromHtml(rssItem.description));
            return item;
        }

}