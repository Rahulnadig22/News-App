package model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

public class article  implements Serializable {
    @SerializedName("author")
    public String author;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String urlToimage;
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("content")
    public String content;
    @SerializedName("source")
    public source source;

}
