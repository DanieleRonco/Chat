package progettochat;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class FrameGrafica extends javax.swing.JFrame {
    CGestoreChat gestore;
    ThreadAscolto ta1;
    
    public FrameGrafica(CGestoreChat gestore, ThreadAscolto ta1) {
        initComponents();
        
        this.gestore = gestore;
        this.ta1 = ta1;
        
        this.gestore.setFrame(this);
        
        ta1.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Connetti = new javax.swing.JButton();
        IndirizzoIP = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        TxtMessaggio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Connetti.setText("Connetti");
        Connetti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnettiActionPerformed(evt);
            }
        });

        IndirizzoIP.setText("IndirizzoIP");

        jButton1.setText("Chiudi connessione");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jButton2.setText("Invia messaggio");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        TxtMessaggio.setText("Inserire qui il messaggio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Connetti, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(IndirizzoIP))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtMessaggio, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Connetti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IndirizzoIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtMessaggio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        IndirizzoIP.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConnettiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnettiActionPerformed
        try {
            gestore.InstauraConnessione(IndirizzoIP.getText());
        } catch (UnknownHostException ex) {
            Logger.getLogger(FrameGrafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ConnettiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            gestore.ChiudiConnessione();
        } catch (IOException ex) {
            Logger.getLogger(FrameGrafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            gestore.InviaMessaggio("m;"+TxtMessaggio.getText()+";");
        } catch (IOException ex) {
            Logger.getLogger(FrameGrafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void SetLabel(String s){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               //Codice da eseguire nel Thread grafico
              jLabel1.setText(s);
              
            }
        });
    }
    
    public String getLabel(){
        return jLabel1.getText();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SocketException, InterruptedException {
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
            java.util.logging.Logger.getLogger(FrameGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        CGestoreChat gestoreChat = new CGestoreChat();
        ThreadAscolto threadAscolto1 = new ThreadAscolto(gestoreChat, 2003);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGrafica(gestoreChat, threadAscolto1).setVisible(true);
            }
        });
        
        /*
        while(c.frame==null )
        {
            //OTTIMIZZABILE! aspetto che venga settata dal form
            sleep(1);
        }
            
        ThreadTest t = new ThreadTest(c);
        t.start();
        
        
        
        
        while(this==null )
                {
                    //OTTIMIZZABILE! aspetto che venga settata dal form
                    sleep(1);
                }
                
                CGestoreChat GestoreChat = null;
                try {
                    GestoreChat = new CGestoreChat(this);
                } catch (SocketException ex) {
                    Logger.getLogger(FrameGrafica.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ThreadAscolto ThreadAscolto1 = null;
                try {
                    ThreadAscolto1 = new ThreadAscolto(GestoreChat, 2003);
                } catch (SocketException ex) {
                    Logger.getLogger(FrameGrafica.class.getName()).log(Level.SEVERE, null, ex);
                }
        */     
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Connetti;
    public javax.swing.JTextField IndirizzoIP;
    private javax.swing.JTextField TxtMessaggio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}