package com.Sunokitaab.sunokitaab.rss;


import java.util.List;

public class RSSObject {

    public String status;
    public Feed feed;
    public List<Item> items;
    public Enclosure enclosure;

    public RSSObject(String status, Feed feed, List<Item> items, Enclosure enclosure) {
        this.status = status;
        this.feed = feed;
        this.items = items;
        this.enclosure = enclosure;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

