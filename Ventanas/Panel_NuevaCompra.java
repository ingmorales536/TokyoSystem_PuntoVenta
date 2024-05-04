
package Ventanas;

import javax.swing.table.DefaultTableCellRenderer;
import ConexionDB.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arman
 */
public class Panel_NuevaCompra extends javax.swing.JPanel {
private DefaultTableModel modelo;
Conexion c = new Conexion();
Connection co = c.Conectar();

 ImageIcon icono = new ImageIcon("src/Img/eliminar2.png");  
    /**
     * Creates new form Panel_NuevaCompra
     */
    public Panel_NuevaCompra() throws SQLException {
        initComponents();
        DiseñoVentana();
        DiseñoTabla();
        ObtenerProveedores();
        LogicaBotones();

        
        
    }
    
    
       private void DiseñoTabla(){
    // Configurar el renderizador para los encabezados
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(java.awt.Color.black); // Color de fondo
        headerRenderer.setForeground(java.awt.Color.white); // Color de fuente
        
        // Aplicar el renderizador a los encabezados de la tabla
        for (int i = 0; i < TablaNuevaCompra.getColumnCount(); i++) {
            TablaNuevaCompra.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }//Fin del metodo diseñoTabla
    
    private void DiseñoVentana(){
    setSize(963, 580);
    
    
    }//Fin del metodo DiseñoVentana
    
    private void ObtenerProveedores() throws SQLException{
          
        try{     
          
            PreparedStatement stmt = co.prepareStatement("SELECT nombre FROM proveedores");
            ResultSet result = stmt.executeQuery();
            
            ComboBoxProveedores.removeAllItems();
            
            while(result.next()){
                String Resulproveedores = result.getString("nombre");
                ComboBoxProveedores.addItem(Resulproveedores);
            
            }
            
        }catch(SQLException ex){
            System.out.println(ex);
        } 
        
        
        PreparedStatement stmt = co.prepareStatement("SELECT descripcion FROM productos");
        ResultSet resultado = stmt.executeQuery();
        
        while(resultado.next()){
           String productos = resultado.getString("descripcion");
           ComboBoxArticulo.addItem(productos); 
        }
        
    }//Fin del metodo ObtenerProveedores

    
    
    private void LogicaBotones() {
        
           BotonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String articulo = (String) ComboBoxArticulo.getSelectedItem();
                String PrecioPza = txtPrecioPza.getText();
                int cantidad = (int) spinnerCantidad.getValue();
                double pza = Double.parseDouble(PrecioPza);
                double total = pza * cantidad;
                
                
                if(txtPrecioPza.equals("")) {
                JOptionPane.showMessageDialog(null,"Ingrese todos los datos", "ERROR", HEIGHT, icono);
            } else {
                if(cantidad == 0){
                    JOptionPane.showMessageDialog(null,"Ingrese la cantidad", "ERROR", HEIGHT, icono);
                } else {
                   if(modelo == null) {
                        modelo = new DefaultTableModel();
                        modelo.addColumn("ARTICULO");
                        modelo.addColumn("SUBTOTAL");
                        modelo.addColumn("CANTIDAD");
                        modelo.addColumn("TOTAL");
                        TablaNuevaCompra.setModel(modelo);
                        DiseñoTabla();
                    }
                    modelo.addRow(new Object[]{articulo,PrecioPza,cantidad,total});
                    System.out.println(articulo+ PrecioPza+ cantidad);
                    Limpiar();
                }
                }
 
            }
        });
           
        BotonEliminar.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                     
                if(TablaNuevaCompra.getSelectedRow() > -1){      
                   DefaultTableModel model = (DefaultTableModel) TablaNuevaCompra.getModel();
                 
                                  
                     for(int x : TablaNuevaCompra.getSelectedRows()){     
                        try{
                                int option = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION);

                                 if (option == JOptionPane.YES_OPTION) {
                                     model.removeRow(x);
                                 }

                            }catch(Exception error){
                                System.out.println("");
                            }//Fin del catch   
                        
                    }//Fin del for
                             
                            
                }else{
                         JOptionPane.showMessageDialog(null,
                "<html><body><h3 style='color:red;'>Seleccione un Producto</h3>",
                "Error-Eliminar",
                JOptionPane.ERROR_MESSAGE); 
                    } 
               }
        });
        
        BotonFinalizar.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                 
                   int rowCount = modelo.getRowCount();
                   
                try{
                      PreparedStatement stmt = co.prepareStatement("INSERT INTO compras(fecha,proveedor,articulos,total,vendedor) VALUES(?,?,?,?,?)");
                      
                       LocalDate now = LocalDate.now();   
                        int dia = now.getDayOfMonth();
                        int year = now.getYear();
                        int month = now.getMonthValue();
                        String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
                        String fecha = dia+"/"+meses[month -1]+"/"+year;
                        System.out.println(fecha);
                        String proveedor =  (String) ComboBoxProveedores.getSelectedItem();
                        String vendedor = "Armando";
                
                   for (int i = 0; i < rowCount; i++) {
    //                int idCompra = (int) modelo.getValueAt(i, 0); // Suponiendo que la primera columna es la ID de compra
                    String articulo = (String) modelo.getValueAt(i, 0); // Suponiendo que la segunda columna es el artículo
                    String precioUnitario = (String) modelo.getValueAt(i, 1); // Suponiendo que la tercera columna es el precio unitario        
                    double total = (double) modelo.getValueAt(i, 3);
                    
                    stmt.setString(1, fecha);
                    stmt.setString(2,proveedor);
                    stmt.setString(3,articulo);
                    stmt.setDouble(4,total);
                    stmt.setString(5,vendedor);
        
                    stmt.executeUpdate();

                 
                    System.out.println(articulo  + precioUnitario   +  total + vendedor);
             
                    }//fin del for
                   c.CerrarConexion();
                }catch(Exception ex){
                    System.out.println(ex);
                
                }//fin del catch
                 
                   
                   
                   
                   
                }
        });
    
    }//Fin del metodo LogicaBotones
    

    private void Limpiar(){
    txtPrecioPza.setText("");
    spinnerCantidad.setValue(0);
    
    }//Fin del metodo Limpiar
    
    
   

    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Label_title = new javax.swing.JLabel();
        ComboBoxProveedores = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaNuevaCompra = new javax.swing.JTable();
        BotonAgregar = new javax.swing.JButton();
        BotonFinalizar = new javax.swing.JButton();
        ComboBoxArticulo = new javax.swing.JComboBox<>();
        txtPrecioPza = new javax.swing.JTextField();
        spinnerCantidad = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        BotonEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelPrecioPza = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(35, 35, 35));

        Label_title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Label_title.setForeground(new java.awt.Color(255, 255, 255));
        Label_title.setText("Nueva Compra");

        ComboBoxProveedores.setBackground(new java.awt.Color(35, 35, 35));
        ComboBoxProveedores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ComboBoxProveedores.setForeground(new java.awt.Color(255, 255, 255));

        TablaNuevaCompra.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TablaNuevaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ARTICULO", "SUBTOTAL", "TOTAL", "CANTIDAD"
            }
        ));
        TablaNuevaCompra.setRowHeight(30);
        jScrollPane1.setViewportView(TablaNuevaCompra);

        BotonAgregar.setBackground(new java.awt.Color(35, 35, 35));
        BotonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nuevo.png"))); // NOI18N
        BotonAgregar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        BotonFinalizar.setBackground(new java.awt.Color(35, 35, 35));
        BotonFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/finalizarCompra.png"))); // NOI18N
        BotonFinalizar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Finalizar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        ComboBoxArticulo.setBackground(new java.awt.Color(35, 35, 35));
        ComboBoxArticulo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ComboBoxArticulo.setForeground(new java.awt.Color(255, 255, 255));

        txtPrecioPza.setBackground(new java.awt.Color(35, 35, 35));
        txtPrecioPza.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPrecioPza.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecioPza.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioPza.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, null));

        spinnerCantidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        spinnerCantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, null));

        jSeparator2.setBackground(new java.awt.Color(35, 35, 35));
        jSeparator2.setForeground(new java.awt.Color(35, 35, 35));

        BotonEliminar.setBackground(new java.awt.Color(35, 35, 35));
        BotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar2.png"))); // NOI18N
        BotonEliminar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eliminar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cantidad");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Proveedores");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Articulo");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("$");

        labelPrecioPza.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelPrecioPza.setForeground(new java.awt.Color(255, 255, 255));
        labelPrecioPza.setText("Precio pza");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Label_title))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(114, 114, 114)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(ComboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(124, 124, 124)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(ComboBoxArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(labelPrecioPza)
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel4))
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPrecioPza, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BotonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(Label_title)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(ComboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addGap(0, 0, 0)
                        .addComponent(ComboBoxArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioPza, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPrecioPza))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonFinalizar;
    private javax.swing.JComboBox<String> ComboBoxArticulo;
    private javax.swing.JComboBox<String> ComboBoxProveedores;
    private javax.swing.JLabel Label_title;
    private javax.swing.JTable TablaNuevaCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelPrecioPza;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JTextField txtPrecioPza;
    // End of variables declaration//GEN-END:variables
}
