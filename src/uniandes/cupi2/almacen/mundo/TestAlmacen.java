package uniandes.cupi2.almacen.mundo;

import java.io.File;
import java.util.List;

import junit.framework.TestCase; 

public class TestAlmacen extends TestCase {
	
	private Almacen almacen;
	private File archivo = new File( "./data/datos.txt" );
	
	public void escenario() throws AlmacenException {
		almacen = new Almacen(archivo);
	}
	
	public void testCargar()
	{
		boolean resultado = true;
		try {
			almacen = new Almacen(archivo);
		}
		catch (AlmacenException e) {
			resultado = false;
		}
		assertTrue(resultado);
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
		Boolean resultadoPrueba;
		
		String tempIdPadre = "11";
		String tempTipo = "Categoria";
		String tempIdentificador = "113";
		String tempNombre = "Consolas";
		almacen.agregarNodo(tempIdPadre, tempTipo, tempIdentificador,  tempNombre);
		NodoAlmacen tempNodo = almacen.buscarNodo("Consolas");
		
		resultadoPrueba = false;
		
		if (tempNombre.equals(tempNodo.darNombre())) {
			if (tempTipo.equals(tempNodo.darTipo())) {
				if (tempIdentificador.equals(tempNodo.darIdentificador())) {
						resultadoPrueba = true;
				}
			}
		}
		
		assertTrue(resultadoPrueba);
	}
	
	public void testEliminarNodo() throws AlmacenException
	{
		escenario();	
		almacen.eliminarNodo("Tecnologia");
		NodoAlmacen tempNodo = almacen.buscarNodo("Tecnologia");
		assertNull(tempNodo);
	}
	
	public void testVenderProducto() throws AlmacenException
	{
		escenario();
		almacen.venderProducto("34089951", 1);
		NodoAlmacen tempNodo = almacen.buscarNodo("1123");
		double tempVentas = tempNodo.darValorVentas();
		assertNotSame(0, tempVentas);
	}
	
	public void testBuscarNodo() throws AlmacenException
	{
		escenario();
		NodoAlmacen tempNodo= almacen.buscarNodo("11");
		assertNotNull(tempNodo);
	}
	
	public void testAgregarProducto() throws AlmacenException
	{
		escenario();
		almacen.agregarProducto("ASUS", "12345678", "PC", "QWERTYUIOP", 1000000);
		almacen.venderProducto("12345678", 1);
		NodoAlmacen tempNodo = almacen.buscarNodo("1121");
		assertEquals(tempNodo.darValorVentas(),1);
	}
	
	public void testEliminarProducto() throws AlmacenException
	{
		escenario();
		boolean resultado = true;
		almacen.eliminarProducto("34089951");
		NodoAlmacen tempNodo = almacen.buscarNodo("112");
		List<Producto> tempProductos = tempNodo.darProductos();
		try {
			tempProductos.get(2);
		}
		catch (IndexOutOfBoundsException e) {
			resultado = false;
		}
		assertTrue(resultado);
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