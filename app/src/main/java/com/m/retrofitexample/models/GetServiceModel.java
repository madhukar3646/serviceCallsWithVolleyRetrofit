package com.m.retrofitexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by madhu on 8/2/2018.
 */

public class GetServiceModel {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("cuisines")
    @Expose
    private List<Cuisine> cuisines = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public class Cuisine {

        @SerializedName("cuisine_id")
        @Expose
        private String cuisineId;
        @SerializedName("cuisine_name")
        @Expose
        private String cuisineName;

        public String getCuisineId() {
            return cuisineId;
        }

        public void setCuisineId(String cuisineId) {
            this.cuisineId = cuisineId;
        }

        public String getCuisineName() {
            return cuisineName;
        }

        public void setCuisineName(String cuisineName) {
            this.cuisineName = cuisineName;
        }

    }
}
