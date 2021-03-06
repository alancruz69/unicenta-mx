/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;
import com.openbravo.basic.BasicException;
//import com.openbravo.data.gui.ComboBoxValModel;
//import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.Component;
//import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JPanel;

/**
 *
 * @author np370
 */
public final class MarcasEditor extends JPanel implements EditorRecord {
private SentenceList m_sentcat;
private SentenceExec m_sentadd;
private SentenceExec m_sentdel;
private Object m_id;
    /**
     * Creates new form MarcasEditor
     */
    public MarcasEditor(AppView app, DirtyManager dirty) {
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSales");
        initComponents();
         // El modelo de marcas
        m_sentcat = dlSales.getMarcasList();
       // m_CategoryModel = new ComboBoxValModel();
        
        m_sentadd = dlSales.getCatalogMarcasAdd();
        m_sentdel = dlSales.getCatalogMarcasDel();
        
        m_jName.getDocument().addDocumentListener(dirty);
//        m_jCategory.addActionListener(dirty);
//        m_jImage.addPropertyChangeListener("image", dirty);
        
        writeValueEOF();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlName = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jlName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlName.setText("Nombre");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Catálogo de Marcas");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlName)
                        .addGap(30, 30, 30)
                        .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlName)
                    .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(214, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlName;
    private javax.swing.JTextField m_jName;
    // End of variables declaration//GEN-END:variables


    @Override
    public void writeValueEOF() {
        m_id = null;
        m_jName.setText(null);               
    }

    @Override
    public void writeValueInsert() {
        m_id = UUID.randomUUID().toString();
        m_jName.setText(null); 
        m_jName.setEnabled(true);
    }

    @Override
    public void writeValueEdit(Object value) {
       Object[] cat = (Object[]) value;
       m_id = cat[0];
       m_jName.setText(Formats.STRING.formatValue(cat[1])); 
       m_jName.setEnabled(true);       
    }

    @Override
    public void writeValueDelete(Object value) {
        Object[] cat = (Object[]) value;
        m_id = cat[0];
        m_jName.setText(Formats.STRING.formatValue(cat[1]));      
        m_jName.setEnabled(false);       
    }

    @Override
    public void refresh() {
        List a;
        try {
            a = m_sentcat.list();
        } catch (BasicException eD) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotloadlists"), eD);
            msg.show(this);
            a = new ArrayList();
        }
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public Object createValue() throws BasicException {
        Object[] cat = new Object[2];
        cat[0] = m_id;
        cat[1] = m_jName.getText();
        return cat;
    }
}
