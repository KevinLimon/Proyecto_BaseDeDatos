
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
public class clientes extends javax.swing.JFrame {

    /**
     * Creates new form clientes
     */
    public clientes() {
        initComponents();
        
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = Gd.getDisplayMode().getWidth();
        int height = Gd.getDisplayMode().getHeight();
        this.setSize(width,height);
        Color azul = new Color(62, 95, 138); // Color azul
        this.getContentPane().setBackground(azul); //Cambiar color de fondo
        setExtendedState(MAXIMIZED_BOTH);
        
        ImageIcon imagen1 = new ImageIcon("lupa.png");
        btnbuscar.setBounds(695,46,40,40);
        btnbuscar.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(btnbuscar.getWidth(),btnbuscar.getHeight(), Image.SCALE_SMOOTH)));
        btnbuscar.setBackground(azul);
        btnbuscar.setOpaque(false);
        btnbuscar.setContentAreaFilled(false);
        /**jScrollPane2.setSize(ancho, 1440);*/
        //JTextField txtbuscar = new JTextField("",20);
        txtbuscar.setBounds(600,40,140,50);
    }
    
    public void guardarCliente(){
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "insert into cliente (nombre, direccion, colonia, ciudad, cp, telefono, telefono2, correo)"
                    + "values ('"+ txtnombre.getText() +"','"+ txtdireccion.getText() +"','"+ txtcolonia.getText() +"','"+ txtciudad.getText() +"'"
                    + ",'"+ txtcp.getText() +"','"+ txttelefono.getText() +"','"+ txttelefono2.getText() +"','"+ txtcorreo.getText() +"')";
            ResultSet result = st.executeQuery(sql);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void getID(){
        String datos[] =  new String[5];
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "SELECT id_cliente from cliente WHERE id_cliente = (select max(id_cliente) from cliente)";
            ResultSet res = st.executeQuery(sql);
            while(res.next()){
                datos[1] = res.getString("id_cliente");
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
    
    public void buscar(){
        String datos[] =  new String[9];
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            //JOptionPane.showMessageDialog(null, "BD conectada con exito");
            java.sql.Statement st = conn.createStatement();
            
            String sql = "select id_cliente,nombre,direccion,colonia,ciudad,cp,correo,telefono,telefono2 FROM cliente WHERE id_cliente ='"+ txtbuscar.getText()+"'";
            ResultSet res = st.executeQuery(sql);
            while(res.next()){
                datos[0]= res.getString("id_cliente");
                datos[1]= res.getString("nombre");
                datos[2]= res.getString("direccion");
                datos[3]= res.getString("colonia");
                datos[4]= res.getString("ciudad");
                datos[5]= res.getString("cp");
                datos[6]= res.getString("correo");
                datos[7]= res.getString("telefono");
                datos[8]= res.getString("telefono2");
            }
            txtid.setText(datos[0]);
            txtnombre.setText(datos[1]);
            txtdireccion.setText(datos[2]);
            txtcolonia.setText(datos[3]);
            txtciudad.setText(datos[4]);
            txtcp.setText(datos[5]);
            txtcorreo.setText(datos[6]);
            txttelefono.setText(datos[7]);
            txttelefono2.setText(datos[8]);

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
            
            String sql = "update cliente set nombre = '"+ txtnombre.getText() +"',direccion='"+txtdireccion.getText()+"', colonia = '"+txtcolonia.getText()+"'"
                    + ",ciudad = '"+txtciudad.getText()+"',cp = '"+txtcp.getText()+"',telefono = '"+txttelefono.getText()+"',correo = '"+txtcorreo.getText()+"'"
                    + ",telefono2 = "+txttelefono2.getText()+" where id_cliente = "+txtid.getText();
            ResultSet result = st.executeQuery(sql);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void insertarTabla2(){
        DefaultTableModel modelo = (DefaultTableModel) dispositvos.tabla.getModel(); 
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
            
            String sql = "select * from dispositivo where id_cliente = "+txtid.getText()+"";
            ResultSet res = st.executeQuery(sql);
            int filas=dispositvos.tabla.getRowCount();
            System.out.println(filas);
            while(res.next()){
                int i = 1;
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
            for(int i = 1;i<10;i++)
                modelo.removeRow(1);
            
            st.executeUpdate(sql);
            conn.close();
            st.close();
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error " + e);
        } 
    }
    
    public void habilitarTF(){
        txtnombre.setEnabled(true);
        txtcolonia.setEnabled(true);
        txtcp.setEnabled(true);
        txtdireccion.setEnabled(true);
        txtciudad.setEnabled(true);
        txtcorreo.setEnabled(true);
        txttelefono.setEnabled(true);
        txttelefono2.setEnabled(true);
    }
    
    public void deshabilitarTF(){
        txtnombre.setEnabled(false);
        txtcolonia.setEnabled(false);
        txtcp.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtciudad.setEnabled(false);
        txtcorreo.setEnabled(false);
        txttelefono.setEnabled(false);
        txttelefono2.setEnabled(false);
    }
    
    public void borrar(){
        txtnombre.setText(null);
        txtcolonia.setText(null);
        txtcp.setText(null);
        txtdireccion.setText(null);
        txtciudad.setText(null);
        txtcorreo.setText(null);
        txtbuscar.setText(null);
        txttelefono.setText(null);
        txttelefono2.setText(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtcolonia = new javax.swing.JTextField();
        txtciudad = new javax.swing.JTextField();
        txtcp = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        txttelefono2 = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        btnnv = new javax.swing.JButton();
        btnb = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(87, 131, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(962, 640));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CLIENTES");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 30, 114, 32);

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 90, 21, 28);

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(220, 90, 130, 28);

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dirección");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 210, 130, 28);

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Colonia");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(500, 210, 130, 28);

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ciudad");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(40, 340, 130, 40);

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("C.P");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(430, 350, 130, 28);

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Teléfono");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(660, 350, 130, 28);

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Segundo Teléfono");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(50, 490, 280, 28);

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Correo");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(370, 490, 280, 28);

        txtid.setBackground(new java.awt.Color(240, 240, 240));
        txtid.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid.setEnabled(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        jPanel1.add(txtid);
        txtid.setBounds(40, 130, 120, 47);

        txtdireccion.setBackground(new java.awt.Color(240, 240, 240));
        txtdireccion.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtdireccion.setEnabled(false);
        jPanel1.add(txtdireccion);
        txtdireccion.setBounds(40, 250, 390, 47);

        txtcolonia.setBackground(new java.awt.Color(240, 240, 240));
        txtcolonia.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtcolonia.setEnabled(false);
        jPanel1.add(txtcolonia);
        txtcolonia.setBounds(500, 250, 320, 47);

        txtciudad.setBackground(new java.awt.Color(240, 240, 240));
        txtciudad.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtciudad.setEnabled(false);
        jPanel1.add(txtciudad);
        txtciudad.setBounds(40, 390, 320, 47);

        txtcp.setBackground(new java.awt.Color(240, 240, 240));
        txtcp.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtcp.setEnabled(false);
        jPanel1.add(txtcp);
        txtcp.setBounds(430, 390, 160, 47);

        txttelefono.setBackground(new java.awt.Color(240, 240, 240));
        txttelefono.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txttelefono.setEnabled(false);
        jPanel1.add(txttelefono);
        txttelefono.setBounds(660, 390, 250, 47);

        txttelefono2.setBackground(new java.awt.Color(240, 240, 240));
        txttelefono2.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txttelefono2.setEnabled(false);
        jPanel1.add(txttelefono2);
        txttelefono2.setBounds(50, 530, 250, 47);

        txtcorreo.setBackground(new java.awt.Color(240, 240, 240));
        txtcorreo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtcorreo.setEnabled(false);
        jPanel1.add(txtcorreo);
        txtcorreo.setBounds(370, 530, 340, 47);

        txtnombre.setBackground(new java.awt.Color(240, 240, 240));
        txtnombre.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtnombre.setEnabled(false);
        jPanel1.add(txtnombre);
        txtnombre.setBounds(220, 130, 530, 47);

        btnbuscar.setEnabled(false);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnbuscar);
        btnbuscar.setBounds(770, 20, 70, 50);

        txtbuscar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtbuscar.setEnabled(false);
        jPanel1.add(txtbuscar);
        txtbuscar.setBounds(659, 20, 180, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(43, 42, 962, 640);

        jButton1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(25, 55, 87));
        jButton1.setText("Cerrar Sesión");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1200, 610, 145, 33);

        btnguardar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(25, 55, 87));
        btnguardar.setText("Guardar");
        btnguardar.setEnabled(false);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnguardar);
        btnguardar.setBounds(1210, 120, 121, 60);

        btnactualizar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnactualizar.setForeground(new java.awt.Color(25, 55, 87));
        btnactualizar.setText("Actualizar");
        btnactualizar.setEnabled(false);
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnactualizar);
        btnactualizar.setBounds(1210, 220, 121, 60);

        btnnuevo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnnuevo.setForeground(new java.awt.Color(25, 55, 87));
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnnuevo);
        btnnuevo.setBounds(1050, 120, 121, 60);

        btnnv.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnnv.setForeground(new java.awt.Color(25, 55, 87));
        btnnv.setText("Nuevo Dispositivo");
        btnnv.setEnabled(false);
        btnnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvActionPerformed(evt);
            }
        });
        getContentPane().add(btnnv);
        btnnv.setBounds(1070, 400, 260, 60);

        btnb.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnb.setForeground(new java.awt.Color(25, 55, 87));
        btnb.setText("Buscar");
        btnb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbActionPerformed(evt);
            }
        });
        getContentPane().add(btnb);
        btnb.setBounds(1050, 220, 121, 60);

        btncancelar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btncancelar.setForeground(new java.awt.Color(25, 55, 87));
        btncancelar.setText("Cancelar");
        btncancelar.setEnabled(false);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btncancelar);
        btncancelar.setBounds(1140, 310, 121, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login ven = new login();
        ven.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(txtnombre.getText().isEmpty() || txtdireccion.getText().isEmpty() || txtcolonia.getText().isEmpty() || 
           txtciudad.getText().isEmpty() || txtcorreo.getText().isEmpty() ||  txtcp.getText().isEmpty() 
           || txttelefono2.getText().isEmpty() || txttelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }else{
            guardarCliente();
            JOptionPane.showMessageDialog(null, "Guardado con exito");
            deshabilitarTF();
            borrar();
            txtid.setText(null);
            btnnuevo.setEnabled(true);
            btnb.setEnabled(true);
            btncancelar.setEnabled(false);
            btnguardar.setEnabled(false);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        if(txtnombre.getText().isEmpty() || txtdireccion.getText().isEmpty() || txtcolonia.getText().isEmpty() || 
           txtciudad.getText().isEmpty() || txtcorreo.getText().isEmpty() ||  txtcp.getText().isEmpty() || txttelefono.getText().isEmpty() || 
           txttelefono2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }else{
            actualizar();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
            borrar();
            deshabilitarTF();
            txtid.setText(null);
            btnbuscar.setEnabled(false);
            txtbuscar.setEnabled(false);
            btnactualizar.setEnabled(false);
            btnnuevo.setEnabled(true);
            btnguardar.setEnabled(false);
            btncancelar.setEnabled(false);
            btnb.setEnabled(true);
            btnnv.setEnabled(false);
        }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        habilitarTF();
        getID();
        btnguardar.setEnabled(true);
        btncancelar.setEnabled(true);
        btnb.setEnabled(false);
        btnnuevo.setEnabled(false);
        btnbuscar.setEnabled(false);
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvActionPerformed
        if(txtid.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Primero busque un cliente");
        }
        else{
            dispositvos ven = new dispositvos();
            ven.setVisible(true);
            dispose();
            dispositvos.txtidcliente.setText(txtid.getText());
            dispositvos.getID();
            insertarTabla2();
        }
    }//GEN-LAST:event_btnnvActionPerformed

    private void btnbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbActionPerformed
        txtbuscar.setEnabled(true);
        btnbuscar.setEnabled(true);
        btnb.setEnabled(false);
        btnnuevo.setEnabled(false);
        btncancelar.setEnabled(true);
    }//GEN-LAST:event_btnbActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        borrar();
        btnbuscar.setEnabled(false);
        txtbuscar.setEnabled(false);
        btnactualizar.setEnabled(false);
        btnnuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btncancelar.setEnabled(false);
        btnb.setEnabled(true);
        btnnv.setEnabled(false);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        if(txtbuscar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo de busqueda no puede quedar vacio");
        }
        else{
            buscar();
            if(txtid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este id no existe, ingrese otro");
                btncancelar.setEnabled(true);
            }
            else{
                habilitarTF();
                btncancelar.setEnabled(true);
                btnactualizar.setEnabled(true);
                btnnuevo.setEnabled(false);
                btnbuscar.setEnabled(true);
                btnb.setEnabled(false);
                btnnv.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

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
            java.util.logging.Logger.getLogger(clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnb;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnnv;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtciudad;
    private javax.swing.JTextField txtcolonia;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtcp;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttelefono2;
    // End of variables declaration//GEN-END:variables
}
