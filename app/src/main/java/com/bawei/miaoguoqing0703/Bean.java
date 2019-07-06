package com.bawei.miaoguoqing0703;
//Bean包
public class Bean {
    private String imageUrl;
    private String title;

    @Override
    public String toString() {
        return "Bean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bean(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }
}
