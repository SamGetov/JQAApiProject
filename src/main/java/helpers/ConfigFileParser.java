package helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigFileParser {

    public static String baseURL;
    public static String loginURL;
    public static String registerURL;
    public static String userIdURL;
    public static String email;
    public static String password;
    public static String name;


    public void configCredentialsParsing() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        FileReader reader = new FileReader("C:/Users/Samuil/Desktop/JQA API Project/java-api-project-Samuil" +
                "/src/main/java/configFile/config.json");
        Object object = jsonparser.parse(reader);
        JSONObject configJsonObj = (JSONObject) object;

        loginURL = (String) configJsonObj.get("loginUrl");
        registerURL = (String) configJsonObj.get("registerUrl");
        email = (String) configJsonObj.get("email");
        password = (String) configJsonObj.get("password");
        name = (String) configJsonObj.get("name");
        baseURL = (String) configJsonObj.get("baseURL");

    }

    public void loginURLSet(String loginURL) {
        this.loginURL = loginURL;
    }

    public static String loginURLGet() {
        return loginURL;
    }

    public void registerURLSet(String registerURL) {
        this.registerURL = registerURL;
    }

    public static String registerURLGet() {
        return registerURL;
    }

    public void nameSet(String name) {
        this.name = name;
    }

    public static String nameGet() {
        return name;
    }

    public void emailSet(String email) {
        this.email = email;
    }

    public static String emailGet() {
        return email;
    }

    public void passwordSet(String password) {
        this.password = password;
    }

    public static String passwordGet() {
        return password;
    }

    public void baseURLSet(String baseURL) {
        this.baseURL = baseURL;
    }

    public static String baseURLGet() {
        return baseURL;
    }

    public void userIdURLSet(String userIdURL) {
        this.userIdURL = userIdURL;
    }
    public static String userIdURLGet() {
        return userIdURL;
    }

    @Test
    public void test() throws IOException, ParseException {
        configCredentialsParsing();
        System.out.println(baseURL + loginURL);
        System.out.println(baseURL + registerURL);
        System.out.println(name);
        System.out.println(email);
        System.out.println(password);
    }
}
