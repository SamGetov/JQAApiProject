package api;

import helpers.ConfigFileParser;
import helpers.JsonParserOld;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

public class GetRequests {

    private static String responseCode;
    private static String responseBody;
    private static String authMessage;
    private static String accessToken;
    private static String userId;
    private static String baseURL;
    private static String loginURL;
    private static String registerURL;
    private static String email;
    private static String password;
    private static String name;

    public static void main(String[] args) throws IOException {

    }


    public static String  GetUserId(String baseURL, String loginURL, String userId, String idURL) throws IOException, ParseException {
        ConfigFileParser configFileParser = new ConfigFileParser();
        configFileParser.configCredentialsParsing();
        HttpGet getUsers = new HttpGet(configFileParser.baseURLGet()+userId);
        getUsers.setHeader("Content-type", "application/json");
        getUsers.setHeader("Authorization", accessToken);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getUsers);
        responseCode = response.getStatusLine().toString();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        // Extract and set the access token
        if (responseCode.contains("200")) {
            JsonParserOld json = new JsonParserOld();
            GetRequests.userId = json.getUserId(responseBody);
        }
        return GetRequests.userId;
    }




    public static String getResponseCode() {
        return responseCode;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getResponseBody() {
        return responseCode;
    }

    public static String getLoginMessage() {
        return authMessage;
    }

    public static String getUserId() {
        return userId;
    }
}
