
package com.debruyckere.florian.steamnews.model.generatedclass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gid",
    "title",
    "url",
    "is_external_url",
    "author",
    "contents",
    "feedlabel",
    "date",
    "feedname",
    "feed_type",
    "appid",
    "tags"
})
public class Newsitem {

    @JsonProperty("gid")
    private String gid;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("is_external_url")
    private Boolean isExternalUrl;
    @JsonProperty("author")
    private String author;
    @JsonProperty("contents")
    private String contents;
    @JsonProperty("feedlabel")
    private String feedlabel;
    @JsonProperty("date")
    private Integer date;
    @JsonProperty("feedname")
    private String feedname;
    @JsonProperty("feed_type")
    private Integer feedType;
    @JsonProperty("appid")
    private Integer appid;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gid")
    public String getGid() {
        return gid;
    }

    @JsonProperty("gid")
    public void setGid(String gid) {
        this.gid = gid;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("is_external_url")
    public Boolean getIsExternalUrl() {
        return isExternalUrl;
    }

    @JsonProperty("is_external_url")
    public void setIsExternalUrl(Boolean isExternalUrl) {
        this.isExternalUrl = isExternalUrl;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("contents")
    public String getContents() {
        return contents;
    }

    @JsonProperty("contents")
    public void setContents(String contents) {
        this.contents = contents;
    }

    @JsonProperty("feedlabel")
    public String getFeedlabel() {
        return feedlabel;
    }

    @JsonProperty("feedlabel")
    public void setFeedlabel(String feedlabel) {
        this.feedlabel = feedlabel;
    }

    @JsonProperty("date")
    public Integer getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Integer date) {
        this.date = date;
    }

    @JsonProperty("feedname")
    public String getFeedname() {
        return feedname;
    }

    @JsonProperty("feedname")
    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    @JsonProperty("feed_type")
    public Integer getFeedType() {
        return feedType;
    }

    @JsonProperty("feed_type")
    public void setFeedType(Integer feedType) {
        this.feedType = feedType;
    }

    @JsonProperty("appid")
    public Integer getAppid() {
        return appid;
    }

    @JsonProperty("appid")
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
