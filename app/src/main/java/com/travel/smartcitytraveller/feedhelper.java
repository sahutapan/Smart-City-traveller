package com.travel.smartcitytraveller;

public class feedhelper {

    String feed,name;
   public feedhelper(){}

    public feedhelper(String name,String feed) {
        this.name = name;
        this.feed = feed;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
