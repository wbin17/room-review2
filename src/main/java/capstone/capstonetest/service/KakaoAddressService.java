package capstone.capstonetest.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KakaoAddressService {
    private final String API_KEY = "26cc4dc1b2c4d98f7d8dae772fddcd85";
    private final String API_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    public List<String> searchAddress(String query) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("query", query)
                .build()
                .toString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray documents = jsonObject.getJSONArray("documents");

        return documents.toList().stream()
                .map(obj -> (String) ((JSONObject) obj).get("address_name"))
                .collect(Collectors.toList());
    }
}
