package capstone.capstonetest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Configuration
public class GeocodingService {
    @Value("${kakao.api.key}")
    private String kakaoApiKey;
//    private final String apiKey;

    public double[] getCoordinatesFromAddress(String address) throws IOException {
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + URLEncoder.encode(address, "UTF-8");
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestProperty("Authorization", "KakaoAK " + kakaoApiKey);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String response = in.readLine();
        in.close();

        // JSON 파싱
        JSONObject json = new JSONObject(response);
        JSONArray documents = json.getJSONArray("documents");
        if (documents.length() > 0) {
            JSONObject location = documents.getJSONObject(0);
            double longitude = location.getJSONObject("address").getDouble("x");
            double latitude = location.getJSONObject("address").getDouble("y");
            return new double[]{latitude, longitude};
        } else {
            throw new IllegalArgumentException("주소를 찾을 수 없습니다.");
        }
    }
}
