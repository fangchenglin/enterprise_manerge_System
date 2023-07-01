import java.sql.*;
import java.util.Scanner;

public class GaussDBDemo {
    static final String JDBC_DRIVER = "com.huawei.opengauss.jdbc.Driver";
    static final String DB_URL = "jdbc:opengauss://116.205.157.173:8000/db_2020_01?ApplicationName=app1&characterEncoding=utf-8";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "db_user2020_265";
    static final String PASS = "db_user@123";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化 Statement 对象...");
            stmt = conn.createStatement();
            String sql;


// String table_employee="CREATE TABLE employee"+"(eid INTEGER,"+"sid INTEGER,"+"did INTEGER,"+"ename VARCHAR(20),"+"gender VARCHAR(20),"+"class VARCHAR(20))";
            // String table_department="CREATE TABLE department"+"(did INTEGER,"+"dname VARCHAR(20),"+"function VARCHAR(20))";
            // String table_salary="CREATE TABLE salary"+"(sid INTEGER,"+"eid INTEGER,"+"ammount INTEGER,"+"awrd INTEGER)";
            // stmt.executeUpdate(table_department);
            //stmt.executeUpdate(table_employee);
            // stmt.executeUpdate(table_salary);

            //命令行界面
            System.out.println("---------------------");
            System.out.println("请选择操作：");
            System.out.println("1:create      2:read");
            System.out.println("3:update      4:delete");
            System.out.println("q:quit");
            System.out.println("---------------------");
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.next();
            while (!cmd.equals("q")) {
                System.out.println("cmd=" + cmd);
                if (cmd.equals("1")) {
                    System.out.println("---------------------");
                    System.out.println("请选择创建对象：");
                    System.out.println("1:employee      2:department");
                    System.out.println("3:salary");
                    System.out.println("b:back");
                    System.out.println("---------------------");
                    cmd = scanner.next();
                    while (!cmd.equals("b")) {
                        System.out.println("请输入数据");
                        if (cmd.equals("1")) {
                            String eid = scanner.next();
                            String sid = scanner.next();
                            String did = scanner.next();

                            String ename = scanner.next();
                            String gender = scanner.next();
                            String eclass = scanner.next();
                            sql = "INSERT INTO employee (eid,sid, did, ename, gender, class) VALUES (?, ?,?, ?, ?, ?)";

                            // 创建 PreparedStatement 对象并设置参数
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, eid);
                            preparedStatement.setString(2, sid);
                            preparedStatement.setString(3, did);
                            preparedStatement.setString(4, ename);
                            preparedStatement.setString(5, gender);
                            preparedStatement.setString(6, eclass);
                            preparedStatement.executeLargeUpdate();

                        } else if (cmd.equals("2")) {
                            System.out.println("请输入数据");
                            String did = scanner.next();
                            String dname = scanner.next();
                            String function = scanner.next();
                            sql = "INSERT INTO department (did,dname, function) VALUES (?, ?,?)";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, did);
                            preparedStatement.setString(2, dname);
                            preparedStatement.setString(3, function);
                            preparedStatement.executeLargeUpdate();
                        } else if (cmd.equals("3")) {
                            System.out.println("请输入数据");
                            String sid = scanner.next();
                            String eid = scanner.next();
                            String ammount = scanner.next();
                            String awrd = scanner.next();
                            sql = "INSERT INTO salary (sid,eid,ammount, awrd) VALUES (?, ?,?,?)";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, sid);
                            preparedStatement.setString(2, eid);
                            preparedStatement.setString(3, ammount);
                            preparedStatement.setString(3, awrd);
                            preparedStatement.executeLargeUpdate();
                        } else {
                            System.out.println("请重新输入");
                        }


