package doppler;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginSimulator {

    private Logger logger = Logger.getLogger(LoginSimulator.class);
    private String cookies;
    /**
     * 模拟登陆学校的教务网站，第一步是登陆，登陆成功服务器会返回一个字符串"0"，失败返回字符串"4"
     */
    public  String sendRequest(String[] loginInfo) throws IOException {
        loginInfo = getMD5LoginInfo();
        logger.info("Send Request");
        URL url = new URL(Constants.URL_LOGIN);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
        //从js里面发现是这样加密的： var signedpwd = hex_md5(user + sign + hex_md5(pwd.trim()));
        out.write("Action=Login&userName=201313070407&pwd=ff7ec7491d89cbe39891e0489fc95922&sign=1462086553535");
        out.write("Action=Login&userName=" + loginInfo[0] + "&pwd=" + loginInfo[1] +"&sign=" + loginInfo[2]);
        out.flush();
        out.close();

        String line = "";
        String result = "";
        InputStream urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));
        while ((line = in.readLine()) != null)
            result += line;

        in.close();
        logger.info("respose : " + result);
        cookies = connection.getHeaderField("Set-Cookie");
        if(cookies == null)
            logger.error("No cookies");
        return cookies;

    }

    private String[] getMD5LoginInfo(){
        String user = null;
        String pwd = null;
        String[] md5LoginInfo = null;
        String pwd_md5;
        MessageDigest md5;
        Scanner scanner = new Scanner(System.in);
        while (user != null && pwd != null){
            System.out.print("学号 :");
            user = scanner.nextLine();
            System.out.println();
            System.out.print("密码 :");
            pwd = scanner.next();
        }
        try {
            md5 = MessageDigest.getInstance("MD5");
            pwd_md5 =new String(md5.digest(user.getBytes()));
            System.out.println(pwd_md5);
        }catch (Exception e) {
            e.printStackTrace();
        }

    return md5LoginInfo;
    }

    /*
        登陆后要进入主页还要设置cookie
     */

    public  String getScorePageText(String cookie) throws IOException{
        URL url = new URL(Constants.URL_ScoreList);
        URLConnection connection = url.openConnection();
        logger.info("Add cookies");
        connection.setRequestProperty("Cookie",cookie);
        connection.connect();
        String line = "";
        String result = "";
        InputStream urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));
        while((line = in.readLine()) != null)
            result += line;
        in.close();
        return  result;
    }

    public static ArrayList<String> getAllItems(String content){
        if (content != null) {
            ArrayList<String> items = new ArrayList<String>();


            Pattern pattern = Pattern.compile(Constants.REGEX_0);
            Matcher matcher = pattern.matcher(content);
            boolean isFind = matcher.find();
            String item = "";
            while (isFind){
                items.add(matcher.group(0));
                isFind = matcher.find();
            }
            return items;
        }
        return null;
    }

    public static ArrayList<Course> getCourses(List items){

        return null;
    }
//    public static void main(String[] args) throws IOException {
//        String cookie = LoginSimulator.post();
//        //LoginSimulator.getPage(cookie);
//        String result = LoginSimulator.scorePage(cookie);
//        //System.out.println(allRows);
//        ArrayList<String> allItems = LoginSimulator.getAllItems(result);
//        //int counter = 1;
//        List lists[] = new ArrayList[7];
//        ArrayList<String> tempArr = new ArrayList<String>();
//        for (String item : allItems) {
//            Pattern pattern = Pattern.compile(Constants.REGEX_MATCHSEM);
//            Matcher matcher = pattern.matcher(item);
//            boolean isFind = matcher.find();
//
//            while (isFind){
//                tempArr.add(matcher.group(1));
//                //System.out.println(counter+ ":" + "[" + matcher.group(1) + "]");
//               isFind = matcher.find();
//              // counter++;
//            }
//            lists[0] = tempArr;
//        }
//
//
//        LoginSimulator.logger.info("lists.length   " + lists.length);
//    }

}
