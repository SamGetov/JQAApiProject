package tests;

import api.GetRequests;
import api.PostRequests;
import helpers.ConfigFileParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {
    private static String baseURL;
    private static String loginURL;
    private static String registerURL;
    private static String email;
    private static String password;
    private static String name;

    //    @Test
//    public static void testSuccessfulLogin() throws IOException {
//        PostRequests postRequests = new PostRequests();
//        postRequests.login(email, password);
//        String responseCode = postRequests.getResponseCode();
//        Assert.assertTrue(responseCode.contains("200"), responseCode);
//        String authMessage = postRequests.getLoginMessage();
//        Assert.assertTrue(authMessage.contains("success"), authMessage);
//    }
//
//    @Test
//    public static void testWrongPassword() throws IOException {
//        PostRequests postRequests = new PostRequests();
//        postRequests.login(email, "123450");
//        String responseCode = postRequests.getResponseCode();
//        Assert.assertTrue(responseCode.contains("200"), responseCode);
//        String authMessage = postRequests.getLoginMessage();
//        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
//    }
//
//    @Test
//    public static void testWrongUsername() throws IOException {
//        PostRequests postRequests = new PostRequests();
//        postRequests.login("test@test.com", password);
//        String responseCode = postRequests.getResponseCode();
//        Assert.assertTrue(responseCode.contains("200"), responseCode);
//        String authMessage = postRequests.getLoginMessage();
//        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
//    }
    @BeforeTest
    public static void credentials() throws IOException, ParseException {
        ConfigFileParser configFileParser = new ConfigFileParser();
        configFileParser.configCredentialsParsing();
        baseURL = ConfigFileParser.baseURLGet();
        loginURL = ConfigFileParser.loginURLGet();
        registerURL = ConfigFileParser.registerURLGet();
        email = ConfigFileParser.emailGet();
        password = ConfigFileParser.passwordGet();
        name = ConfigFileParser.nameGet();
    }

    @Test
    public static void testRegisterNewAcc() throws IOException, ParseException {
        PostRequests postRequests = new PostRequests();
        postRequests.register(name, baseURL, registerURL, email, password);// TODO to be fixed
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test
    public static void testSuccessfulLoginNewAcc() throws IOException, ParseException {

        PostRequests postRequests = new PostRequests();
        PostRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        System.out.println(authMessage);
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }


}
