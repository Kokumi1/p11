
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
    "game_count",
    "games"
})
public class ResponseGame {

    @JsonProperty("game_count")
    private Integer gameCount;
    @JsonProperty("games")
    private List<Game> games = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("game_count")
    public Integer getGameCount() {
        return gameCount;
    }

    @JsonProperty("game_count")
    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    @JsonProperty("games")
    public List<Game> getGames() {
        return games;
    }

    @JsonProperty("games")
    public void setGames(List<Game> games) {
        this.games = games;
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
