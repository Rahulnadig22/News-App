package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;


import java.util.ArrayList;

import model.article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsListHolder> {

    private final Context context;
    private final ArrayList<article> articles;

    private onNewsClicked listener;

    public NewsAdapter(Context context, ArrayList<article> articles){
        this.context = context;
        this.articles = articles;
    }

    public void setListener(onNewsClicked listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsListHolder(LayoutInflater.from(context).inflate(R.layout.cell_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListHolder holder, int position) {
        article item = articles.get(position);
        if(item!=null){
            Glide.with(context).load(item.urlToimage).placeholder(R.drawable.ic_launcher_background).into(holder.mIvNewsImg);
            holder.mTvNewsTitle.setText(item.title);
            holder.mTvNewsDescription.setText(item.description);
        }
        holder.mllnews.setOnClickListener(v -> {
            if(listener!=null){
                listener.newsClicked(articles.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class NewsListHolder extends RecyclerView.ViewHolder{
        private final ImageView mIvNewsImg;
        private final TextView mTvNewsTitle;
        private final TextView mTvNewsDescription;

        private final LinearLayout mllnews;
        public NewsListHolder(@NonNull View itemView) {
            super(itemView);
            mIvNewsImg = itemView.findViewById(R.id.iv_news_image);
            mTvNewsTitle = itemView.findViewById(R.id.tv_news_title);
            mTvNewsDescription = itemView.findViewById(R.id.tv_news_description);
            mllnews = itemView.findViewById(R.id.ll_news);
        }
    }

    public interface onNewsClicked{
        void newsClicked(article particularArticle);
    }
}
