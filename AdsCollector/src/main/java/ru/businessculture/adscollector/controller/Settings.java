package ru.businessculture.adscollector.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.businessculture.adscollector.dto.Setting;
import ru.businessculture.adscollector.repository.SettingsRepository;
import ru.businessculture.adscollector.utils.DateUtils;

import javax.persistence.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@Controller
public class Settings
{
    private static String LastDataLoadingDateParamName = "LastDataLoadingDate";
    private static Calendar LastDataLoadingDateDefaultValue = new GregorianCalendar(2020, Calendar.JANUARY, 1);

    @Autowired
    private SettingsRepository settingsRepository;

    private Setting lastDataLoadingDate;

    public Date getLastDataLoadingDate()
    {
        try
        {
            lastDataLoadingDate = settingsRepository.findById(LastDataLoadingDateParamName).orElse(
                    new Setting(LastDataLoadingDateParamName, DateUtils.getSimpleDateFormat().format(LastDataLoadingDateDefaultValue.getTime())));
            return DateUtils.getSimpleDateFormat().parse(lastDataLoadingDate.getValue());
        }
        catch (ParseException e)
        {
            return LastDataLoadingDateDefaultValue.getTime();
        }
    }

    public void setLastDataLoadingDate(Date value)
    {
        if (lastDataLoadingDate == null)
            getLastDataLoadingDate();
        lastDataLoadingDate.setValue(DateUtils.getSimpleDateFormat().format(value));
        settingsRepository.save(lastDataLoadingDate);
    }
}
