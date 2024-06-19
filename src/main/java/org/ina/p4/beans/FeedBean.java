package org.ina.p4.beans;

import java.util.ArrayList;
import java.util.List;

public class FeedBean {
    private List<String> feedUrls = new ArrayList<>();

    public List<String> getFeedUrls(){return this.feedUrls;}
    public void setFeedUrls(List<String> feedUrls){this.feedUrls = feedUrls;}
}
