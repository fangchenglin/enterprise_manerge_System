import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class AddDepartView extends JFrame {

    private JPanel contentPane;

    private JTextField didText;
    private JTextField dnameText;
    private JTextField functionText;

    public static GaussDBDemo gaussDBDemo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    AddDepartView frame = new AddDepartView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddDepartView() {
        gaussDBDemo=new GaussDBDemo();
        if (gaussDBDemo.connect_database()){
            System.out.println("连接数据库成功！");
        }
        setTitle("员工添加");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 443, 270);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("部门ID：");
        lblNewLabel.setBounds(102, 50, 53, 15);
        contentPane.add(lblNewLabel);

        didText = new JTextField();
        didText.setBounds(160, 44, 160, 28);
        contentPane.add(didText);
        didText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("部门名称：");
        lblNewLabel_1.setBounds(102, 93, 70, 15);
        contentPane.add(lblNewLabel_1);

        dnameText = new JTextField();
        dnameText.setBounds(160, 87, 160, 28);
        contentPane.add(dnameText);
        dnameText.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("功能：");
        lblNewLabel_2.setBounds(111, 134, 43, 15);
        contentPane.add(lblNewLabel_2);

        functionText = new JTextField();
        functionText.setBounds(160, 128, 160, 28);
        contentPane.add(functionText);
        functionText.setColumns(10);


        //保存
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer did= Integer.valueOf(didText.getText());
                String dname= dnameText.getText();
                String funtion= functionText.getText();

                if(did == null || "".equals(did)){
                    JOptionPane.showMessageDialog(contentPane, "请输入员工ID", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(dname == null || "".equals(dname)){
                    JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(funtion == null || "".equals(funtion)){
                    JOptionPane.showMessageDialog(contentPane, "请输入部门编号", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Department department = new Department(did,dname,funtion);

                boolean flag = false;
                try {
                    flag = gaussDBDemo.add_department(department);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if(flag){
                    dispose();
                    JOptionPane.showMessageDialog(contentPane, "添加成功，刷新可查看!");
                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                return;

            }
        });
        saveBtn.setBounds(151, 174, 74, 23);
        contentPane.add(saveBtn);

        //取消
        JButton cancleBtn = new JButton("取消");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancleBtn.setBounds(237, 174, 74, 23);
        contentPane.add(cancleBtn);
    }
    public void close_connection() {
        System.out.println("正在关闭连接......");
        try {
            gaussDBDemo.close_connect();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}