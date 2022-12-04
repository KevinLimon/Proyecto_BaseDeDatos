
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author limon
 */
public class orden extends javax.swing.JFrame {

    /**
     * Creates new form orden
     */
    public orden() {
        initComponents();
        
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = Gd.getDisplayMode().getWidth();
        int height = Gd.getDisplayMode().getHeight();
        this.setSize(width,height);
        Color azul = new Color(62, 95, 138); // Color azul
        this.getContentPane().setBackground(azul); //Cambiar color de fondo
        setExtendedState(MAXIMIZED_BOTH);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lb9.setText(String.valueOf(dtf.format(LocalDateTime.now())));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtid20 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtid21 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtid6 = new javax.swing.JTextField();
        txtid13 = new javax.swing.JTextField();
        lbl10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lb10 = new javax.swing.JLabel();
        txtid19 = new javax.swing.JTextField();
        lb9 = new javax.swing.JLabel();
        txtid22 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtid23 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        txtid7 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txtid8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(87, 131, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(962, 640));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COSTOS");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 30, 270, 32);

        lbl9.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lbl9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl9);
        lbl9.setBounds(550, 20, 170, 30);

        jLabel23.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("IVA");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(350, 180, 50, 28);

        txtid20.setBackground(new java.awt.Color(240, 240, 240));
        txtid20.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid20.setEnabled(false);
        jPanel1.add(txtid20);
        txtid20.setBounds(30, 110, 190, 150);

        jLabel24.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Descripción");
        jPanel1.add(jLabel24);
        jLabel24.setBounds(30, 80, 170, 28);

        txtid21.setBackground(new java.awt.Color(240, 240, 240));
        txtid21.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid21.setEnabled(false);
        jPanel1.add(txtid21);
        txtid21.setBounds(300, 120, 160, 47);

        jLabel25.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Precio");
        jPanel1.add(jLabel25);
        jLabel25.setBounds(300, 80, 170, 28);

        jToggleButton5.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton5.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton5.setText("Agregar costo");
        jPanel1.add(jToggleButton5);
        jToggleButton5.setBounds(150, 280, 210, 40);

        jToggleButton8.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton8.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton8.setText("Activo");
        jPanel1.add(jToggleButton8);
        jToggleButton8.setBounds(320, 220, 100, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(760, 40, 480, 330);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(762, 400, 480, 130);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(1150, 540, 150, 50);

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TOTAL");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(1050, 550, 90, 30);

        jToggleButton2.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton2.setText("Terminar");
        getContentPane().add(jToggleButton2);
        jToggleButton2.setBounds(1120, 610, 120, 40);

        jToggleButton9.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton9.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton9.setText("Eliminar");
        getContentPane().add(jToggleButton9);
        jToggleButton9.setBounds(760, 550, 120, 40);

        jToggleButton10.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton10.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton10.setText("GENERAR ORDEN");
        getContentPane().add(jToggleButton10);
        jToggleButton10.setBounds(760, 610, 270, 50);

        jToggleButton11.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jToggleButton11.setForeground(new java.awt.Color(25, 55, 87));
        jToggleButton11.setText("Editar");
        getContentPane().add(jToggleButton11);
        jToggleButton11.setBounds(910, 550, 120, 40);

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
        jLabel14.setText("ID");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(40, 90, 21, 28);

        jLabel15.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Fecha entregado");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(400, 90, 170, 28);

        jLabel16.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tipo pago");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(250, 700, 120, 28);

        jLabel17.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Diagnostico general");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(300, 220, 230, 28);

        txtid6.setBackground(new java.awt.Color(240, 240, 240));
        txtid6.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid6.setEnabled(false);
        jPanel2.add(txtid6);
        txtid6.setBounds(400, 810, 120, 47);

        txtid13.setBackground(new java.awt.Color(240, 240, 240));
        txtid13.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid13.setEnabled(false);
        jPanel2.add(txtid13);
        txtid13.setBounds(310, 250, 230, 110);

        lbl10.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lbl10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl10);
        lbl10.setBounds(550, 20, 170, 30);

        jLabel22.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Fecha recibido");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(210, 90, 140, 28);

        lb10.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lb10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lb10);
        lb10.setBounds(410, 20, 160, 40);

        txtid19.setBackground(new java.awt.Color(240, 240, 240));
        txtid19.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid19.setEnabled(false);
        jPanel2.add(txtid19);
        txtid19.setBounds(400, 130, 140, 47);

        lb9.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        lb9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lb9);
        lb9.setBounds(400, 20, 160, 40);

        txtid22.setBackground(new java.awt.Color(240, 240, 240));
        txtid22.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid22.setEnabled(false);
        jPanel2.add(txtid22);
        txtid22.setBounds(210, 130, 140, 47);

        jLabel26.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Partes usadas");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(40, 210, 130, 28);

        txtid23.setBackground(new java.awt.Color(240, 240, 240));
        txtid23.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid23.setEnabled(false);
        jPanel2.add(txtid23);
        txtid23.setBounds(40, 250, 230, 110);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(130, 390, 160, 50);

        jLabel18.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Estatus");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(50, 400, 90, 28);

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

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable3);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(50, 530, 480, 130);

        jLabel19.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Costos");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(50, 480, 90, 28);

        txtid7.setBackground(new java.awt.Color(240, 240, 240));
        txtid7.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid7.setEnabled(false);
        jPanel2.add(txtid7);
        txtid7.setBounds(40, 130, 120, 47);

        jLabel20.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(430, 780, 120, 28);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox2);
        jComboBox2.setBounds(250, 740, 120, 40);

        jLabel21.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Descuentos");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(50, 690, 120, 28);

        txtid8.setBackground(new java.awt.Color(240, 240, 240));
        txtid8.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtid8.setEnabled(false);
        jPanel2.add(txtid8);
        txtid8.setBounds(50, 730, 120, 47);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(50, 40, 580, 870);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orden().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JLabel lb10;
    private javax.swing.JLabel lb9;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl9;
    private javax.swing.JTextField txtid13;
    private javax.swing.JTextField txtid19;
    private javax.swing.JTextField txtid20;
    private javax.swing.JTextField txtid21;
    private javax.swing.JTextField txtid22;
    private javax.swing.JTextField txtid23;
    private javax.swing.JTextField txtid6;
    private javax.swing.JTextField txtid7;
    private javax.swing.JTextField txtid8;
    // End of variables declaration//GEN-END:variables
}
