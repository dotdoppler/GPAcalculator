import java.io.Console;

/**
 * Created by doppler on 2016/5/5.
 */
public class TestConsole {
  /*
    ava.io.Console 这个类是 JDK 6 中新增的类库，用于操作系统的控制台，
    系统的控制台只能在操作系统原生的控制台中使用，不能在 IDE 的控制台中使用，因为 IDE 中的控制台是经过重定向的。
     */
    public static void main(String[] args) {
        Console console = System.console();
        System.out.print("password :");
        char[] pwd = console.readPassword();
        System.out.println(pwd);
    }
}
