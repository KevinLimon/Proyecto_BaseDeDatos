

import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author limon
 */
public class orden1 extends javax.swing.JFrame {

    /**
     * Creates new form orden1
     */
    public orden1() {
        initComponents();
        
        Color azul = new Color(62, 95, 138);
        this.setExtendedState(MAXIMIZED_BOTH);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int ancho = (int)d.getWidth();
        int alto = (int)d.getHeight();
        jScrollPane1.setSize(ancho, alto);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        lbl10.setText(String.valueOf(dtf.format(LocalDateTime.now())));
        
        //descuento1();
       
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
            
            String sql = "SELECT id_orden from orden WHERE id_orden = (select max(id_orden) from orden)";
            ResultSet res = st.executeQuery(sql);
            while(res.next()){
                datos[1] = res.getString("id_orden");
            }
            int id = Integer.parseInt(datos[1]);
            int ultimo = id+1;
            String ult = String.valueOf(ultimo);
            txtid_orden.setText(ult);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void insertarCosto(){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        String info[] =new String[6];
        info[0] = txtdescripcion.getText();
        if(btniva.isSelected()){
            double aux = Double.parseDouble(txtprecio.getText());
            double res = aux*0.16;
            double fin = aux+res;
            String ultimo = Double.toString(fin);
            info[1] = ultimo;
            info[2] = "Activo";
        }
        else{
            info[1] = txtprecio.getText();
            info[2] = "No Activo";
        }
        modelo.addRow(info);
        txtdescripcion.setText(null);
        txtprecio.setText(null);
    }
    
    public void sumarColumna(){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        DecimalFormat formato2 = new DecimalFormat("#.##");
        double fila = 0;
        double total = 0;
        
        for(int i = 0;i<modelo.getRowCount();i++){
            fila = Double.parseDouble(modelo.getValueAt(i, 1).toString());
            total += fila;
        }
             
        String str = Double.toString(total);
        txttotal.setText(String.valueOf(String.format("%.2f", total)));
    }
    
    public void sumarColumna2(){
        DefaultTableModel modelo = (DefaultTableModel) tabla2.getModel();
        DecimalFormat formato2 = new DecimalFormat("#.##");
        double fila = 0;
        double total = 0;
        
        for(int i = 0;i<modelo.getRowCount();i++){
            fila = Double.parseDouble(modelo.getValueAt(i, 1).toString());
            total += fila;
        }
        int dis2 = tabla2.getRowCount();
        int dis = dispositvos.tabla.getRowCount();
        if(dis>=3){
            double des = Double.parseDouble(txtdescuentos.getText());
            double aux = des + total*0.10;
            double descuento = total - aux;
            txtdescuentos.setText(String.valueOf(String.format("%.2f", aux)));
            String str = Double.toString(total);
            txttotalf.setText(String.valueOf(String.format("%.2f", descuento)));
            System.out.println("HOLA 1");
        }
        else if(dis2>=3){
            double des = Double.parseDouble(txtdescuentos.getText());
            double aux = des + total*0.10;
            double descuento = total - aux;
            txtdescuentos.setText(String.valueOf(String.format("%.2f", aux)));
            String str = Double.toString(total);
            txttotalf.setText(String.valueOf(String.format("%.2f", descuento)));
            System.out.println("HOLA 2");
        }
        else if(dis>=3 && dis2>=3){
           double des = Double.parseDouble(txtdescuentos.getText());
           double aux = des + total*0.20;
           double descuento = total - aux;
           txtdescuentos.setText(String.valueOf(String.format("%.2f", aux)));
           String str = Double.toString(total);
           txttotalf.setText(String.valueOf(String.format("%.2f", descuento)));
           System.out.println("HOLA 3");
           }
        else{
            txttotalf.setText(String.valueOf(String.format("%.2f", total)));
            System.out.println("HOLA 4");
        }
        }
        
    
    
    public void guardarOrden(){
        int aux = 0;
        if(cb2.getSelectedIndex()==0){
            aux = 2;
        }
        else if (cb2.getSelectedIndex()==1)
            aux = 3;
        else if (cb2.getSelectedIndex()==2)
            aux = 5;
        

        String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        String usuario = "postgres";
        String contra = "AD41uq6&";
        Connection conn = null;
        
        String hola = cb.getSelectedItem().toString();
        System.out.println(hola);
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(BD, usuario, contra);
            java.sql.Statement st = conn.createStatement();
            
            String sql = "insert into orden (id_dispo, id_cliente, fechacrea, fechacierre, partes, diagnostico, status, id_departamento,descuentos,tip_pago,total)"
                    + "values ("+ txtid_dispo.getText() +","+ txtid_cliente.getText() +",'"+ lbl10.getText() +"','"+ lbl10.getText() +"'"
                    + ",'"+ txtpartes.getText() +"','"+ txtdiagnostico.getText() +"','"+ cb.getSelectedItem().toString() +"',"+ aux +","+ txtdescuentos.getText() +",'"+ cb3.getSelectedItem().toString() +"',"+ txttotalf.getText() +")";
            ResultSet result = st.executeQuery(sql);
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (Exception e){
        
        }
    }
    
    public void nuevaTabla(){
        DefaultTableModel modelo;
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
    }
    
    public void descuento1(){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        DecimalFormat formato2 = new DecimalFormat("#.##");
        double fila = 0;
        double total = 0;
        
        for(int i = 0;i<modelo.getRowCount();i++){
            fila = Double.parseDouble(modelo.getValueAt(i, 1).toString());
            total += fila;
        }
        int dis = dispositvos.tabla.getRowCount();
        if(dis>=3){
            
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txttotalf = new javax.swing.JTextField();
        lbl10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lb9 = new javax.swing.JLabel();
        txtid_cliente = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cb2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cb3 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txtdescuentos = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtid20 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtid21 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jLabel30 = new javax.swing.JLabel();
        txtid_dispo = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtid_orden = new javax.swing.JTextField();
        cb = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        btneditar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtdiagnostico = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtpartes = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl11 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btniva = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        txttotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        btnterminar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(62, 95, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(1358, 1050));

        jPanel2.setBackground(new java.awt.Color(87, 131, 188));
        jPanel2.setPreferredSize(new java.awt.Dimension(962, 640));
        jPanel2.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ORDEN DE SERVICIO");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(30, 30, 270, 32);

        jLabel14.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID - Dispositivo");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(210, 90, 160, 28);

        jLabel16.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tipo pago");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(390, 690, 120, 28);

        jLabel17.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Diagnostico general");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(310, 210, 230, 28);

        txttotalf.setBackground(new java.awt.Color(240, 240, 240));
        txttotalf.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txttotalf.setEnabled(false);
        jPanel2.add(txttotalf);
        txttotalf.setBounds(400, 810, 120, 47);

        lbl10.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lbl10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl10);
        lbl10.setBounds(320, 30, 210, 40);

        jLabel22.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("ID - Cliente");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(390, 90, 140, 28);

        lb9.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lb9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lb9);
        lb9.setBounds(400, 20, 160, 40);

        txtid_cliente.setBackground(new java.awt.Color(240, 240, 240));
        txtid_cliente.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid_cliente.setEnabled(false);
        jPanel2.add(txtid_cliente);
        txtid_cliente.setBounds(390, 130, 140, 47);

        jLabel26.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Departamento");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(280, 380, 150, 28);

        cb2.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        cb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reparaciones", "Analisis", "Otros" }));
        jPanel2.add(cb2);
        cb2.setBounds(280, 410, 160, 50);

        jLabel18.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Estatus");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(50, 380, 90, 28);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(762, 400, 480, 130);

        jLabel19.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Costos");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(50, 480, 90, 28);

        jLabel20.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(430, 780, 120, 28);

        cb3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta", "Transferencia", "Pay-Pal", "Otro" }));
        jPanel2.add(cb3);
        cb3.setBounds(390, 730, 120, 40);

        jLabel21.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Descuentos");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(220, 690, 120, 28);

        txtdescuentos.setBackground(new java.awt.Color(240, 240, 240));
        txtdescuentos.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtdescuentos.setText("0");
        txtdescuentos.setEnabled(false);
        jPanel2.add(txtdescuentos);
        txtdescuentos.setBounds(220, 730, 120, 47);

        jPanel3.setBackground(new java.awt.Color(87, 131, 188));
        jPanel3.setPreferredSize(new java.awt.Dimension(962, 640));
        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COSTOS");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(30, 30, 270, 32);

        lbl9.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lbl9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl9);
        lbl9.setBounds(550, 20, 170, 30);

