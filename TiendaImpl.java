/**
 * Clase "TiendaImpl", que contiene los metodos ya construidos y detallados.
 * @author Amando Antoñano
 *
 */

import java.util.*;
import java.rmi.*;
import java.rmi.server.*;


class TiendaImpl extends UnicastRemoteObject implements Tienda,Database
{
    private List<Producto> inventario;
    private Producto producto;
    private float cashFlow;


    TiendaImpl() throws RemoteException {
	inventario = new LinkedList<Producto>();
    }

    public Producto sacaProducto (int id, int cantidad) throws RemoteException
    {
	//La idea seria que este metodo tenga una variable del tipo DataBase para poder usar sus metodos
	Database p = new DatabaseImpl();
        this.cantidad = cantidad;
	this.id = id;

	producto = p.getProducto(id, cantidad);

	return producto;	
    }

    public void meteProducto (int id, int cantidad) throws RemoteException
    {
       	Database p = new DatabaseImpl();
        this.cantidad = cantidad;
	this.id = id;

	producto = p.addProducto(id, cantidad);
	//No se si poner aqui un printf tipo "Se ha añadido la cantidad x del producto con tal id en el inventario
	System.out.println ("Se ha añadido la cantidad de: "+cantidad+"del producto: " + id);
    }

    public List<Producto> obtenerProductos () throws RemoteException
    {
       	Database p = new DatabaseImpl();

	//Aqui tenemos inconveniente porque el metodo de getInventrio devuelve un string y yo quiero una lista para que la profe vea que las manejamos.
	inventario = p.getInventario;

	return inventario;
    }

    public float obtenerCashFlow () throws RemoteException
    {
	Database p = new DatabaseImpl();

	cashFlow = p.getCuentas();

	return cashFlow;
    }
}
    
    
