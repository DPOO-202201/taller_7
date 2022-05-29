package uniandes.cupi2.almacen.mundo;

import java.io.File;

import junit.framework.TestCase; 

public class TestAlmacen extends TestCase {

	private Almacen almacen;	
	
	public void escenario() throws AlmacenException {
		almacen = new Almacen(new File( "./data/datos.txt" ));
	}
	
	public void testDarCategoriaRaiz()
	{
		
	}
	
	public void testCargar()
	{
		
	}
	
	public void testAgregarNodo()
	{
		
	}
	
	public void testEliminarNodo()
	{
		
	}
	
	public void testVenderProducto()
	{
		
	}
	
	public void testBuscarNodo()
	{
		
	}
	
	public void testAgregarProducto()
	{
		
	}
	
	public void testEliminarProducto()
	{
		
	}
	
	public void testMetodo1()
	{
		assertEquals("Respuesta 1", almacen.metodo1());
	}
	
	public void testMetodo2()
	{
		assertEquals("Respuesta 2", almacen.metodo2());
	}
	
}