package com.bayu.springboot32.forms;

import com.bayu.springboot32.ConvertKitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping(path = "/api/forms")
public class FormsController {

    private static final Logger log = LoggerFactory.getLogger(FormsController.class);

    private ConvertKitProperties ckProps;
    private final RestClient restClient;

    public FormsController(ConvertKitProperties ckProps) {
        this.ckProps = ckProps;
        this.restClient = RestClient.create(ckProps.url());
    }

    @PostMapping(path = "/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscriberRequest subscriberRequest) {
        var body = new SubscriberRequest(
                subscriberRequest.email(),
                subscriberRequest.formId(),
                ckProps.apiKey());

        ResponseEntity<String> response = restClient.post()
                .uri("/forms/" + body.formId() + "/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        log.info("Subscribe {}", response);
        return response;
    }

}