        jLabel23.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("IVA");
        jPanel3.add(jLabel23);
        jLabel23.setBounds(350, 180, 50, 28);

        txtid20.setBackground(new java.awt.Color(240, 240, 240));
        txtid20.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid20.setEnabled(false);
        jPanel3.add(txtid20);
        txtid20.setBounds(30, 110, 190, 150);

        jLabel24.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Descripción");
        jPanel3.add(jLabel24);
        jLabel24.setBounds(30, 80, 170, 28);

        txtid21.setBackground(new java.awt.Color(240, 240, 240));
        txtid21.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid21.setEnabled(false);
        jPanel3.add(txtid21);
        txtid21.setBounds(300, 120, 160, 47);

        jLabel25.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Precio");
        jPanel3.add(jLabel25);
        jLabel25.setBounds(300, 80, 170, 28);

        jToggleButton5.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton5.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton5.setText("Agregar costo");
        jPanel3.add(jToggleButton5);
        jToggleButton5.setBounds(150, 280, 210, 40);

        jToggleButton8.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton8.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton8.setText("Activo");
        jPanel3.add(jToggleButton8);
        jToggleButton8.setBounds(320, 220, 100, 40);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(760, 40, 480, 330);

        jLabel30.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Partes usadas");
        jPanel2.add(jLabel30);
        jLabel30.setBounds(40, 210, 130, 28);

