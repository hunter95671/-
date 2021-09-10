package mysql;

import java.sql.*;
import java.util.Scanner;

public class delete {//管理员对数据的删除操作
    public static void main(String[] args) {

        //注册驱动获取连接
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/luoxiao01MSI?&useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "oooppp999666";

        Connection con=null;
        Statement stmt=null;
        CallableStatement clbStmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt=con.createStatement();

            String x=new String();
            Scanner input=new Scanner(System.in);
            administrator m = new administrator();

            //选择下一步删除操作
            System.out.println("");
            System.out.println("删除信息");
            System.out.println("请选择下一步操作：");
            System.out.println("1：删除学生信息");
            System.out.println("2：删除教师信息");
            System.out.println("3：删除课程信息");
            System.out.println("4：删除学生成绩信息");
            System.out.println("5：删除专业信息");
            System.out.println("6：删除班级信息");
            System.out.println("7：删除地区信息");
            System.out.println("8：清空学生用户访问记录");
            System.out.println("9：清空教师用户访问记录");
            System.out.println("10：返回上一级菜单");
            System.out.println("0：退出");
            boolean bool=true;
            while (bool) {
                int n = input.nextInt();
                switch (n) {
                    case 1://删除学生信息
                        System.out.println("请输入要删除的学生学号：");
                        String sno=input.next();
                        String sql0="delete from luox_student where lx_Sno="+"\""+sno+"\""+";";
                        int count0=stmt.executeUpdate(sql0);
                        System.out.print(count0 == 1 ? "删除成功" : "删除失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 2://删除教师信息
                        System.out.println("请输入要删除的教师的教师编号：");
                        String tno=input.next();
                        String sql1="delete from luox_teacher where lx_Tno="+"\""+tno+"\""+";";
                        int count1=stmt.executeUpdate(sql1);
                        System.out.print(count1 == 1 ? "删除成功" : "删除失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 3://删除课程信息
                        System.out.println("请输入要删除的课程的课程号：");
                        String cno=input.next();
                        String sql2="delete from luox_lesson where lx_Lno="+"\""+cno+"\""+";";
                        int count2=stmt.executeUpdate(sql2);
                        System.out.print(count2 == 1 ? "删除成功" : "删除失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 4://删除学生成绩信息
                        System.out.println("请输入要删除成绩的课程的课程号：");
                        String acno=input.next();
                        System.out.println("请输入要删除成绩的学生的学号：");
                        String asno=input.next();
                        String sql3="delete from luox_achievement where lx_Lno="+"\""+acno+"\""+" "+"and"+" "+"lx_Sno="+"\""+asno+"\""+";";
                        int count3=stmt.executeUpdate(sql3);
                        System.out.print(count3 == 1 ? "删除成功" : "删除失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 5://删除专业信息
                        System.out.println("请输入要删除的专业的专业号：");
                        String mno=input.next();
                        String sql4="delete from luox_Major where lx_Mno="+"\""+mno+"\""+";";
                        int count4=stmt.executeUpdate(sql4);
                        System.out.print(count4 == 1 ? "删除成功" : "删除失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 6://删除班级信息
                        System.out.println("请输入要删除的班级的班级号：");
                        String ccno=input.next();
                        String sql5="delete from luox_class where lx_Cno="+"\""+ccno+"\""+";";
                        int count5=stmt.executeUpdate(sql5);
                        System.out.print(count5 == 1 ? "删除成功" : "删除失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 7://删除地区信息
                        System.out.println("请输入要删除的地区的地区名：");
                        String ppname=input.next();
                        String sql6="delete from luox_place where lx_Pname="+"\""+ppname+"\""+";";
                        int count6=stmt.executeUpdate(sql6);
                        System.out.print(count6 == 1 ? "删除成功" : "删除失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 8://清空学生用户访问记录
                        String sql7="truncate table luox_student_visit;";
                        int count7=stmt.executeUpdate(sql7);
                        System.out.print("清空成功");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 9://清空教师用户访问记录
                        String sql8="truncate table luox_teacher_visit;";
                        int count8=stmt.executeUpdate(sql8);
                        System.out.print("清空成功");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        delete.main(args);
                        bool = false;
                        break;
                    case 10://返回主菜单
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
