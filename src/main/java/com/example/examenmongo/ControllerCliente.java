package com.example.examenmongo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerCliente {
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnMostrar;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrimerApellido;

    @FXML
    private TextField txtSegundoApellido;

    ConexionNube conexionNube = new ConexionNube();
    public void guardar(){
        Cliente clt=new Cliente();
        clt.setIdCliente(Integer.parseInt(txtId.getText()));
        clt.setNombre(txtNombre.getText());
        clt.setPrimerApellido(txtPrimerApellido.getText());
        clt.setSegundoApellido(txtSegundoApellido.getText());
        clt.setCorreo(txtCorreo.getText());

        if (conexionNube.insertar(clt)==true){
            lblResultado.setText("Se guardaron los datos.");
            clean();
        }else{
            lblResultado.setText("Error al guardar los datos");
        }
    }

    public void actualizar(){
        Cliente clienteAnterior = new Cliente();
        Cliente clienteNuevo = new Cliente();
        if (txtId!=null) {
            clienteAnterior.setIdCliente(Integer.parseInt(txtId.getText()));
            clienteNuevo.setIdCliente(Integer.parseInt(txtId.getText()));
            clienteNuevo.setDomicilio(txtDomicilio.getText());
            clienteNuevo.setNombre(txtNombre.getText());
            clienteNuevo.setPrimerApellido(txtPrimerApellido.getText());
            clienteNuevo.setSegundoApellido(txtSegundoApellido.getText());
            clienteNuevo.setCorreo(txtCorreo.getText());
            if(conexionNube.actualizar(clienteAnterior,clienteNuevo)==true){
                lblResultado.setText("Se actualizaron con exito");
                clean();
            } else{
                lblResultado.setText("Error al actualizar los datos");
            }
        } else{
            txtId.setText("Asigna un valor al id");
        }
    }

    public void eliminar(){
        if(txtId!=null) {
            int idCliente = Integer.parseInt(txtId.getText());
            if (conexionNube.eliminar(idCliente) == true) {
                lblResultado.setText("Registro eliminado");
                clean();
            } else {
                lblResultado.setText("Error al eliminar");
            }
        } else{
            txtId.setText("Valor nulo");
        }
    }

    public void mostrar(){
        if(conexionNube.mostrar()==true){
            lblResultado.setText("Mostrar datos");
            clean();
        } else{
            lblResultado.setText("Error al mostrar los datos");
            clean();
        }
    }

    public void clean(){
        txtId.setText("");
        txtDomicilio.setText("");
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtCorreo.setText("");
    }

}
