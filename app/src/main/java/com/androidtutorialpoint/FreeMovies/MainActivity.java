package com.androidtutorialpoint.FreeMovies;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class MainActivity extends Activity {

    ListView list;
    Button button;
    EditText text;
    String[] movie_name;
    String[] target_homepage={"http://www.123movies.to/movie/search/","http://www.123movies.is/movie/search/"};
    String[] web;
    String final_url;
    Jsoup_parser parser;
    String Movie_name;
    String[] movie_link;
    RelativeLayout rlayout;

    @Override
    public void onCreate(Bundle saved)
    {
        super.onCreate(saved);
        setContentView(com.androidtutorialpoint.volleytutorial.R.layout.activity_main);
        button=(Button)findViewById(com.androidtutorialpoint.volleytutorial.R.id.searchbtn);
        text=(EditText)findViewById(com.androidtutorialpoint.volleytutorial.R.id.searchtxt);

        rlayout=(RelativeLayout)findViewById(com.androidtutorialpoint.volleytutorial.R.id.loadingPanel);
        rlayout.setVisibility(View.GONE);


        final Intent intent=new Intent(MainActivity.this,movie_activity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie_name=text.getText().toString();
                movie_name=split(Movie_name);
                final_url=FinalURL(target_homepage[1],movie_name);
                rlayout.setVisibility(View.VISIBLE);
               new Mytask().execute(final_url);
            }
        });

        list=(ListView)findViewById(com.androidtutorialpoint.volleytutorial.R.id.listView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("Movie_Link",movie_link[i]);
                intent.putExtra("Movie_id",movie_name[i]);
                startActivity(intent);
            }


        });


    }

    private String FinalURL(String home,String[] movie_name) {
        int l=movie_name.length;
        int a=0;
        String m=home;
        while(a!=l){

            m+=movie_name[a];
            a++;
            m+="+";
        }
        return m;
    }

    private String[] split(String movie_name) {
        return movie_name.split("\\s+");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class Mytask extends AsyncTask<String,String[],String[]> {
        String[] s=null;
        Document document;
        ArrayAdapter<String> adapter;


        @Override
        protected String[] doInBackground(String... strings) {
            try {

                if (Jsoup.connect(strings[0]).execute().statusCode()==200) {
                    document = Jsoup.connect(strings[0]).get();
                }

                else if(Jsoup.connect(strings[1]).execute().statusCode()==200){
                    document = Jsoup.connect(strings[1]).get();
                }
                else{
                    web=s;
                    return s;
                }

                parser=new Jsoup_parser(document,0);
                web=parser.getMovie_name();
                movie_name=parser.getMovie_name();
                movie_link=parser.getMovie_link();
                return web;
            } catch (IOException e) {
                e.printStackTrace();
            }


        return s;

        }


        @Override
        protected void onPreExecute() {
            adapter=(ArrayAdapter<String>)list.getAdapter();
        }

        @Override
        protected void onPostExecute(String[] s) {
            if(s!=null) {
                rlayout.setVisibility(View.GONE);
                CustomList adapter = new CustomList(MainActivity.this, s);
                list.setAdapter(adapter);
            }
            else{
                Toast.makeText(MainActivity.this, "Failed to fetch The Movie, Please try again after some Time.........", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(String[]... values) {

        }
    }

}