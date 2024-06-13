package org.ina.p3.beans;

/**
 * The {@code InputBean} class is a JavaBean component that holds user input data.
 * It encapsulates the properties for 'name' and multiple URLs ('url', 'url2', 'url3'),
 * providing getters and setters for each. The getters return a default message "No Input"
 * if the corresponding property is null or empty.
 */
public class InputBean {
    private static final String EMPTY = "No Input";
    private String name;
    private String url;
    private String url2;
    private String url3;

    public String getName() {return (name == null || name.isEmpty()) ? EMPTY : name;}
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {return (url == null || url.isEmpty()) ? EMPTY : url;}
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {return (url2 == null || url2.isEmpty()) ? EMPTY : url2;}
    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {return (url3 == null || url3.isEmpty()) ? EMPTY : url3;}
    public void setUrl3(String url3) {
        this.url3 = url3;
    }
}
