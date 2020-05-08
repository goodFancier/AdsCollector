package ru.businessculture.adscollector.service;

import org.springframework.stereotype.Service;
import ru.businessculture.adscollector.dto.Ads;
import ru.businessculture.adscollector.service.rules.FindDuplicateRule;
import ru.businessculture.adscollector.service.rules.FindTrulyCoordinates;
import ru.businessculture.adscollector.service.rules.IRule;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleManager {
    List<IRule> ruleList = new ArrayList<>();

    public RuleManager() {
        ruleList.add(new FindDuplicateRule());
        ruleList.add(new FindTrulyCoordinates());
    }

    public void applyRules(ArrayList<Ads> adsList) throws Exception {
        for (IRule rule : ruleList) {
            rule.applyRule(adsList);
        }
    }
}