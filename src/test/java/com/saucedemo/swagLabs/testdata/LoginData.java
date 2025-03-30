package com.saucedemo.swagLabs.testdata;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginData {

    public String validUsername, inValidUsername, emptyUsername
    ,validPassword, invalidPassword, emptyPassword, invalidUsernameOrPassword, usrReqErrMsg, passReqErrMsg;

    public void data() throws IOException, ParseException {
        String srcFilePath = System.getProperty("user.dir") + "/src/test/resources/testdata/LoginJsonData.json";
        File file = new File(srcFilePath);

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file));


        for (Object object : jsonArray) {
            JSONObject user = (JSONObject) object;

            validUsername = (String) user.get("validUsername");
            inValidUsername = (String) user.get("inValidUsername");
            emptyUsername = (String) user.get("emptyUsername");
            validPassword = (String) user.get("validPassword");
            invalidPassword = (String) user.get("invalidPassword");
            emptyPassword = (String) user.get("emptyPassword");

            invalidUsernameOrPassword = (String) user.get("invalidUsernameOrPassword");
            usrReqErrMsg = (String) user.get("usernameRequired");
            passReqErrMsg = (String) user.get("passwordRequired");
        }
    }
}

