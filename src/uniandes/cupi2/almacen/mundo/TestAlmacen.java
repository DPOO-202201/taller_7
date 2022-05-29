package uniandes.cupi2.almacen.mundo;

import java.io.File;

import junit.framework.TestCase; 

public class TestAlmacen extends TestCase {
	
	private Almacen almacen;
	private File archivo = new File( "./data/datos.txt" );
	
	public void escenario() throws AlmacenException {
		almacen = new Almacen(archivo);
	}
	
	public void testCargar() throws AlmacenException
	{
		
	}
	
	
	public void testDarCategoriaRaiz() throws AlmacenException
	{
		Categoria testCategoriaRaiz = almacen.darCategoriaRaiz(); 
	}
	
	public void testAgregarNodo() throws AlmacenException
	{
		
	}
	
	public void testEliminarNodo() throws AlmacenException
	{
		
	}
	
	public void testVenderProducto() throws AlmacenException
	{
		
	}
	
	public void testBuscarNodo() throws AlmacenException
	{
		
	}
	
	public void testAgregarProducto() throws AlmacenException
	{
		
	}
	
	public void testEliminarProducto() throws AlmacenException
	{
		
	}
	
	public void testMetodo1() throws AlmacenException
	{
		escenario();
		String tempStr = "Respuesta 1";
		String resultado = almacen.metodo1();
		assertEquals(tempStr, resultado);
	}
	
	public void testMetodo2() throws AlmacenException
	{
		escenario();
		String tempStr = "Respuesta 2";
		assertEquals(tempStr, almacen.metodo2());
	}
	
}