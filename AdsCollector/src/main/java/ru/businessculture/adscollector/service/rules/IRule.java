package ru.businessculture.adscollector.service.rules;

import ru.businessculture.adscollector.dto.Ads;

import java.util.List;

public interface IRule {
    void applyRule(List<Ads> adsList) throws Exception;
}
