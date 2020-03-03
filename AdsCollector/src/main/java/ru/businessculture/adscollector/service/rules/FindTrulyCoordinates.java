package ru.businessculture.adscollector.service.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.stereotype.Component;
import ru.businessculture.adscollector.dto.Ads;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class FindTrulyCoordinates implements IRule {

    @Override
    public void applyRule(List<Ads> adsList) throws Exception {
        String response = sendGet("http://dev.virtualearth.net/REST/v1/Locations/RU/Moscow?key=AtxwwHUGYk49B1yXRHQlgA8vHPvJzLv-EGC21D68thQIa-vfGCwxBlrsFxjXemxN", getConfigTimeouts());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(response, Map.class);
        List<Object> objectList = ((ArrayList<Object>) (map.get("resourceSets")));
        LinkedHashMap<Object, Object> resourceSetsMap = (LinkedHashMap<Object, Object>) ((ArrayList<Object>) (map.get("resourceSets"))).get(0);
        LinkedHashMap<Object, Object> resourcesMap = (LinkedHashMap<Object, Object>) ((ArrayList<Object>) (resourceSetsMap.get("resources"))).get(0);
        LinkedHashMap<Object, Object> pointMap = (LinkedHashMap<Object, Object>) resourcesMap.get("point");
        String x = ((ArrayList<Object>) (pointMap.get("coordinates"))).get(0).toString();
        String y = ((ArrayList<Object>) (pointMap.get("coordinates"))).get(1).toString();
        System.out.println(x + ";" + y);
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
        } /*finally {
            if (rd != null)
                rd.close();
        }*/
        return res.toString();
    }
}
