/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motCLD.Frames;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import motCLD.Classes.Customers;
import motCLD.Classes.Parts;
import motCLD.Classes.SQLConnection;
import static motCLD.Frames.PartsControlFrame.type;

/**
 *
 * @author jouse
 */
public class PurchasedFrame extends javax.swing.JFrame {

    /**
     * Creates new form PartsControlFrame
     */
    
    Connection con=null;
    public static String type;
    String url = "jdbc:mysql://localhost:3306/Bestie_Shopkeeper_DB";
    String login = "admeSkpper";
    String pass = "memeSkpper";
    public PurchasedFrame() {
        initComponents();
        this.setTitle("©mot");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fillDataCombo();
    }
    
    
    private void fillDataCombo(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
		Statement stmt = con.createStatement();
		ResultSet typeRs = stmt.executeQuery("select Type from Parts group by Type");
		while (typeRs.next()) {
                    jComboBox1.addItem(typeRs.getString(1).toString());
                }
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) { 
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public boolean checkInputs(){
        if(txtPart==null
           || txtCust_Name==null
           || txtEachPartPrice==null){
         return false;
        }else{
            return true;
        }
    }
    
    public ArrayList<Parts> getPartsList() throws Exception{
        
        ArrayList<Parts> partsList=new ArrayList<Parts>();
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            String query="Select * from Parts";
            Statement stmt;
            ResultSet rs;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            Parts part;
            
            while (rs.next()) {
                part=new Parts(rs.getInt("Part_ID"),rs.getString("Type"),rs.getString("Part_Name"),rs.getString("Part_Type"),rs.getString("Avaliable_Quantity"),Double.parseDouble(String.valueOf(rs.getInt("Price"))),rs.getString("Inserted_Date"));
                partsList.add(part);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partsList;
    }
    
    public ArrayList<Parts> getPartsList(String type) throws Exception{
        
        ArrayList<Parts> partsList=new ArrayList<Parts>();
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            String query="select * from Parts where Type='"+type+"'";
            Statement stmt;
            ResultSet rs;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            Parts part;
            
            while (rs.next()) {
                part=new Parts(rs.getInt("Part_ID"),rs.getString("Type"),rs.getString("Part_Name"),rs.getString("Part_Type"),rs.getString("Avaliable_Quantity"),Double.parseDouble(String.valueOf(rs.getInt("Price"))),rs.getString("Inserted_Date"));
                partsList.add(part);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partsList;
    }

    
    //catch table index for edit
    public void setDataP(int index){
        try {
            Part_ID.setVisible(false);
            Part_ID.setText(String.valueOf(getPartsList().get(index).getPart_ID()));
            Type.setVisible(false);
            Type.setText(String.valueOf(getPartsList().get(index).getType()));
            Part_Name.setVisible(false);
            Part_Name.setText(String.valueOf(getPartsList().get(index).getPart_Name()));
            Part_Type.setVisible(false);
            Part_Type.setText(String.valueOf(getPartsList().get(index).getPart_Type()));
            txtPart.setText(String.valueOf(getPartsList().get(index).getPart_Name())+" ( "+String.valueOf(getPartsList().get(index).getPart_Type()+" )"));
            txtEachPartPrice.setText(String.valueOf(getPartsList().get(index).getPrice()));
        } catch (Exception ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setDataP(int index,String type){
        try {
            Part_ID.setVisible(false);
            Part_ID.setText(String.valueOf(getPartsList(type).get(index).getPart_ID()));
            Type.setVisible(false);
            Type.setText(String.valueOf(getPartsList(type).get(index).getType()));
            Part_Name.setVisible(false);
            Part_Name.setText(String.valueOf(getPartsList(type).get(index).getPart_Name()));
            Part_Type.setVisible(false);
            Part_Type.setText(String.valueOf(getPartsList(type).get(index).getPart_Type()));
            txtPart.setText(String.valueOf(getPartsList(type).get(index).getPart_Name())+" ( "+String.valueOf(getPartsList(type).get(index).getPart_Type()+" )"));
            txtEachPartPrice.setText(String.valueOf(getPartsList(type).get(index).getPrice()));
        } catch (Exception ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public ArrayList<Customers> getCustList() throws Exception{
        
        ArrayList<Customers> custList=new ArrayList<Customers>();
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            String query="select * from Customers";
            Statement stmt;
            ResultSet rs;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            Customers cust;
            
            while (rs.next()) {
                cust=new Customers(rs.getString("Cust_Name"),rs.getString("Phone_No"),rs.getString("Address"),Double.parseDouble(String.valueOf(rs.getInt("Debt"))),Double.parseDouble(String.valueOf(rs.getInt("Payment"))),Double.parseDouble(String.valueOf(rs.getInt("Total_Purchased_Charged"))),rs.getString("Registered_Date"));
                custList.add(cust);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return custList;
    }
    
    public ArrayList<Customers> getCustList(String custName) throws Exception{
        
        ArrayList<Customers> custList=new ArrayList<Customers>();
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            String query="select * from Customers where Cust_Name like '"+custName+"%'";
            Statement stmt;
            ResultSet rs;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            Customers cust;
            
            while (rs.next()) {
                cust=new Customers(rs.getString("Cust_Name"),rs.getString("Phone_No"),rs.getString("Address"),Double.parseDouble(String.valueOf(rs.getInt("Debt"))),Double.parseDouble(String.valueOf(rs.getInt("Payment"))),Double.parseDouble(String.valueOf(rs.getInt("Total_Purchased_Charged"))),rs.getString("Registered_Date"));
                custList.add(cust);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return custList;
    }
     
     
     //catch table index for edit
    public void setDataC(int index){
        try {
            lbDebt.setVisible(false);
            lbTotalPaid.setVisible(false);
            lbTotalCharged.setVisible(false);
            Debt.setVisible(false);
            Debt.setText(String.valueOf(getCustList().get(index).getDebt()));
            Payment.setVisible(false);
            Payment.setText(String.valueOf(getCustList().get(index).getPayment()));
            Total_Charged.setVisible(false);
            Total_Charged.setText(String.valueOf(getCustList().get(index).getTotal_Charged()));
            txtCust_Name.setText(String.valueOf(getCustList().get(index).getCust_Name()));
        } catch (Exception ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setDataC(int index,String custName){
        try {
            lbDebt.setVisible(false);
            lbTotalPaid.setVisible(false);
            lbTotalCharged.setVisible(false);
            Debt.setVisible(false);
            Debt.setText(String.valueOf(getCustList(custName).get(index).getDebt()));
            Payment.setVisible(false);
            Payment.setText(String.valueOf(getCustList(custName).get(index).getPayment()));
            Total_Charged.setVisible(false);
            Total_Charged.setText(String.valueOf(getCustList(custName).get(index).getTotal_Charged()));
            txtCust_Name.setText(String.valueOf(getCustList(custName).get(index).getCust_Name()));
        } catch (Exception ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupDecision = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRefreshData = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCust_Name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEachPartPrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTotalBill = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        txtPart = new javax.swing.JTextField();
        errorLable = new javax.swing.JLabel();
        txtOrederQunatity = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();
        jRadioButtonDebt = new javax.swing.JRadioButton();
        jRadioButtonCashDown = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        txtTotalPaid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDebt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        Part_ID = new javax.swing.JLabel();
        Type = new javax.swing.JLabel();
        Part_Name = new javax.swing.JLabel();
        Part_Type = new javax.swing.JLabel();
        Debt = new javax.swing.JLabel();
        Payment = new javax.swing.JLabel();
        Total_Charged = new javax.swing.JLabel();
        lbDebt = new javax.swing.JLabel();
        lbTotalPaid = new javax.swing.JLabel();
        lbTotalCharged = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        LbCustFlag = new javax.swing.JLabel();
        LbPartsFlag = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(140, 122, 230));

        jPanel3.setBackground(new java.awt.Color(140, 122, 230));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select For Parts Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), java.awt.SystemColor.textHighlightText)); // NOI18N

        jTable1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Part ID", "Type", "Part Type", "Part Name", "Quantity", "Price", "Lastest Modified Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        btnRefreshData.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnRefreshData.setText("Refresh Data");
        btnRefreshData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshDataActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(140, 122, 230));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "New Part Purchase", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), java.awt.SystemColor.textHighlightText)); // NOI18N

        jLabel7.setBackground(new java.awt.Color(220, 221, 225));
        jLabel7.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel7.setForeground(java.awt.SystemColor.control);
        jLabel7.setText("   Customer Name");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel8.setBackground(new java.awt.Color(220, 221, 225));
        jLabel8.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel8.setForeground(java.awt.SystemColor.control);
        jLabel8.setText("   Part ");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtCust_Name.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        txtCust_Name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCust_NameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCust_NameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCust_NameMouseExited(evt);
            }
        });
        txtCust_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCust_NameActionPerformed(evt);
            }
        });
        txtCust_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCust_NameKeyReleased(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(220, 221, 225));
        jLabel9.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel9.setForeground(java.awt.SystemColor.control);
        jLabel9.setText("   Each Part Price");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtEachPartPrice.setEditable(false);
        txtEachPartPrice.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        txtEachPartPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEachPartPriceActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(220, 221, 225));
        jLabel10.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel10.setForeground(java.awt.SystemColor.control);
        jLabel10.setText("   Order Quantity");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel11.setBackground(new java.awt.Color(220, 221, 225));
        jLabel11.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel11.setForeground(java.awt.SystemColor.control);
        jLabel11.setText("   Total Bill");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtTotalBill.setEditable(false);
        txtTotalBill.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        txtTotalBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBillActionPerformed(evt);
            }
        });
        txtTotalBill.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalBillKeyTyped(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnInsert.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnInsert.setText("Purchase");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        txtPart.setEditable(false);
        txtPart.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        txtPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartActionPerformed(evt);
            }
        });

        errorLable.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        errorLable.setForeground(java.awt.Color.white);

        txtOrederQunatity.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtOrederQunatity.setForeground(java.awt.Color.gray);
        txtOrederQunatity.setText("Enter Digit Only! & Click!");
        txtOrederQunatity.setToolTipText("");
        txtOrederQunatity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOrederQunatityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOrederQunatityFocusLost(evt);
            }
        });
        txtOrederQunatity.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtOrederQunatityCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        txtOrederQunatity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrederQunatityActionPerformed(evt);
            }
        });
        txtOrederQunatity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOrederQunatityKeyTyped(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        buttonGroupDecision.add(jRadioButtonDebt);
        jRadioButtonDebt.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jRadioButtonDebt.setForeground(java.awt.Color.red);
        jRadioButtonDebt.setText("Debt");
        jRadioButtonDebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDebtActionPerformed(evt);
            }
        });

        buttonGroupDecision.add(jRadioButtonCashDown);
        jRadioButtonCashDown.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jRadioButtonCashDown.setForeground(java.awt.Color.green);
        jRadioButtonCashDown.setText("Cash Down");
        jRadioButtonCashDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCashDownActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(220, 221, 225));
        jLabel12.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel12.setForeground(java.awt.SystemColor.control);
        jLabel12.setText("   Total Paid");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtTotalPaid.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtTotalPaid.setForeground(java.awt.Color.gray);
        txtTotalPaid.setText("Enter Digit Only! & Click!");
        txtTotalPaid.setToolTipText("");
        txtTotalPaid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTotalPaidFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTotalPaidFocusLost(evt);
            }
        });
        txtTotalPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPaidActionPerformed(evt);
            }
        });
        txtTotalPaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalPaidKeyTyped(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(220, 221, 225));
        jLabel13.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel13.setForeground(java.awt.SystemColor.control);
        jLabel13.setText("   Debt");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtDebt.setEditable(false);
        txtDebt.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        txtDebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDebtActionPerformed(evt);
            }
        });
        txtDebt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDebtKeyTyped(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(220, 221, 225));
        jLabel14.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel14.setForeground(java.awt.SystemColor.control);
        jLabel14.setText("   Type");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jComboBox1.setFont(new java.awt.Font("Serif", 0, 17)); // NOI18N
        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, null, java.awt.Color.lightGray));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(txtTotalPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEachPartPrice)
                            .addComponent(txtOrederQunatity))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCust_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(txtPart))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jRadioButtonCashDown, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCust_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPart, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEachPartPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrederQunatity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonCashDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLable, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(140, 122, 230));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select For Customers Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), java.awt.SystemColor.textHighlightText)); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer_Name", "Phone_No", "Address", "Debt", "Payment", "Total_Purchased", "Registered_Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
        );

        jLabel15.setFont(new java.awt.Font("Monospaced", 3, 30)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 153, 51));
        jLabel15.setText("  Bestie Shopkeeper ");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(Part_Type)
                .addContainerGap(721, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Payment)
                    .addComponent(Debt, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LbCustFlag)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Part_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbTotalPaid)
                                    .addComponent(Total_Charged))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbDebt)
                                .addGap(202, 202, 202))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Type)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Part_Name)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(lbTotalCharged)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(LbPartsFlag)))
                                .addGap(18, 18, 18)))
                        .addComponent(btnRefreshData, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Payment))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Part_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(Type)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(LbCustFlag)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbTotalCharged)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Part_Name))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Part_Type)))))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Total_Charged)
                                .addGap(27, 27, 27)
                                .addComponent(Debt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LbPartsFlag)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnRefreshData, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbDebt)
                                            .addComponent(lbTotalPaid))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // TODO add your handling code here:
            
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            //Parts Data
            Statement stmtP = con.createStatement();
            ResultSet dataRsP = stmtP.executeQuery("select * from Parts");
            DefaultTableModel modelP=(DefaultTableModel)jTable1.getModel();
            modelP.setRowCount(0);
            while (dataRsP.next()) {
                String Part_ID = dataRsP.getString(1);
                String Type = dataRsP.getString(2);
                String Part_Name = dataRsP.getString(3);
                String Part_Type = dataRsP.getString(4);
                String Quantity = dataRsP.getString(5)+" "+dataRsP.getString(6);
                String Price = dataRsP.getString(7);
                String Lastest_Modified_Date = dataRsP.getString(8);
                Object[] rowP={Part_ID,Type,Part_Name,Part_Type,Quantity,Price,Lastest_Modified_Date};
                modelP.addRow(rowP);
            }
            
            //Customers Data
            Statement stmtC = con.createStatement();
            ResultSet dataRsC = stmtC.executeQuery("select Cust_Name,Phone_No,Address,Debt,Payment,Total_Purchased_Charged,Registered_Date from Customers");
            DefaultTableModel modelC=(DefaultTableModel)jTable3.getModel();
            modelC.setRowCount(0);
            while (dataRsC.next()) {
                String Cust_Name = dataRsC.getString("Cust_Name");
                String Phone_No = dataRsC.getString("Phone_No");
                String Address = dataRsC.getString("Address");
                String Debt=dataRsC.getString("Debt");
                String Payment=dataRsC.getString("Payment");
                String Total_Charged = dataRsC.getString("Total_Purchased_Charged");
                String Registered_Date = dataRsC.getString("Registered_Date");
                Object[] rowC={Cust_Name,Phone_No,Address,Debt,Payment,Total_Charged,Registered_Date};
                modelC.addRow(rowC);
            }
                
                con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index=jTable1.getSelectedRow();
        System.out.println(index);
        if(LbPartsFlag.getText().equals("1")){
            setDataP(index,jComboBox1.getSelectedItem().toString());
        }else if(LbPartsFlag.getText().equals(""))
            setDataP(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnRefreshDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshDataActionPerformed
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            //Parts Data
            Statement stmtP = con.createStatement();
            ResultSet dataRsP = stmtP.executeQuery("select * from Parts");
            DefaultTableModel modelP=(DefaultTableModel)jTable1.getModel();
            modelP.setRowCount(0);
            while (dataRsP.next()) {
                String Part_ID = dataRsP.getString(1);
                String Type = dataRsP.getString(2);
                String Part_Name = dataRsP.getString(3);
                String Part_Type = dataRsP.getString(4);
                String Quantity = dataRsP.getString(5)+" "+dataRsP.getString(6);
                String Price = dataRsP.getString(7);
                String Lastest_Modified_Date = dataRsP.getString(8);
                Object[] row={Part_ID,Type,Part_Name,Part_Type,Quantity,Price,Lastest_Modified_Date};
                modelP.addRow(row);
            }
            
            //Customers Data
            Statement stmtC = con.createStatement();
            ResultSet dataRsC = stmtC.executeQuery("select Cust_Name,Phone_No,Address,Debt,Payment,Total_Purchased_Charged,Registered_Date from Customers");
            DefaultTableModel modelC=(DefaultTableModel)jTable3.getModel();
            modelC.setRowCount(0);
            while (dataRsC.next()) {
                String Cust_Name = dataRsC.getString("Cust_Name");
                String Phone_No = dataRsC.getString("Phone_No");
                String Address = dataRsC.getString("Address");
                String Debt=dataRsC.getString("Debt");
                String Payment=dataRsC.getString("Payment");
                String Total_Charged = dataRsC.getString("Total_Purchased_Charged");
                String Registered_Date = dataRsC.getString("Registered_Date");
                Object[] rowC={Cust_Name,Phone_No,Address,Debt,Payment,Total_Charged,Registered_Date};
                modelC.addRow(rowC);
            }
            
            con.close();
            LbPartsFlag.setText("");
            txtCust_Name.setText("");
            txtPart.setText("");
            txtEachPartPrice.setText("");
            txtOrederQunatity.setText("Enter Digit Only!");
            txtTotalBill.setText("");
            jRadioButtonCashDown.setSelected(false);
            jRadioButtonDebt.setSelected(false);
            txtTotalPaid.setText("Enter Digit Only!");
            txtDebt.setText("");
            errorLable.setText("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshDataActionPerformed

    private void txtCust_NameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCust_NameMouseClicked
        // TODO add your handling code here:
        LbCustFlag.setVisible(false);
        LbCustFlag.setText("1");
    }//GEN-LAST:event_txtCust_NameMouseClicked

    private void txtCust_NameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCust_NameMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCust_NameMouseEntered

    private void txtCust_NameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCust_NameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCust_NameMouseExited

    private void txtCust_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCust_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCust_NameActionPerformed

    private void txtEachPartPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEachPartPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEachPartPriceActionPerformed

    private void txtTotalBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalBillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalBillActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtCust_Name.setText("");
        txtPart.setText("");
        txtEachPartPrice.setText("");
        txtOrederQunatity.setText("Enter Digit Only!");
        txtTotalBill.setText("");
        jRadioButtonCashDown.setSelected(false);
        jRadioButtonDebt.setSelected(false);
        txtTotalPaid.setText("Enter Digit Only!");
        txtDebt.setText("");
        errorLable.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            
            if(txtCust_Name.getText().equals("")
                    ||txtPart.getText().equals("")
                    ||txtEachPartPrice.getText().equals("")
                    ||txtOrederQunatity.getText().equals("")
                    ||txtOrederQunatity.getText().equals("Enter Digit Only! & Click!")
                    ||txtTotalBill.getText().equals("")
                    ||txtTotalBill.getText().equals("Enter Digit Only! & Click!")
                    ||txtTotalPaid.getText().equals("")
                    ||txtDebt.getText().equals("")){
                if(!jRadioButtonCashDown.isSelected()&&!jRadioButtonDebt.isSelected()){
                    errorLable.setForeground(Color.red);
                    errorLable.setText("       Any field has Empty!");
                }
            }else{
                
                    PreparedStatement pres;
            String CustName=txtCust_Name.getText();
            String Part_ID=this.Part_ID.getText();
            String Type=this.Type.getText();
            String Part_Name=this.Part_Name.getText();
            String Part_Type=this.Part_Type.getText();
            double Total_Bill=Double.parseDouble(txtTotalBill.getText());
            double Total_Paid=Double.parseDouble(txtTotalPaid.getText());
            double Debt=Double.parseDouble(txtDebt.getText());
            double exD=Debt,exTBill=Total_Bill,exTPaid=Total_Paid;
            String moneyQ="select Debt,Payment,Total_Charged from Customers where Cust_Name=?";
            PreparedStatement moneyStmt=con.prepareStatement(moneyQ);
            moneyStmt.setString(1, CustName);
            ResultSet moneyRs= moneyStmt.executeQuery();
            double Exist_Debt=0,Exist_Payment=0,Exist_Total_Charged=0;
            while(moneyRs.next()){
                Exist_Debt=moneyRs.getDouble(1);
                Exist_Payment=moneyRs.getDouble(2);
                Exist_Total_Charged=moneyRs.getDouble(3);
            }
                       
            
            //Debt Calculation
            Debt+=Exist_Debt;
            Total_Paid+=Exist_Payment;
            Total_Bill+=Exist_Total_Charged;
            lbDebt.setText(String.valueOf(Debt));
            lbTotalPaid.setText(String.valueOf(Total_Paid));
            lbTotalCharged.setText(String.valueOf(Total_Bill));
            
            System.out.println(Debt+" "+Total_Paid+" "+Total_Bill);
                    if(Total_Bill==Debt+Total_Paid && errorLable.getText().equals("")){
                        String query="update Customers set Debt=?,Payment=?,Total_Charged=? where Cust_Name=?";
                        pres=con.prepareStatement(query);
                        pres.setDouble(1,Double.parseDouble(lbDebt.getText()));
                        pres.setDouble(2,Double.parseDouble(lbTotalPaid.getText()));
                        pres.setDouble(3,Double.parseDouble(lbTotalCharged.getText()));
                        pres.setString(4, CustName);
                        pres.execute();
                        Statement stmtC = con.createStatement();
            ResultSet dataRsC = stmtC.executeQuery("select Cust_Name,Phone_No,Address,Debt,Payment,Total_Charged,Registered_Date from Customers");
            DefaultTableModel modelC=(DefaultTableModel)jTable3.getModel();
            modelC.setRowCount(0);
            while (dataRsC.next()) {
                String Cust_Name = dataRsC.getString("Cust_Name");
                String Phone_No = dataRsC.getString("Phone_No");
                String Address = dataRsC.getString("Address");
                String Debtt=dataRsC.getString("Debt");
                String Payment=dataRsC.getString("Payment");
                String Total_Charged = dataRsC.getString("Total_Charged");
                String Registered_Date = dataRsC.getString("Registered_Date");
                Object[] rowC={Cust_Name,Phone_No,Address,Debtt,Payment,Total_Charged,Registered_Date};
                modelC.addRow(rowC);
            }
            
            //Statement selPurchase=con.createStatement();
            String qr="select Purchased_ID from Customers_Purchased_Parts where Cust_Name=? and Existing_Debt=? and Existing_Total_Charged=?";
            PreparedStatement selP=con.prepareStatement(qr);
            selP.setString(1, CustName);
            selP.setDouble(2, Double.parseDouble(lbDebt.getText()));
            selP.setDouble(3, Double.parseDouble(lbTotalCharged.getText()));
            ResultSet reS= selP.executeQuery();
            int purID=0;
            while(reS.next()){
             purID=reS.getInt("Purchased_ID");
            }
            System.out.println(purID);
            
                    String queryP="update Customers_Purchased_Parts set Part_ID=?,Type=?,Part_Name=?,Part_Type=?,Current_Total_Paid=?,Current_Debt=?,Current_Total_Bill=? where Purchased_ID=?";
                        PreparedStatement presC=con.prepareStatement(queryP);
                        presC.setString(1, Part_ID);
                        presC.setString(2, Type);
                        presC.setString(3, Part_Name);
                        presC.setString(4, Part_Type);
                        presC.setDouble(5, exTPaid);
                        presC.setDouble(6, exD);
                        presC.setDouble(7, exTBill);
                        presC.setInt(8, purID);
                        presC.execute();
                        errorLable.setText("       Purchased Succcessful!");
                    }else{
                        errorLable.setForeground(Color.red);
                        errorLable.setText("       Any field has Empty!");
                    }
            }
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnInsertActionPerformed

    private void txtPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartActionPerformed

    private void txtOrederQunatityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOrederQunatityFocusGained
        // TODO add your handling code here:
        txtOrederQunatity.setText("");
        errorLable.setText("");
    }//GEN-LAST:event_txtOrederQunatityFocusGained

    private void txtOrederQunatityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOrederQunatityFocusLost
        // TODO add your handling code here:
        if(txtOrederQunatity.getText().equals("")){
            txtOrederQunatity.setText("Enter Digit Only! & Click!");   
        }
    }//GEN-LAST:event_txtOrederQunatityFocusLost

    private void txtOrederQunatityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrederQunatityKeyTyped
        // TODO add your handling code here:
        char vchar=evt.getKeyChar();
        if(!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
        
    }//GEN-LAST:event_txtOrederQunatityKeyTyped

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtTotalPaidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTotalPaidFocusGained
        // TODO add your handling code here:
        txtTotalPaid.setText("");
        errorLable.setText("");
    }//GEN-LAST:event_txtTotalPaidFocusGained

    private void txtTotalPaidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTotalPaidFocusLost
        // TODO add your handling code here:
        if(txtTotalPaid.getText().equals("")){
            txtTotalPaid.setText("Enter Digit Only! & Click!");   
        }
    }//GEN-LAST:event_txtTotalPaidFocusLost

    private void txtTotalPaidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPaidKeyTyped
        // TODO add your handling code here:
        txtTotalPaid.setForeground(Color.BLACK);
        char vchar=evt.getKeyChar();
        if(!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtTotalPaidKeyTyped

    private void txtDebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDebtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDebtActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        try {
            int flag=0;
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            Statement stmt=con.createStatement();
            int index=jTable3.getSelectedRow();
            if(txtCust_Name.getText().equals("") || LbCustFlag.getText().equals("")){
                setDataC(index);
            }else if(!txtCust_Name.getText().equals("") && LbCustFlag.getText().equals("1")){
                
                //if(txtCust_Name.getText().length())
                ResultSet dataRs = stmt.executeQuery("select Cust_Name from Customers");
                String str="";
                while(dataRs.next()){
                    if(txtCust_Name.getText().equals(dataRs.getString(1).toString())){
                        flag=1;
                    }  
                    System.out.println(txtCust_Name.getText()+" "+dataRs.getString(1).toString());
                }
                System.out.println(flag);
                if(flag!=1){
                    setDataC(index, txtCust_Name.getText().trim());
                }else if(flag==1){
                    setDataC(index);
                }
            }
            System.out.println(index);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTable3MouseClicked

    private void txtOrederQunatityCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtOrederQunatityCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrederQunatityCaretPositionChanged

    private void txtOrederQunatityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrederQunatityActionPerformed
        // TODO add your handling code here:
        
        double totalBill=0,eachPrice=0,orderQty=0;
        eachPrice=Double.parseDouble(txtEachPartPrice.getText());
        orderQty=Double.parseDouble(txtOrederQunatity.getText());
        totalBill=eachPrice*orderQty;
        txtTotalBill.setText(String.valueOf(totalBill));
    }//GEN-LAST:event_txtOrederQunatityActionPerformed

    private void jRadioButtonCashDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCashDownActionPerformed
        // TODO add your handling code here:
        txtDebt.setText("");
        txtDebt.disable();
    }//GEN-LAST:event_jRadioButtonCashDownActionPerformed

    private void jRadioButtonDebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDebtActionPerformed
        // TODO add your handling code here:
        txtDebt.setText("");
        txtDebt.disable();
    }//GEN-LAST:event_jRadioButtonDebtActionPerformed

    private void txtDebtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDebtKeyTyped
        // TODO add your handling code here:
        char vchar=evt.getKeyChar();
        if(!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtDebtKeyTyped

    private void txtTotalBillKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalBillKeyTyped
        // TODO add your handling code here:
        char vchar=evt.getKeyChar();
        if(!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtTotalBillKeyTyped

    private void txtTotalPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPaidActionPerformed
        // TODO add your handling code here:
        double totalPaid=Double.parseDouble(txtTotalPaid.getText());
        double totalBill=Double.parseDouble(txtTotalBill.getText()),debt=0;
        if(jRadioButtonDebt.isSelected()){
            if(totalBill>totalPaid){
                debt=totalBill-totalPaid;
                txtTotalPaid.setForeground(Color.green);
                txtDebt.setForeground(Color.red);
                txtDebt.setText(String.valueOf(debt));
                errorLable.setText("");
            }else if(totalBill<totalPaid){
                    errorLable.setForeground(Color.red);
                    errorLable.setText("You entered more total paid amount than total bill.");
                    txtDebt.setText("");
            }
        }else if(jRadioButtonCashDown.isSelected()){
            if(totalBill==totalPaid){
                txtTotalPaid.setForeground(Color.green);
                txtDebt.setForeground(Color.green);
                txtDebt.setText("0");
                errorLable.setText("");
            }else{
                errorLable.setForeground(Color.red);
                if(totalBill<totalPaid){
                    //txtTotalPaid.setForeground(Color.red);
                    errorLable.setText("You entered more total paid amount than total bill.");
                    txtDebt.setText("");
                }else if(totalBill>totalPaid){
                    //txtTotalPaid.setForeground(Color.red);
                    errorLable.setText("You entered less total paid amount than total bill.");
                    txtDebt.setText("");
                }
                
            }
        }
    }//GEN-LAST:event_txtTotalPaidActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
        LbPartsFlag.setVisible(false);
        LbPartsFlag.setText("1");
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            Statement stmt = con.createStatement();

            //for Type
            type=jComboBox1.getSelectedItem().toString();
            System.out.println(type);

            ResultSet dataRs = stmt.executeQuery("select * from Parts where Type='"+type+"'");
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            while (dataRs.next()) {
                String Part_ID = dataRs.getString(1);
                String Type = dataRs.getString(2);
                String Part_Name = dataRs.getString(3);
                String Part_Type = dataRs.getString(4);
                String Quantity = dataRs.getString(5)+" "+dataRs.getString(6);
                String Price = dataRs.getString(7);
                String Lastest_Modified_Date = dataRs.getString(8);
                Object[] row={Part_ID,Type,Part_Name,Part_Type,Quantity,Price,Lastest_Modified_Date};
                model.addRow(row);
            }
            con.close();
        }catch (SQLException ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtCust_NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCust_NameKeyReleased
        // TODO add your handling code here:
        String custName=txtCust_Name.getText().trim();
        try {           
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            Statement stmt = con.createStatement();
            ResultSet dataRsC = stmt.executeQuery("select * from Customers where Cust_Name like '"+custName+"%'");
            DefaultTableModel modelC=(DefaultTableModel)jTable3.getModel();
            modelC.setRowCount(0);
            while (dataRsC.next()) {
                String Cust_Name = dataRsC.getString("Cust_Name");
                String Phone_No = dataRsC.getString("Phone_No");
                String Address = dataRsC.getString("Address");
                String Debt=dataRsC.getString("Debt");
                String Payment=dataRsC.getString("Payment");
                String Total_Charged = dataRsC.getString("Total_Purchased_Charged");
                String Registered_Date = dataRsC.getString("Registered_Date");
                Object[] rowC={Cust_Name,Phone_No,Address,Debt,Payment,Total_Charged,Registered_Date};
                modelC.addRow(rowC);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PurchasedFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCust_NameKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PartsControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PartsControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PartsControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PartsControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchasedFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Debt;
    private javax.swing.JLabel LbCustFlag;
    private javax.swing.JLabel LbPartsFlag;
    private javax.swing.JLabel Part_ID;
    private javax.swing.JLabel Part_Name;
    private javax.swing.JLabel Part_Type;
    private javax.swing.JLabel Payment;
    private javax.swing.JLabel Total_Charged;
    private javax.swing.JLabel Type;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnRefreshData;
    private javax.swing.ButtonGroup buttonGroupDecision;
    private javax.swing.JLabel errorLable;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonCashDown;
    private javax.swing.JRadioButton jRadioButtonDebt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbDebt;
    private javax.swing.JLabel lbTotalCharged;
    private javax.swing.JLabel lbTotalPaid;
    private javax.swing.JTextField txtCust_Name;
    private javax.swing.JTextField txtDebt;
    private javax.swing.JTextField txtEachPartPrice;
    private javax.swing.JTextField txtOrederQunatity;
    private javax.swing.JTextField txtPart;
    private javax.swing.JTextField txtTotalBill;
    private javax.swing.JTextField txtTotalPaid;
    // End of variables declaration//GEN-END:variables
}
