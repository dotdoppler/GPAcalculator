import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by doppler on 2016/5/5.
 */

public class TestMD5 {
    public static void main(String[] args) {
//        String user = null;
//        String pwd = null;
//        String[] md5LoginInfo = null;
//        String pwd_md5;

//        Scanner scanner = new Scanner(System.in);
//        do{
//            System.out.print("学号 :");
//            user = scanner.nextLine();
//            System.out.println();
//            System.out.print("密码 :");
//            pwd = scanner.next();
//        }while (user != null && pwd != null);
        System.out.println(DigestUtils.md5Hex("1111"));


    }
}
