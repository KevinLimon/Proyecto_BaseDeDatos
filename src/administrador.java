
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author limon
 */
public class administrador extends javax.swing.JFrame {

    /**
     * Creates new form administrador
     */
    public administrador() {
        initComponents();
        
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = Gd.getDisplayMode().getWidth();
        int height = Gd.getDisplayMode().getHeight();
        this.setSize(width,height);
        Color azul = new Color(111, 115, 210); // Color azul
        this.getContentPane().setBackground(azul); //Cambiar color de fondo
        setExtendedState(MAXIMIZED_BOTH); //this.setExtendedState(MAXIMIZED_BOTH); //Tamaño
        
        ImageIcon imagen1 = new ImageIcon("empleados.png");
        JLabel imginv = new JLabel(new ImageIcon("empleados.png"));
        imginv.setBounds(210,210,254,230);
        imginv.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH)));
        panel1.add(imginv);
        
        ImageIcon imagen2 = new ImageIcon("clasificacion.png");
        JLabel imgc = new JLabel(new ImageIcon("clasificacion.png"));
        imgc.setBounds(210,210,280,230);
        imgc.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH)));
        panel2.add(imgc);
        
        imginv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imginv.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        lble = new javax.swing.JLabel();
        lblimg = new javax.swing.JLabel();
        btnae = new javax.swing.JButton();
        panel2 = new javax.swing.JPanel();
        lblc = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnac = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panel1.setBackground(new java.awt.Color(111, 115, 210));
        panel1.setForeground(new java.awt.Color(111, 115, 210));
        panel1.setToolTipText("");
        panel1.setPreferredSize(new java.awt.Dimension(683, 1366));
        panel1.setRequestFocusEnabled(false);

        lble.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 48)); // NOI18N
        lble.setForeground(new java.awt.Color(255, 255, 255));
        lble.setText("EMPLEADOS");

        btnae.setFont(new java.awt.Font("Microsoft YaHei", 0, 36)); // NOI18N
        btnae.setForeground(new java.awt.Color(25, 55, 87));
        btnae.setText("Administrar empleados");
        btnae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lble)
                .addGap(193, 193, 193))
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnae)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lble, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182)
                .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnae)
                .addContainerGap(805, Short.MAX_VALUE))
        );

        getContentPane().add(panel1);
        panel1.setBounds(0, 0, 683, 1366);

        panel2.setBackground(new java.awt.Color(131, 201, 244));
        panel2.setMinimumSize(new java.awt.Dimension(683, 1366));
        panel2.setPreferredSize(new java.awt.Dimension(683, 1366));

        lblc.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 48)); // NOI18N
        lblc.setForeground(new java.awt.Color(255, 255, 255));
        lblc.setText("CLIENTES");

        jButton1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(25, 55, 87));
        jButton1.setText("Cerrar Sesión");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnac.setFont(new java.awt.Font("Microsoft YaHei", 0, 36)); // NOI18N
        btnac.setForeground(new java.awt.Color(25, 55, 87));
        btnac.setText("Administrar clientes");
        btnac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(lblc)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(btnac)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(lblc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(358, 358, 358)
                .addComponent(btnac)
                .addGap(68, 68, 68)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(695, Short.MAX_VALUE))
        );

        getContentPane().add(panel2);
        panel2.setBounds(680, 0, 683, 1366);
        panel2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login ven = new login();
        ven.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaeActionPerformed
        empleados ven = new empleados();
        ven.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnaeActionPerformed

    private void btnacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnacActionPerformed
        clientes ven = new clientes();
        ven.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnacActionPerformed

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
            java.util.logging.Logger.getLogger(administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnac;
    private javax.swing.JButton btnae;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblc;
    private javax.swing.JLabel lble;
    private javax.swing.JLabel lblimg;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    // End of variables declaration//GEN-END:variables
}
