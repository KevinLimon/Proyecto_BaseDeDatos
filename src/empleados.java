
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author limon
 */
public class empleados extends javax.swing.JFrame {

    public empleados() {
        initComponents();
        
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = Gd.getDisplayMode().getWidth();
        int height = Gd.getDisplayMode().getHeight();
        this.setSize(width,height);
        Color azul = new Color(62, 95, 138); // Color azul
        this.getContentPane().setBackground(azul); //Cambiar color de fondo
        setExtendedState(MAXIMIZED_BOTH); //this.setExtendedState(MAXIMIZED_BOTH); //Tamaño
        
        ImageIcon imagen1 = new ImageIcon("lupa.png");
        btnbuscar.setBounds(695,126,40,40);
        btnbuscar.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(btnbuscar.getWidth(),btnbuscar.getHeight(), Image.SCALE_SMOOTH)));
        btnbuscar.setBackground(azul);
        btnbuscar.setOpaque(false);
        btnbuscar.setBounds(695,126,40,40);
        btnbuscar.setContentAreaFilled(false);
        /**jScrollPane2.setSize(ancho, 1440);*/
        //JTextField txtbuscar = new JTextField("",20);
        txtbuscar.setBounds(90,120,650,50);
    }
    
    public void guardarEmpleado(){
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        int item = cb.getSelectedIndex();
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "insert into empleado (nombre, direccion, colonia, ciudad, cp, rfc, correo, nss, sueldo, id_departamento)"
                    + "values ('"+ txtnombre.getText() +"','"+ txtdireccion.getText() +"','"+ txtcolonia.getText() +"','"+ txtciudad.getText() +"'"
                    + ",'"+ txtcp.getText() +"','"+ txtrfc.getText() +"','"+ txtcorreo.getText() +"','"+ txtnss.getText() +"',"+ txtsueldo.getText() +""
                    + "," + item +")";
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
            
            String sql = "SELECT id_empleado from empleado WHERE id_empleado = (select max(id_empleado) from empleado)";
            ResultSet res = st.executeQuery(sql);
            while(res.next()){
                datos[1] = res.getString("id_empleado");
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
        String datos[] =  new String[12];
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            //JOptionPane.showMessageDialog(null, "BD conectada con exito");
            java.sql.Statement st = conn.createStatement();
            
            String sql = "select id_empleado,nombre,direccion,colonia,ciudad,cp,rfc,correo,sueldo,nss,id_departamento FROM empleado WHERE id_empleado ='"+ txtbuscar.getText()+"'";
            ResultSet res = st.executeQuery(sql);
            while(res.next()){
                datos[0]= res.getString("id_empleado");
                datos[1]= res.getString("nombre");
                datos[2]= res.getString("direccion");
                datos[3]= res.getString("colonia");
                datos[4]= res.getString("ciudad");
                datos[5]= res.getString("cp");
                datos[6]= res.getString("rfc");
                datos[7]= res.getString("correo");
                datos[8]= res.getString("sueldo");
                datos[9]= res.getString("nss");
                datos[10]= res.getString("id_departamento");
            }
            txtid.setText(datos[0]);
            txtnombre.setText(datos[1]);
            txtdireccion.setText(datos[2]);
            txtcolonia.setText(datos[3]);
            txtciudad.setText(datos[4]);
            txtcp.setText(datos[5]);
            txtrfc.setText(datos[6]);
            txtcorreo.setText(datos[7]);
            txtsueldo.setText(datos[8]);
            txtnss.setText(datos[9]);
            int dato = Integer.parseInt(datos[10]);
            cb.setSelectedIndex(dato-1);

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
        int item = cb.getSelectedIndex();
        int res = item+1;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "update empleado set nombre = '"+ txtnombre.getText() +"',direccion='"+txtdireccion.getText()+"', colonia = '"+txtcolonia.getText()+"'"
                    + ",ciudad = '"+txtciudad.getText()+"',cp = '"+txtcp.getText()+"',rfc = '"+txtrfc.getText()+"',correo = '"+txtcorreo.getText()+"'"
                    + ",sueldo = "+txtsueldo.getText()+",nss = '"+txtnss.getText()+"',id_departamento = "+res+" where id_empleado = "+txtid.getText();
            ResultSet result = st.executeQuery(sql);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        int item = cb.getSelectedIndex();
        int res = item+1;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "delete from empleado where id_empleado = "+txtid.getText();
            ResultSet result = st.executeQuery(sql);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    //public void combobox(){
     //   if(cb.getSelectedIndex()==0)
    //}
    
    public void habilitarTF(){
        txtnombre.setEnabled(true);
        txtcolonia.setEnabled(true);
        txtcp.setEnabled(true);
        txtdireccion.setEnabled(true);
        txtciudad.setEnabled(true);
        txtrfc.setEnabled(true);
        txtcorreo.setEnabled(true);
        txtnss.setEnabled(true);
        txtsueldo.setEnabled(true);
        cb.setEnabled(true);
    }
    
    public void deshabilitarTF(){
        txtnombre.setEnabled(false);
        txtcolonia.setEnabled(false);
        txtcp.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtciudad.setEnabled(false);
        txtrfc.setEnabled(false);
        txtcorreo.setEnabled(false);
        txtnss.setEnabled(false);
        txtsueldo.setEnabled(false);
        cb.setEnabled(false);
        txtdepa.setEnabled(false);
    }
    
    public void borrar(){
        txtnombre.setText(null);
        txtcolonia.setText(null);
        txtcp.setText(null);
        txtdireccion.setText(null);
        txtciudad.setText(null);
        txtrfc.setText(null);
        txtcorreo.setText(null);
        txtnss.setText(null);
        txtsueldo.setText(null);
        txtdepa.setText(null);
        txtbuscar.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtid10 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcolonia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnss = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtcp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtciudad = new javax.swing.JTextField();
        txtsueldo = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        txtrfc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtdepa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cb = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btneliminar = new javax.swing.JButton();
        btnb = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();

        txtid10.setBackground(new java.awt.Color(240, 240, 240));
        txtid10.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(111, 115, 210));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(87, 131, 188));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EMPLEADOS");

        txtnombre.setBackground(new java.awt.Color(240, 240, 240));
        txtnombre.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtnombre.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID");

        txtcolonia.setBackground(new java.awt.Color(240, 240, 240));
        txtcolonia.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtcolonia.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Código Postal");

        txtid.setBackground(new java.awt.Color(240, 240, 240));
        txtid.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ciudad");

        txtdireccion.setBackground(new java.awt.Color(240, 240, 240));
        txtdireccion.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtdireccion.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Direccion");

        txtnss.setBackground(new java.awt.Color(240, 240, 240));
        txtnss.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtnss.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Colonia");

        txtcorreo.setBackground(new java.awt.Color(240, 240, 240));
        txtcorreo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtcorreo.setEnabled(false);
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("RFC");

        txtcp.setBackground(new java.awt.Color(240, 240, 240));
        txtcp.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtcp.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sueldo");

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Correo");

        txtciudad.setBackground(new java.awt.Color(240, 240, 240));
        txtciudad.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtciudad.setEnabled(false);

        txtsueldo.setBackground(new java.awt.Color(240, 240, 240));
        txtsueldo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtsueldo.setEnabled(false);

        btnbuscar.setEnabled(false);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        txtbuscar.setBackground(new java.awt.Color(240, 240, 240));
        txtbuscar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtbuscar.setEnabled(false);

        txtrfc.setBackground(new java.awt.Color(240, 240, 240));
        txtrfc.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtrfc.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("NSS");

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Departamento");

        txtdepa.setBackground(new java.awt.Color(240, 240, 240));
        txtdepa.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtdepa.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID-Departamento");

        cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        cb.setEnabled(false);
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnss, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel13)
                                .addGap(26, 26, 26)
                                .addComponent(txtdepa, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addComponent(txtrfc, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(494, 494, 494)
                                .addComponent(jLabel8))
                            .addComponent(txtciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1)))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(396, 396, 396)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3))))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtrfc, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnss, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtdepa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(45, 41, 970, 640);

        btneliminar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(25, 55, 87));
        btneliminar.setText("Eliminar");
        btneliminar.setEnabled(false);
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminar);
        btneliminar.setBounds(1220, 340, 121, 60);

        btnb.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnb.setForeground(new java.awt.Color(25, 55, 87));
        btnb.setText("Buscar");
        btnb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbActionPerformed(evt);
            }
        });
        getContentPane().add(btnb);
        btnb.setBounds(1050, 230, 121, 60);

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
        btnactualizar.setBounds(1220, 230, 121, 60);

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
        btncancelar.setBounds(1050, 340, 121, 60);

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
        btnguardar.setBounds(1220, 120, 121, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        eliminar();
        JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
        txtid.setText(null);
        deshabilitarTF();
        borrar();
        btnbuscar.setEnabled(false);
        txtbuscar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnactualizar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnnuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btncancelar.setEnabled(false);
        btnb.setEnabled(true);
    }//GEN-LAST:event_btneliminarActionPerformed

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
                btneliminar.setEnabled(true);
                btnnuevo.setEnabled(false);
                btnbuscar.setEnabled(false);
                btnb.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        habilitarTF();
        getID();
        btnguardar.setEnabled(true);
        btncancelar.setEnabled(true);
        btnb.setEnabled(false);
        btnnuevo.setEnabled(false);
        btnbuscar.setEnabled(false);
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(txtnombre.getText().isEmpty() || txtdireccion.getText().isEmpty() || txtcolonia.getText().isEmpty() || 
           txtciudad.getText().isEmpty() || txtcorreo.getText().isEmpty() ||  txtcp.getText().isEmpty() || txtrfc.getText().isEmpty() || 
           txtnss.getText().isEmpty() || txtsueldo.getText().isEmpty() || txtdepa.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }else{
            guardarEmpleado();
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

    private void btnbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbActionPerformed
        txtbuscar.setEnabled(true);
        btnbuscar.setEnabled(true);
        btnb.setEnabled(false);
        btnnuevo.setEnabled(false);
    }//GEN-LAST:event_btnbActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        if(txtnombre.getText().isEmpty() || txtdireccion.getText().isEmpty() || txtcolonia.getText().isEmpty() || 
           txtciudad.getText().isEmpty() || txtcorreo.getText().isEmpty() ||  txtcp.getText().isEmpty() || txtrfc.getText().isEmpty() || 
           txtnss.getText().isEmpty() || txtsueldo.getText().isEmpty() || txtdepa.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }else{
            actualizar();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
            borrar();
            deshabilitarTF();
            btnbuscar.setEnabled(false);
            txtbuscar.setEnabled(false);
            btneliminar.setEnabled(false);
            btnactualizar.setEnabled(false);
            btneliminar.setEnabled(false);
            btnnuevo.setEnabled(true);
            btnguardar.setEnabled(false);
            btncancelar.setEnabled(false);
            btnb.setEnabled(true);
        }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        borrar();
        btnbuscar.setEnabled(false);
        txtbuscar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnactualizar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnnuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btncancelar.setEnabled(false);
        btnb.setEnabled(true);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login ven = new login();
        ven.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        if(cb.getSelectedIndex()==0){
            txtdepa.setText("Recursos humanos");
        }
        else if (cb.getSelectedIndex()==1)
            txtdepa.setText("Reparaciones");
        else if (cb.getSelectedIndex()==2)
            txtdepa.setText("Analisis");
        else if (cb.getSelectedIndex()==3)
            txtdepa.setText("Recepecion");
        else if (cb.getSelectedIndex()==4)
            txtdepa.setText("Otros");
    }//GEN-LAST:event_cbActionPerformed

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
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnb;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox<String> cb;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTextField txtdepa;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtid10;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnss;
    private javax.swing.JTextField txtrfc;
    private javax.swing.JTextField txtsueldo;
    // End of variables declaration//GEN-END:variables
}
