package org.ina.p4.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bean (POJO) for managing feed URLs.
 * Stores a list of feed URLs associated with a website.
 */
@Entity
public class FeedBean implements Serializable {
    private List<String> feedUrls = new ArrayList<>();

    protected Long id;
    @Id
    @GeneratedValue
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<String> getFeedUrls(){return this.feedUrls;}
    public void setFeedUrls(List<String> feedUrls){this.feedUrls = feedUrls;}
}
