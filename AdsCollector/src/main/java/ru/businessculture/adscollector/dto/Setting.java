package ru.businessculture.adscollector.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Setting {
    @Id
    private String name;
    private String value;

    public Setting()
    {};

    public Setting(String name, String value)
    {
      this.name = name;
      this.value = value;
    }
}
