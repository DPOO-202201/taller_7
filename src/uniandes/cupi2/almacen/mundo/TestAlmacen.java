package uniandes.cupi2.almacen.mundo;

import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.util.List;

import junit.framework.TestCase; 

public class TestAlmacen extends TestCase {
	
	private Almacen almacen;
	private File archivo = new File( "./data/dataPruebas.txt" );
	
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
		almacen.agregarNodo("112", "Marca", "1124", "TOSHIBA");
		NodoAlmacen tempNodo = almacen.buscarNodo("TOSHIBA");
		assertNotNull(tempNodo);
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
		almacen.agregarProducto("ASUS", "12345678", "PCGamer", "Swoosh", 10);
		almacen.venderProducto("12345678", 1);
		NodoAlmacen tempNodo = almacen.buscarNodo(getName());
		assertEquals(tempNodo.darValorVentas(),10);
	}
	
	public void testEliminarProducto() throws AlmacenException
	{
		escenario();
		almacen.eliminarProducto("34089951");
		NodoAlmacen tempNodo = almacen.buscarNodo("1123");
		List<Producto> tempProductos = tempNodo.darProductos();
		int tempSize = tempProductos.size();
		assertEquals(2,tempSize);
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