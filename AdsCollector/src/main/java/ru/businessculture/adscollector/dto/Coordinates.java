package ru.businessculture.adscollector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class Coordinates {
    protected Double lat;
    protected Double lng;
}
