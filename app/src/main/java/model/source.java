package model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

public class source implements Serializable {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

}
