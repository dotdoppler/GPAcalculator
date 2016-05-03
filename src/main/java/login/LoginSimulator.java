package login;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginSimulator {
    /**
     * 模拟登陆学校的教务网站，第一步是登陆，登陆成功服务器会返回一个字符串"0"，失败返回字符串"4"
     */
    public  static String post() throws IOException {
        URL url = new URL(Constants.URL_LOGIN);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
        //从js里面发现是这样加密的： var signedpwd = hex_md5(user + sign + hex_md5(pwd.trim()));
        out.write("Action=Login&userName=201313070407&pwd=ff7ec7491d89cbe39891e0489fc95922&sign=1462086553535");
        out.flush();
        out.close();

        String line = "";
        String result = "";
        InputStream urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));
        while ((line = in.readLine()) != null)
            result += line;

        in.close();
        System.out.println(result);
        return connection.getHeaderField("Set-Cookie");


    }
    /*
        登陆后要进入主页还要设置cookie
     */
    public static  void getPage(String cookie) throws IOException{
        URL url = new URL(Constants.URL_Default);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Cookie",cookie);
        connection.connect();
        String line = "";
        String result = "";
        InputStream urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));
        FileUtil.writeHTML("e:\\html","test.html",in);
        in.close();
        System.out.println(result);
    }
    public static String scorePage(String cookie) throws IOException{
        URL url = new URL(Constants.URL_ScoreList);
        URLConnection connection = url.openConnection();
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
    //先匹配所有的行
    public ArrayList<String> getAllRows(String page){
        ArrayList<String> allRows = new ArrayList<String>();

        return allRows;
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
    public static void main(String[] args) throws IOException {
        String cookie = LoginSimulator.post();
        //LoginSimulator.getPage(cookie);
        //System.out.println(LoginSimulator.scorePage(cookie));
        ArrayList<String> rows = LoginSimulator.getAllItems(LoginSimulator.scorePage(cookie));
        int counter = 1;
        for (String row : rows) {
            Pattern pattern = Pattern.compile(Constants.REGEX_MATCHGRADETYPE);
            Matcher matcher = pattern.matcher(row);
            boolean isFind = matcher.find();

            while (isFind){
                String s = matcher.group(1);
                if (!s.equals("")){
                    System.out.println(counter+ ":" + "[" + matcher.group(1) + "]");
               isFind = matcher.find();
               counter++;
                }
               isFind = matcher.find();

            }
        }
    }

}
