
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class UserListView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField nameText;
    public static GaussDBDemo gaussDBDemo;

    List<Employee> employeeList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    gaussDBDemo = new GaussDBDemo();
                    if (gaussDBDemo.connect_database()) {
                        System.out.println("连接数据库成功！");
                    }
                    UserListView frame = new UserListView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public UserListView() {
        setTitle("员工信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 620, 360);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 600, 250);
        contentPane.add(scrollPane);

        Object[] columns = {"员工ID", "姓名", "性别", "部门ID", "部门", "工资ID", "工资", "等级"};// 字段
        Object[][] data = null;// 需要展示的数据，一般是二维数组
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);

        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("姓名");
        lblNewLabel.setBounds(12, 12, 42, 15);
        contentPane.add(lblNewLabel);

        nameText = new JTextField();
        nameText.setBounds(44, 5, 115, 28);
        nameText.setColumns(10);
        contentPane.add(nameText);


        //查询姓名按钮
        JButton searchBtn = new JButton("查询");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    search(nameText.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchBtn.setBounds(169, 8, 63, 25);
        contentPane.add(searchBtn);

        //查询所有信息
        JButton searchAllBtn = new JButton("查询所有");
        searchAllBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);// 清除原有行
                // 填充数据
                try {
                    employeeList = gaussDBDemo.searchAll();
                    for (Employee item : employeeList) {
                        String[] arr = new String[8];
                        arr[0] = String.valueOf(item.getEid());
                        arr[1] = item.getEname();
                        arr[2] = item.getGender();
                        arr[3] = String.valueOf(item.getDid());
                        arr[4] = item.getDname();
                        arr[5] = String.valueOf(item.getSid());
                        arr[6] = String.valueOf(item.getSalary());
                        arr[7] = item.getClass_level();
                        // 添加数据到表格
                        tableModel.addRow(arr);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchAllBtn.setBounds(229, 8, 90, 25);
        contentPane.add(searchAllBtn);

        //添加按钮
        JButton addBtn = new JButton("添加");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddView view = new AddView();
                view.setVisible(true);
//                view.addWindowListener(
//                        new WindowAdapter() {   // 实现了WindowListener接口
//                            @Override
//                            public void windowClosing(WindowEvent e) {
//                                view.close_connection();
//                                System.out.println("关闭了更新窗口");
//                            }
//                        }
//                );
            }
        });
        addBtn.setBounds(365, 8, 63, 25);
        contentPane.add(addBtn);

        //修改按钮
        JButton updateBtn = new JButton("修改");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取选中行
                int row = table.getSelectedRow();
//                System.out.println("update row:"+row);
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
//                int id = Integer.valueOf(table.getValueAt(row, 0).toString());    //或取选中行的第一列的值
                UpdateView view = new UpdateView(employeeList.get(row));
                view.setVisible(true);
            }
        });
        updateBtn.setBounds(438, 8, 63, 25);

        //删除按钮
        JButton deleteBtn = new JButton("删除");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取选中行
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int result = JOptionPane.showConfirmDialog(contentPane, "确认删除该员工吗？", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
//                    int id = Integer.valueOf(table.getValueAt(row, 0).toString());
                    boolean flag = false;
                    try {
                        flag = gaussDBDemo.delete(employeeList.get(row));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    if (flag) {
                        JOptionPane.showMessageDialog(contentPane, "删除成功!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                    }
                }
                return;
            }
        });
        deleteBtn.setBounds(511, 8, 63, 25);
        contentPane.add(deleteBtn);
        contentPane.add(updateBtn);

        //查询所有部门
        JButton searchDepartment = new JButton("查询所有部门");
        searchDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);// 清除原有行

                // 填充数据
                try {
                    List<Department> departmentList = gaussDBDemo.search_all_department();
                    for (Department item : departmentList) {
                        String[] arr = new String[8];
                        arr[3] = String.valueOf(item.getDid());
                        arr[4] = item.getDname();
                        // 添加数据到表格
                        tableModel.addRow(arr);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchDepartment.setBounds(5, 300, 120, 25);
        contentPane.add(searchDepartment);

        // 添加部门
        JButton addDepartment = new JButton("添加部门");
        addDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddDepartView view = new AddDepartView();
                view.setVisible(true);
            }
        });
        addDepartment.setBounds(130, 300, 100, 25);
        contentPane.add(addDepartment);
    }

    public void search(String name) throws SQLException {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        List<Employee> employeeList = gaussDBDemo.search(name);
        for (Employee item : employeeList) {
            String[] arr = new String[5];
            arr[0] = String.valueOf(item.getEid());
            arr[1] = item.getEname();
            arr[2] = item.getDname();
            arr[3] = item.getGender();
            arr[4] = String.valueOf(item.getSid());
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }

    // 填充表格数据
    public void load(String name) throws SQLException {
        List<Employee> list = gaussDBDemo.search(name);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Employee item : list) {
            String[] arr = new String[5];
            arr[0] = item.getEid() + "";
            arr[1] = item.getGender();
            arr[2] = item.getEname();
            arr[3] = item.getClass_level();
            arr[4] = sdf.format(item.getClass_level());
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }
}
