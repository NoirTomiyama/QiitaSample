package jp.tomiyama.noir.qiitasample;


import com.squareup.moshi.Json;

public class ItemEntity {

    @Json(name = "id") // 記事ID
    private String id;

    @Json(name = "title") // タイトル
    private String title;

    @Json(name = "body") // 記事の中身
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
