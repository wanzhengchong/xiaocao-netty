package in.xiaocao.echo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputUtil {
	
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    /**
     * 读取一行
     * @param prompt
     * @return
     */
    public static String getString(String prompt) {
        boolean flag = true; // 数据接收标记
        String str = null;
        while (flag) {
            System.out.print(prompt);
            try {
                str = KEYBOARD_INPUT.readLine(); // 读取一行数据
                if (str == null || "".equals(str)) {
                    System.out.println("数据输入错误 ，该内容不允许为空：");
                } else {
                    flag = false;
                }
            } catch (IOException e) {
                System.out.println("数据输入错误 ，该内容不允许为空：");
            }
        }
        return str;
    }
}