        txtid_dispo.setBackground(new java.awt.Color(240, 240, 240));
        txtid_dispo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid_dispo.setEnabled(false);
        jPanel2.add(txtid_dispo);
        txtid_dispo.setBounds(210, 130, 120, 47);

        jLabel31.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("ID - Orden");
        jPanel2.add(jLabel31);
        jLabel31.setBounds(40, 90, 120, 28);

        txtid_orden.setBackground(new java.awt.Color(240, 240, 240));
        txtid_orden.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid_orden.setEnabled(false);
        jPanel2.add(txtid_orden);
        txtid_orden.setBounds(40, 130, 120, 47);

        cb.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reparado", "No Reparado", "Traer Después", "Revisado", "Otro", " " }));
        jPanel2.add(cb);
        cb.setBounds(50, 410, 160, 50);

        tabla2.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción", "Precio", "IVA"
            }
        ));
        tabla2.setRowHeight(24);
        jScrollPane5.setViewportView(tabla2);
        if (tabla2.getColumnModel().getColumnCount() > 0) {
            tabla2.getColumnModel().getColumn(0).setMinWidth(200);
        }

        jPanel2.add(jScrollPane5);
        jScrollPane5.setBounds(50, 520, 452, 160);

        btneditar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btneditar.setForeground(new java.awt.Color(25, 55, 87));
        btneditar.setText("Editar");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });
        jPanel2.add(btneditar);
        btneditar.setBounds(60, 720, 136, 40);

        txtdiagnostico.setColumns(20);
        txtdiagnostico.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtdiagnostico.setRows(5);
        jScrollPane6.setViewportView(txtdiagnostico);

        jPanel2.add(jScrollPane6);
        jScrollPane6.setBounds(310, 250, 220, 110);

        txtpartes.setColumns(20);
        txtpartes.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtpartes.setRows(5);
        jScrollPane7.setViewportView(txtpartes);

        jPanel2.add(jScrollPane7);
        jScrollPane7.setBounds(40, 250, 220, 110);

        jPanel4.setBackground(new java.awt.Color(87, 131, 188));
        jPanel4.setPreferredSize(new java.awt.Dimension(962, 640));
        jPanel4.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("COSTOS");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(30, 30, 270, 32);

        lbl11.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lbl11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl11);
        lbl11.setBounds(550, 20, 170, 30);

        jLabel27.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("IVA");
        jPanel4.add(jLabel27);
        jLabel27.setBounds(350, 180, 50, 28);

        jLabel28.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Descripción");
        jPanel4.add(jLabel28);
        jLabel28.setBounds(30, 80, 170, 28);

        txtprecio.setBackground(new java.awt.Color(240, 240, 240));
        txtprecio.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });
        jPanel4.add(txtprecio);
        txtprecio.setBounds(300, 120, 160, 47);

        jLabel29.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Precio");
        jPanel4.add(jLabel29);
        jLabel29.setBounds(300, 80, 170, 28);

        btniva.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btniva.setForeground(new java.awt.Color(25, 55, 87));
        btniva.setText("Activo");
        jPanel4.add(btniva);
        btniva.setBounds(320, 220, 100, 40);

        jButton1.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(25, 55, 87));
        jButton1.setText("Agregar costo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(130, 280, 230, 40);

        txtdescripcion.setColumns(20);
        txtdescripcion.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtdescripcion.setRows(5);
        jScrollPane2.setViewportView(txtdescripcion);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(20, 110, 220, 140);

        txttotal.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txttotal.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TOTAL");

        tabla.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción", "Precio", "IVA"
            }
        ));
        tabla.setRowHeight(24);
        jScrollPane4.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setMinWidth(200);
        }

        jButton3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(25, 55, 87));
        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnterminar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btnterminar.setForeground(new java.awt.Color(25, 55, 87));
        btnterminar.setText("Terminar");
        btnterminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnterminarActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(25, 55, 87));
        jButton4.setText("GENERAR ORDEN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(25, 55, 87));
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btnterminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(60, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(44, 44, 44))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnterminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(jButton3)))
                .addGap(570, 570, 570))
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 1360, 1000);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispositvos ven = new dispositvos();
        ven.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
        char validar=evt.getKeyChar();
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresa solo numeros");
        }
    }//GEN-LAST:event_txtprecioKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(txtpartes.getText().isEmpty() || txttotalf.getText().isEmpty()|| txtdiagnostico.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }
        else{
            JOptionPane.showMessageDialog(null, "Orden generada con exito");
            guardarOrden();
            clientes ven = new clientes();
            ven.setVisible(true);
            dispose();
            
            Document documento = new Document();
            try{
                String ruta = System.getProperty("user.home");
                PdfWriter.getInstance(documento, new FileOutputStream("C:/Users/limon/Desktop/Sexto Semestre/Seminario de Bases de Datos/Ordenes/orden "+txtid_orden.getText()+".pdf"));
                documento.open();
                PdfPTable tabla5 = new PdfPTable(4);
                tabla5.addCell("id_Orden");
                tabla5.addCell("Descuento");
                tabla5.addCell("Total");
                tabla5.addCell(txtdescuentos.getText());
                documento.add(tabla5);
                documento.close();
            }
            catch(DocumentException | FileNotFoundException e){
                
            }
            
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(txtdescripcion.getText().isEmpty() || txtprecio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede quedar vacio");
        }else{
            insertarCosto();
            sumarColumna();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int fila = tabla.getSelectedRow();
        if(fila>=0){
            modelo.removeRow(fila);
        }
        else{
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnterminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnterminarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) tabla2.getModel();
        
        for(int i = 0;i<modelo.getRowCount();i++){
            Object f [] =  new Object [modelo.getColumnCount()];
            for(int j = 0;j<modelo.getColumnCount();j++){
                f[j] = modelo.getValueAt(i, j);
            }
            modelo2.addRow(f);
    }
        
        int fila = modelo.getRowCount();
        for (int i = fila-1;i>=0;i--)
            modelo.removeRow(i);
        txttotal.setText(null);
        sumarColumna2();
        
    }//GEN-LAST:event_btnterminarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) tabla2.getModel();
        
        for(int i = 0;i<modelo2.getRowCount();i++){
            Object f [] =  new Object [modelo2.getColumnCount()];
            for(int j = 0;j<modelo2.getColumnCount();j++){
                f[j] = modelo2.getValueAt(i, j);
            }
            modelo.addRow(f);
    }
        
        int fila = modelo2.getRowCount();
        for (int i = fila-1;i>=0;i--)
            modelo2.removeRow(i);
        
        txtdescuentos.setText("0");
    }//GEN-LAST:event_btneditarActionPerformed

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
            java.util.logging.Logger.getLogger(orden1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orden1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orden1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orden1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orden1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JToggleButton btniva;
    private javax.swing.JButton btnterminar;
    private javax.swing.JComboBox<String> cb;
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JComboBox<String> cb3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable2;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JLabel lb9;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl9;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextArea txtdescripcion;
    private javax.swing.JTextField txtdescuentos;
    private javax.swing.JTextArea txtdiagnostico;
    private javax.swing.JTextField txtid20;
    private javax.swing.JTextField txtid21;
    public static javax.swing.JTextField txtid_cliente;
    public static javax.swing.JTextField txtid_dispo;
    public static javax.swing.JTextField txtid_orden;
    private javax.swing.JTextArea txtpartes;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotalf;
    // End of variables declaration//GEN-END:variables
}
