
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author limon
 */
public class dispositvos extends javax.swing.JFrame {

    /**
     * Creates new form dispositvos
     */
    public dispositvos() {
        initComponents();
        
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = Gd.getDisplayMode().getWidth();
        int height = Gd.getDisplayMode().getHeight();
        this.setSize(width,height);
        Color azul = new Color(62, 95, 138); // Color azul
        this.getContentPane().setBackground(azul); //Cambiar color de fondo
        setExtendedState(MAXIMIZED_BOTH);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        lbl9.setText(String.valueOf(dtf.format(LocalDateTime.now())));

        
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(100);
        
    }
    
    public static void getID(){
        String datos[] =  new String[5];
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "SELECT id_dispo from dispositivo WHERE id_dispo = (select max(id_dispo) from dispositivo)";
            ResultSet res = st.executeQuery(sql);
            while(res.next()){
                datos[1] = res.getString("id_dispo");
            }
            int id = Integer.parseInt(datos[1]);
            int ultimo = id+1;
            String ult = String.valueOf(ultimo);
            txtid.setText(ult);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void agregar(){
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "insert into dispositivo(sn,caso,tipo_dis,modelo,estado_fisi,marca,esta_recep,color,inventario, id_cliente,fecha)"
                    + "values ('"+ txtsn.getText() +"','"+ txtcaso.getText() +"','"+ txttipo.getText() +"','"+ txtmodelo.getText() +"'"
                    + ",'"+ txtef.getText() +"','"+ txtmarca.getText() +"','"+ txtrecibido.getText() +"','"+ txtcolor.getText() +"',"+txtinv.getText()+""
                    + ","+txtidcliente.getText()+",'"+lbl9.getText()+"')";
            ResultSet result = st.executeQuery(sql);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void insertarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel(); 
        //modelo.setAutoResizeMode(jTableE.AUTO_RESIZE_OFF);
        String datos[] =  new String[12];
        ArrayList<String> ListID = new ArrayList();
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            //JOptionPane.showMessageDialog(null, "BD conectada con exito");
            java.sql.Statement st = conn.createStatement();
            
            String sql = "select * from dispositivo where id_cliente = "+txtidcliente.getText()+"";
            ResultSet res = st.executeQuery(sql);
            
            while(res.next()){
                datos[0] = res.getString("id_dispo");
                datos[1] = res.getString("id_cliente");
                datos[2] = res.getString("fecha");
                datos[3] = res.getString("sn");
                datos[4] = res.getString("caso");
                datos[5] = res.getString("tipo_dis");
                datos[6] = res.getString("modelo");
                datos[7] = res.getString("estado_fisi");
                datos[8] = res.getString("marca");
                datos[9] = res.getString("esta_recep");
                datos[10] = res.getString("color");
                datos[11] = res.getString("inventario");
                modelo.addRow(datos);
            }
            
            st.executeUpdate(sql);
            conn.close();
            st.close();
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error " + e);
        } 
    }
    
    public void actualizar(){
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "update dispositivo set sn = '"+ txtsn.getText() +"',tipo_dis='"+txttipo.getText()+"', modelo = '"+txtmodelo.getText()+"'"
                    + ",estado_fisi = '"+txtef.getText()+"',esta_recep = '"+txtrecibido.getText()+"',color = '"+txtcolor.getText()+"',marca = '"+txtmarca.getText()+"'"
                    + ",caso = '"+txtcaso.getText()+"',inventario = "+txtinv.getText()+" where id_dispo = "+txtid.getText();
            ResultSet result = st.executeQuery(sql);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        int fila = tabla.getSelectedRow();
        String valor = tabla.getValueAt(fila, 0).toString();
        
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            //JOptionPane.showMessageDialog(null, "BD conectada con exito");
            java.sql.Statement st = conn.createStatement();
            
            String sql = "delete from dispositivo where id_dispo = "+valor+"";
            
            ResultSet res = st.executeQuery(sql);
            
            
            st.executeUpdate(sql);
            conn.close();
            st.close();
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }
    
    public void modificar(){
        int fila = tabla.getSelectedRow();
        String id_dispo = tabla.getValueAt(fila, 0).toString();
        String id_cliente = tabla.getValueAt(fila, 1).toString();
        String fecha = tabla.getValueAt(fila, 2).toString();
        String sn = tabla.getValueAt(fila, 3).toString();
        String caso = tabla.getValueAt(fila, 4).toString();
        String tipo = tabla.getValueAt(fila, 5).toString();
        String modelo = tabla.getValueAt(fila, 6).toString();
        String estado_fisico = tabla.getValueAt(fila, 7).toString();
        String marca = tabla.getValueAt(fila, 8).toString();
        String estado_recibido = tabla.getValueAt(fila, 9).toString();
        String color = tabla.getValueAt(fila, 10).toString();
        String inventario = tabla.getValueAt(fila, 11).toString();
        
        txtid.setText(id_dispo);
        txtidcliente.setText(id_cliente);
        txtsn.setText(sn);
        txtcaso.setText(caso);
        txttipo.setText(tipo);
        txtmodelo.setText(modelo);
        txtef.setText(estado_fisico);
        txtmarca.setText(marca);
        txtrecibido.setText(estado_recibido);
        txtcolor.setText(color);
        txtinv.setText(inventario);
    }
    
