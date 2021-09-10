package mysql;

import java.sql.SQLException;
import java.util.Scanner;

public class register {//用户注册菜单
    public static void main(String[] args) throws SQLException {

        administratorRegister administrator = new administratorRegister();
        teacherRegister teacher = new teacherRegister();
        studentRegister student = new studentRegister();
        menu m = new menu();
        //首先进行需要注册身份的选择
        System.out.println("请选择您的身份");
        System.out.println("1:管理员");
        System.out.println("2:教师");
        System.out.println("3:学生");
        System.out.println("0:返回");
        boolean bool=true;
        while (bool){
            Scanner input=new Scanner(System.in);
            int n=input.nextInt();
            switch (n){
                case     1://输入1时进行管理员用户注册
                    administrator.main(args);
                    bool=false;
                    break;
                case     2://输入2时进行教师用户注册
                    teacher.main(args);
                    bool=false;
                    break;
                case     3://输入3时进行学生用户注册
                    student.main(args);
                    bool=false;
                    break;
                case     0://输入0时返回主菜单
                    m.main(args);
                    bool=false;
                    break;
                default ://如果输入其他字符，则提示用户重新输入
                    System.out.println("请重新输入正确的数字！");
            }
        }
    }
}
