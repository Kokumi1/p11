
package com.debruyckere.florian.steamnews.model.generatedclass;

import java.util.HashMap;
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
    "playtime_forever",
    "playtime_windows_forever",
    "playtime_mac_forever",
    "playtime_linux_forever",
    "playtime_2weeks"
})
public class Game {

    @JsonProperty("appid")
    private Integer appid;
    @JsonProperty("playtime_forever")
    private Integer playtimeForever;
    @JsonProperty("playtime_windows_forever")
    private Integer playtimeWindowsForever;
    @JsonProperty("playtime_mac_forever")
    private Integer playtimeMacForever;
    @JsonProperty("playtime_linux_forever")
    private Integer playtimeLinuxForever;
    @JsonProperty("playtime_2weeks")
    private Integer playtime2weeks;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("appid")
    public Integer getAppid() {
        return appid;
    }

    @JsonProperty("appid")
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    @JsonProperty("playtime_forever")
    public Integer getPlaytimeForever() {
        return playtimeForever;
    }

    @JsonProperty("playtime_forever")
    public void setPlaytimeForever(Integer playtimeForever) {
        this.playtimeForever = playtimeForever;
    }

    @JsonProperty("playtime_windows_forever")
    public Integer getPlaytimeWindowsForever() {
        return playtimeWindowsForever;
    }

    @JsonProperty("playtime_windows_forever")
    public void setPlaytimeWindowsForever(Integer playtimeWindowsForever) {
        this.playtimeWindowsForever = playtimeWindowsForever;
    }

    @JsonProperty("playtime_mac_forever")
    public Integer getPlaytimeMacForever() {
        return playtimeMacForever;
    }

    @JsonProperty("playtime_mac_forever")
    public void setPlaytimeMacForever(Integer playtimeMacForever) {
        this.playtimeMacForever = playtimeMacForever;
    }

    @JsonProperty("playtime_linux_forever")
    public Integer getPlaytimeLinuxForever() {
        return playtimeLinuxForever;
    }

    @JsonProperty("playtime_linux_forever")
    public void setPlaytimeLinuxForever(Integer playtimeLinuxForever) {
        this.playtimeLinuxForever = playtimeLinuxForever;
    }

    @JsonProperty("playtime_2weeks")
    public Integer getPlaytime2weeks() {
        return playtime2weeks;
    }

    @JsonProperty("playtime_2weeks")
    public void setPlaytime2weeks(Integer playtime2weeks) {
        this.playtime2weeks = playtime2weeks;
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
