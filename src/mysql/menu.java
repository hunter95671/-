package mysql;

import java.sql.SQLException;
import java.util.Scanner;

public class menu {//主菜单
    public static void main(String[] args) throws SQLException {

        //选择注册还是登录
        System.out.println("欢迎来到高校学生成绩管理系统！");
        System.out.println("请输入：");
        System.out.println("1：用户登录");
        System.out.println("2：用户注册");
        System.out.println("0：退出");
        login l=new login();


        register r=new register();
        boolean bool=true;
        while (bool) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            switch (n) {
                case 1://调用登录类的main方法
                    l.main(args);
                    bool = false;
                    break;
                case 2://调用注册类的main方法
                    r.main(args);
                    bool = false;
                    break;
                case 0://退出程序
                    System.exit(0);
                    bool = false;
                    break;
                default:
                    System.out.println("请重新输入正确的数字！");
            }
        }
    }
}
