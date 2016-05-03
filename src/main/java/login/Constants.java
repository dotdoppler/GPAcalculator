package login;

/**
 * Created by doppler on 2016/5/3.
 */
public final class Constants {

    public final static String REGEX_0 = "<li class=\"item\">.*?</li>";
    public final static String REGEX_MATCHSEM = "6%;\"> +(\\d{6})&";//取group(1);
    public final static String REGEX_MATCHNAME = "20%;\"> +(.*?)&";//取group(1);
    public final static String REGEX_MATCHTEACHER = "7%;\"> +(.*?)&";//取group(1);
    public final static String REGEX_MATCHCREDITS = "";
    public final static String REGEX_MATCHGRADE = "";
    public final static String REGEX_MATCHGRADETYPE = "10%;\"> +([\\u4E00-\\u9FA5]*?)&";
    public final static String REGEX_MATCHGRADEPOINT = "";

    public final static String URL_LOGIN = "http://202.115.133.173:8080/Common/Handler/UserLogin.ashx";
    public final static String URL_Default = "http://202.115.133.173:8080/Default.aspx";
    public final static String URL_ScoreList = "http://202.115.133.173:8080/SearchInfo/Score/ScoreList.aspx";
}