    public void actualizarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) dispositvos.tabla.getModel();
        int fila = modelo.getRowCount();
        
        for(int i = 1;i<=fila;i++){
            modelo.removeRow(1);
    }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtidcliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txttipo = new javax.swing.JTextField();
        txtmodelo = new javax.swing.JTextField();
        txtcolor = new javax.swing.JTextField();
        txtinv = new javax.swing.JTextField();
        txtsn = new javax.swing.JTextField();
        txtef = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnagregar = new javax.swing.JButton();
        lbl9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtmarca = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtcaso = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtrecibido = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnmod = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(87, 131, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(962, 640));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DISPOSITIVOS");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 30, 200, 32);

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID-Cliente");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(570, 510, 280, 28);

        txtidcliente.setBackground(new java.awt.Color(240, 240, 240));
        txtidcliente.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtidcliente.setEnabled(false);
        jPanel1.add(txtidcliente);
        txtidcliente.setBounds(570, 560, 110, 47);

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 90, 21, 28);

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("S/N");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(220, 90, 130, 28);

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 210, 130, 28);

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Modelo");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(260, 220, 130, 28);

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Estado Físico");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(40, 330, 130, 40);

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Caso");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(460, 90, 130, 28);

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Inventario");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(280, 460, 130, 28);

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Estado recibido");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(470, 330, 280, 28);

        txtid.setBackground(new java.awt.Color(240, 240, 240));
        txtid.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid.setEnabled(false);
        jPanel1.add(txtid);
        txtid.setBounds(40, 130, 120, 47);

        txttipo.setBackground(new java.awt.Color(240, 240, 240));
        txttipo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jPanel1.add(txttipo);
        txttipo.setBounds(40, 250, 160, 47);

        txtmodelo.setBackground(new java.awt.Color(240, 240, 240));
        txtmodelo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jPanel1.add(txtmodelo);
        txtmodelo.setBounds(260, 250, 170, 47);

        txtcolor.setBackground(new java.awt.Color(240, 240, 240));
        txtcolor.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jPanel1.add(txtcolor);
        txtcolor.setBounds(40, 490, 200, 47);

        txtinv.setBackground(new java.awt.Color(240, 240, 240));
        txtinv.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtinv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinvKeyTyped(evt);
            }
        });
        jPanel1.add(txtinv);
        txtinv.setBounds(280, 490, 140, 47);

        txtsn.setBackground(new java.awt.Color(240, 240, 240));
        txtsn.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jPanel1.add(txtsn);
        txtsn.setBounds(220, 130, 210, 47);

        txtef.setBackground(new java.awt.Color(240, 240, 240));
        txtef.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jPanel1.add(txtef);
        txtef.setBounds(40, 370, 180, 47);

        jLabel12.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Color");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(40, 460, 80, 28);

        btnagregar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnagregar.setForeground(new java.awt.Color(25, 55, 87));
        btnagregar.setText("Agregar Dispositivo");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnagregar);
        btnagregar.setBounds(40, 560, 250, 50);

        lbl9.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lbl9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl9);
        lbl9.setBounds(550, 20, 170, 30);

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Marca");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(250, 340, 280, 28);

        txtmarca.setBackground(new java.awt.Color(240, 240, 240));
        txtmarca.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jPanel1.add(txtmarca);
        txtmarca.setBounds(250, 370, 180, 47);

        txtcaso.setColumns(20);
        txtcaso.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtcaso.setRows(5);
        jScrollPane2.setViewportView(txtcaso);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(470, 130, 210, 170);

        txtrecibido.setBackground(new java.awt.Color(240, 240, 240));
        txtrecibido.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(txtrecibido);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(470, 370, 200, 50);

        jButton6.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(25, 55, 87));
        jButton6.setText("Cancelar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(380, 560, 145, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(43, 42, 730, 640);

        btneliminar.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(25, 55, 87));
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminar);
        btneliminar.setBounds(1200, 540, 145, 50);

        jButton3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(25, 55, 87));
        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(1240, 650, 107, 33);

        jButton4.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(25, 55, 87));
        jButton4.setText("Generar orden servicio");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(840, 610, 330, 50);

        btneditar.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        btneditar.setForeground(new java.awt.Color(25, 55, 87));
        btneditar.setText("Actualizar");
        btneditar.setEnabled(false);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });
        getContentPane().add(btneditar);
        btneditar.setBounds(1020, 540, 145, 50);

        tabla.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_dispositivo", "Id_cliente", "Fecha", "S/N", "Caso", "Tipo", "Modelo", "Estado físico", "Marca", "Estado recibido", "Color", "Inventario"
            }
        ));
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(840, 50, 500, 290);

        btnmod.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        btnmod.setForeground(new java.awt.Color(25, 55, 87));
        btnmod.setText("Modificar");
        btnmod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodActionPerformed(evt);
            }
        });
        getContentPane().add(btnmod);
        btnmod.setBounds(840, 540, 145, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        eliminar();
        JOptionPane.showMessageDialog(null, "Eliminado con exito");
        txtsn.setText(null);
        txtcaso.setText(null);
        txttipo.setText(null);
        txtcolor.setText(null);
        txtinv.setText(null);
        txtmarca.setText(null);
        txtrecibido.setText(null);
        txtef.setText(null);
        txtmodelo.setText(null);
        DefaultTableModel modelo = (DefaultTableModel) dispositvos.tabla.getModel();
        int a = modelo.getRowCount()-1;
        for (int i = a; i >= 0; i--) {          
            modelo.removeRow(modelo.getRowCount()-1);
        }
        insertarTabla();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clientes ven = new clientes();
        ven.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(txtid.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Primero busque un cliente");
        }
        else{
            int fila = tabla.getSelectedRow();
            if(fila>=0){
                String valor = tabla.getValueAt(fila, 0).toString();
                String valor2 = tabla.getValueAt(fila, 1).toString();
                orden1 ven = new orden1();
                ven.setVisible(true);
                dispose();
                orden1.txtid_cliente.setText(valor2);
                orden1.txtid_dispo.setText(valor);
                orden1.getID();
            }
            else{
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if(txtsn.getText().isEmpty() || txtcaso.getText().isEmpty() || txttipo.getText().isEmpty() || 
           txtmodelo.getText().isEmpty() || txtef.getText().isEmpty() ||  txtmarca.getText().isEmpty() || txtrecibido.getText().isEmpty() || 
           txtcolor.getText().isEmpty() ||  txtinv.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }else{
            actualizar();
            txtsn.setText(null);
            txtcaso.setText(null);
            txttipo.setText(null);
            txtcolor.setText(null);
            txtinv.setText(null);
            txtmarca.setText(null);
            txtrecibido.setText(null);
            txtef.setText(null);
            txtmodelo.setText(null);
            btneditar.setEnabled(false);
            btnmod.setEnabled(true);
            btnagregar.setEnabled(true);
            jButton4.setEnabled(true);
            btneliminar.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
            DefaultTableModel modelo = (DefaultTableModel) dispositvos.tabla.getModel();
            int a = modelo.getRowCount()-1;
            for (int i = a; i >= 0; i--) {          
                modelo.removeRow(modelo.getRowCount()-1);
            }
            insertarTabla();
            
        }
    }//GEN-LAST:event_btneditarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtcaso.setText(null);
        txtsn.setText(null);
        txttipo.setText(null);
        txtmodelo.setText(null);
        txtef.setText(null);
        txtmarca.setText(null);
        txtrecibido.setText(null);
        txtcolor.setText(null);
        txtinv.setText(null);
        btnagregar.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        if(txtsn.getText().isEmpty() || txtcaso.getText().isEmpty() || txttipo.getText().isEmpty() || 
           txtmodelo.getText().isEmpty() || txtef.getText().isEmpty() ||  txtmarca.getText().isEmpty() || txtrecibido.getText().isEmpty() || 
           txtcolor.getText().isEmpty() || txtinv.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }else{
            agregar();
            JOptionPane.showMessageDialog(null, "Guardado con exito");
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            //deshabilitarTF();
            //borrar();
            txtsn.setText(null);
            txtcaso.setText(null);
            txttipo.setText(null);
            txtcolor.setText(null);
            txtinv.setText(null);
            txtmarca.setText(null);
            txtrecibido.setText(null);
            txtef.setText(null);
            txtmodelo.setText(null);
            
            int aux = Integer.parseInt(txtid.getText());
            int res = aux+1;
            txtid.setText(String.valueOf(res));
            for( int i = modelo.getRowCount() - 1; i >= 0; i-- ) {
                modelo.removeRow(i);
            }
            insertarTabla();

        }
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnmodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodActionPerformed
        modificar();
        btnagregar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(false);
        jButton4.setEnabled(false);
    }//GEN-LAST:event_btnmodActionPerformed

    private void txtinvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinvKeyTyped
        char validar=evt.getKeyChar();
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresa solo numeros");
        }
    }//GEN-LAST:event_txtinvKeyTyped

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
            java.util.logging.Logger.getLogger(dispositvos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dispositvos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dispositvos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dispositvos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dispositvos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmod;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl9;
    public static javax.swing.JTable tabla;
    private javax.swing.JTextArea txtcaso;
    private javax.swing.JTextField txtcolor;
    private javax.swing.JTextField txtef;
    private static javax.swing.JTextField txtid;
    public static javax.swing.JTextField txtidcliente;
    private javax.swing.JTextField txtinv;
    private javax.swing.JTextField txtmarca;
    private javax.swing.JTextField txtmodelo;
    private javax.swing.JTextField txtrecibido;
    private javax.swing.JTextField txtsn;
    private javax.swing.JTextField txttipo;
    // End of variables declaration//GEN-END:variables
}
