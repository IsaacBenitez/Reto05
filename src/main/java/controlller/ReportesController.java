package controlller;

import model.dao.InformesDAO;

import java.sql.ResultSet;

public class ReportesController {
    // TODO Implementar la clase
    public static ResultSet peticion(int pedido){
        ResultSet resultado = InformesDAO.listar(pedido);

        return resultado;
    }

}





