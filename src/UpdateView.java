import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UpdateView extends JFrame {

    private JPanel contentPane;

    private JTextField eidText;
    private JTextField salaryText;
    private JTextField didText;
    private JTextField nameText;
    private JTextField genderText;
    private JTextField class_level_Text;

    public static GaussDBDemo gaussDBDemo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateView frame = new UpdateView(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param employee
     */
    public UpdateView(final Employee employee) {
        gaussDBDemo=new GaussDBDemo();
        setTitle("员工更新");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 443, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("员工ID：");
        lblNewLabel.setBounds(102, 50, 53, 15);
        contentPane.add(lblNewLabel);

        eidText = new JTextField();
        eidText.setBounds(151, 44, 160, 28);
        contentPane.add(eidText);
        eidText.setColumns(10);
        eidText.setText(String.valueOf(employee.getEid()));

        JLabel lblNewLabel_1 = new JLabel("部门ID：");
        lblNewLabel_1.setBounds(102, 93, 53, 15);
        contentPane.add(lblNewLabel_1);

        didText = new JTextField();
        didText.setBounds(151, 87, 160, 28);
        contentPane.add(didText);
        didText.setColumns(10);
        didText.setText(String.valueOf(employee.getDid()));

        JLabel lblNewLabel_2 = new JLabel("姓名：");
        lblNewLabel_2.setBounds(111, 134, 43, 15);
        contentPane.add(lblNewLabel_2);

        nameText = new JTextField();
        nameText.setBounds(151, 128, 160, 28);
        contentPane.add(nameText);
        nameText.setColumns(10);
        nameText.setText(employee.getEname());

        JLabel lblNewLabel_3 = new JLabel("性别：");
        lblNewLabel_3.setBounds(111, 174, 43, 15);
        contentPane.add(lblNewLabel_3);

        genderText = new JTextField();
        genderText.setBounds(151, 168, 160, 28);
        contentPane.add(genderText);
        genderText.setColumns(10);
        genderText.setText(employee.getGender());

        JLabel lblNewLabel_4 = new JLabel("工资：");
        lblNewLabel_4.setBounds(111, 214, 43, 15);
        contentPane.add(lblNewLabel_4);

        salaryText = new JTextField();
        salaryText.setBounds(151, 208, 160, 28);
        contentPane.add(salaryText);
        salaryText.setColumns(10);
        salaryText.setText(String.valueOf(employee.getSalary()));


        JLabel lblNewLabel_5 = new JLabel("等级：");
        lblNewLabel_5.setBounds(111, 254, 43, 15);
        contentPane.add(lblNewLabel_5);

        class_level_Text = new JTextField();
        class_level_Text.setBounds(151, 248, 160, 28);
        contentPane.add(class_level_Text);
        class_level_Text.setColumns(10);
        class_level_Text.setText(employee.getClass_level());

        //保存
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer eid= Integer. valueOf(eidText.getText());
                Integer did= Integer.valueOf(didText.getText());
                Integer salary= Integer.valueOf(salaryText.getText());
                String name=nameText.getText();
                String gender=genderText.getText();
                String class_level = class_level_Text.getText();

                if(eid == null || "".equals(eid)){
                    JOptionPane.showMessageDialog(contentPane, "请输入员工ID", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(name == null || "".equals(name)){
                    JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(did == null || "".equals(did)){
                    JOptionPane.showMessageDialog(contentPane, "请输入部门编号", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(salary == null || "".equals(salary)){
                    JOptionPane.showMessageDialog(contentPane, "请输入工资", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(gender == null || "".equals(gender)){
                    JOptionPane.showMessageDialog(contentPane, "请输入性别", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(class_level == null || "".equals(class_level)){
                    JOptionPane.showMessageDialog(contentPane, "请输入等级", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Employee employee=new Employee(eid,eid,did,name,gender,class_level,null,salary);

                boolean flag = false;
                try {
                    flag = gaussDBDemo.update(employee);
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
        saveBtn.setBounds(151, 300, 74, 23);
        contentPane.add(saveBtn);

        //取消
        JButton cancleBtn = new JButton("取消");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancleBtn.setBounds(237, 300, 74, 23);
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