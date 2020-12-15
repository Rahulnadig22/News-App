package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;

import Adapter.NewsAdapter;
import Adapter.NewsCategoryAdapter;
import model.article;
import model.news;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsCategoryAdapter.CategoryClickListener,NewsAdapter.onNewsClicked {

    private final static String APIKEY = "9a27fad88cf4493aa0398c10b736da22";
    public static String KEY = "NEWS";

    private RecyclerView mRcNewsCategories;
    private RecyclerView mRcNews;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRcNewsCategories = findViewById(R.id.rc_categories);
        mRcNews = findViewById(R.id.rc_news);
        mRcNews.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        mRcNewsCategories.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        NewsCategoryAdapter categoryAdapter = new NewsCategoryAdapter(this);
        categoryAdapter.setListener(this);
        mRcNewsCategories.setAdapter(categoryAdapter);
        pd = new ProgressDialog(MainActivity.this);
        onGetEveryThingClicked();

    }

    public void onGetEveryThingClicked(){
        pd.setTitle("Loading News Please Wait If Taking Too Long Connect To Faster Network");
        pd.show();
        HashMap<String,Object> queries = new HashMap<>();
        queries.put("apiKey",APIKEY);
        queries.put("sources","google-news");
        ApiInterface apiInterface =ApiClient.getClient().create(ApiInterface.class);
        Call<news> callNews = apiInterface.getEveryNews(queries);
        callNews.enqueue(new Callback<news>() {
            @Override
            public void onResponse(Call<news> call, Response<news> response) {
                pd.hide();
                news news = response.body();
                setDataToRecycler(news.mArticles);

            }

            @Override
            public void onFailure(Call<news> call, Throwable t) {
                Log.i("Respone","Failure");
                pd.hide();

            }
        });

    }

    private void onCategorynews(String category){
        pd.setTitle("Getting you ".concat(category).concat(" News"));
        pd.show();
        HashMap<String ,Object> queries = new HashMap<>();
        queries.put("apiKey",APIKEY);
        queries.put("category",category);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<news> getCategoryNews = apiInterface.getTopHeadlines(queries);

        getCategoryNews.enqueue(new Callback<news>() {
            @Override
            public void onResponse(Call<news> call, Response<news> response) {
                pd.hide();
                news news = response.body();
                setDataToRecycler(news.mArticles);
            }

            @Override
            public void onFailure(Call<news> call, Throwable t) {
                Log.i("Respone","Failure");
                pd.hide();

            }
        });
    }

    private void setDataToRecycler(ArrayList<article> articles){
        NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this,articles);
        newsAdapter.setListener(this);
        mRcNews.setAdapter(newsAdapter);
    }

    @Override
    public void onCategoryClicked(String category) {
        onCategorynews(category);
    }

    @Override
    public void newsClicked(article particularArticle) {
        Intent news = new Intent(MainActivity.this,ViewNews.class);
        news.putExtra(KEY,particularArticle);
        startActivity(news);
        finish();
    }
}