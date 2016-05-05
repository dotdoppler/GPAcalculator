package doppler;

/**
 * Created by doppler on 2016/5/3.
 */
public final class Constants {

    public final static String REGEX_0 = "<li class=\"item\">.*?</li>";
    public final static String REGEX_MATCHSEM = "6%;\"> +(\\d{6})&";
    public final static String REGEX_MATCHNAME = "20%;\"> +(.*?)&";
    public final static String REGEX_MATCHTEACHER = "7%;\"> +(.*?)&";
    public final static String REGEX_MATCHCREDITS = "4%;\"> +(([1-9])|(\\d.50))&";
    public final static String REGEX_MATCHGRADE = "4%;\"> +(((\\d)|([1-9]\\d)|([\\u4E00-\\u9FA5]*?)))&";
    public final static String REGEX_MATCHGRADETYPE = "10%;\"> +([\\u4E00-\\u9FA5]*?)&";
    public final static String REGEX_MATCHGRADEPOINT = "10%;\"> +(([1-5].\\d\\d)|(\\d))&";
    public final static String REGEX_REPALCECREDITS = "";

    public final static String URL_LOGIN = "http://202.115.133.173:8080/Common/Handler/UserLogin.ashx";
    public final static String URL_Default = "http://202.115.133.173:8080/Default.aspx";
    public final static String URL_ScoreList = "http://202.115.133.173:8080/SearchInfo/Score/ScoreList.aspx";
}
