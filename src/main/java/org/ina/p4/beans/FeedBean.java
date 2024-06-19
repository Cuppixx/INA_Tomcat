package org.ina.p4.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bean (POJO) for managing feed URLs.
 * Stores a list of feed URLs associated with a website.
 */
public class FeedBean implements Serializable {
    private List<String> feedUrls = new ArrayList<>();

    public List<String> getFeedUrls(){return this.feedUrls;}
    public void setFeedUrls(List<String> feedUrls){this.feedUrls = feedUrls;}
}
