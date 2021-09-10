package mysql;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class select {//管理员对数据的查询操作
    public static void main(String[] args) {

        //注册驱动获取连接
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/luoxiao01MSI?&useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "oooppp999666";

        Connection con=null;
        Statement stmt=null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt=con.createStatement();

            String x=new String();
            Scanner input=new Scanner(System.in);
            administrator m = new administrator();

            //选择下一步查询操作
            System.out.println("");
            System.out.println("查询信息");
            System.out.println("请选择下一步操作：");
            System.out.println("1：查询学生信息");
            System.out.println("2：查询教师信息");
            System.out.println("3：查询课程信息");
            System.out.println("4：查询学生成绩信息");
            System.out.println("5：查询专业信息");
            System.out.println("6：查询班级信息");
            System.out.println("7：查询地区信息");
            System.out.println("8：查询学生用户访问记录");
            System.out.println("9：查询教师用户访问记录");
            System.out.println("10：返回上一级菜单");
            System.out.println("0：退出");
            boolean bool=true;
            while (bool) {
                int n = input.nextInt();
                switch (n) {
                    case 1://查询学生信息
                        String sql0="select * from luox_student";
                        rs=stmt.executeQuery(sql0);

                        while (rs.next()){
                            String ssno=rs.getString("lx_Sno");
                            String ssname=rs.getString("lx_Sname");
                            String ssex=rs.getString("lx_Ssex");
                            int sage=rs.getInt("lx_Sage");
                            String splace=rs.getString("lx_Splace");
                            int scredit=rs.getInt("lx_Scredit");
                            String syear=rs.getString("lx_Syear");
                            String scno=rs.getString("lx_Cno");
                            String smno=rs.getString("lx_Mno");

                            System.out.println(ssno+"\t"+ssname+"\t"+ssex+"\t"+sage+"\t"+splace+"\t"+scredit+"\t"+syear+"\t"+scno+"\t"+smno);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        select.main(args);
                        bool = false;
                        break;
                    case 2://查询教师信息
                        String sql1="select * from luox_teacher";
                        rs=stmt.executeQuery(sql1);
                        while (rs.next()){
                            String ttno=rs.getString("lx_Tno");
                            String ttname=rs.getString("lx_Tname");
                            String ttex=rs.getString("lx_Tsex");
                            int tage=rs.getInt("lx_Tage");
                            String trank=rs.getString("lx_Trank");
                            String ttel=rs.getString("lx_Ttel");

                            System.out.println(ttno+"\t"+ttname+"\t"+ttex+"\t"+tage+"\t"+trank+"\t"+ttel);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        select.main(args);
                        bool = false;
                        break;
                    case 3://查询课程信息
                        String sql2="select * from luox_lesson";
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
                        select.main(args);
                        bool = false;
                        break;
                    case 4://查询学生成绩信息
                        String sql3="select * from luox_achievement";
                        rs=stmt.executeQuery(sql3);

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
                        select.main(args);
                        bool = false;
                        break;
                    case 5://查询专业信息
                        String sql4="select * from luox_Major";
                        rs=stmt.executeQuery(sql4);

                        while (rs.next()){
                            String mmno=rs.getString("lx_Mno");
                            String mmname=rs.getString("lx_Mname");

                            System.out.println(mmno+"\t"+mmname);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        select.main(args);
                        bool = false;
                        break;
                    case 6://查询班级信息
                        String sql5="select * from luox_class";
                        rs=stmt.executeQuery(sql5);

                        while (rs.next()){
                            String cmno=rs.getString("lx_Mno");
                            String ccno=rs.getString("lx_Cno");


                            System.out.println(cmno+"\t"+ccno);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        select.main(args);
                        bool = false;
                        break;

                    case 7://查询地区信息
                        String sql6="select * from luox_place";
                        rs=stmt.executeQuery(sql6);

                        while (rs.next()){
                            String pname=rs.getString("lx_Pname");
                            int pcount=rs.getInt("lx_pcount");

                            System.out.println(pname+"\t"+pcount);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        select.main(args);
                        bool = false;
                        break;
                    case 8://查询学生用户访问记录
                        String sql7="select * from luox_student_visit";
                        rs=stmt.executeQuery(sql7);

                        while (rs.next()){
                            String vsno=rs.getString("lx_Sno");
                            Date vctime=rs.getDate("lx_time");

                            System.out.println(vsno+"\t"+vctime);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        select.main(args);
                        bool = false;
                        break;
                    case 9://查询教师用户访问记录
                        String sql8="select * from luox_teacher_visit";
                        rs=stmt.executeQuery(sql8);

                        while (rs.next()){
                            String vtno=rs.getString("lx_Tno");
                            Date vttime=rs.getDate("lx_time");

                            System.out.println(vtno+"\t"+vttime);
                        }
                        System.out.println("查询完成！");
                        System.out.println("输入任意数字继续！");
                        x=input.next();
                        select.main(args);
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
