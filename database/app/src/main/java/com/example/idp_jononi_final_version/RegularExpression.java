package com.example.idp_jononi_final_version;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    public RegularExpression(String testString) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]+";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(testString);
        while (matcher.find()) {
            // Print the substring of each match
            String message= "Your email is not correct";
            System.out.println(message);
        }

    }

}
