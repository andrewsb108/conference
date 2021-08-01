package com.spring.project;

import java.util.Locale;
import java.util.ResourceBundle;

public class ViewRegistration {
    public static final String RESOURSE_BUNDLE_NAME = "message";
    public static ResourceBundle bundle = ResourceBundle.getBundle(RESOURSE_BUNDLE_NAME);

    public static String howToLogin;
    public static String loginAsUser;
    public static String loginAsAdmin;
    public static String loggedInAsUser;
    public static String loggedInAsAdmin;


    public static void initStringFields(ResourceBundle bundle) {
        howToLogin = bundle.getString(TextConstants.HOW_DO_YOU_WISH_TO_LOGIN);
        loginAsUser = bundle.getString(TextConstants.LOGIN_AS_USER);
        loginAsAdmin = bundle.getString(TextConstants.LOGIN_AS_ADMIN);
        loggedInAsUser = bundle.getString(TextConstants.LOGGED_IN_AS_USER);
        loggedInAsAdmin = bundle.getString(TextConstants.LOGGED_IN_AS_ADMIN);
    }

    public ViewRegistration() {
    }

    public static void changeLanguageToUa() {
        bundle = ResourceBundle.getBundle(RESOURSE_BUNDLE_NAME, new Locale("ua", "UA"));
        initStringFields(bundle);
    }

    public static void changeLanguageToEn() {
        bundle = ResourceBundle.getBundle(RESOURSE_BUNDLE_NAME, new Locale("en", "EN"));
        initStringFields(bundle);
    }

    public static String getHowToLogin() {
        return howToLogin;
    }

    public static String getLoginAsUser() {
        return loginAsUser;
    }

    public static String getLoginAsAdmin() {
        return loginAsAdmin;
    }

    public static String getLoggedInAsUser() {
        return loggedInAsUser;
    }

    public static String getLoggedInAsAdmin() {
        return loggedInAsAdmin;
    }
}
