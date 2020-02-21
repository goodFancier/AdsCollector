package ru.businessculture.adscollector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllAdsResponse {
    protected Integer Code;
    protected ArrayList<Ads> data;
}
