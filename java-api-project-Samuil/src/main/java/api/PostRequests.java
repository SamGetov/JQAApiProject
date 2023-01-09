package api;

import java.io.IOException;
import java.io.InputStream;

import helpers.ConfigFileParser;
import helpers.JsonParserOld;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;

public class PostRequests  {
    private static String responseCode;
    private static String responseBody;
    private static String accessToken;
    private static String authMessage;
    private static String userId;

    private static String baseURL;
    private static String loginURL;


    public static void login( String email, String password) throws IOException, ParseException {
        // Build the post request
        ConfigFileParser configFileParser= new ConfigFileParser();
        configFileParser.configCredentialsParsing();
        String postBody = "{\"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";
        HttpPost postLogin = new HttpPost(configFileParser.baseURLGet() + configFileParser.loginURLGet());
        postLogin.setEntity(new StringEntity(postBody));
        postLogin.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postLogin);
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            String responseBodyText = responseBody;
            instream.close();
        }
        // Extract and set the access token
        if (responseCode.contains("200")) {
            JsonParserOld json = new JsonParserOld();
            String authCode = JsonParserOld.getResponseCode(responseBody);
            authMessage = JsonParserOld.getAuthMessage(responseBody);
            if (authCode.equals("0")) {
                accessToken = JsonParserOld.getAccessToken(responseBody);
                userId = JsonParserOld.getUserId(responseBody);
            }
        }

    }

    public static void register(String name, String baseURL, String registerURL, String email, String password) throws IOException, ParseException {
        // Build the post request
        ConfigFileParser configFileParser = new ConfigFileParser();
        configFileParser.configCredentialsParsing();
        String postBody = "{\"name\":\"" + name + "\"," + " \"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";
        HttpPost postRegister = new HttpPost(configFileParser.baseURLGet() + configFileParser.registerURLGet());
        postRegister.setEntity(new StringEntity(postBody));
        postRegister.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postRegister);
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
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
            String authCode = JsonParserOld.getResponseCode(responseBody);
            authMessage = JsonParserOld.getAuthMessage(responseBody);
            if (authCode.equals("0")) {
                accessToken = JsonParserOld.getAccessToken(responseBody);
            }
        }
    }


    public static String getAccessToken() {
        return accessToken;
    }

    public static String getResponseCode() {
        return responseCode;
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
