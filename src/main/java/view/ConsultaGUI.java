package view;

import controlller.ReportesController;
import model.dao.InformesDAO;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConsultaGUI{
    private JPanel panel1;
    private JButton btnConsulta;
    private JComboBox cbConsultas;
    private JTable tablaInforme;
    private JButton btnLimpiar;

    public ConsultaGUI() {
        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                ResultSet resultado = ReportesController.peticion(cbConsultas.getSelectedIndex());
                try {
                    ResultSetMetaData infoResultado = resultado.getMetaData();
                    String[] titles = new String[infoResultado.getColumnCount()];
                    for(int i=0;i<infoResultado.getColumnCount();i++){
                        titles[i] = infoResultado.getColumnName(i+1);
                    }
                    modelo.setColumnIdentifiers(titles);
                    Object[] row = new Object[infoResultado.getColumnCount()];
                    while(resultado.next()){
                        for(int i=0;i<infoResultado.getColumnCount();i++){
                            row[i] = resultado.getObject(i+1);
                        }
                        modelo.addRow(row);
                    }
                    tablaInforme.setModel(modelo);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablaInforme.setModel(new DefaultTableModel());
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Informes Base de Datos");
        frame.setDefaultCloseOperation(3);
        frame.setSize(800,800);

        String query1="SELECT ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia\n" +
                "FROM Lider\n" +
                "ORDER BY Ciudad_Residencia;";
        String query2 = "SELECT ID_Proyecto, Constructora, Numero_Habitaciones, Ciudad\n" +
                "FROM Proyecto\n" +
                "WHERE Ciudad IN ('Santa Marta','Cartagena','Barranquilla')\n" +
                "AND Clasificacion = 'Casa Campestre'";
        String query3 = "SELECT ID_Compra, Constructora, Banco_Vinculado\n" +
                "FROM Compra c\n" +
                "INNER JOIN Proyecto p ON c.ID_Proyecto = p.ID_Proyecto\n" +
                "WHERE Ciudad='Salento' AND Proveedor='Homecenter'";
        InformesDAO.querys.add(query1);
        InformesDAO.querys.add(query2);
        InformesDAO.querys.add(query3);
        ConsultaGUI panel = new ConsultaGUI();
        frame.setContentPane(panel.getPanel1());
        frame.getContentPane().setBackground(Color.gray);

        ImageIcon logo = new ImageIcon("logoEmpresa.png");
        frame.setIconImage(logo.getImage());
        frame.setVisible(true);


    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getBtnConsulta() {
        return btnConsulta;
    }

    public void setBtnConsulta(JButton btnConsulta) {
        this.btnConsulta = btnConsulta;
    }

    public JComboBox getCbConsultas() {
        return cbConsultas;
    }

    public void setCbConsultas(JComboBox cbConsultas) {
        this.cbConsultas = cbConsultas;
    }


}


