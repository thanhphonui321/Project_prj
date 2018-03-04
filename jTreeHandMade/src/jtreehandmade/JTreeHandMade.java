/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtreehandmade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author nguyenducthanh
 */
public class JTreeHandMade extends JFrame {

    // khoi tao bien
    private javax.swing.JButton btnNewDept, btnNewEmp, btnRemoveDept, btnRemoveEmp, btnSaveDept, btnSaveEmp, btnSaveToFile;
    private javax.swing.JLabel jLabelDepCode, jLabelDepName, jLabelEmpCode, jLabelEmpName, jLabelEmpSalary;
    private javax.swing.JPanel pnlMain, pnlDepartment, pnlEmployee;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree tree;
    private javax.swing.JTextField txtDeptCode, txtDeptName, txtEmpCode, txtEmpName, txtEmpSalary;
    Font f12 = new Font("Times New Roman", 0, 20);

    //
    String filename = "department.txt";
    DefaultMutableTreeNode root;
    DefaultMutableTreeNode curDeptNode = null;
    DefaultMutableTreeNode curEmpNode = null;
    boolean addNewDept = true;
    boolean addNewEmp = true;

    public JTreeHandMade() {
        initComponents();
        loadData();
        TreePath path = new TreePath(root);
        tree.expandPath(path);

    }

