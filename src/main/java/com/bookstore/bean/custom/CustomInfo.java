package com.bookstore.bean.custom;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomInfo {

    private Map<String, String> details;

    @JsonAnyGetter
    public Map<String, String> getDetails() {
        return details;
    }

    @JsonAnySetter
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
