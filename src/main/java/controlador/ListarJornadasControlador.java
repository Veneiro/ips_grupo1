package controlador;

import vista.ListaJornadasVista;

public class ListarJornadasControlador {

    private ListaJornadasVista listaJornadasVista;

    public ListarJornadasControlador() {
	this.listaJornadasVista = new ListaJornadasVista();
    }

    public void inicializar() {
	listaJornadasVista.setVisible(true);
    }

}
