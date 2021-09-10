package mysql;

import java.sql.*;
import java.util.Scanner;

public class student {//学生用户功能选择
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

            //通过存储过程获得当前登录学生姓名
            clbStmt = con.prepareCall("{CALL proc_get_student_name(?)}");
            clbStmt.setString(1,studentLogin.studentusername);
            ResultSet rsc = clbStmt.executeQuery();
            rsc.next();
            String name = rsc.getString(1);

            String x=new String();
            Scanner input=new Scanner(System.in);
            menu m = new menu();

            //学生用户选择下一步操作
            System.out.println("欢迎"+name+"同学");
            System.out.println("登陆成功！请选择下一步操作：");
            System.out.println("1：我的成绩查询");
            System.out.println("2：查看我的所学课程及学分统计");
            System.out.println("3：班级课程开设查询");
            System.out.println("4：返回主菜单");
            System.out.println("0：退出");
            boolean bool=true;
            while (bool) {
                int n = input.nextInt();
                switch (n) {
                    case 1://学生成绩查询
                        System.out.println("请选择查询选项：");
                        System.out.println("1：查询某一门课程的成绩");
                        System.out.println("2：查询所有课程平均成绩");
                        System.out.println("3：查询所有成绩");
                        System.out.println("4：查询某一门课程的成绩排名");
                        System.out.println("5：查询在某个学年的所有课程成绩");
                        System.out.println("6：返回上一级菜单");
                        System.out.println("0：退出");
                        boolean bool0=true;
                        while (bool0) {
                            int n0 = input.nextInt();
                            switch (n0) {//学生成绩查询
                                case 1://查询某一门课程的成绩
                                    System.out.println("输入要查询的课程号");
                                    String lno=input.next();
                                    String sql0="select * from luox_achievement where lx_Sno="+"\""+studentLogin.studentusername+"\""+" and lx_Lno="+"\""+lno+"\""+";";
                                    rs=stmt.executeQuery(sql0);

                                    while (rs.next()){
                                        int agrand=rs.getInt("lx_Agrand");
                                        String asno=rs.getString("lx_Sno");
                                        String alno=rs.getString("lx_Lno");
                                        String alname=rs.getString("lx_Lname");
                                        String alyear=rs.getString("lx_Lyear");

                                        System.out.println(agrand+"分"+"\t"+asno+"\t"+alno+"\t"+alname+"\t"+alyear);
                                    }
                                    System.out.println("查询完成！");
                                    System.out.println("输入任意数字继续！");
                                    x=input.next();
                                    student.main(args);
                                    bool0 = false;
                                    break;
                                case 2://查询所有课程平均成绩
                                    String sql1="select lx_Sno,avg(lx_Agrand) agrandavg from luox_achievement where lx_Sno="+"\""+studentLogin.studentusername+"\""+";";
                                    rs=stmt.executeQuery(sql1);

                                    while (rs.next()){
                                        String asno=rs.getString("lx_Sno");
                                        double agrand=rs.getDouble("agrandavg");

                                        System.out.println(asno+"\t"+agrand);
                                    }
                                    System.out.println("查询完成！");
                                    System.out.println("输入任意数字继续！");
                                    x=input.next();
                                    student.main(args);
                                    bool0 = false;
                                    break;
                                case 3://查询所有成绩
                                    String sql2="select * from luox_achievement where lx_Sno="+"\""+studentLogin.studentusername+"\""+";";
                                    rs=stmt.executeQuery(sql2);

                                    while (rs.next()){
                                        int agrand=rs.getInt("lx_Agrand");
                                        String asno=rs.getString("lx_Sno");
                                        String alno=rs.getString("lx_Lno");
                                        String alname=rs.getString("lx_Lname");
                                        String alyear=rs.getString("lx_Lyear");
                                        System.out.println(agrand+"分"+"\t"+asno+"\t"+alno+"\t"+alname+"\t"+alyear);
                                    }
                                    System.out.println("查询完成！");
                                    System.out.println("输入任意数字继续！");
                                    x=input.next();
                                    student.main(args);
                                    bool0 = false;
                                    break;
                                case 4://查询某一门课程的成绩排名
                                    System.out.println("输入要查询的课程号");
                                    String lno1=input.next();
                                    String sql3="SELECT b.* FROM (SELECT t.*, @rownum := @rownum + 1 AS rownum FROM (SELECT @rownum := 0) r,(SELECT * FROM luox_achievement where lx_Lno="+"\""+lno1+"\""+" ORDER BY lx_Agrand DESC) AS t) AS b WHERE b.lx_Sno ="+"\""+studentLogin.studentusername+"\""+";";
                                    rs=stmt.executeQuery(sql3);

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
                                    student.main(args);
                                    bool0 = false;
                                    break;
                                case 5://查询在某个学年的所有课程成绩
                                    System.out.println("输入要查询的学年");
                                    String year=input.next();
                                    String sql4="select * from luox_achievement where lx_Sno="+"\""+studentLogin.studentusername+"\""+" and lx_Lyear="+"\""+year+"\""+";";
                                    rs=stmt.executeQuery(sql4);

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
                                    student.main(args);
                                    bool0 = false;
                                    break;
                                case 6://返回学生操作选择菜单
                                    student.main(args);
                                    bool0 = false;
                                    break;
                                case 0://退出程序
                                    System.exit(0);
                                    bool0 = false;
                                    break;
                                default:
                                    System.out.println("请重新输入正确的数字！");
                            }
                        }
                        bool = false;
                        break;
                    case 2://查看我的所学课程及学分统计
                        System.out.println("输入要查询的学年");
                        String year=input.next();
                        String sql4="select s.lx_Sno,l.lx_Lno,l.lx_Lname,l.lx_Lcredit from luox_student s join luox_achievement a on s.lx_Sno = a.lx_Sno join luox_lesson l on a.lx_Lno = l.lx_Lno where s.lx_Sno="+"\""+studentLogin.studentusername+"\""+" and a.lx_Agrand>=60 and a.lx_Lyear="+"\""+year+"\"";
                        rs=stmt.executeQuery(sql4);

                        while (rs.next()){

                            String asno=rs.getString("lx_Sno");
                            String alno=rs.getString("lx_Lno");
                            String alname=rs.getString("lx_Lname");
                            int credit=rs.getInt("lx_Lcredit");
                            System.out.println(asno+"\t"+alno+"\t"+alname+"\t"+credit);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        student.main(args);
                        bool0 = false;
                        break;
                    case 3://班级课程开设查询
                        String sql5="select c.lx_Cno,a.lx_Lname from luox_student s join luox_class c on s.lx_Cno = c.lx_Cno join luox_achievement a on s.lx_Sno = a.lx_Sno where s.lx_Sno="+"\""+studentLogin.studentusername+"\"";
                        rs=stmt.executeQuery(sql5);

                        while (rs.next()){

                            String ccno=rs.getString("lx_Cno");
                            String clname=rs.getString("lx_Lname");
                            System.out.println(ccno+"\t"+clname);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        student.main(args);
                        bool0 = false;
                        break;
                    case 4://返回主菜单
                        m.main(args);
                        bool0 = false;
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
