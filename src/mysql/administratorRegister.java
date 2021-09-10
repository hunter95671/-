package mysql;

import java.sql.*;
import java.util.Scanner;

public class administratorRegister {//管理员用户注册
    public static void main(String[] args) throws SQLException {

        register registermenu = new register();

        //先输入超级管理员密码oooppp
        //用来验证当前用户有权注册管理员账户
        System.out.println("请输入超级管理员密码：");
        Scanner input=new Scanner(System.in);
        String rootpwd=input.next();
        if(rootpwd.equals("oooppp")){
            System.out.println("验证成功，继续注册");
        }else {
            System.out.println("验证失败，返回上一级菜单：");
            registermenu.main(args);
        }

        //下面是连接数据库查询管理员用户表，查询是否已经注册，如果没有注册，则插入该用户
        //注册驱动获取连接
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/luoxiao01MSI?&useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "oooppp999666";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt=con.createStatement();

            //执行sql
            String sql1="select * from luox_rootpsw;";
            rs=stmt.executeQuery(sql1);

            System.out.println("请输入用户名：");
            String name=input.next();
            String userpassword1= new String();
            boolean bool1=true;//用来标识是否停止循环
            boolean bool2=true;//用来标识输入的用户名是否重复
            while (bool1){
                while (rs.next()){
                    String uname=rs.getString("lx_Raccount");
                    String pwd=rs.getString("lx_Rpass");
                    if (uname.equals(name)){
                        bool2=false;
                    }else {
                        continue;
                    }
                }
                //判断用户名是否重复
                if(bool2==false){//如果重复则重新输入或退出
                    bool2=true;//将用来标识输入的用户名是否重复的值恢复，以便下一次判断
                    System.out.println("该用户名已存在，请重新输入或返回上一级菜单！");
                    System.out.println("1:重新输入");
                    System.out.println("0:返回");
                    boolean boolx=true;
                    while (boolx){
                        int n=input.nextInt();
                        switch (n){
                            case     1://输入1时重新输入用户名
                                System.out.println("请输入用户名：");
                                name=input.next();
                                boolx=false;
                                break;
                            case     0://输入0时返回上一级菜单
                                registermenu.main(args);
                                boolx=false;
                                break;
                            default ://如果输入其他字符，则提示用户重新输入
                                System.out.println("请重新输入正确的数字！");
                        }
                    }
                }else{//如果用户名可用，则输入密码
                    bool1=false;
                    System.out.println("该用户名可用，请输入密码：");
                    System.out.println("注意：密码长度不能大于16位");
                    userpassword1=input.next();
                    boolean boolp=true;
                    while (boolp){
                        if (userpassword1.length()>16){
                            System.out.println("密码长度不能大于16位！请重新输入或者返回上一级菜单：");
                            System.out.println("1:重新输入");
                            System.out.println("0:返回");
                            boolean boolx=true;
                            while (boolx){
                                int n=input.nextInt();
                                switch (n){
                                    case     1://输入1时重新输入密码
                                        System.out.println("请重新输入密码：");
                                        userpassword1=input.next();
                                        boolx=false;
                                        break;
                                    case     0://输入0时返回上一级菜单
                                        registermenu.main(args);
                                        boolx=false;
                                        break;
                                    default ://如果输入其他字符，则提示用户重新输入
                                        System.out.println("请重新输入正确的数字！");
                                }
                            }
                        }else {//二次输入密码确保密码正确
                            System.out.println("请再次输入以便确认密码输入无误：");
                            String userpassword2=input.next();
                            if (userpassword1.equals(userpassword2)){
                                System.out.println("密码可用！");
                                boolp=false;
                            }else {
                                System.out.println("两次输入不同！请重新输入或者返回上一级菜单：");
                                System.out.println("1:重新输入");
                                System.out.println("0:返回");
                                boolean boolx=true;
                                while (boolx){
                                    int n=input.nextInt();
                                    switch (n){
                                        case     1://输入1时重新输入第二次确认的密码
                                            boolx=false;
                                            break;
                                        case     0://输入0时返回上一级菜单
                                            registermenu.main(args);
                                            boolx=false;
                                            break;
                                        default ://如果输入其他字符，则提示用户重新输入
                                            System.out.println("请重新输入正确的数字！");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //向管理员用户表中插入数据
            String sql2="insert into  luox_rootpsw values (\'"+name+"\',\'"+userpassword1+"\');";
            int count=stmt.executeUpdate(sql2);
            System.out.println(count == 1 ? "管理员用户注册成功" : "管理员用户注册失败");

            //注册后续操作
            menu m = new menu();
            System.out.println("请选择下一步操作");
            System.out.println("1：返回主菜单");
            System.out.println("0：退出");
            boolean bool=true;
            while (bool) {
                int n = input.nextInt();
                switch (n) {
                    case 1://返回主菜单
                        m.main(args);
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
