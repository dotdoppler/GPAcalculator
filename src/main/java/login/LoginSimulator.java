package login;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class LoginSimulator {
    /**
     * 模拟登陆学校的教务网站，第一步是登陆，登陆成功服务器会返回一个字符串"0"，失败返回字符串"4"
     */
    public  static String post() throws IOException {
        URL url = new URL("http://202.115.133.173:8080/Common/Handler/UserLogin.ashx");
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
        URL url = new URL("http://202.115.133.173:8080/Default.aspx");
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

    public static void main(String[] args) throws IOException {
        String cookie = LoginSimulator.post();
        LoginSimulator.getPage(cookie);

    }

}
