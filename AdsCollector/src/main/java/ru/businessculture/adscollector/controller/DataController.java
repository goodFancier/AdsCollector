package ru.businessculture.adscollector.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.businessculture.adscollector.utils.DateUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

@Controller
public class DataController {

    @Value("${application.server.url}")
    private String serverUrl;

    @Value("${application.server.user}")
    private String user;

    @Value("${application.server.token}")
    private String token;

    @Value("${application.server.CategoryIds}")
    private String categoryIds;

    @Value("${application.server.AdsType}")
    private String adsType;

    @Autowired
    private Settings settings;

    public String sendGet(String url, RequestConfig configTimeouts) throws Exception {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
        StringBuilder res = new StringBuilder();
        BufferedReader rd = null;
        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(configTimeouts).setSSLSocketFactory(
                sslsf).build()) {
            HttpRequestBase request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
            String line = "";
            while ((line = rd.readLine()) != null)
                res.append(line);
        } catch (IOException e) {
            // TODO: Обработать исключение
        } finally {
            if (rd != null)
                rd.close();
        }
        return res.toString();
    }

    public RequestConfig getConfigTimeouts() {
        final int timeoutSec = 60;
        return RequestConfig
                .custom()
                .setConnectTimeout(timeoutSec * 1000)
                .setConnectionRequestTimeout(timeoutSec * 1000)
                .setSocketTimeout(timeoutSec * 1000)
                .build();
    }

    public String loadData(Calendar startDate, Calendar endDate) throws Exception {
        String response;
        try {
            String url = serverUrl;
            int i = 0;
            do {
                String request = String.format(url + "/main/api?user=%s&token=%s&category_id=%s&nedvigimost_type=%s&date1=%s&date2=%s",
                        URLEncoder.encode(user, "ASCII"),
                        token,
                        categoryIds,
                        adsType,
                        URLEncoder.encode(DateUtils.getSimpleDateFormat().format(startDate.getTime()), "ASCII"),
                        URLEncoder.encode(DateUtils.getSimpleDateFormat().format(endDate.getTime()), "ASCII"));
                response = sendGet(request, getConfigTimeouts());
                i++;
            }
            while (response.equals("") && i < 5);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(String.format("Кодировка %s не поддерживается", StandardCharsets.UTF_8), e);
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    private JSONObject getJsonObject() {
        JSONObject json = new JSONObject();
        json.put("user", user);
        json.put("token", token);
        return json;
    }
}