                        System.out.println("---------------------");
                        System.out.println("请选择创建对象：");
                        System.out.println("1:employee      2:department");
                        System.out.println("3:salary      4:delete");
                        System.out.println("b:back");
                        System.out.println("---------------------");
                        cmd = scanner.next();
                    }
                } else if (cmd.equals("2")) {
                    System.out.println("---------------------");
                    System.out.println("请选择查询操作：");
                    System.out.println("1:列出所有员工信息      2:列出所有部门信息");
                    System.out.println("3:列出所有工资信息      4:列出某一员工的信息");
                    System.out.println("b:back");
                    System.out.println("---------------------");
                    cmd = scanner.next();
                    while (!cmd.equals("b")) {
                        if (cmd.equals("1")) {
                            sql = "SELECT * FROM employee;";
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                System.out.println(rs.getString("eid") + "\t"
                                        + rs.getString("sid") + "\t"
                                        + rs.getString("did") + "\t"
                                        + rs.getString("ename") + "\t"
                                        + rs.getString("gender") + "\t"
                                        + rs.getString("class"));
                            }
                        } else if (cmd.equals("2")) {
                            sql = "SELECT * FROM department;";
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                System.out.println(rs.getString("did") + "\t"
                                        + rs.getString("dname") + "\t"
                                        + rs.getString("function") + "\t");
                            }
                        } else if (cmd.equals("3")) {
                            sql = "SELECT * FROM salary;";
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                System.out.println(rs.getString("sid") + "\t"
                                        + rs.getString("eid") + "\t"
                                        + rs.getString("ammount") + "\t"
                                        + rs.getString("awrd") + "\t");
                            }
                        } else if (cmd.equals("4")) {
                            String searchName = scanner.next();
                            sql = "SELECT ename,dname,ammount FROM employee,department,salary " +
                                    "WHERE ename=? AND employee.did=department.did AND employee.sid=salary.sid";
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setString(1, searchName);
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                System.out.println(rs.getString("ename") + "\t"
                                        + rs.getString("dname") + "\t"
                                        + rs.getString("ammount") + "\t");
                            }
                        } else {
                            System.out.println("请重新输入");
                        }
                        System.out.println("---------------------");
                        System.out.println("请选择查询操作：");
                        System.out.println("1:列出所有员工信息      2:列出所有部门信息");
                        System.out.println("3:列出所有工资信息      4:列出某一员工的信息");
                        System.out.println("b:back");
                        System.out.println("---------------------");
                        cmd = scanner.next();
                    }
                } else if (cmd.equals("3")) {
                    System.out.println("---------------------");
                    System.out.println("请选择更新对象：");
                    System.out.println("1:employee      2:department");
                    System.out.println("3:salary");
                    System.out.println("b:back");
                    System.out.println("---------------------");
                    cmd = scanner.next();
                    while (!cmd.equals("b")) {
                        System.out.println("请输入要更新的记录的id:");
                        String id = scanner.next();
                        if (cmd.equals("1")) {
                            System.out.println("请输入新数据:");
                            String eid = scanner.next();
                            String sid = scanner.next();
                            String did = scanner.next();
                            String ename = scanner.next();
                            String gender = scanner.next();
                            String eclass = scanner.next();
                            sql = "UPDATE employee SET eid=?, sid=?, did=?, ename=?, gender=?, class=? WHERE eid=?";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, eid);
                            preparedStatement.setString(2, sid);
                            preparedStatement.setString(3, did);
                            preparedStatement.setString(4, ename);
                            preparedStatement.setString(5, gender);
                            preparedStatement.setString(6, eclass);
                            preparedStatement.setString(7, id);
                            preparedStatement.executeUpdate();
                        } else if (cmd.equals("2")) {
                            System.out.println("请输入新数据:");
                            String did = scanner.next();
                            String dname = scanner.next();
                            String function = scanner.next();
                            sql = "UPDATE department SET did=?, dname=?, function=? WHERE did=?";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, did);
                            preparedStatement.setString(2, dname);
                            preparedStatement.setString(3, function);
                            preparedStatement.setString(4, id);
                            preparedStatement.executeUpdate();
                        } else if (cmd.equals("3")) {
                            System.out.println("请输入新数据:");
                            String sid = scanner.next();
                            String eid = scanner.next();
                            String ammount = scanner.next();
                            String awrd = scanner.next();
                            sql = "UPDATE salary SET sid=?, eid=?, ammount=?, awrd=? WHERE sid=?";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, sid);
                            preparedStatement.setString(2, eid);
                            preparedStatement.setString(3, ammount);
                            preparedStatement.setString(4, awrd);
                            preparedStatement.setString(5, id);
                            preparedStatement.executeUpdate();
                        } else {
                            System.out.println("请重新输入");
                        }
                        System.out.println("---------------------");
                        System.out.println("请选择更新对象：");
                        System.out.println("1:employee      2:department");
                        System.out.println("3:salary");
                        System.out.println("b:back");
                        System.out.println("---------------------");
                        cmd = scanner.next();
                    }
                } else if (cmd.equals("4")) {
                    System.out.println("---------------------");
                    System.out.println("请选择删除对象：");
                    System.out.println("1:employee      2:department");
                    System.out.println("3:salary");
                    System.out.println("b:back");
                    System.out.println("---------------------");
                    cmd = scanner.next();
                    while (!cmd.equals("b")) {
                        System.out.println("请输入要删除的记录的id:");
                        String id = scanner.next();
                        if (cmd.equals("1")) {
                            sql = "DELETE FROM employee WHERE eid=?";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, id);
                            preparedStatement.executeUpdate();
                        } else if (cmd.equals("2")) {
                            sql = "DELETE FROM department WHERE did=?";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, id);
                            preparedStatement.executeUpdate();
                        } else if (cmd.equals("3")) {
                            sql = "DELETE FROM salary WHERE sid=?";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, id);
                            preparedStatement.executeUpdate();
                        } else {
                            System.out.println("请重新输入");
                        }
                        System.out.println("---------------------");
                        System.out.println("请选择删除对象：");
                        System.out.println("1:employee      2:department");
                        System.out.println("3:salary");
                        System.out.println("b:back");
                        System.out.println("---------------------");
                        cmd = scanner.next();
                    }
                }
                System.out.println("---------------------");
                System.out.println("请选择操作：");
                System.out.println("1:create      2:read");
                System.out.println("3:update      4:delete");
                System.out.println("q:quit");
                System.out.println("---------------------");
                cmd = scanner.next();

            }

            // 完成后关闭

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}