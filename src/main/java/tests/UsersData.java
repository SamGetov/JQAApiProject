package tests;

import api.GetRequests;
import api.PostRequests;
import helpers.ConfigFileParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class UsersData {

    private static String password;
    private static String name;
    private static String idURL;
    private static String email;
    private static String userId;
    private static String token;
    private static String loginURL;
    private static String baseURL;
    private static String registerURL;
    private static String userIdURL;


    //    @BeforeTest
//    public static void credentials() throws IOException, ParseException {
//        email = "samgg15@mail.bg";
//        password = "123456";
//        name = "SamGG15";
//        idURL = "http://restapi.adequateshop.com/api/users/";
//
//        PostRequests postRequests = new PostRequests();
//        postRequests.login();
//        String responseCode = postRequests.getResponseCode();
//        Assert.assertTrue(responseCode.contains("200"), responseCode);
//        String authMessage = postRequests.getLoginMessage();
//        Assert.assertTrue(authMessage.contains("success"), authMessage);
//        token = postRequests.getAccessToken();
//        userId = postRequests.getUserId();
//    }
    @BeforeTest
    public static void credentials() throws IOException, ParseException {
        ConfigFileParser configFileParser = new ConfigFileParser();
        configFileParser.configCredentialsParsing();
        baseURL = ConfigFileParser.baseURLGet();
        loginURL = ConfigFileParser.loginURLGet();
        registerURL = ConfigFileParser.registerURLGet();
        userIdURL = ConfigFileParser.userIdURLGet();
        email = ConfigFileParser.emailGet();
        password = ConfigFileParser.passwordGet();
        name = ConfigFileParser.nameGet();

    }

    @Test
    public static void testGetRequestWithUserId() throws IOException, ParseException {

        GetRequests getRequests = new GetRequests();
        String currentId = getRequests.GetUserId(baseURL,loginURL, userId, token);
        String responseCode = getRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);

        Assert.assertEquals(userId, currentId);

    }

}
