package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import model.article;

public class ViewNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);

        TextView mTitle = findViewById(R.id.tv_title);
        TextView mAuthor = findViewById(R.id.tv_author);
        TextView mDescription = findViewById(R.id.tv_description);
        TextView mUrl = findViewById(R.id.tv_url);
        ImageView mUrlToImage = findViewById(R.id.iv_urlImage);
        TextView mPublishedAt = findViewById(R.id.tv_publishedAt);
        TextView mContent = findViewById(R.id.tv_content);

        Bundle data = getIntent().getExtras();
        article particularArticle = (article) data.getSerializable(MainActivity.KEY);

        mTitle.setText(particularArticle.title);
        mAuthor.setText(particularArticle.author);
        mDescription.setText(particularArticle.description);
        mUrl.setText(particularArticle.url);
        Glide.with(ViewNews.this).load(particularArticle.urlToimage).placeholder(R.drawable.ic_launcher_background).into(mUrlToImage);
        mPublishedAt.setText(particularArticle.publishedAt);
        mContent.setText(particularArticle.content);

    }
}