package model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class news {
    @SerializedName("articles")
    public ArrayList<article> mArticles;
    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public int totalResults;

}
