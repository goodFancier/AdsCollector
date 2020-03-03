package ru.businessculture.adscollector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class Coordinates {

    /**
     * Широта
     */
    public Double lat;

    /**
     * Долгота
     */
    public Double lng;

}
