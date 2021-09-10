package mysql;

import java.sql.*;
import java.util.Scanner;

public class studentLogin {//学生登录
    public static String studentusername=new String();
    public static void main(String[] args) {

        //注册驱动获取连接
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/luoxiao01MSI?&useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "oooppp999666";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement clbStmt = null; // CallableStatement对象
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt=con.createStatement();
            menu m = new menu();

            //执行sql
            System.out.println("请输入用户名（学号）");
            Scanner input=new Scanner(System.in);
            studentusername=input.next();
            String pwd =new String();

            String sql="select * from luox_studentpsw;";
            rs=stmt.executeQuery(sql);

            //判断用户名和密码正确性
            boolean boolu=true;//用来标识是否停止循环
            boolean bool0=true;//用来标识用户是否存在
            boolean bool1=true;//用来标识密码是否正确
            while (boolu) {
                rs=stmt.executeQuery(sql);
                while (rs.next()) {
                    String uname = rs.getString("lx_Saccount");
                    String upassword = rs.getString("lx_Spass");
                    if (uname.equals(studentusername)) {
                        bool0=false;
                        System.out.println("请输入密码：");
                        pwd = input.next();
                        if (upassword.equals(pwd)) {
                            boolu=false;
                            bool1=false;
                            System.out.println("登录成功！");
                            System.out.println("");
                        }
                    }
                }
                if(bool0==true){//判断学生用户是否存在
                    System.out.println("用户不存在！请重新输入或返回！");
                    System.out.println("1:重新输入");
                    System.out.println("0:返回");
                    boolean boolx = true;
                    while (boolx) {
                        int n = input.nextInt();
                        switch (n) {
                            case 1://输入1时重新输入用户名
                                System.out.println("请输入用户名（学号）：");
                                studentusername = input.next();
                                boolx = false;
                                break;
                            case 0://输入0时返回上一级菜单
                                m.main(args);
                                boolx = false;
                                break;
                            default://如果输入其他字符，则提示用户重新输入
                                System.out.println("请重新输入正确的数字！");
                        }
                    }
                }
                else if(bool1==true){
                    System.out.println("密码错误！请重新输入或返回！");
                    System.out.println("1:重新输入");
                    System.out.println("0:返回");
                    boolean boolx = true;
                    while (boolx) {
                        int n = input.nextInt();
                        switch (n) {
                            case 1://输入1时重新输入密码
                                boolx = false;
                                break;
                            case 0://输入0时返回上一级菜单
                                m.main(args);
                                boolx = false;
                                break;
                            default://如果输入其他字符，则提示用户重新输入
                                System.out.println("请重新输入正确的数字！");
                        }
                    }
                }
            }

            //通过存储过程向学生访问记录表中添加访问记录
            clbStmt = con.prepareCall("{CALL proc_student_visit(?)}");
            clbStmt.setString(1,studentLogin.studentusername);
            clbStmt.execute();

            //登录后续操作
            student s = new student();
            s.main(args);//调用学生类的main方法

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("连接失败");
        }finally {
            //释放资源
            try {
                if(rs!=null){
                    rs.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
                System.out.print("关闭失败");
            }

            try {
                if(stmt!=null){
                    stmt.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
                System.out.print("关闭失败");
            }

            try {
                if(con!=null){
                    con.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
                System.out.print("关闭失败");
            }
        }
    }
}
