package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class insert {//管理员对数据的插入操作
    public static void main(String[] args) {

        //注册驱动获取连接
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/luoxiao01MSI?&useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "oooppp999666";

        Connection con=null;
        Statement stmt=null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt=con.createStatement();

            String x=new String();
            Scanner input=new Scanner(System.in);
            administrator m = new administrator();

            //选择下一步录入操作
            System.out.println("");
            System.out.println("录入信息");
            System.out.println("请选择下一步操作：");
            System.out.println("1：录入学生信息");
            System.out.println("2：录入教师信息");
            System.out.println("3：录入课程信息");
            System.out.println("4：录入学生成绩信息");
            System.out.println("5：录入专业信息");
            System.out.println("6：录入班级信息");
            System.out.println("7：录入地区信息");
            System.out.println("8：返回上一级菜单");
            System.out.println("0：退出");
            boolean bool=true;
            while (bool) {
                int n = input.nextInt();
                switch (n) {
                    case 1://录入学生信息
                        System.out.println("开始输入学生信息：");
                        System.out.println("请输入学号：");
                        String sno=input.next();
                        System.out.println("请输入学生姓名：");
                        String sname=input.next();
                        System.out.println("请输入学生性别（男/女）：");
                        String ssex=input.next();
                        System.out.println("请输入学生年龄：");
                        int sage=input.nextInt();
                        System.out.println("请输入生源所在地（省份名）：");
                        String splace=input.next();
                        System.out.println("请输入学生已修学分数：");
                        int scredit=input.nextInt();
                        System.out.println("请输入学生年级：");
                        String syear=input.next();
                        System.out.println("请输入班级号：");
                        String cno=input.next();
                        System.out.println("请输入专业号：");
                        String mno=input.next();

                        String sql0="insert into luox_student values("+"\""+sno+"\""+",\""+sname+"\""+",\""+ssex+"\","+sage+",\""+splace+"\","+scredit+",\""+syear+"\""+",\""+cno+"\""+",\""+mno+"\""+");";
                        int count0=stmt.executeUpdate(sql0);
                        System.out.print(count0 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        insert.main(args);
                        bool = false;
                        break;
                    case 2://录入教师信息
                        System.out.println("开始输入教师信息：");
                        System.out.println("请输入教师编号：");
                        String tno=input.next();
                        System.out.println("请输入教师姓名：");
                        String tname=input.next();
                        System.out.println("请输入教师性别（男/女）：");
                        String tsex=input.next();
                        System.out.println("请输入教师年龄：");
                        int tage=input.nextInt();
                        System.out.println("请输入教师职称（一级，二级，三级）：");
                        String trank=input.next();
                        System.out.println("请输入教师联系电话：");
                        String ttel=input.next();
                        String sql1="insert into luox_teacher values("+"\""+tno+"\""+",\""+tname+"\""+",\""+tsex+"\","+tage+",\""+trank+"\","+"\""+ttel+"\""+");";
                        int count1=stmt.executeUpdate(sql1);
                        System.out.print(count1 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        insert.main(args);
                        bool = false;
                        break;
                    case 3://录入课程信息
                        System.out.println("开始输入课程信息：");
                        System.out.println("请输入课程号：");
                        String lno=input.next();
                        System.out.println("请输入课程名：");
                        String lname=input.next();
                        System.out.println("请输入教师号：");
                        String ltno=input.next();
                        System.out.println("请输入开课学期：");
                        String lyear=input.next();
                        System.out.println("请输入学时：");
                        int lhour=input.nextInt();
                        System.out.println("请输入是考试还是考察：");
                        String lexam=input.next();
                        System.out.println("请输入课程学分：");
                        int lcredit=input.nextInt();

                        String sql2="insert into luox_lesson values("+"\""+lno+"\""+",\""+lname+"\""+",\""+ltno+"\","+"\""+lyear+"\","+lhour+",\""+lexam+"\","+lcredit+");";
                        int count2=stmt.executeUpdate(sql2);
                        System.out.print(count2 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        insert.main(args);
                        bool = false;
                        break;
                    case 4://录入学生成绩信息
                        System.out.println("开始输入成绩信息：");
                        System.out.println("请输入成绩：");
                        int agrand=input.nextInt();
                        System.out.println("请输入学号：");
                        String asno=input.next();
                        System.out.println("请输入课程号：");
                        String alno=input.next();
                        System.out.println("请输入课程名：");
                        String alname=input.next();
                        System.out.println("请输入开课学期：");
                        String alyear=input.next();

                        String sql3="insert into luox_achievement values("+agrand+",\""+asno+"\""+",\""+alno+"\""+",\""+alname+"\","+"\""+alyear+"\""+");";
                        int count3=stmt.executeUpdate(sql3);
                        System.out.print(count3 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        insert.main(args);
                        bool = false;
                        break;
                    case 5://录入专业信息
                        System.out.println("开始输入专业信息：");
                        System.out.println("请输入专业号：");
                        String mmno=input.next();
                        System.out.println("请输入专业名：");
                        String mname=input.next();

                        String sql4="insert into luox_Major values("+"\""+mmno+"\""+",\""+mname+"\""+");";
                        int count4=stmt.executeUpdate(sql4);
                        System.out.print(count4 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        insert.main(args);
                        bool = false;
                        break;
                    case 6://录入班级信息
                        System.out.println("请输入班级信息：");
                        System.out.println("请输入专业号：");
                        String cmno=input.next();
                        System.out.println("请输入班级号：");
                        String ccno=input.next();

                        String sql5="insert into luox_class values("+"\""+cmno+"\""+",\""+ccno+"\""+");";
                        int count5=stmt.executeUpdate(sql5);
                        System.out.print(count5 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        insert.main(args);
                        bool = false;
                        break;
                    case 7://录入地区信息
                        System.out.println("请输入地区信息：");
                        System.out.println("请输入地区名：");
                        String ppname=input.next();

                        String sql6="insert into luox_place values("+"\""+ppname+"\""+",\""+0+"\""+");";
                        int count6=stmt.executeUpdate(sql6);
                        System.out.print(count6 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        insert.main(args);
                        bool = false;
                        break;
                    case 8://返回主菜单
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
