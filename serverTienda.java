/**
 *Clase main del server que controla su ejecución correcta.
 * @author Amando Antoñano
 *
 */

import java.rmi.*;
import java.rmi.server.*;


class serverTienda  {
    static public void main (String args[]) {
       if (args.length!=1) {
            System.err.println("Uso: ServidorFabrica numPuertoRegistro");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            TiendaImpl t = new TiendaImpl();
	    
	    //Lo que hace rebind es vincular el arg[0] (puertoregistro) con el nuevo objeto remoto
	    //rebind(String name, Remote obj) donde name tiene que tener formato de url
            Naming.rebind("rmi://localhost:" + args[0] + "/Tienda", t);
	    
	    
	    //CREO QUE HEMOS TENIDO UN ERROR DE CONCEPTO Y EN EL SERVER NO EJECUTAMOS NADA. EN VEZ DEL ROL "CLIENTE", LO QUE SE HACE ES TENER
	    //UN ROL "TRABAJADOR" Y DENTRO DE LO QUE ES EL ARCHIVO "trabajadorTienda.java" tendriamos algo asi:
	    /** if (args.length!=5) {
            System.err.println("Uso: ClienteBanco hostregistro numPuertoRegistro ACCION IDPRODUCTO CANTIDAD");
            return;
	    }

	    if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

	    try {
            Tienda tienda = (Tienda) Naming.lookup("//" + args[0] + ":" + args[1] + "/Tienda");  //Esto ya te habilita a que tu variable utilice mis procesos de forma remota 
	    String Accion = args[2];
            int idProducto = args[3];
	    int cantidad = args[4];
	    Producto producto = null;
	    String keyWord1 = "COMPRAR";
	    String keyWord2 = "DEVOLVER";
	    String keyWord3 = "LISTA";
	    String kewWord4 = "CASHFLOW";
            Tendriamos varios if tal que asi
	    if (Accion == keyWord1)
	    {
	      producto =  tienda.sacaProducto(idProducto, cantidad);
              System.out.println ("Se ha sacado el producto: " + producto.getNombre() + "con el precio: " + producto.getPrecio());
	    }
            ASI CON VARIOS IF Y TECNICAMENTE COMO YO HE PUESTO arriba que la url x esta asociada al objeto que es de tipo t y que TE "ENVIO" de forma remota peudes usar mis metodos
	    Lo que es el tema de la clase producto que ha hecho el maño imagino que tu puedes poner en cliente class trabajadorTienda implements (hereda) por lo que podras hacer uso de la interfaz de esta clase
	    Y si, tecnicamente si ponemos en todas las clases que implementen todas nuestras interfaces abstractas, no haria falta hacer uso de rmi(Remoto)
	    
	    OTRA COSA para Alvaro PODRIAMOS HACER que la clase del producto (no se si la interfaztambien), fuera SERIEZABLE porque es simplemente poner el implements y no tiene metodos y hacer que guarde los nuevos flujos de caja en un             archivo porqu al final sirve para convertir datos en bytes recuperables y blabla, lo digo por ponerle mas cosas de las practicas
	    */

	    
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorFabrica:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