    private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch) {

        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);

    }

    private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch, Insets myInsets) {

        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = myInsets;
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);

    }

    private void initComponents() {
        // set layout for mainFrame
        this.setLayout(new GridLayout(1, 1, 0, 0));

        // set Layout for pnlMain
        pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());

        // khoi tao tree va jscroll panel
        tree = new JTree();
        root = new DefaultMutableTreeNode("Department");
        tree.setModel(new DefaultTreeModel(root));
        jScrollPane1 = new JScrollPane(tree);
        addComp(pnlMain, jScrollPane1, 0, 0, 1, 2, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);

        // khoi tao panel department
        pnlDepartment = new JPanel();
        pnlDepartment.setLayout(new GridBagLayout());
        pnlDepartment.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pnlDepartment.setBorder(BorderFactory.createTitledBorder("Departments Details"));
        // add depcode, depname
        JPanel pnlDepartmentNum1 = new JPanel();
        pnlDepartmentNum1.setLayout(new GridBagLayout());
        jLabelDepCode = new JLabel("Dept.code");
        addComp(pnlDepartmentNum1, jLabelDepCode, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        txtDeptCode = new JTextField("", 20);
        txtDeptCode.setFont(f12);
        addComp(pnlDepartmentNum1, txtDeptCode, 1, 0, 2, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

        jLabelDepName = new JLabel("Dept.name");
        addComp(pnlDepartmentNum1, jLabelDepName, 0, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        txtDeptName = new JTextField("", 20);
        txtDeptName.setFont(f12);
        addComp(pnlDepartmentNum1, txtDeptName, 1, 1, 2, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

        addComp(pnlDepartment, pnlDepartmentNum1, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

//         add 3 button of department
        JPanel pnlDepartmentNum2 = new JPanel();
        pnlDepartmentNum2.setLayout(new GridBagLayout());
        btnNewDept = new JButton("  New ");
        addComp(pnlDepartmentNum2, btnNewDept, 0, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 70));

        btnRemoveDept = new JButton("Remove");
        addComp(pnlDepartmentNum2, btnRemoveDept, 1, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 70));
        btnSaveDept = new JButton(" Save ");
        addComp(pnlDepartmentNum2, btnSaveDept, 2, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0));

        addComp(pnlDepartment, pnlDepartmentNum2, 0, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        // add department to mainPnl
        addComp(pnlMain, pnlDepartment, 1, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);

        // khoi tao panel Employee
        pnlEmployee = new JPanel();
        pnlEmployee.setLayout(new GridBagLayout());
        pnlEmployee.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pnlEmployee.setBorder(BorderFactory.createTitledBorder("Employees Details"));
        // add empcode, empname, empSalary
        JPanel pnlEmployeeNum1 = new JPanel();
        pnlEmployeeNum1.setLayout(new GridBagLayout());
        jLabelEmpCode = new JLabel("Emp.code");
        addComp(pnlEmployeeNum1, jLabelEmpCode, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        txtEmpCode = new JTextField("", 20);
        txtEmpCode.setFont(f12);
        addComp(pnlEmployeeNum1, txtEmpCode, 1, 0, 2, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

        jLabelEmpName = new JLabel("Emp.name");
        addComp(pnlEmployeeNum1, jLabelEmpName, 0, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        txtEmpName = new JTextField("", 20);
        txtEmpName.setFont(f12);
        addComp(pnlEmployeeNum1, txtEmpName, 1, 1, 2, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

        jLabelEmpSalary = new JLabel("Emp.salary");
        addComp(pnlEmployeeNum1, jLabelEmpSalary, 0, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        txtEmpSalary = new JTextField("", 20);
        txtEmpSalary.setFont(f12);
        addComp(pnlEmployeeNum1, txtEmpSalary, 1, 2, 2, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

        addComp(pnlEmployee, pnlEmployeeNum1, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

//         add 3 button of emp
        JPanel pnlEmployeeNum2 = new JPanel();
        pnlEmployeeNum2.setLayout(new GridBagLayout());
        btnNewEmp = new JButton("  New ");
        addComp(pnlEmployeeNum2, btnNewEmp, 0, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 70));

        btnRemoveEmp = new JButton("Remove");
        addComp(pnlEmployeeNum2, btnRemoveEmp, 1, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 70));
        btnSaveEmp = new JButton(" Save ");
        addComp(pnlEmployeeNum2, btnSaveEmp, 2, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0));

        addComp(pnlEmployee, pnlEmployeeNum2, 0, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        // add emp to mainPnl
        addComp(pnlMain, pnlEmployee, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);

        // add button "Save to file" to mainPnl
        btnSaveToFile = new JButton("Save to file");
        addComp(pnlMain, btnSaveToFile, 0, 2, 2, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        // add main Pnl
        this.add(pnlMain);

        // add eventListener
        // mouse clicked
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked();
            }
        });
        // department
        btnNewDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnNewDeptActionPerformed();
            }
        });

        btnRemoveDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnRemoveDeptActionPerformed();
            }
        });

        btnSaveDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnSaveDeptActionPerformed();
            }
        });

        // Employee
        btnNewEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnNewEmpActionPerformed();
            }
        });
        btnRemoveEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnRemoveEmpActionPerformed();
            }
        });
        btnSaveEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnSaveEmpActionPerformed();
            }
        });
        btnSaveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnSaveToFileActionPerformed();
            }
        });
    }

    private void loadData() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return;
            }
            String details;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((details = br.readLine()) != null) {
                details = details.trim();
                boolean isDept = (details.charAt(details.length() - 1) == ':');
                StringTokenizer stk = new StringTokenizer(details, "-:,");
                String code = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                if (isDept) {
                    Department dept = new Department(code, name);
                    curDeptNode = new DefaultMutableTreeNode(dept);
                    root.add(curDeptNode);
                } else {
                    int salary = Integer.parseInt(stk.nextToken().trim());
                    Employee emp = new Employee(code, name, salary);
                    curEmpNode = new DefaultMutableTreeNode(emp);
                    curDeptNode.add(curEmpNode);
                }
            }

            curDeptNode = curEmpNode = null;
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void treeMouseClicked() {
        // TODO add your handling code here:
        tree.cancelEditing();
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            return;
        }
        DefaultMutableTreeNode selectedNode = null;
        selectedNode = (DefaultMutableTreeNode) (path.getLastPathComponent());
        Object selecObject = selectedNode.getUserObject();
        if (selectedNode == root) {
            this.curDeptNode = this.curEmpNode = null;
        } else {
            if (selecObject instanceof Department) {
                this.curDeptNode = selectedNode;
                this.curEmpNode = null;
            } else if (selecObject instanceof Employee) {
                curEmpNode = selectedNode;
                curDeptNode = (DefaultMutableTreeNode) (selectedNode.getParent());
            }
        }
        viewDeptAndEmp();

    }

    private boolean validDepDetails(Department dept) {

        // check code
        if (!dept.getDeptCode().matches("[A-Z]{2}")) {
            return false;
        }
        for (int i = 0; i < root.getChildCount(); i++) {
            Department tmpDep = (Department) (((DefaultMutableTreeNode) root.getChildAt(i)).getUserObject());
            if (tmpDep.getDeptCode().equals(dept.getDeptCode())) {
                return false;
            }
        }
        // check name

        if (dept.getDeptName().matches("")) {
            return false;
        }
        return true;
    }

    private void btnNewEmpActionPerformed() {
        // TODO add your handling code here:
        txtEmpCode.setText("");
        txtEmpName.setText("");
        txtEmpSalary.setText("");
        txtEmpCode.setEditable(true);
        txtEmpCode.requestFocus();
    }

    private boolean validEmpDetails(Employee emp) {
        if (!emp.getCode().matches("[A-Z][0-9]{3}") || emp.getName().matches("") || emp.getSalary() <= 0) {
            return false;
        }
        for (int i = 0; i < root.getChildCount(); i++) {
            for (int j = 0; j < root.getChildAt(i).getChildCount(); j++) {
                DefaultMutableTreeNode empNode = (DefaultMutableTreeNode) root.getChildAt(i).getChildAt(j);
                Employee tmpEmp = (Employee) empNode.getUserObject();
                if (tmpEmp.getCode().equals(emp.getCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void btnSaveEmpActionPerformed() {
        // TODO add your handling code here:
        // check if dep exist
        if (curDeptNode == null) {
            JOptionPane.showMessageDialog(this, "You have to choose Department.");
        } else {
            String empCode = txtEmpCode.getText();
            String empName = txtEmpName.getText();
            int empSalary;
            try {
                empSalary = Integer.parseInt(txtEmpSalary.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "uhm uhm, Wrong !!!");
                return;
            }
            Employee tmpEmp = new Employee(empCode, empName, empSalary);
            if (validEmpDetails(tmpEmp)) {
                curDeptNode.add(new DefaultMutableTreeNode(tmpEmp));
            } else {
                JOptionPane.showMessageDialog(this, "uhm uhm, Wrong !!!");
            }
            tree.updateUI();
        }

        // check valid and add
    }

    private void btnRemoveEmpActionPerformed() {
        // TODO add your handling code here:
        if (curEmpNode == null) {
            JOptionPane.showMessageDialog(this, "Nothing to remove !!!");
        } else {
            int respone = JOptionPane.showConfirmDialog(this, "Delete this employee ???");
            if (respone == JOptionPane.OK_OPTION) {
                curDeptNode.remove(this.curEmpNode);
                tree.updateUI();
                curDeptNode = null;
                curEmpNode = null;
            }
        }

    }

    private void btnSaveToFileActionPerformed() {
        // TODO add your handling code here:
        try {
            PrintWriter pw = new PrintWriter(filename);
            for (int i = 0; i < root.getChildCount(); i++) {
                DefaultMutableTreeNode tmpDepNode = (DefaultMutableTreeNode) root.getChildAt(i);
                Department tmpDepObject = (Department) tmpDepNode.getUserObject();
                pw.println(tmpDepObject.toString() + ":");
                for (int j = 0; j < tmpDepNode.getChildCount(); j++) {
                    DefaultMutableTreeNode tmpEmpNode = (DefaultMutableTreeNode) tmpDepNode.getChildAt(j);
                    Employee tmpEmpObject = (Employee) tmpEmpNode.getUserObject();
                    pw.println(String.format("%s, %s, %d", tmpEmpObject.getCode(), tmpEmpObject.getName(),
                            tmpEmpObject.getSalary()));
                }
            }
            pw.close();
            JOptionPane.showMessageDialog(this, "Succesful !!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnSaveDeptActionPerformed() {
        // TODO add your handling code here:
        String deptCode = txtDeptCode.getText().trim().toUpperCase();
        String deptName = txtDeptName.getText().trim();
        if (validDepDetails(new Department(deptCode, deptName))) {
            root.add(new DefaultMutableTreeNode(new Department(deptCode, deptName)));
        } else {
            JOptionPane.showMessageDialog(this, "uhm uhm, Wrong !!!");
        }
        tree.updateUI();
    }

    private void btnRemoveDeptActionPerformed() {
        // TODO add your handling code here:
        if (this.curDeptNode != null) {
            if (this.curDeptNode.getChildCount() > 0) {
                String msg = "Remove all employees before deleting a department.";
                JOptionPane.showMessageDialog(this, msg);
            } else {
                int respone = JOptionPane.showConfirmDialog(this, "Delete this department ???");
                if (respone == JOptionPane.OK_OPTION) {
                    root.remove(this.curDeptNode);
                    tree.updateUI();
                    curDeptNode = null;
                    curEmpNode = null;
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Nothing to remove !!!");
        }

    }

    private void btnNewDeptActionPerformed() {
        // TODO add your handling code here:

        txtDeptCode.setText("");
        txtDeptName.setText("");
        txtEmpCode.setText("");
        txtEmpName.setText("");
        txtEmpSalary.setText("");
        txtDeptCode.setEditable(true);
        txtDeptCode.requestFocus();
    }

    private void viewDeptAndEmp() {
        Department curDep = null;
        Employee curEmp = null;
        if (curDeptNode != null) {
            curDep = (Department) curDeptNode.getUserObject();

        }
        if (curEmpNode != null) {
            curEmp = (Employee) curEmpNode.getUserObject();

        }
        this.txtDeptCode.setText(curDep != null ? curDep.getDeptCode() : "");
        this.txtDeptName.setText(curDep != null ? curDep.getDeptName() : "");
        this.txtEmpCode.setText(curEmp != null ? curEmp.getCode() : "");
        this.txtEmpName.setText(curEmp != null ? curEmp.getName() : "");
        this.txtEmpSalary.setText("" + (curEmp != null ? curEmp.getSalary() : ""));
        this.txtDeptCode.setEditable(false);
        this.txtEmpCode.setEditable(false);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JTreeHandMade c = new JTreeHandMade();
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setTitle("Workshop 4");
        c.pack();
        c.setSize(800, 500);
        c.setLocationRelativeTo(null);
        c.setVisible(true);
        c.setResizable(false);
    }

}
