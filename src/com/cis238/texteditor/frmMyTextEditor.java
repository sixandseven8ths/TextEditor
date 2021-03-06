/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cis238.texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeffrey.Herold
 */
public class frmMyTextEditor extends javax.swing.JFrame {
    
    //global variables
    File myFile;
    
    /**
     * Creates new form frmMyTextEditor
     */
    public frmMyTextEditor() {
        initComponents();
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
        txtEditor = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuFileNew = new javax.swing.JMenuItem();
        mnuOpen = new javax.swing.JMenuItem();
        mnuSave = new javax.swing.JMenuItem();
        mnuSaveAs = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtEditor.setColumns(20);
        txtEditor.setRows(5);
        jScrollPane1.setViewportView(txtEditor);

        mnuFile.setText("File");

        mnuFileNew.setText("New");
        mnuFileNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileNewActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFileNew);

        mnuOpen.setText("Open");
        mnuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenActionPerformed(evt);
            }
        });
        mnuFile.add(mnuOpen);

        mnuSave.setText("Save");
        mnuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSaveActionPerformed(evt);
            }
        });
        mnuFile.add(mnuSave);

        mnuSaveAs.setText("Save As");
        mnuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSaveAsActionPerformed(evt);
            }
        });
        mnuFile.add(mnuSaveAs);

        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuExit);

        jMenuBar1.add(mnuFile);

        mnuEdit.setText("Edit");
        jMenuBar1.add(mnuEdit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenActionPerformed
        //clear the text area
        this.txtEditor.setText("");
        
        //choose the file to open
        JFileChooser myFileChooser = new JFileChooser();
        myFileChooser.showOpenDialog(this);
        
        //make sure the user didn't click cancel
        if (myFileChooser.getSelectedFile() != null)
        {
            //get a file object
            myFile = new File(myFileChooser.getSelectedFile().getPath());
            
            //display the file path in the text area
            this.txtEditor.setText(myFile.getPath());
            
            try
            {
                //get a file object
                FileReader myReader = new FileReader(myFile);
                
                //create buffered reader object
                BufferedReader myBuffer = new BufferedReader(myReader);
                
                //read a line from the text file
                String strLine = myBuffer.readLine();
                
                //loop through the rest of the lines
                while (strLine != null)
                {
                    this.txtEditor.append(strLine); //add the new line to the text area
                    this.txtEditor.append("/n");    //add a line break
                    strLine = myBuffer.readLine(); //read another line from the file
                }
                
                //close stuff
                myBuffer.close();
                myReader.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.toString());
            }
        }
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void mnuFileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileNewActionPerformed
        // clear text area
        this.txtEditor.setText("");
        
        //clear file object
        myFile = null;
    }//GEN-LAST:event_mnuFileNewActionPerformed

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        // exit app
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveActionPerformed
        // make sure the file is not null
        if(myFile != null)
        {
            //call the method to save
            saveFileContents();
        }
        else
        {
            //if new, show save as dialog
            mnuSaveAsActionPerformed(evt);
        }
    }//GEN-LAST:event_mnuSaveActionPerformed

    private void mnuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveAsActionPerformed
        // change file object
        JFileChooser myChooser = new JFileChooser();
        myChooser.setDialogTitle("Save As...");
        myChooser.showSaveDialog(this);    //make sure user didn't click cancel
    
        if (myChooser.getSelectedFile() != null)
        {
            //change to new file object
            myFile = new File(myChooser.getSelectedFile().getPath());
        
            //call method to save
            saveFileContents();
        }
    }//GEN-LAST:event_mnuSaveAsActionPerformed

    private void saveFileContents()
    {
        try
        {
            //create file writer
            FileWriter myWriter = new FileWriter(myFile);
            
            //creat print writer
            PrintWriter myPrinter = new PrintWriter(myWriter);
            
            //write the contents of the text are to the file
            myPrinter.print(this.txtEditor.getText());
            
            //close stuff
            myPrinter.close();
            myWriter.close();
            
            //message to user
            JOptionPane.showMessageDialog(this, "Changes were saved successfully.");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
    
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
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMyTextEditor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuFileNew;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenuItem mnuSave;
    private javax.swing.JMenuItem mnuSaveAs;
    private javax.swing.JTextArea txtEditor;
    // End of variables declaration//GEN-END:variables
}
