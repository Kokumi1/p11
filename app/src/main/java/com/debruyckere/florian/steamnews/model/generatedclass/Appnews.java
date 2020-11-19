
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
    "appid",
    "newsitems",
    "count"
})
public class Appnews {

    @JsonProperty("appid")
    private Integer appid;
    @JsonProperty("newsitems")
    private List<Newsitem> newsitems = null;
    @JsonProperty("count")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("appid")
    public Integer getAppid() {
        return appid;
    }

    @JsonProperty("appid")
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    @JsonProperty("newsitems")
    public List<Newsitem> getNewsitems() {
        return newsitems;
    }

    @JsonProperty("newsitems")
    public void setNewsitems(List<Newsitem> newsitems) {
        this.newsitems = newsitems;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
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
