package jp.tomiyama.noir.qiitasample;

import com.squareup.moshi.Json;

public class User {

    @Json(name = "followees_count") // フォロー中の数
    private int followeesCount;

    @Json(name = "followers_count") // フォロワーの数
    private int followersCount;

    @Json(name = "id") // ユーザID
    private String id;

    @Json(name = "items_count") // 記事の数
    private int itemsCount;

    @Json(name = "name") // ユーザの名前
    private String name;

    public int getFolloweesCount() {
        return followeesCount;
    }

    public void setFolloweesCount(int followeesCount) {
        this.followeesCount = followeesCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public String getId() {
        return id;
    }

    public String showDetail(){

        return "followees_count : " + followeesCount +"\n" +
                "followees_count : " + followersCount + "\n" +
                "id : " + id + "\n" +
                "items_count : " + itemsCount + "\n" +
                "name : " + name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
