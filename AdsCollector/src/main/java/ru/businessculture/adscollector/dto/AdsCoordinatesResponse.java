package ru.businessculture.adscollector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.Embeddable;
import java.util.Iterator;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class AdsCoordinatesResponse {

    @Getter
    @Setter
    Map<String, String> point;

    @Getter
    @Setter
    String authenticationResultCode;
}
