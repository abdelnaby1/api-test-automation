package com.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateLimit {
    private int coreLimit;
    private int searchLimit;

    public int getCoreLimit() {
        return coreLimit;
    }

    public int getSearchLimit() {
        return searchLimit;
    }

    @JsonProperty("resources")
    private void unmarshallNested(Map<String, Object> resources) {
        Map<String, Integer> core = (Map<String, Integer>) resources.get("core");
        coreLimit = core.get("limit");

        Map<String, Integer> search = (Map<String, Integer>) resources.get("search");
        searchLimit = search.get("limit");
    }
}
