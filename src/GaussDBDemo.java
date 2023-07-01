import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GaussDBDemo {
    static final String JDBC_DRIVER = "com.huawei.opengauss.jdbc.Driver";
    static final String DB_URL = "jdbc:opengauss://116.205.157.173:8000/db_2020_01?ApplicationName=app1&characterEncoding=utf-8";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "db_user2020_265";
    static final String PASS = "db_user@123";
    public static Connection conn;
    public static Statement stmt;

    public static void main(String[] args) {

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
            sql = "SELECT id, name, url FROM db_user2020_265.websites  where name='华为云'";
            ResultSet rs = stmt.executeQuery(sql);


            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }

            // String table_employee="CREATE TABLE employee"+"(eid INTEGER,"+"sid INTEGER,"+"did INTEGER,"+"ename VARCHAR(20),"+"gender VARCHAR(20),"+"class VARCHAR(20))";
            // String table_department="CREATE TABLE department"+"(did INTEGER,"+"dname VARCHAR(20),"+"function VARCHAR(20))";
            // String table_salary="CREATE TABLE salary"+"(sid INTEGER,"+"eid INTEGER,"+"ammount INTEGER,"+"awrd INTEGER)";
            // stmt.executeUpdate(table_department);
            //stmt.executeUpdate(table_employee);
            // stmt.executeUpdate(table_salary);


            // String sql="INSERT INTO Person VALUES (1, "a", "B", 18)";
            //  sql="INSERT INTO department VALUES(1,'研发部','研发')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO department VALUES(2,'人事部','人事任命')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO department VALUES(3,'销售部','销售')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO employee VALUES(1,1,1,'张三','男','A')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO salary VALUES(1,1,9000,1000)";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO employee VALUES(2,2,3,'李四','男','C')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO salary VALUES(2,3,6000,1000)";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO employee VALUES(3,3,2,'李娜','女','B')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO salary VALUES(3,3,8000,500)";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO employee VALUES(4,4,1,'王建','男','D')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO salary VALUES(4,4,10000,2000)";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO employee VALUES(5,5,2,'李莲','女','A')";
            // stmt.executeUpdate(sql);
            // sql="INSERT INTO salary VALUES(5,5,7000,1100)";
            // stmt.executeUpdate(sql);


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
                    System.out.println("3:salary      4:delete");
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
                            System.out.println("ename=" + ename);
                            // //sql="INSERT INTO employee VALUES(5,5,2,'李莲','女','A')";
                            // sql="INSERT INTO employee VALUES('"+eid+"','"+eid+"','"+did+"','"+ename+"','"+gender+"','"+eclass+"'')";
                            preparedStatement.executeLargeUpdate();
                            System.out.println("---------------------");
                            System.out.println("请选择创建对象：");
                            System.out.println("1:employee      2:department");
                            System.out.println("3:salary      4:delete");
                            System.out.println("b:back");
                            System.out.println("---------------------");
                            cmd = scanner.next();
                        }
                    }
                } else if (cmd.equals("2")) {

                    System.out.println("fjadskff");
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
            rs.close();
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


    public List<Employee> searchAll() throws SQLException {
        String sql = "SELECT * FROM employee,department,salary WHERE employee.did=department.did AND employee.sid=salary.sid;";
        ResultSet rs = stmt.executeQuery(sql);
        List<Employee> employeeList = new ArrayList<>();
        while (rs.next()) {
            Integer eid = Integer.valueOf(rs.getString("eid"));
            Integer sid = Integer.valueOf(rs.getString("sid"));
            Integer did = Integer.valueOf(rs.getString("did"));
            Integer salary = Integer.valueOf(rs.getString("ammount"));
            String ename = rs.getString("ename");
            String dname = rs.getString("dname");
            String gender = rs.getString("gender");
            String class_level = rs.getString("class");
            System.out.println(eid + "\t"
                    + sid + "\t"
                    + did + "\t"
                    + ename + "\t"
                    + gender + "\t"
                    + class_level);
            employeeList.add(new Employee(eid, sid, did, ename, gender, class_level, dname, salary));
        }
        return employeeList;
    }

    public List<Employee> search(String name) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employee,department,salary " +
                "WHERE ename=? AND employee.did=department.did AND employee.sid=salary.sid";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        System.out.println("查询结果：");
        while (rs.next()) {
            Integer eid = Integer.valueOf(rs.getString("eid"));
            Integer sid = Integer.valueOf(rs.getString("sid"));
            Integer did = Integer.valueOf(rs.getString("did"));
            Integer salary = Integer.valueOf(rs.getString("ammount"));
            String ename = rs.getString("ename");
            String dname = rs.getString("dname");
            String gender = rs.getString("gender");
            String class_level = rs.getString("class");
            System.out.println(eid + "\t"
                    + sid + "\t"
                    + did + "\t"
                    + ename + "\t\t"
                    + gender + "\t"
                    + class_level);
            employeeList.add(new Employee(eid, sid, did, ename, gender, class_level, dname, salary));
        }
        return employeeList;
    }

    public boolean connect_database() {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println(" 实例化 Statement 对象...");
            stmt = conn.createStatement();

            return true;
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            return false;
        }
    }
    public boolean close_connect() throws SQLException {
        stmt.close();
        conn.close();
        System.out.println("数据库连接关闭成功！");
        return true;
    }

    public boolean add(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (eid,sid, did, ename, gender, class) VALUES ( ? , ?, ?, ?, ?, ?)";

        // 创建 PreparedStatement 对象并设置参数
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getEid());
        preparedStatement.setInt(2, employee.getEid());
        preparedStatement.setInt(3, employee.getDid());
        preparedStatement.setString(4, employee.getEname());
        preparedStatement.setString(5, employee.getGender());
        preparedStatement.setString(6, employee.getClass_level());
        System.out.println("ename=" + employee.getEname());
        preparedStatement.executeLargeUpdate();

        sql = "INSERT INTO salary (sid,eid,ammount, awrd) VALUES (?, ?,?,?)";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getEid());
        preparedStatement.setInt(2, employee.getEid());
        preparedStatement.setInt(3, employee.getSalary());
        preparedStatement.setString(4, employee.getAward());
        preparedStatement.executeLargeUpdate();

        return true;
    }

    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET eid=?, sid=?, did=?, ename=?, gender=?, class=? WHERE eid=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getEid());
        preparedStatement.setInt(2, employee.getSid());
        preparedStatement.setInt(3, employee.getDid());
        preparedStatement.setString(4, employee.getEname());
        preparedStatement.setString(5, employee.getGender());
        preparedStatement.setString(6, employee.getClass_level());
        preparedStatement.setInt(7, employee.getEid());
        preparedStatement.executeUpdate();

        sql = "UPDATE salary SET sid=?, eid=?, ammount=?, awrd=? WHERE sid=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getEid());
        preparedStatement.setInt(2, employee.getEid());
        preparedStatement.setInt(3, employee.getSalary());
        preparedStatement.setString(4, employee.getAward());
        preparedStatement.setInt(5, employee.getEid());
        preparedStatement.executeLargeUpdate();
        return true;
    }

    public boolean delete(Employee employee) throws SQLException {
        System.out.println("delete id:" + employee.getEid());
        String sql = "DELETE FROM employee WHERE eid=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getEid());
        preparedStatement.executeUpdate();

        sql = "DELETE FROM salary WHERE sid=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,employee.getEid());
        preparedStatement.executeUpdate();
        return true;
    }

    public List<Department> search_all_department() throws SQLException {
        List<Department> departmentList = new ArrayList<>();
        String sql = "SELECT * FROM department;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Integer did = Integer.valueOf(rs.getString("did"));
            String dname = rs.getString("dname");
            String function = rs.getString("function");
            System.out.println(did + "\t"
                    + dname + "\t"
                    + function);
            departmentList.add(new Department(did,dname,function));
        }
        return departmentList;
    }

    public boolean add_department(Department department) throws SQLException {
        String sql = "INSERT INTO department (did,dname, function) VALUES (?, ?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, department.getDid());
        preparedStatement.setString(2, department.getDname());
        preparedStatement.setString(3, department.getFunction());
        preparedStatement.executeLargeUpdate();
        return true;
    }
}