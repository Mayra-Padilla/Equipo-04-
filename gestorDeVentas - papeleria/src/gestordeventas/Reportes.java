/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeventas;

import gestordeventas.Conexion.conexion;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import PDF.GenerarPDF;
import java.awt.event.ActionListener;
/**
 *
 * @author Mayra
 */
public class Reportes extends javax.swing.JFrame {

    conexion conecion = new conexion();
    Connection cin = conexion.getConexion();
    PreparedStatement ps;
    ResultSet rs;
    String opcion;

    /**
     * Creates new form Reportes
     */
    public Reportes() {
        initComponents();
        setTitle(".:: Reportes ::.");
        setDefaultCloseOperation(Reportes.DISPOSE_ON_CLOSE);
//        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        LlenarTabla();

        txtClaveProducto.setEnabled(false);
        txtNombre.setEnabled(false);
        txtExisRangoInicial.setEnabled(false);
        txtExisRangoFinal.setEnabled(false);
        txtPrecioInicial.setEnabled(false);
        txtPrecioFinal.setEnabled(false);
        cbxFechaInicio.setEnabled(false);
        cbxFechaFinal.setEnabled(false);
        txtDescripcion.setEnabled(false);

        cbxSeleccione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcion = String.valueOf(cbxSeleccione.getSelectedItem());
                switch (opcion) {
                    case " ":
//                        txtClaveProducto.setEnabled(false);
//                        txtNombre.setEnabled(false);
//                        txtExisRangoInicial.setEnabled(false);
//                        txtExisRangoFinal.setEnabled(false);
//                        txtPrecioInicial.setEnabled(false);
//                        txtPrecioFinal.setEnabled(false);
//                        cbxFechaInicio.setEnabled(false);
//                        cbxFechaFinal.setEnabled(false);
//                        txtDescripcion.setEnabled(false);
                        LlenarTabla();
                        break;
                    case "Clave producto":
                        txtClaveProducto.setEnabled(true);
                        txtNombre.setEnabled(false);
                        txtExisRangoInicial.setEnabled(false);
                        txtExisRangoFinal.setEnabled(false);
                        txtPrecioInicial.setEnabled(false);
                        txtPrecioFinal.setEnabled(false);
                        cbxFechaInicio.setEnabled(false);
                        cbxFechaFinal.setEnabled(false);
                        txtDescripcion.setEnabled(false);
                        break;
                    case "Nombre":
                        txtClaveProducto.setEnabled(false);
                        txtNombre.setEnabled(true);
                        txtExisRangoInicial.setEnabled(false);
                        txtExisRangoFinal.setEnabled(false);
                        txtPrecioInicial.setEnabled(false);
                        txtPrecioFinal.setEnabled(false);
                        cbxFechaInicio.setEnabled(false);
                        cbxFechaFinal.setEnabled(false);
                        txtDescripcion.setEnabled(false);
                        break;
                    case "Existencias":
                        txtClaveProducto.setEnabled(false);
                        txtNombre.setEnabled(false);
                        txtExisRangoInicial.setEnabled(true);
                        txtExisRangoFinal.setEnabled(true);
                        txtPrecioInicial.setEnabled(false);
                        txtPrecioFinal.setEnabled(false);
                        cbxFechaInicio.setEnabled(false);
                        cbxFechaFinal.setEnabled(false);
                        txtDescripcion.setEnabled(false);
                        break;
                    case "Precio unitario":
                        txtClaveProducto.setEnabled(false);
                        txtNombre.setEnabled(false);
                        txtExisRangoInicial.setEnabled(false);
                        txtExisRangoFinal.setEnabled(false);
                        txtPrecioInicial.setEnabled(true);
                        txtPrecioFinal.setEnabled(true);
                        cbxFechaInicio.setEnabled(false);
                        cbxFechaFinal.setEnabled(false);
                        txtDescripcion.setEnabled(false);
                        break;
                    case "Fecha":
                        txtClaveProducto.setEnabled(false);
                        txtNombre.setEnabled(false);
                        txtExisRangoInicial.setEnabled(false);
                        txtExisRangoFinal.setEnabled(false);
                        txtPrecioInicial.setEnabled(false);
                        txtPrecioFinal.setEnabled(false);
                        cbxFechaInicio.setEnabled(true);
                        cbxFechaFinal.setEnabled(true);
                        txtDescripcion.setEnabled(false);
                        break;
                    case "Descripción":
                        txtClaveProducto.setEnabled(false);
                        txtNombre.setEnabled(false);
                        txtExisRangoInicial.setEnabled(false);
                        txtExisRangoFinal.setEnabled(false);
                        txtPrecioInicial.setEnabled(false);
                        txtPrecioFinal.setEnabled(false);
                        cbxFechaInicio.setEnabled(false);
                        cbxFechaFinal.setEnabled(false);
                        txtDescripcion.setEnabled(true);
                        break;
                }
            }
        });

    }

    public void LlenarTabla() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTable1.setModel(modelo);
            String consulta = "SELECT Cod_Producto, Nombre_Producto, Existencias, Precio_Venta, Descripcion, Fecha_ingreso FROM Materiales";
            ps = cin.prepareStatement(consulta);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Clave Producto");
            modelo.addColumn("Nombre");
            modelo.addColumn("Existencias");
            modelo.addColumn("Precio Unitario");
            modelo.addColumn("Descripción");
            modelo.addColumn("Fecha");

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }

                modelo.addRow(filas);
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error (Revise sus datos)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ClaveProducto() {
        String clave = txtClaveProducto.getText();

        if (isNumeric(clave)) {
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                Object filas[] = new Object[1];

                String consulta = "SELECT Cod_Producto, Nombre_Producto, Existencias, Precio_Venta, Descripcion, Fecha_ingreso FROM Materiales WHERE Cod_Producto = " + clave;
                System.out.println(consulta);
                ps = cin.prepareStatement(consulta);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Clave Producto");
                modelo.addColumn("Nombre");
                modelo.addColumn("Existencias");
                modelo.addColumn("Precio Unitario");
                modelo.addColumn("Descripción");
                modelo.addColumn("Fecha");

                while (rs.next()) {
                    filas = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }

                if (filas[0] == null) {
                    JOptionPane.showMessageDialog(this, "El código del producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    txtClaveProducto.requestFocus();
                }
                txtClaveProducto.setText(null);
                txtClaveProducto.requestFocus();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error (Revise sus datos)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El código producto es obligatorio (Solo números)", "Error", JOptionPane.ERROR_MESSAGE);
            txtClaveProducto.setText(null);
            txtClaveProducto.requestFocus();
        }
        txtClaveProducto.setText("");
    }

    public void NombreProducto() {
        String nombre = txtNombre.getText();
        nombre = nombre.trim();

        if (nombre.length() > 51 || nombre.length() == 0) {
            JOptionPane.showMessageDialog(this, "El Nombre es obligatorio (Maximo 50 caracteres)", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
        } else {
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                Object filas[] = new Object[1];

                String consulta = "SELECT Cod_Producto, Nombre_Producto, Existencias, Precio_Venta, Descripcion, Fecha_ingreso FROM Materiales WHERE Nombre_Producto LIKE '%" + nombre + "%'";
                ps = cin.prepareStatement(consulta);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Clave Producto");
                modelo.addColumn("Nombre");
                modelo.addColumn("Existencias");
                modelo.addColumn("Precio Unitario");
                modelo.addColumn("Descripción");
                modelo.addColumn("Fecha");

                while (rs.next()) {
                    filas = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }

                if (filas[0] == null) {
                    JOptionPane.showMessageDialog(this, "El Nombre del producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNombre.requestFocus();
                }
                txtNombre.setText(null);
                txtNombre.requestFocus();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error (Revise sus datos)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        txtNombre.setText("");
    }

    public void RangoExistencias() {
        String inicial = txtExisRangoInicial.getText();
        String finall = txtExisRangoFinal.getText();

        if (isNumeric(inicial) && isNumeric(finall)) {
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                Object filas[] = new Object[1];

                String consulta = "SELECT Cod_Producto, Nombre_Producto, Existencias, Precio_Venta, Descripcion, Fecha_ingreso FROM Materiales WHERE Existencias BETWEEN " + inicial + " AND " + finall;
                System.out.println(consulta);
                ps = cin.prepareStatement(consulta);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Clave Producto");
                modelo.addColumn("Nombre");
                modelo.addColumn("Existencias");
                modelo.addColumn("Precio Unitario");
                modelo.addColumn("Descripción");
                modelo.addColumn("Fecha");

                while (rs.next()) {
                    filas = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }

                if (filas[0] == null) {
                    JOptionPane.showMessageDialog(this, "El código del producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    txtClaveProducto.requestFocus();
                }
                txtClaveProducto.setText(null);
                txtClaveProducto.requestFocus();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error (Revise sus datos)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El código producto es obligatorio (Solo números)", "Error", JOptionPane.ERROR_MESSAGE);
            txtClaveProducto.setText(null);
            txtClaveProducto.requestFocus();
        }
        txtExisRangoInicial.setText("");
        txtExisRangoFinal.setText("");
    }

    public void RangoPrecio() {
        String inicial = txtPrecioInicial.getText();
        String finall = txtPrecioFinal.getText();

        if (isNumeric(inicial) && isNumeric(finall)) {
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                Object filas[] = new Object[1];

                String consulta = "SELECT Cod_Producto, Nombre_Producto, Existencias, Precio_Venta, Descripcion, Fecha_ingreso FROM Materiales WHERE Precio_Venta BETWEEN " + inicial + " AND " + finall;
                ps = cin.prepareStatement(consulta);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Clave Producto");
                modelo.addColumn("Nombre");
                modelo.addColumn("Existencias");
                modelo.addColumn("Precio Unitario");
                modelo.addColumn("Descripción");
                modelo.addColumn("Fecha");

                while (rs.next()) {
                    filas = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }

                if (filas[0] == null) {
                    JOptionPane.showMessageDialog(this, "El código del producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    txtClaveProducto.requestFocus();
                }
                txtClaveProducto.setText(null);
                txtClaveProducto.requestFocus();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error (Revise sus datos)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El código producto es obligatorio (Solo números)", "Error", JOptionPane.ERROR_MESSAGE);
            txtClaveProducto.setText(null);
            txtClaveProducto.requestFocus();
        }
        txtPrecioInicial.setText("");
        txtPrecioFinal.setText("");
    }

    public void Descripcion() {
        String descripcion = txtDescripcion.getText();
        descripcion = descripcion.trim();

        if (descripcion.length() > 51 || descripcion.length() == 0) {
            JOptionPane.showMessageDialog(this, "El Nombre es obligatorio (Maximo 50 caracteres)", "Error", JOptionPane.ERROR_MESSAGE);
            txtDescripcion.requestFocus();
        } else {
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                Object filas[] = new Object[1];

                String consulta = "SELECT Cod_Producto, Nombre_Producto, Existencias, Precio_Venta, Descripcion, Fecha_ingreso FROM Materiales WHERE Descripcion LIKE '%" + descripcion + "%'";
                ps = cin.prepareStatement(consulta);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Clave Producto");
                modelo.addColumn("Nombre");
                modelo.addColumn("Existencias");
                modelo.addColumn("Precio Unitario");
                modelo.addColumn("Descripción");
                modelo.addColumn("Fecha");

                while (rs.next()) {
                    filas = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }

                if (filas[0] == null) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados", "Error", JOptionPane.ERROR_MESSAGE);
                    txtDescripcion.requestFocus();
                }
                txtDescripcion.setText(null);
                txtDescripcion.requestFocus();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error (Revise sus datos)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        txtDescripcion.setText("");
    }

    public void Fecha() {
        String formato = "dd/MM/yyyy";
        Date fechaInicial = cbxFechaInicio.getDate();
        Date fechaFinal = cbxFechaFinal.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTable1.setModel(modelo);
            Object filas[] = new Object[1];

            String consulta = "SELECT Cod_Producto, Nombre_Producto, Existencias, Precio_Venta, Descripcion, Fecha_ingreso FROM Materiales WHERE Fecha_ingreso BETWEEN '" + sdf.format(fechaInicial) + "' AND '" + sdf.format(fechaFinal) + "'";
            System.out.println(consulta);
            ps = cin.prepareStatement(consulta);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Clave Producto");
            modelo.addColumn("Nombre");
            modelo.addColumn("Existencias");
            modelo.addColumn("Precio Unitario");
            modelo.addColumn("Descripción");
            modelo.addColumn("Fecha");

            while (rs.next()) {
                filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

            if (filas[0] == null) {
                JOptionPane.showMessageDialog(this, "El código del producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                txtClaveProducto.requestFocus();
            }
            txtClaveProducto.setText(null);
            txtClaveProducto.requestFocus();
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error (Revise sus datos)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isNumeric(String cad) {
        try {
            Integer.parseInt(cad);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
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

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        etqAgregarProducto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbxSeleccione = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtClaveProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtExisRangoInicial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtExisRangoFinal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioInicial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPrecioFinal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnFiltrar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbxFechaInicio = new org.freixas.jcalendar.JCalendarCombo();
        jLabel11 = new javax.swing.JLabel();
        cbxFechaFinal = new org.freixas.jcalendar.JCalendarCombo();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnGenerarReporte = new javax.swing.JButton();
        btnRestablecer = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(99, 145, 239));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(40, 84, 173));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(3, 37, 105));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        etqAgregarProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        etqAgregarProducto.setText("Reportes");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/papeleria2.jpg"))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Seleccione:");

        cbxSeleccione.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Clave producto", "Nombre", "Existencias", "Precio unitario", "Fecha", "Descripción" }));

        jLabel3.setText("Clave producto:");

        jLabel5.setText("Existencias:");

        jLabel6.setText("a");

        jLabel7.setText("Precio:");

        jLabel8.setText("a");

        jLabel9.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.setMaximumSize(new java.awt.Dimension(111, 23));
        btnFiltrar.setMinimumSize(new java.awt.Dimension(111, 23));
        btnFiltrar.setPreferredSize(new java.awt.Dimension(111, 23));
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jLabel10.setText("Fecha:");

        jLabel11.setText("a");

        jLabel4.setText("Nombre:");

        btnGenerarReporte.setText("Generar reporte");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        btnRestablecer.setText("Restablecer");
        btnRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.setMaximumSize(new java.awt.Dimension(111, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(111, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(324, 324, 324)
                                .addComponent(jLabel11)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(328, 328, 328)
                                .addComponent(cbxFechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(etqAgregarProducto)
                                .addGap(120, 120, 120)
                                .addComponent(jLabel2)
                                .addGap(87, 87, 87))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(cbxSeleccione, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtClaveProducto))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(30, 30, 30)
                                .addComponent(txtExisRangoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtExisRangoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioInicial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(12, 12, 12)
                                .addComponent(txtPrecioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(btnRestablecer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(etqAgregarProducto)))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cbxSeleccione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtPrecioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtExisRangoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtExisRangoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(cbxFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRestablecer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        switch (opcion) {
            case " ":
                LlenarTabla();
                break;
            case "Clave producto":
                ClaveProducto();
                break;
            case "Nombre":
                NombreProducto();
                break;
            case "Existencias":
                RangoExistencias();
                break;
            case "Precio unitario":
                RangoPrecio();
                break;
            case "Fecha":
                Fecha();
                break;
            case "Descripción":
                Descripcion();
                break;
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnRestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerActionPerformed
        // TODO add your handling code here:
        LlenarTabla();
    }//GEN-LAST:event_btnRestablecerActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        try {
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            String fInicial = f.format(cbxFechaInicio.getDate());
            String fFinal = f.format(cbxFechaFinal.getDate());

            String ruta = "PDF GENERADOS/Reporte.pdf";
            GenerarPDF g = new GenerarPDF();
            g.createPDF(ruta, jTable1, fInicial, fFinal);
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu m = new Menu();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnRestablecer;
    private org.freixas.jcalendar.JCalendarCombo cbxFechaFinal;
    private org.freixas.jcalendar.JCalendarCombo cbxFechaInicio;
    private javax.swing.JComboBox<String> cbxSeleccione;
    private javax.swing.JLabel etqAgregarProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtClaveProducto;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtExisRangoFinal;
    private javax.swing.JTextField txtExisRangoInicial;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioFinal;
    private javax.swing.JTextField txtPrecioInicial;
    // End of variables declaration//GEN-END:variables
}
