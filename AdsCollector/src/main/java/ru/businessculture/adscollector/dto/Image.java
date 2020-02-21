package ru.businessculture.adscollector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Image {
    @Id
    protected  Integer Id;
    protected  String imgurl;
}
