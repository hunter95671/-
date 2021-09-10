package mysql;

import java.sql.*;
import java.util.Scanner;

public class teacher {//教师用户功能选择
    public static void main(String[] args) {

        //注册驱动获取连接
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/luoxiao01MSI?&useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "oooppp999666";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement clbStmt = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt=con.createStatement();

            //通过存储过程获得当前登录教师姓名
            clbStmt = con.prepareCall("{CALL proc_get_teacher_name(?)}");
            clbStmt.setString(1,teacherLogin.teacherusername);
            ResultSet rsc = clbStmt.executeQuery();
            rsc.next();
            String name = rsc.getString(1);

            String x=new String();
            Scanner input=new Scanner(System.in);
            menu m = new menu();

            //教师用户选择下一步操作
            System.out.println("欢迎"+name+"老师");
            System.out.println("请选择下一步操作：");
            System.out.println("1：学生成绩录入");
            System.out.println("2：学生成绩信息查询");
            System.out.println("3：任课信息查询");
            System.out.println("4：返回主菜单");
            System.out.println("0：退出");
            boolean bool=true;
            while (bool) {
                int n = input.nextInt();
                switch (n) {
                    case 1://录入成绩操作
                        System.out.println("开始输入成绩信息：");
                        System.out.println("请输入成绩：");
                        int agrand0=input.nextInt();
                        System.out.println("请输入学号：");
                        String asno0=input.next();
                        System.out.println("请输入课程号：");
                        String alno0=input.next();
                        System.out.println("请输入课程名：");
                        String alname0=input.next();
                        System.out.println("请输入开课学期：");
                        String alyear0=input.next();

                        String sql0="insert into luox_achievement values("+agrand0+",\""+asno0+"\""+",\""+alno0+"\""+",\""+alname0+"\","+"\""+alyear0+"\""+");";
                        int count0=stmt.executeUpdate(sql0);
                        System.out.print(count0 == 1 ? "信息录入成功" : "信息录入失败");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        teacher.main(args);
                        bool = false;
                        break;
                    case 2://学生成绩信息查询功能选择
                        System.out.println("请选择查询选项：");
                        System.out.println("1：查询我任课的所有学生成绩");
                        System.out.println("2：查询某一课程的学生成绩排名");
                        System.out.println("3：查询我任课的学生平均成绩");
                        System.out.println("4：返回上一级菜单");
                        System.out.println("0：退出");
                        boolean bool0=true;
                        while (bool0) {
                            int n0 = input.nextInt();
                            switch (n0) {//学生成绩查询
                                case 1://查询该教师任课的所有学生成绩
                                    String sql1="select * from luox_achievement a join luox_lesson ll on a.lx_lno=ll.lx_lno join luox_teacher t on t.lx_Tno = ll.lx_Tno where t.lx_Tno="+"\""+teacherLogin.teacherusername+"\""+";";
                                    rs=stmt.executeQuery(sql1);

                                    while (rs.next()){
                                        int agrand=rs.getInt("lx_Agrand");
                                        String asno=rs.getString("lx_Sno");
                                        String alno=rs.getString("lx_Lno");
                                        String alname=rs.getString("lx_Lname");
                                        String alyear=rs.getString("lx_Lyear");

                                        System.out.println(agrand+"\t"+asno+"\t"+alno+"\t"+alname+"\t"+alyear);
                                    }
                                    System.out.println("查询完成！");
                                    System.out.println("输入任意数字继续！");
                                    x=input.next();
                                    teacher.main(args);
                                    bool = false;
                                    break;
                                case 2://查询某一门课程的学生成绩排名
                                    System.out.println("输入要查询的课程号");
                                    String lno1=input.next();
                                    String sql2="SELECT b.* FROM (SELECT t.*, @rownum := @rownum + 1 AS rownum FROM (SELECT @rownum := 0) r,(SELECT * FROM luox_achievement where lx_Lno="+"\""+lno1+"\""+" ORDER BY lx_Agrand DESC) AS t) AS b ;";
                                    rs=stmt.executeQuery(sql2);

                                    while (rs.next()){
                                        int agrand=rs.getInt("lx_Agrand");
                                        String asno=rs.getString("lx_Sno");
                                        String alno=rs.getString("lx_Lno");
                                        String alname=rs.getString("lx_Lname");
                                        String alyear=rs.getString("lx_Lyear");
                                        int rownum=rs.getInt("rownum");
                                        System.out.println(agrand+"\t"+asno+"\t"+alno+"\t"+alname+"\t"+alyear+"\t"+"排名"+rownum);
                                    }
                                    System.out.println("查询完成！");
                                    System.out.println("输入任意数字继续！");
                                    x=input.next();
                                    teacher.main(args);
                                    bool0 = false;
                                    break;
                                case 3://查询我任课的课程平均成绩
                                    String sql3="select l.lx_lno,avg(lx_Agrand) agrandavg from luox_achievement a join luox_lesson l on a.lx_Lno = l.lx_Lno where l.lx_tno="+"\""+teacherLogin.teacherusername+"\""+";";
                                    rs=stmt.executeQuery(sql3);

                                    while (rs.next()){
                                        String alno=rs.getString("lx_lno");
                                        double agrand=rs.getDouble("agrandavg");

                                        System.out.println(alno+"\t"+agrand);
                                    }
                                    System.out.println("查询完成！");
                                    System.out.println("输入任意数字继续！");
                                    x=input.next();
                                    teacher.main(args);
                                    bool0 = false;
                                    break;
                                case 4:
                                    m.main(args);
                                    bool = false;
                                    break;
                                case 0:
                                    System.exit(0);
                                    bool = false;
                                    break;
                                default:
                                    System.out.println("请重新输入正确的数字！");
                            }
                        }
                        bool = false;
                        break;
                    case 3://任课信息查询操作
                        String sql2="select * from luox_lesson where lx_Tno="+"\""+teacherLogin.teacherusername+"\"";
                        rs=stmt.executeQuery(sql2);

                        while (rs.next()){
                            String llno=rs.getString("lx_Lno");
                            String llname=rs.getString("lx_Lname");
                            String ltno=rs.getString("lx_Tno");
                            String llyear=rs.getString("lx_Lyear");
                            int llhour=rs.getInt("lx_Lhour");
                            String llexam=rs.getString("lx_Lexam");
                            int llcredit=rs.getInt("lx_Lcredit");

                            System.out.println(llno+"\t"+llname+"\t"+ltno+"\t"+llyear+"\t"+llhour+"\t"+llexam+"\t"+llcredit);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        teacher.main(args);
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
