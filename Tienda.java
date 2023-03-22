/**
 * Interfaz "Tienda", que contiene definicion de sus metodos.
 * @author Amando Antoñano
 *
 */

import java.rmi.*;
import java.util.*;


interface Tienda extends Remote {
         /**
	 * Saca un producto con una id y la cantidad x del inventario (la bbdd)
	 * @return producto
	 * @throws SQLException 
	 */
    public Producto sacaProducto (int id, int cantidad) throws RemoteException;


         /**
	 * Mete un producto con una id y una cantidad x al inventario
	 * @throws SQLException 
	 */
    public void meteProducto (int id, int cantidad) throws RemoteException;


    
         /**
	 * Devuelve una lista con todos los productos disponibles en el inventario
	 * @return Lista de productos
	 * @throws SQLException 
	 */
    public List<Producto> obtenerProductos () throws RemoteException;


         /**
	 * Devuelve el estado de la caja en ese instante
	 * @return estado de la caja
	 * @throws SQLException 
	 */
    public float obtenerCashFlow () throws RemoteException;
}
