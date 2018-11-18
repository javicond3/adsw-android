package es.upm.dit.adsw.ej7;

import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private Button boton1;
    private ProgressBar barra;
    private Spinner feedSpinner;
    private List<String> optionList;
    private List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        optionList= new ArrayList<>();
        urlList = new ArrayList<>();
        String [] valores = getResources().getStringArray(R.array.tipos);
        add(valores[0], "http://epimg.net/rss/elpais/portada.xml" );
        add(valores[1],"http://dat.etsit.upm.es/?q=/rss/all");
        add(valores[2], "http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml");
        add(valores[3], "http://feeds.weblogssl.com/xatakandroid");


        feedSpinner = (Spinner) findViewById(R.id.feedSpinner);



        boton1 = (Button) findViewById(R.id.boton1);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1 = (EditText) findViewById(R.id.editText1);
                int position =feedSpinner.getSelectedItemPosition();
                String url = urlList.get(position);



                String [] params = {url, editText1.getText().toString()};
                new RssRetrieveTask().execute(params);
            }
        });


    }



    private void add(String site, String url){
        optionList.add(site);
        urlList.add(url);
    }

    private class RssRetrieveTask extends AsyncTask<String,Void,Void>{

        @Override
        protected void onPreExecute(){
            barra = (ProgressBar) findViewById(R.id.barra);
            barra.setVisibility(View.VISIBLE);
        }

        @Override

        protected Void doInBackground(String ... strings){

            try {
                List<RssItem> lista = new RssFeedDownloader().loadXmlFromNetwork(strings[0]);
                FilteredRssFeed.reset(strings[1]);
                for (RssItem item : lista)
                    FilteredRssFeed.add(item);
                FilteredRssFeed.getEntries();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;


        }

        @Override
        protected void onPostExecute(Void v){
            barra = (ProgressBar) findViewById(R.id.barra);
            barra.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(MainActivity.this.getApplicationContext(), RssListActivity.class);
            startActivity(intent);

        }
    }

}
