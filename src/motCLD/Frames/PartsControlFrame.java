/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motCLD.Frames;

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
import motCLD.Classes.Parts;
import motCLD.Classes.SQLConnection;

/**
 *
 * @author jouse
 */
public class PartsControlFrame extends javax.swing.JFrame {

    /**
     * Creates new form PartsControlFrame
     */
    
    Connection con=null;
    public static String type;
    String url = "jdbc:mysql://localhost:3306/Bestie_Shopkeeper_DB";
    String login = "admeSkpper";
    String pass = "memeSkpper";
    public PartsControlFrame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("©mot");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        if(txtPartName==null
           || txtType==null
           || txtPartType==null
           || txtQuantity==null){
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
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partsList;
    }
    
    
    //for Combox selected Item in Table
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
    
    //show parts data in table
    /*public void show_Parts_Table(){
        try {
            ArrayList<Parts> partsList=getPartsList();
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            Object row[]=new Object[7];
            for(int i=0; i<partsList.size(); i++){
                row[0]=partsList.get(i).getPart_ID();
                row[1]=partsList.get(i).getType();
                row[2]=partsList.get(i).getPart_Name();
                row[3]=partsList.get(i).getPart_Type();
                row[4]=partsList.get(i).getQuantity();
                row[5]=partsList.get(i).getPrice();
                row[6]=partsList.get(i).getLastest_Modified_Date();
                model.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    
    //catch table index for edit
    public void setData(int index){
        try {
            System.out.println(getPartsList().get(index).getPart_ID()+"KK");
            txtPartID.setText(String.valueOf(getPartsList().get(index).getPart_ID()));
            txtPartName.setText(getPartsList().get(index).getPart_Name());
            txtType.setText(getPartsList().get(index).getType());
            txtPartType.setText(getPartsList().get(index).getPart_Type());
            txtQuantity.setText(String.valueOf(getPartsList().get(index).getAvaliable_Quantity()));
            txtPrice.setText(String.valueOf(getPartsList().get(index).getPrice()));
        } catch (Exception ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setData(int index,String type){
        try {
            System.out.println(getPartsList(type).get(index).getPart_ID()+"KK");
            txtPartID.setText(String.valueOf(getPartsList(type).get(index).getPart_ID()));
            txtPartName.setText(getPartsList(type).get(index).getPart_Name());
            txtType.setText(getPartsList(type).get(index).getType());
            txtPartType.setText(getPartsList(type).get(index).getPart_Type());
            txtQuantity.setText(String.valueOf(getPartsList(type).get(index).getAvaliable_Quantity()));
            txtPrice.setText(String.valueOf(getPartsList(type).get(index).getPrice()));
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPartName = new javax.swing.JTextField();
        txtType = new javax.swing.JTextField();
        txtPartType = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtPartID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        btnPartsInsert = new javax.swing.JButton();
        btnRefreshData = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        LbFlag = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(140, 122, 230));

        jPanel2.setBackground(new java.awt.Color(140, 122, 230));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit Part", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), java.awt.SystemColor.textHighlightText)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(220, 221, 225));
        jLabel1.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        jLabel1.setForeground(java.awt.SystemColor.control);
        jLabel1.setText("   Type");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setBackground(new java.awt.Color(220, 221, 225));
        jLabel3.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        jLabel3.setForeground(java.awt.SystemColor.control);
        jLabel3.setText("   Part Name");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setBackground(new java.awt.Color(220, 221, 225));
        jLabel4.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        jLabel4.setForeground(java.awt.SystemColor.control);
        jLabel4.setText("   Part Type");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setBackground(new java.awt.Color(220, 221, 225));
        jLabel5.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        jLabel5.setForeground(java.awt.SystemColor.control);
        jLabel5.setText("   Quantity");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPartName.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtPartName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartNameActionPerformed(evt);
            }
        });

        txtType.setFont(new java.awt.Font("Serif", 0, 17)); // NOI18N
        txtType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeActionPerformed(evt);
            }
        });

        txtPartType.setFont(new java.awt.Font("Serif", 0, 17)); // NOI18N
        txtPartType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartTypeActionPerformed(evt);
            }
        });

        txtQuantity.setFont(new java.awt.Font("Serif", 0, 17)); // NOI18N
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantityKeyTyped(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(220, 221, 225));
        jLabel7.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        jLabel7.setForeground(java.awt.SystemColor.control);
        jLabel7.setText("   Part ID");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtPartID.setEditable(false);
        txtPartID.setFont(new java.awt.Font("Serif", 0, 17)); // NOI18N
        txtPartID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartIDActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(220, 221, 225));
        jLabel9.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        jLabel9.setForeground(java.awt.SystemColor.control);
        jLabel9.setText("   Price");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPrice.setFont(new java.awt.Font("Serif", 0, 17)); // NOI18N
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPriceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtType))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPartType)
                            .addComponent(txtQuantity)
                            .addComponent(txtPartID, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPartName)
                            .addComponent(txtPrice))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPartID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPartName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPartType, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Part ID", "Type", "Part Type", "Part Name", "Avaliable_Quantity", "Price", "Inserted_Date"
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel4.setBackground(new java.awt.Color(140, 122, 230));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Part Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), java.awt.SystemColor.textHighlightText)); // NOI18N

        jLabel6.setBackground(new java.awt.Color(220, 221, 225));
        jLabel6.setFont(new java.awt.Font("Serif", 1, 17)); // NOI18N
        jLabel6.setForeground(java.awt.SystemColor.control);
        jLabel6.setText("   Please Select Parts Type");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        btnPartsInsert.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnPartsInsert.setText("New Part Insert");
        btnPartsInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPartsInsertActionPerformed(evt);
            }
        });

        btnRefreshData.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        btnRefreshData.setText("Refresh Data");
        btnRefreshData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshDataActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Courier New", 1, 17)); // NOI18N
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Monospaced", 3, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 153, 51));
        jLabel8.setText("  Bestie Shopkeeper ");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(LbFlag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPartsInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnRefreshData, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPartsInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefreshData, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LbFlag)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
		Statement stmt = con.createStatement();
                
                //for Type
                ResultSet typeRs = stmt.executeQuery("select Type from Parts group by Type");
		int rowCount=0;
		while (typeRs.next()) {
			rowCount++;
		}
		String[] types=new String[rowCount];
		typeRs = stmt.executeQuery("select Type from Parts group by Type");
		int cou=0;
		while (typeRs.next()) {
			types[cou]=typeRs.getString(1);
				cou++;
		}
                type=types[0];
                System.out.println(type);
                
                //show_Parts_Table();
                
                ResultSet dataRs = stmt.executeQuery("select * from Parts");
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
    }//GEN-LAST:event_formWindowOpened

    private void txtPartNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartNameActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtPartID.setText("");
        txtPartName.setText("");
        txtType.setText("");
        txtPartType.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
        } catch (Exception ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pres=null;
        int index=100;
        String type=txtType.getText();
        String partName=txtPartName.getText();
        String partType=txtPartType.getText();
        String price=txtQuantity.getText();
        if(type.equals("") || partName.equals("") || partType.equals("") || price.equals("")){
            JOptionPane.showMessageDialog(this,
        "Any Fields has Empty!",
        "Field Blank problem",
        JOptionPane.WARNING_MESSAGE);
        }else
        if(checkInputs()&& txtPartID!=null){
            
            try {
                Statement stmt = con.createStatement();
                
                index = JOptionPane.showConfirmDialog(rootPane, "Are you sure to DELETE !");
                if(index==0){
                    String query="delete from Parts where Part_ID=?";
                pres=con.prepareStatement(query);
                pres.setInt(1, Integer.parseInt(txtPartID.getText()));
                pres.execute();
                JOptionPane.showMessageDialog(this,
                                              "Updated Successfully!",
                                              "Successful Tasks",
                                              JOptionPane.INFORMATION_MESSAGE);
                ResultSet dataRs = stmt.executeQuery("select * from Parts");
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
                txtPartID.setText("");
                txtPartName.setText("");
                txtType.setText("");
                txtPartType.setText("");
                txtQuantity.setText("");
                txtPrice.setText("");
                con.close();
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index=jTable1.getSelectedRow();
        System.out.println(index);
        if(LbFlag.getText().equals("1")){
            setData(index,jComboBox1.getSelectedItem().toString());
        }else if(LbFlag.getText().equals(""))
            setData(index);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
        } catch (Exception ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pres=null;
        String type=txtType.getText();
        String partName=txtPartName.getText();
        String partType=txtPartType.getText();
        String price=txtQuantity.getText();
        if(type.equals("") || partName.equals("") || partType.equals("") || price.equals("")){
            JOptionPane.showMessageDialog(this,
        "Any Fields has Empty!",
        "Field Blank problem",
        JOptionPane.WARNING_MESSAGE);
             
        }else
        if(checkInputs()&& txtPartID!=null){
            
            try {
                Statement stmt = con.createStatement();
//                ResultSet typeRs = stmt.executeQuery("select Type from Parts group by Type");
//                int rowCount=0;
//                while (typeRs.next()) {
//                    rowCount++;
//                }
//                String[] types=new String[rowCount];
//                typeRs = stmt.executeQuery("select Type from Parts group by Type");
//                int cou=0;
//                while (typeRs.next()) {
//                    types[cou]=typeRs.getString(1);
//                    cou++;
//                }
//                String typ=txtType.getText();
//                int count=0,temp=types.length-1;
//                boolean flag=false;
//                for(String t : types){
//                    if(!t.equals(typ) && temp==count) {
//                            flag=true;
//                            System.out.println(flag);
//                    }
//                    System.out.println(t);
//                    System.out.println(flag);
//                    System.out.println(count+" "+temp);
//                    count++;
//                }
//                System.out.println(flag);
//                if(flag==true){
//                    JOptionPane.showMessageDialog(rootPane, "You update new type!");
//                }
                
                String query="update Parts set Type=?,Part_Name=?,Part_Type=?,Avaliable_Quantity=?,Price=? where Part_ID=?";
                pres=con.prepareStatement(query);
                pres.setString(1, txtType.getText());
                pres.setString(2, txtPartName.getText());
                pres.setString(3, txtPartType.getText());
                pres.setDouble(4, Double.parseDouble(txtQuantity.getText()));
                pres.setDouble(5, Double.parseDouble(txtPrice.getText()));
                pres.setInt(6, Integer.parseInt(txtPartID.getText()));
                pres.execute();
                JOptionPane.showMessageDialog(rootPane, "Updated Successfully!");
                ResultSet dataRs = stmt.executeQuery("select * from Parts");
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
            } catch (Exception ex) {
                Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtPartTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartTypeActionPerformed

    private void txtTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTypeActionPerformed

    private void txtPartIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartIDActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
        LbFlag.setVisible(false);
        LbFlag.setText("1");
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRefreshDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshDataActionPerformed
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url,login,pass);
            Statement stmt = con.createStatement();
            ResultSet dataRs = stmt.executeQuery("select * from Parts");
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
            txtPartID.setText("");
            txtPartName.setText("");
            txtType.setText("");
            txtPartType.setText("");
            txtQuantity.setText("");
            LbFlag.setText("");
            txtPrice.setText("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PartsControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshDataActionPerformed

    private void btnPartsInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartsInsertActionPerformed
        // TODO add your handling code here:
        new PartInsertFrame().setVisible(true);
    }//GEN-LAST:event_btnPartsInsertActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyTyped
        // TODO add your handling code here:
        char vchar=evt.getKeyChar();
        if(!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtQuantityKeyTyped

    private void txtPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyTyped
        // TODO add your handling code here:
        char vchar=evt.getKeyChar();
        if(!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtPriceKeyTyped

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
                new PartsControlFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbFlag;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPartsInsert;
    private javax.swing.JButton btnRefreshData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtPartID;
    private javax.swing.JTextField txtPartName;
    private javax.swing.JTextField txtPartType;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
