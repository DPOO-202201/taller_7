package uniandes.cupi2.almacen.mundo;

import java.io.File;

import junit.framework.TestCase; 

public class TestAlmacen extends TestCase {
	
	private Almacen almacen, testAlmacen;
	private File archivo = new File( "./data/datos.txt" );
	
	public void escenario() throws AlmacenException {
		almacen = new Almacen(archivo);
	}
	
	public void testCargar()
	{
		try {
			testAlmacen = new Almacen(archivo);
		}
		catch (AlmacenException e) {
			//TODO Ver como hacer que marque error o faliure cuando reciba AlmacenException
			//assertFalse(fName, false)
		}
		
		
	}
	
	
	public void testDarCategoriaRaiz() throws AlmacenException
	{
		escenario();
		Categoria testCategoriaRaiz = almacen.darCategoriaRaiz(); 
		String testCategoriaRaizNombre = testCategoriaRaiz.darNombre();
		assertEquals(testCategoriaRaizNombre, "Cupi2");
	}
	
	public void testAgregarNodo() throws AlmacenException
	{
		escenario();
	}
	
	public void testEliminarNodo() throws AlmacenException
	{
		escenario();
	}
	
	public void testVenderProducto() throws AlmacenException
	{
		escenario();
	}
	
	public void testBuscarNodo() throws AlmacenException
	{
		escenario();
	}
	
	public void testAgregarProducto() throws AlmacenException
	{
		escenario();
	}
	
	public void testEliminarProducto() throws AlmacenException
	{
		escenario();
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