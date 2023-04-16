import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.sql.*;


class ClienteMain {
    @SuppressWarnings({ "removal", "deprecation", "resource" })
	static public void main (String args[]) {
      
        if (args.length != 2) {
            System.err.println("Uso: ClienteMain hostregistro numPuertoRegistro ");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
	    
	    Tienda srv = (Tienda) Naming.lookup("//" + args[0] + ":" + args[1] + "/Tienda");
	    //Creamos las cadenas para comparar que acción va a realizar el cliente
	    Scanner EntradaDatos =new Scanner(System.in); //para pedir datos por línea de comando
	    boolean salir = false;
	    int opcion;
	    Producto p;
	    
	    while(!salir){
		 System.out.println("1. Comprar");
		 System.out.println("2. Lista");
		 System.out.println("3. Devolver");
		 System.out.println("4. Introducir Producto");
		 System.out.println("5. Salir");
		 try {
 
		     System.out.println("Elija una de las anteriores opciones");
		     opcion = EntradaDatos.nextInt();
 
		     switch (opcion) {
		     case 1:
			 System.out.println("Has seleccionado la opcion de COMPRAR\n");
			 System.out.println("Ingrese el Id del producto que quiera comprar: ");
			 int id_producto_compra = EntradaDatos.nextInt();
			 System.out.println("Ingrese la cantidad del producto a comprar: ");
			 int cantidad_producto_comprar = EntradaDatos.nextInt();
			 p = srv.getProducto(id_producto_compra, 0);
			 float precio_compra = p.getPrecio();
			 precio_compra = cantidad_producto_comprar*precio_compra;
			 p = srv.compraProducto(id_producto_compra, cantidad_producto_comprar, precio_compra);
			 System.out.println("Se ha efectuado la compra del producto: "+ p.getNombre() + "con un coste total: "+ precio_compra);
			 break;
		     case 2:
			 System.out.println("Has seleccionado la opcion de LISTAR PRODUCTOS\n");
			 List <Producto> listaProducto;
			    listaProducto = srv.obtenerProductos();
			String resultado = "◢__ID__.________NOMBRE________.__PRECIO__._CANTIDAD_◣";

			
			for(Producto i: listaProducto) {
				String fila = String.format("| %4d | %-20s | %8.2f | %-8d |", 
							i.getId(),i.getNombre(), 
							i.getPrecio(),i.getCantidad());
				resultado = resultado +"\n"+fila;
			}

			 System.out.println(resultado);
			 break;
		     case 3:
			 System.out.println("Has seleccionado la opcion de DEVOLVER\n");
			 System.out.println("Ingrese el Id del producto que desea devolver: ");
			 int id_producto_devolver = EntradaDatos.nextInt();
			 System.out.println("Ingrese la cantidad del producto a devolver: ");
			 int cantidad_producto_devolver = EntradaDatos.nextInt();
			 p = srv.getProducto(id_producto_devolver, 0);
			 float precio_devolver = p.getPrecio();
			 precio_devolver = cantidad_producto_devolver*precio_devolver;
	
			 srv.devuelveProducto(id_producto_devolver, cantidad_producto_devolver, precio_devolver);
			 System.out.println("Se ha efectuado la devolución del producto: "+ id_producto_devolver);
			 
			 break;
		     case 4:
				 System.out.println("Has seleccionado la opcion de INTRODUCIR NUEVO PRODUCTO\n");
				 EntradaDatos.nextLine();
				 System.out.println("Ingrese el nombre del producto a introducir: ");
				 String nombre_producto_introducir = EntradaDatos.nextLine();
				 System.out.println("Ingrese la cantidad del producto a introducir: ");
				 int cantidad_producto_introducir = EntradaDatos.nextInt();
				 System.out.println("Ingrese el precio del producto a introducir: ");
				 float precio_producto_introducir = EntradaDatos.nextFloat();
				 int nuevoProducto = srv.nuevoProducto(nombre_producto_introducir,precio_producto_introducir ,cantidad_producto_introducir);
				 System.out.println("Se ha inntroducido correctamente el nuevo producto con id: "+ nuevoProducto);
				 break;
		     case 5:
				 salir = true;
				 break;
		     default:
			 System.out.println("Solo números entre 1 y 5");
		     }
		 }
		catch (Exception DBException) {
		   System.err.println("Error en el acceso a la base de datos\n");
		 } 
		catch (Exception e) {
		     System.err.println("Excepcion en ClienteTienda:");
		     e.printStackTrace();
		 }
			
	    }
	  
      
        }
	 catch (Exception e) {
		     System.err.println("Excepcion en ClienteTienda:");
		     e.printStackTrace();
        }

    }
}
