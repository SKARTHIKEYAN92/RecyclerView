package com.gsk.www.recyclerview1;

/**
 * Created by DELL on 21-03-2018.
 */

public class ListItem {
    private String head;
    private String desc;
    private String imageUrl;

    public ListItem(String display_name, String head, String desc) {
        this.head = head;
        this.desc = desc;
        this.imageUrl = imageUrl;

    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;}

        public String getImageUrl()
        {
            return  imageUrl;
        }

    }

