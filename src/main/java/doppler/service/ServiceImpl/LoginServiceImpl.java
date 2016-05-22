package doppler.service.ServiceImpl;

import doppler.constants.GlobalConstants;
import doppler.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


@ComponentScan
@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger = Logger.getLogger(LoginServiceImpl.class);
    private String cookies;


    public String loginAndGetText(String[] loginInfo){
        String text = null;
        try {
            String cookie = sendRequest(loginInfo);
            if (cookie == null)
                return null;
            text  = getScorePageText(cookie);
        }catch (IOException e) {e.printStackTrace();}
        logger.info("get Text");
        return text;
    }
    /**
     * 登陆学校的教务网站，登陆成功服务器会返回一个字符串"0"，失败返回字符串"4"
     */
    private String sendRequest(String[] loginInfo) throws IOException {
        logger.info("Send Request");
        URL url = new URL(GlobalConstants.URL_LOGIN);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
        String s = "Action=Login&userName=" + loginInfo[0] + "&pwd="+ loginInfo[1] +"&sign="+ loginInfo[2];
        out.write(s);
        out.flush();
        out.close();

        String line = "";
        String result = "";
        InputStream urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));

        while ((line = in.readLine()) != null)
            result += line;
        in.close();
        logger.info("response : " + result);
        if (result.equals("4"))
            return null;
        cookies = connection.getHeaderField("Set-Cookie");
        if(cookies == null)
            logger.error("No cookies");
        return cookies;

    }

    /*
     *  登陆后要进入主页前还要设置cookie
     */
    private   String getScorePageText(String cookies) throws IOException{
        URL url = new URL(GlobalConstants.URL_SCORELIST);
        URLConnection connection = url.openConnection();
        logger.info("Add cookies");
        connection.setRequestProperty("Cookie",cookies);
        connection.connect();
        String line = "";
        StringBuilder result = new StringBuilder();
        InputStream urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));
        while((line = in.readLine()) != null)
            result.append(line);
        in.close();
        return  result.toString();
    }
}
