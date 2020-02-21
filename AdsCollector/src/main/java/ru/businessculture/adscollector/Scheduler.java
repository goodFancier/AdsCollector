package ru.businessculture.adscollector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.businessculture.adscollector.controller.DataController;
import ru.businessculture.adscollector.controller.Settings;
import ru.businessculture.adscollector.dto.Ads;
import ru.businessculture.adscollector.dto.GetAllAdsResponse;
import ru.businessculture.adscollector.repository.AdsRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class Scheduler {

    @Autowired
    private DataController dataController;

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private Settings settings;

    @Value("${application.server.AdsTimePeriodPerRequestSec}")
    private Integer AdsTimePeriodPerRequestSec;

    @Value("${application.server.MaxAdsCountPerRequest}")
    private Integer MaxAdsCountPerRequest;

    @Scheduled(fixedDelayString = "${application.server.SchedulerIntervalSec}000")
    private void sendPost() throws Exception {
        Calendar endDate = GregorianCalendar.getInstance();
        endDate.add(Calendar.MINUTE, -31);
        Calendar startDate = GregorianCalendar.getInstance();
        startDate.setTime(settings.getLastDataLoadingDate());
        startDate.add(Calendar.MINUTE, -31);
        Calendar nowDate = (Calendar) startDate.clone();
        nowDate.add(Calendar.SECOND, AdsTimePeriodPerRequestSec);
        if (nowDate.after(endDate)) {
            nowDate.setTime(endDate.getTime());
            startDate.setTime(endDate.getTime());
            startDate.add(Calendar.SECOND, -AdsTimePeriodPerRequestSec);
        }
        String response = dataController.loadData(startDate, nowDate);

        ObjectMapper mapper = new ObjectMapper();
        GetAllAdsResponse ads = (GetAllAdsResponse) mapper.readValue(response, GetAllAdsResponse.class);
        Date lastDate = settings.getLastDataLoadingDate();
        Boolean needSave = false;
        if (ads.getCode() == 200) {
            if (ads.getData() != null) {
                needSave = (ads.getData().size() < MaxAdsCountPerRequest) || (AdsTimePeriodPerRequestSec <= 1);
                if (!needSave && AdsTimePeriodPerRequestSec > 1)
                    AdsTimePeriodPerRequestSec = AdsTimePeriodPerRequestSec / 2;
                else {
                    if (ads.getData().size() < MaxAdsCountPerRequest / 2)
                        AdsTimePeriodPerRequestSec = Math.round(AdsTimePeriodPerRequestSec * 1.6f);
                }
            }
            if (needSave) {
                adsRepository.saveAll(ads.getData());
                nowDate.add(Calendar.MINUTE, 31);
                settings.setLastDataLoadingDate(nowDate.getTime());
            }
        }
    }
}
