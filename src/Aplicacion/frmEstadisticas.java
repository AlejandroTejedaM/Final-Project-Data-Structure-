/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Aplicacion;

import Clases.PilaDinamica;
import Clases.Personal;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author CC_101
 */
public class frmEstadisticas extends javax.swing.JFrame {

    fmrColectivosMapache a;

    /**
     * Creates new form frmEstadisticas
     */
    public frmEstadisticas() {
//        pilaConectada = a.getPila();
//        initComponents();
//        Conectar(a); 
    }
    PilaDinamica pilaConectada;
//    
//    private void Conectar(fmrColectivosMapache a) {
//        pilaConectada = a.p;
//    }

    //
    private String ObtenerGeneros() {
        String temporal = "";
        Stack<HashMap<Integer, Personal[]>> ptemp = pilaConectada.getPila();
        List<HashMap<Integer, Personal[]>> aux = new ArrayList<>();
        String matriculasCombi = "";
        for (HashMap<Integer, Personal[]> e : ptemp) {
            aux.add(e);
        }
        for (HashMap<Integer, Personal[]> hashMap : aux) {
            for (Map.Entry<Integer, Personal[]> x : hashMap.entrySet()) {
                if (x.getKey() == null) {
                    matriculasCombi += "";
                } else {
                    temporal = "";
                    Personal[] arraytemp = x.getValue();
                    for (int i = 0; i < arraytemp.length; i++) {
                        temporal += arraytemp[i].getGenero();
                    }
                }
            }
        }
        return temporal;
    }

    private static int BuscarHombres(String palabra, int contador) {
        if (palabra.length() == 0) {
            return contador;
        } else {
            char aux = palabra.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("M")) {
                contador++;
            }
            return BuscarMujeres(palabra.substring(1), contador);
        }
    }

    private static int BuscarMujeres(String palabra, int contador) {
        if (palabra.length() == 0) {
            return contador;
        } else {
            char aux = palabra.charAt(0);
            String var = String.valueOf(aux);
            if (var.equalsIgnoreCase("M")) {
                contador++;
            }
            return BuscarMujeres(palabra.substring(1), contador);
        }
    }

    private String MostrarTipoPersona() {
        return null;
    }

    private String MostrarModalidad() {
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextADatos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 51));

        jLabel1.setFont(new java.awt.Font("Modern No. 20", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Estadisticas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(103, 103, 103))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(0, 153, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Obtener datos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        TextADatos.setColumns(20);
        TextADatos.setRows(5);
        jScrollPane1.setViewportView(TextADatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(115, 115, 115))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        TextADatos.setText("");
        TextADatos.setText("ESTADISTICAS:\n)" + MostrarGeneros());
//        TextADatos.append("ESTADISTICAS:\n)" + MostrarGeneros() + "\n" + MostrarTipoPersona() + "\n" + MostrarModalidad() + "\n" + MostrarSemestre());
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEstadisticas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TextADatos;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private String MostrarGeneros() {
        String infoGenero = "Numero de hombres: ";
        int contHombres = 0;
        String hombres = String.valueOf(BuscarHombres(ObtenerGeneros(), contHombres));
        int contMujeres = 0;
        String mujeres = String.valueOf(BuscarMujeres(ObtenerGeneros(), contMujeres));
        infoGenero += hombres + "Numero de mujeres: " + mujeres;
        return infoGenero;
    }

}