package com.example.chongryong.friendly_eye_mover;

/**
 * Created by xxxx on 11/10/2015.
 */
public class ItemData {
    String text;
    Integer imageId;

    public ItemData(String text, Integer imageId) {
        this.text = text;
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public Integer getImageId() {
        return imageId;
    }
}
