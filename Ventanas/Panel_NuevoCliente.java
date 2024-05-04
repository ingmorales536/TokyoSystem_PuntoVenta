
package Ventanas;

import Implementaciones.ClientesImpl;
import Modelo.ModeloClientes;
import interfaces.InterfaceClientes;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Armando
 */
public class Panel_NuevoCliente extends javax.swing.JPanel {
boolean isEdition = false;
ModeloClientes userEdition;
 
    public Panel_NuevoCliente() {
        initComponents();
         InitStyles();
        GuardarCliente();   
       
    }//Fin del constructor
    
    public Panel_NuevoCliente(ModeloClientes cliente){
        initComponents();
        GuardarCliente();
        isEdition = true;
        userEdition = cliente;
        InitStyles();
    
    }//Fin del 2 constructor
    
    private void InitStyles() {
        
        if (isEdition) {
            LabelTitulo.setText("Editar Cliente");
            BotonRegistrar.setText("Guardar");

            if (userEdition != null) {
                txtNombre.setText(userEdition.getNombre());
                txtApellidoPaterno.setText(userEdition.getApellidoPaterno());
                txtApellidoMaterno.setText(userEdition.getApellidoMaterno());  
                txtTelefono.setText(userEdition.getTelefono());
                txtDireccion.setText(userEdition.getDireccion());
            }
        }
    }//Fin del metodo estilos
    
    
    private void GuardarCliente(){
      
        BotonRegistrar.addActionListener(new ActionListener() {  
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nombre = txtNombre.getText();
                String ApellidoPa = txtApellidoPaterno.getText();
                String ApellidoMa = txtApellidoMaterno.getText();
                String Telefono = txtTelefono.getText();
                String Direccion= txtDireccion.getText();
             
             
             if(Nombre.isEmpty() && ApellidoPa.isEmpty() && ApellidoMa.isEmpty() && Telefono.isEmpty() && Direccion.isEmpty() ){
                txtNombre.setBackground(Color.red);
                 txtApellidoPaterno.setBackground(Color.red);
                 txtApellidoMaterno.setBackground(Color.red);
                 txtTelefono.setBackground(Color.red);
                 txtDireccion.setBackground(Color.red);
                 JOptionPane.showMessageDialog(null, "Ingrese todos los datos","Error-Ingresar Cliente",0);
                 
                 
             }else{
                ModeloClientes cliente1 = isEdition ? userEdition : new ModeloClientes(); 
                cliente1.setNombre(Nombre);
                cliente1.setApellidoPaterno(ApellidoPa);
                cliente1.setApellidoMaterno(ApellidoMa);
                cliente1.setTelefono(Telefono);
                cliente1.setDireccion(Direccion);
                try{
                 InterfaceClientes dao = new ClientesImpl();
                if(!isEdition){
                     dao.Registrar(cliente1);   
                }else{  
                    dao.Modificar(cliente1);
                }
                if(!isEdition){
                     Limpiar();
                }
            
                    }catch(Exception a){
                        System.out.println(a);
                }//Fin del catch
               
             }
              
            }
        });
    
    }//Fin del metodo registrar cliente
    
    
    private void Limpiar(){
                 txtNombre.setText("");
                 txtApellidoPaterno.setText("");
                 txtApellidoMaterno.setText("");
                 txtTelefono.setText("");
                 txtDireccion.setText("");
    
    }
    
    
    
    
    
     private void establecerFondo() {
        // Crear un ImageIcon con la imagen de fondo
        ImageIcon imagenFondo = new ImageIcon("src/Img/playa.jpg");

        // Crear un JLabel para mostrar la imagen de fondo
        JLabel labelFondo = new JLabel(imagenFondo);

        // Establecer el tamaño y la posición del JLabel
        labelFondo.setBounds(0, 0, imagenFondo.getIconWidth(), imagenFondo.getIconHeight());

        // Agregar el JLabel al panel de fondo
        Panel_Background.add(labelFondo);

        // Asegurar que el panel de fondo se redibuje
        Panel_Background.revalidate();
        Panel_Background.repaint();
    }//Fin del metodo establecer fondo
    
    
    
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Background = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        LabelTitulo = new javax.swing.JLabel();
        BotonRegistrar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(32, 32, 32));

        Panel_Background.setBackground(new java.awt.Color(32, 32, 32));
        Panel_Background.setMinimumSize(new java.awt.Dimension(751, 446));
        Panel_Background.setPreferredSize(new java.awt.Dimension(751, 446));

        jPanel1.setBackground(new java.awt.Color(35, 35, 35));

        jSeparator1.setBackground(new java.awt.Color(35, 35, 35));
        jSeparator1.setForeground(new java.awt.Color(35, 35, 35));

        txtNombre.setBackground(new java.awt.Color(35, 35, 35));
        txtNombre.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOMBRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        txtNombre.setMaximumSize(new java.awt.Dimension(200, 60));
        txtNombre.setMinimumSize(new java.awt.Dimension(200, 55));
        txtNombre.setPreferredSize(new java.awt.Dimension(200, 55));
        txtNombre.setVerifyInputWhenFocusTarget(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtApellidoPaterno.setBackground(new java.awt.Color(35, 35, 35));
        txtApellidoPaterno.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtApellidoPaterno.setForeground(new java.awt.Color(255, 255, 255));
        txtApellidoPaterno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellidoPaterno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "APELLIDO PATERNO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        txtApellidoPaterno.setMaximumSize(new java.awt.Dimension(200, 60));
        txtApellidoPaterno.setMinimumSize(new java.awt.Dimension(200, 55));
        txtApellidoPaterno.setPreferredSize(new java.awt.Dimension(200, 55));
        txtApellidoPaterno.setVerifyInputWhenFocusTarget(false);
        txtApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPaternoActionPerformed(evt);
            }
        });

        txtApellidoMaterno.setBackground(new java.awt.Color(35, 35, 35));
        txtApellidoMaterno.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtApellidoMaterno.setForeground(new java.awt.Color(255, 255, 255));
        txtApellidoMaterno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellidoMaterno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "APELLIDO MATERNO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        txtApellidoMaterno.setMaximumSize(new java.awt.Dimension(200, 60));
        txtApellidoMaterno.setMinimumSize(new java.awt.Dimension(200, 55));
        txtApellidoMaterno.setPreferredSize(new java.awt.Dimension(200, 55));
        txtApellidoMaterno.setVerifyInputWhenFocusTarget(false);

        txtTelefono.setBackground(new java.awt.Color(35, 35, 35));
        txtTelefono.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TELEFONO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        txtTelefono.setMaximumSize(new java.awt.Dimension(200, 55));
        txtTelefono.setMinimumSize(new java.awt.Dimension(200, 55));
        txtTelefono.setPreferredSize(new java.awt.Dimension(200, 55));
        txtTelefono.setVerifyInputWhenFocusTarget(false);

        txtDireccion.setBackground(new java.awt.Color(35, 35, 35));
        txtDireccion.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DIRECCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        txtDireccion.setMaximumSize(new java.awt.Dimension(200, 55));
        txtDireccion.setMinimumSize(new java.awt.Dimension(200, 55));
        txtDireccion.setPreferredSize(new java.awt.Dimension(200, 55));
        txtDireccion.setVerifyInputWhenFocusTarget(false);

        LabelTitulo.setFont(new java.awt.Font("Bahnschrift", 0, 26)); // NOI18N
        LabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LabelTitulo.setText("Registrar Nuevo Cliente");

        BotonRegistrar.setBackground(new java.awt.Color(102, 0, 102));
        BotonRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BotonRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        BotonRegistrar.setText("Registrar");
        BotonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(35, 35, 35));
        jSeparator2.setForeground(new java.awt.Color(35, 35, 35));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0,1));
        jSeparator3.setForeground(new java.awt.Color(35, 35, 35));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0,1));
        jSeparator5.setForeground(new java.awt.Color(35, 35, 35));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0,1));
        jSeparator6.setForeground(new java.awt.Color(35, 35, 35));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0,1));
        jSeparator7.setForeground(new java.awt.Color(35, 35, 35));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0,1));
        jSeparator8.setForeground(new java.awt.Color(35, 35, 35));

        jSeparator4.setBackground(new java.awt.Color(35, 35, 35));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addGap(133, 133, 133))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator6)
                                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator7))
                                .addGap(36, 36, 36)))
                        .addGap(19, 19, 19)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(jSeparator8)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator3)
                                .addGap(7, 7, 7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(BotonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator5)
                        .addGap(668, 668, 668))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(LabelTitulo)
                .addGap(98, 98, 98)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(BotonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addComponent(jSeparator1))
        );

        javax.swing.GroupLayout Panel_BackgroundLayout = new javax.swing.GroupLayout(Panel_Background);
        Panel_Background.setLayout(Panel_BackgroundLayout);
        Panel_BackgroundLayout.setHorizontalGroup(
            Panel_BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Panel_BackgroundLayout.setVerticalGroup(
            Panel_BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Background, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Background, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPaternoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void BotonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarActionPerformed
    
 
    }//GEN-LAST:event_BotonRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonRegistrar;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JPanel Panel_Background;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    void setMaximumSize(int i, int par1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setMinimumSize(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
