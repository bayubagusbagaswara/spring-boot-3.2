package com.bayu.springboot32.forms;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubscriberRequest(
        String email,
        String formId,
        @JsonProperty("api_key")
        String apiKey

) {
}
