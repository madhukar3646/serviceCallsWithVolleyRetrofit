package com.m.retrofitexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by madhu on 8/2/2018.
 */

public class PostServiceModel {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_info")
    @Expose
    private UserInfo userInfo;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public class UserInfo {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("mobilenumber")
        @Expose
        private String mobilenumber;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("device_type")
        @Expose
        private String deviceType;
        @SerializedName("device_token")
        @Expose
        private String deviceToken;
        @SerializedName("facebookid")
        @Expose
        private Object facebookid;
        @SerializedName("twitterid")
        @Expose
        private Object twitterid;
        @SerializedName("googleid")
        @Expose
        private Object googleid;
        @SerializedName("profile_image")
        @Expose
        private String profileImage;
        @SerializedName("profession")
        @Expose
        private Object profession;
        @SerializedName("education")
        @Expose
        private Object education;
        @SerializedName("bio_pic")
        @Expose
        private Object bioPic;
        @SerializedName("cover_photo")
        @Expose
        private Object coverPhoto;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("date_birth")
        @Expose
        private Object dateBirth;
        @SerializedName("gender")
        @Expose
        private Object gender;
        @SerializedName("city")
        @Expose
        private Object city;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobilenumber() {
            return mobilenumber;
        }

        public void setMobilenumber(String mobilenumber) {
            this.mobilenumber = mobilenumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public Object getFacebookid() {
            return facebookid;
        }

        public void setFacebookid(Object facebookid) {
            this.facebookid = facebookid;
        }

        public Object getTwitterid() {
            return twitterid;
        }

        public void setTwitterid(Object twitterid) {
            this.twitterid = twitterid;
        }

        public Object getGoogleid() {
            return googleid;
        }

        public void setGoogleid(Object googleid) {
            this.googleid = googleid;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public Object getProfession() {
            return profession;
        }

        public void setProfession(Object profession) {
            this.profession = profession;
        }

        public Object getEducation() {
            return education;
        }

        public void setEducation(Object education) {
            this.education = education;
        }

        public Object getBioPic() {
            return bioPic;
        }

        public void setBioPic(Object bioPic) {
            this.bioPic = bioPic;
        }

        public Object getCoverPhoto() {
            return coverPhoto;
        }

        public void setCoverPhoto(Object coverPhoto) {
            this.coverPhoto = coverPhoto;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Object getDateBirth() {
            return dateBirth;
        }

        public void setDateBirth(Object dateBirth) {
            this.dateBirth = dateBirth;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }
    }
}
