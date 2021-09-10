package mysql;

import java.sql.*;
import java.util.Scanner;

public class administrator {//管理员用户功能选择
        //有最高权限，负责向表中添加数据，也能查询所有数据
    public static void main(String[] args) throws SQLException {

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

            Scanner input=new Scanner(System.in);
            menu m = new menu();
            insert i=new insert();
            delete d=new delete();
            select s=new select();

            //管理员用户选择下一步操作
            System.out.println("请选择下一步操作：");
            System.out.println("1：录入信息");
            System.out.println("2：删除信息");
            System.out.println("3：查询信息");
            System.out.println("4：返回主菜单");
            System.out.println("0：退出");
            boolean bool=true;
            while (bool) {
                int n = input.nextInt();
                switch (n) {
                    case 1://调用插入类的main方法
                        i.main(args);
                        bool = false;
                        break;
                    case 2://调用删除类的main方法
                        d.main(args);
                        bool = false;
                        break;
                    case 3://调用查询类的main方法
                        s.main(args);
                        bool = false;
                        break;
                    case 4://返回主菜单
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
