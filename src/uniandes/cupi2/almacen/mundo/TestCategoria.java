package uniandes.cupi2.almacen.mundo;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestCategoria extends TestCase
	{
		
		private Categoria categoria;
		
		public void crearEscenario() throws AlmacenException
			{
			
				categoria = new Categoria("88", "Calzado");
				categoria.agregarNodo("88", "Calzado", "89", "Nike");
				categoria.agregarNodo("89", "Calzado", "90", "Air Max");
			
			}
		
	    public void testAgregarNodo() throws AlmacenException
		    {
	
		    	crearEscenario();
	    		assertEquals("Nike", categoria.darNodos().get(0).darNombre());
	    	
		    }
	

	    public void testDarNodos() throws AlmacenException
		    {
	    	
	    		
	    		crearEscenario();
	    		assertEquals(1, categoria.darNodos().size());
		       
		    }


	    public void testTieneHijo() throws AlmacenException
		    {
	    	
    			crearEscenario();
    			assertEquals(true, categoria.tieneHijo("89"));
	
		    }


	    public void testBuscarPadre() throws AlmacenException
		    {
	    	
    			crearEscenario();
    			assertEquals(categoria, categoria.buscarPadre("89"));
	
		    }

	    public void testBuscarNodo() throws AlmacenException
		    {
	    	
	    		crearEscenario();
	    		assertEquals(categoria.darNodos().get(0), categoria.buscarNodo("89"));
	
		    }

	    public void testEliminarNodo() throws AlmacenException
		    {
	
    			crearEscenario();
    			categoria.eliminarNodo("89");
    			assertEquals(0, categoria.darNodos().size());
	    	
		    }


	    public void testBuscarProducto() throws AlmacenException
		    {
	
    			crearEscenario();
    			assertEquals(null, categoria.buscarProducto("13"));
	    	
		    }


	    public void testDarProductos() throws AlmacenException
		    {
		    	
    			crearEscenario();
    			assertEquals(0, categoria.darProductos().size());
	
		    }


	    public void testDarMarcas( ) throws AlmacenException
		    {
		    	
    			crearEscenario();
    			assertEquals(1, categoria.darMarcas().size());
	
		    }


	    public void testDarPreorden() throws AlmacenException
		    {
	
    			crearEscenario();
    			assertEquals("Calzado", categoria.darPreorden().get(0).darNombre());
		    	
		    }


	    public void testDarPosorden() throws AlmacenException
		    {
		    	
    			crearEscenario();
    			assertEquals("Nike", categoria.darPosorden().get(0).darNombre());
		    }


	    public void testDarValorVentas() throws AlmacenException
		    {
	
    			crearEscenario();
    			assertEquals(0.0, categoria.darValorVentas());
		    	
		    }

	}