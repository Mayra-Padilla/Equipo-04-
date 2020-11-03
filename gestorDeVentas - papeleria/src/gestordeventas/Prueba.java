/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeventas;
import java.awt.event.*;
import gestordeventas.Conexion.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Prueba extends javax.swing.JFrame {
    Connection cin = conexion.getConexion();
    PreparedStatement ps;
    DefaultTableModel tabla;
    String cuerpov[] = new String[6];
    String columnaVenta[] = {"codigoProducto","nombreProducto","descripcion","Precio individual","Cantidad","Precio"};
    DefaultTableModel tablav = new DefaultTableModel(null, columnaVenta);
    /**
     * Creates new form Prueba
     */
    
    public Prueba() {
        initComponents();
        tablaProducto();
        tablaUsuario();
        tablaDiarioDeVenta();
        //MAXIMIZA LA VENTANA 
        //this.setExtendedState(MAXIMIZED_BOTH);
        // buscamos el nombre del usuario activo
        try{
            
            String consulta = "SELECT usuario.nombre FROM usuario INNER JOIN loginUsuario \n" +
                            "ON usuario.claveUsuario = loginUsuario.claveUsuario\n" +
                            "INNER JOIN login ON login.claveLogin = loginUsuario.claveLogin\n" +
                            "WHERE login.activo = 1";
            Statement st = cin.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            rs.next();
            etqBienvenido.setText(" Bienvenido  "+ rs.getString("nombre"));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }         
    }
        //llenamos la tabla producto
 void tablaProducto(){
        try{
            String consulta = "SELECT codigoProducto,existencias,descripcion,nombreProducto,precioVenta FROM Producto";
            String col[] = {"codigoProducto","existencias","descripcion","nombre","precio"};
            //sera el tamaño de las columnas
            String cuerpo[] = new String[5];
            //declaramos el  modelo para llenar nuestra tabla
            tabla = new DefaultTableModel(null, col);
            Statement st = cin.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next()){
                    //iremos llenando las filas de nuestra tabla
                    cuerpo [0] = rs.getString("codigoProducto");
                    cuerpo [1] = rs.getString("existencias");
                    cuerpo [2] = rs.getString("descripcion");
                    cuerpo [3] = rs.getString("nombreProducto");
                    cuerpo [4] = rs.getString("precioVenta");              
                    tabla.addRow(cuerpo);
            }
            //pasamos los datos a la tabla producto
            tbProducto.setModel(tabla);
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error en : " + e);
        }
    }
 
         //llenamos la tabla producto
 void tablaUsuario(){
        try{
            String consulta = "SELECT claveUsuario, nombre, apellidoPaterno, apellidoMaterno, calle,colonia, noExterior,codigoPostal, ciudad, telefonoCelular, telefonoCasa, email FROM usuario";
            String col[] = {"claveUsuario", "nombre", "apellidoPaterno", "apellidoMaterno", "calle", "colonia", "noExterior", "codigoPostal", "ciudad", "telefonoCelular", "telefonoCasa", "email"};
            //sera el tamaño de las columnas
            String cuerpo[] = new String[12];
            //declaramos el  modelo para llenar nuestra tabla
            tabla = new DefaultTableModel(null, col);
            Statement st = cin.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next()){
                    //iremos llenando las filas de nuestra tabla
                    cuerpo [0] = rs.getString("claveUsuario");
                    cuerpo [1] = rs.getString("nombre");
                    cuerpo [2] = rs.getString("apellidoPaterno");
                    cuerpo [3] = rs.getString("apellidoMaterno");
                    cuerpo [4] = rs.getString("calle");
                    cuerpo [5] = rs.getString("colonia");   
                    cuerpo [6] = rs.getString("noExterior");
                    cuerpo [7] = rs.getString("codigoPostal");
                    cuerpo [8] = rs.getString("ciudad");
                    cuerpo [9] = rs.getString("telefonoCelular");
                    cuerpo [10] = rs.getString("telefonoCasa");
                    cuerpo [11] = rs.getString("email");
                    
                    tabla.addRow(cuerpo);
            }
            //pasamos los datos a la tabla producto
            tablaUsuarios.setModel(tabla);
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error en : " + e);
        }
    }

    void tablaDiarioDeVenta(){
        String combo = (String)cbxFiltroDiariosVenta.getSelectedItem();
        
        try{
            String consulta = "SELECT venta.folioVenta, Producto.codigoProducto, Producto.nombreProducto, " + 
                                "ventaProducto.cantidadVendida, usuarioVenta.fecha, usuarioVenta.hora \n" +
                                "FROM venta INNER JOIN usuarioVenta ON usuarioVenta.folioVenta = venta.folioVenta \n" +
                                "INNER JOIN ventaProducto ON venta.folioVenta = ventaProducto.folioVenta\n" +
                                "INNER JOIN Producto ON ventaProducto.codigoProducto = Producto.codigoProducto";
            
            String col[] = {"folioVenta","codigoProducto","nombreProducto","canridadVendida", "fecha","hora"};
            String cuerpo[] = new String[6];
            
            tabla = new DefaultTableModel(null, col);
            Statement st = cin.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next()){
                    //iremos llenando las filas de nuestra tabla
                    cuerpo [0] = rs.getString("folioVenta");
                    cuerpo [1] = rs.getString("codigoProducto");
                    cuerpo [2] = rs.getString("nombreProducto");
                    cuerpo [3] = rs.getString("cantidadVendida");
                    cuerpo [4] = rs.getString("fecha");
                    cuerpo [5] = rs.getString("hora");
                     tabla.addRow(cuerpo);
            }
            //pasamos los datos a la tabla producto
            tablaDiariosDeVenta.setModel(tabla);
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error en : " + e);
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

        panelPrincipal = new javax.swing.JTabbedPane();
        panelVentas = new javax.swing.JPanel();
        etqVenta = new javax.swing.JLabel();
        txtCodigoProductoVenta = new javax.swing.JTextField();
        etqCodigoProducto = new javax.swing.JLabel();
        etqCantidad = new javax.swing.JLabel();
        txtCantidadVenta = new javax.swing.JTextField();
        btnAgregarVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
        btnCobrar = new javax.swing.JButton();
        etqBienvenido = new javax.swing.JLabel();
        panelUsuarios = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btnAgregarUsuario = new javax.swing.JButton();
        etqUsuarios = new javax.swing.JLabel();
        panelProductos = new javax.swing.JPanel();
        etqProducto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProducto = new javax.swing.JTable();
        btnAgregarProducto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        etqBuscarPor = new javax.swing.JLabel();
        txtTextoaBuscar = new javax.swing.JTextField();
        btnIraBuscar = new javax.swing.JButton();
        cbxBuscarProducto = new javax.swing.JComboBox();
        panelDiariosVenta = new javax.swing.JPanel();
        etqDiariosDeVenta = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaDiariosDeVenta = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbxFiltroDiariosVenta = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        etqGestorDeVentas = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        etqVenta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        etqVenta.setText("Venta");

        etqCodigoProducto.setText(" Codigo Producto");

        etqCantidad.setText("Cantidad");

        txtCantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVentaActionPerformed(evt);
            }
        });

        btnAgregarVenta.setText("Agregar");
        btnAgregarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVentaActionPerformed(evt);
            }
        });

        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaVenta);

        btnCobrar.setText("Cobrar");

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(panelVentasLayout.createSequentialGroup()
                                .addComponent(etqBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(163, 163, 163)
                                .addComponent(etqVenta)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etqCodigoProducto)
                            .addComponent(etqCantidad))
                        .addGap(68, 68, 68)
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(btnAgregarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnAgregarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etqBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etqVenta))
                        .addGap(11, 11, 11)
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etqCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etqCantidad)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        panelPrincipal.addTab("Venta", panelVentas);

        panelUsuarios.setToolTipText("");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaUsuarios);

        btnAgregarUsuario.setText("Agregar");
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });

        etqUsuarios.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        etqUsuarios.setText("Usuarios");

        javax.swing.GroupLayout panelUsuariosLayout = new javax.swing.GroupLayout(panelUsuarios);
        panelUsuarios.setLayout(panelUsuariosLayout);
        panelUsuariosLayout.setHorizontalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuariosLayout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(etqUsuarios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsuariosLayout.createSequentialGroup()
                .addContainerGap(298, Short.MAX_VALUE)
                .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
        );
        panelUsuariosLayout.setVerticalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqUsuarios)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        panelPrincipal.addTab("Usuarios", panelUsuarios);

        etqProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        etqProducto.setText("Productos");

        tbProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbProducto);

        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        jLabel1.setText(" ");

        etqBuscarPor.setText("Buscar por:");

        btnIraBuscar.setText("Actualizar");
        btnIraBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIraBuscarActionPerformed(evt);
            }
        });

        cbxBuscarProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "codigoProducto", "nombreProducto" }));

        javax.swing.GroupLayout panelProductosLayout = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(panelProductosLayout);
        panelProductosLayout.setHorizontalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelProductosLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(panelProductosLayout.createSequentialGroup()
                        .addComponent(etqBuscarPor)
                        .addGap(18, 18, 18)
                        .addComponent(cbxBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTextoaBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(btnIraBuscar)
                        .addGap(29, 29, 29)))
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(etqProducto)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
        );
        panelProductosLayout.setVerticalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(etqProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelProductosLayout.createSequentialGroup()
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etqBuscarPor)
                            .addComponent(txtTextoaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIraBuscar)
                            .addComponent(cbxBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        panelPrincipal.addTab("Productos", panelProductos);

        etqDiariosDeVenta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        etqDiariosDeVenta.setText("Diarios de venta");

        tablaDiariosDeVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaDiariosDeVenta);

        jLabel2.setText("Filtar por:");

        cbxFiltroDiariosVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rango de fecha", "Vendedor", "Producto", "Sucursal" }));

        jButton1.setText("Generar archivo");

        javax.swing.GroupLayout panelDiariosVentaLayout = new javax.swing.GroupLayout(panelDiariosVenta);
        panelDiariosVenta.setLayout(panelDiariosVentaLayout);
        panelDiariosVentaLayout.setHorizontalGroup(
            panelDiariosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiariosVentaLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(etqDiariosDeVenta)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelDiariosVentaLayout.createSequentialGroup()
                .addGroup(panelDiariosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDiariosVentaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(cbxFiltroDiariosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDiariosVentaLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDiariosVentaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(267, 267, 267))
        );
        panelDiariosVentaLayout.setVerticalGroup(
            panelDiariosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiariosVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqDiariosDeVenta)
                .addGap(30, 30, 30)
                .addGroup(panelDiariosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxFiltroDiariosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        panelPrincipal.addTab("Diarios de venta", panelDiariosVenta);

        etqGestorDeVentas.setFont(new java.awt.Font("Poor Richard", 1, 48)); // NOI18N
        etqGestorDeVentas.setText("GESTOR DE VENTAS");

        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(etqGestorDeVentas)
                .addContainerGap(122, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(289, 289, 289))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(etqGestorDeVentas)
                .addGap(69, 69, 69)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(264, Short.MAX_VALUE))
        );

        panelPrincipal.addTab("Gestor de ventas", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentaActionPerformed

    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed
        // TODO add your handling code here:
        AgregarUsuario agregarUsuario = new AgregarUsuario();
        agregarUsuario.setVisible(true);
        
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        // TODO add your handling code here:
        AgregarProducto agregarProducto = new AgregarProducto();
        agregarProducto.setVisible(true);
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnAgregarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVentaActionPerformed
      try {
                //Obtenemos el texto dentro de cada caja de texto
                String codigoProducto = txtCodigoProductoVenta.getText();
                String cantidadEnCompra = txtCantidadVenta.getText();
                int cantidadP = Integer.parseInt(cantidadEnCompra);
                //declaramos la consulta para checar si existe el usuario y el pass
                if (cantidadP > 0) {
                    String consulta = "SELECT codigoProducto, nombreProducto, descripcion, precioVenta FROM Producto "
                                  + "WHERE codigoProducto =" +codigoProducto;

                    Statement st = cin.createStatement();
                    ResultSet rs = st.executeQuery(consulta);
                    while(rs.next()){
                         //iremos llenando las filas de nuestra tabla
                         cuerpov [0] = rs.getString("codigoProducto");
                         cuerpov [1] = rs.getString("nombreProducto");
                         cuerpov [2] = rs.getString("descripcion");
                         cuerpov [3] = rs.getString("precioVenta");
                         cuerpov [4] = cantidadEnCompra;         
                         double precio = Double.parseDouble(rs.getString("precioVenta"));
                         precio*= cantidadP;
                         cuerpov [5] = Double.toString(precio); 

                    }        
                    tablav.addRow(cuerpov);
                    //pasamos los datos a la tabla producto
                    tablaVenta.setModel(tablav);
               }
                         
            }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Revise los campos","Error",JOptionPane.INFORMATION_MESSAGE);
        }
       txtCantidadVenta.setText("");
       txtCodigoProductoVenta.setText("");
    }//GEN-LAST:event_btnAgregarVentaActionPerformed

    private void btnIraBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIraBuscarActionPerformed
        // TODO add your handling code here:
        //String consulta = "SELECT codigoProducto,existencias,descripcion,nombreProducto,precioVenta FROM Producto";
        String valor = txtTextoaBuscar.getText();
        String combo = (String)cbxBuscarProducto.getSelectedItem();
        String consulta = "SELECT codigoProducto,existencias,descripcion,nombreProducto,precioVenta FROM Producto WHERE " + combo + " LIKE '%" + valor + "%'";
        String col[] = {"codigoProducto","existencias","descripcion","nombre","precio"};


        try{
            //sera el tamaño de las columnas
            String cuerpo[] = new String[5];
            //declaramos el  modelo para llenar nuestra tabla
            tabla = new DefaultTableModel(null, col);
            Statement st = cin.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next()){
                    //iremos llenando las filas de nuestra tabla
                    cuerpo [0] = rs.getString("codigoProducto");
                    cuerpo [1] = rs.getString("existencias");
                    cuerpo [2] = rs.getString("descripcion");
                    cuerpo [3] = rs.getString("nombreProducto");
                    cuerpo [4] = rs.getString("precioVenta");              
                    tabla.addRow(cuerpo);
            }
            //pasamos los datos a la tabla producto
            tbProducto.setModel(tabla);
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error en : " + e);
        }
    }//GEN-LAST:event_btnIraBuscarActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
         String sesion = "UPDATE login SET activo = 0 WHERE activo = 1";
         Login log = new Login();
         log.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prueba().setVisible(true);
            }
        });
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnAgregarVenta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnIraBuscar;
    private javax.swing.JComboBox cbxBuscarProducto;
    private javax.swing.JComboBox cbxFiltroDiariosVenta;
    private javax.swing.JLabel etqBienvenido;
    private javax.swing.JLabel etqBuscarPor;
    private javax.swing.JLabel etqCantidad;
    private javax.swing.JLabel etqCodigoProducto;
    private javax.swing.JLabel etqDiariosDeVenta;
    private javax.swing.JLabel etqGestorDeVentas;
    private javax.swing.JLabel etqProducto;
    private javax.swing.JLabel etqUsuarios;
    private javax.swing.JLabel etqVenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelDiariosVenta;
    private javax.swing.JTabbedPane panelPrincipal;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JTable tablaDiariosDeVenta;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTable tablaVenta;
    private javax.swing.JTable tbProducto;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoProductoVenta;
    private javax.swing.JTextField txtTextoaBuscar;
    // End of variables declaration//GEN-END:variables
}
