/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_almacen
 * Autor: Equipo Cupi2 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.mundo;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una categor�a del almac�n.
 */
public class Categoria extends NodoAlmacen
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el tipo del nodo categor�a.
     */
    public final static String TIPO = "Categoria";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con los nodos hijos de la categor�a.
     */
    private List<NodoAlmacen> nodosHijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva categor�a sin nodosHijos.<br>
     * <b>post: </b> Se inicializaron los atributos de la clase padre con los valores dados por par�metro y el tipo respectivo. Se inicializ� la lista de nodosHijos como una lista
     * vac�a.
     * @param pIdentificador Identificador �nico de la marca. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre de la categor�a. pNombre != null && pNombre != "".
     */
    public Categoria( String pIdentificador, String pNombre )
    {
        super( TIPO, pIdentificador, pNombre );
        nodosHijos = new ArrayList<>( );
    }

    /**
     * Construye una nueva categor�a a partir de la l�nea con la informaci�n general y el lector para la informaci�n adicional.<br>
     * <b>post:</b> Se inicializaron los atributos de la clase padre con el identificador que viene en la l�nea y el tipo respectivo. Se cargaron los nodosHijos de la categor�a de
     * la informaci�n contenida en el lector.
     * @param pLinea L�nea que contiene la informaci�n general de la marca. pLinea != null && pLinea != "" && pLinea.startsWith(TIPO).
     * @param pLector Lector para acceder a la informaci�n de los productos.
     * @throws AlmacenException Si ocurren errores al leer la informaci�n de los productos.
     */
    public Categoria( String pLinea, BufferedReader pLector ) throws AlmacenException
    {
        super( TIPO, pLinea.split( ";;;" )[ 1 ], pLinea.split( ";;;" )[ 2 ] );
        nodosHijos = new ArrayList<>( );
        try
        {
            String datos[] = pLinea.split( ";;;" );
            int numHijos = Integer.parseInt( datos[ 3 ] );
            while( numHijos-- > 0 )
            {
                agregarNodo( identificador, crearNodo( pLector ) );
            }
        }
        catch( Exception e )
        {
            throw new AlmacenException( "Error al leer la marca:\n" + e.getMessage( ) );
        }

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la lista de los nodosHijos hijos.
     * @return Lista de nodosHijos.
     */
    public List<NodoAlmacen> darNodos( )
    {
        return nodosHijos;
    }

    /**
     * Indica si esta categor�a tiene como hijo el nodo con el identificador dado.<br>
     * <b>pre: </b> La lista de nodosHijos est� inicializada.<br>
     * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
     * @return True si esta categor�a tiene un hijo con el identificador dado, False en caso contrario.
     */
    private boolean tieneHijo( String pIdNodo )
    {
        boolean respuesta = false;
        for( int i = 0; i < nodosHijos.size( ) && !respuesta; i++ )
        {
            NodoAlmacen nodo = nodosHijos.get( i );
            respuesta = nodo.darIdentificador( ).equals( pIdNodo );
        }
        return respuesta;
    }

    /**
     * Retorna la categor�a padre del nodo con identificador dado.<br>
     * <b>pre: </b> La lista de nodosHijos est� inicializada y existe un nodo con el identificador dado.<br>
     * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
     * @return Padre del nodo dado.
     */
    public Categoria buscarPadre( String pIdNodo )
    {
        Categoria respuesta = tieneHijo( pIdNodo ) ? this : null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            NodoAlmacen actual = nodosHijos.get( i );
            if( actual.darTipo( ).equals( Categoria.TIPO ) )
            {
                respuesta = ( ( Categoria )actual ).buscarPadre( pIdNodo );
            }

        }
        return respuesta;
    }

    /**
     * Busca el nodo con el identificador dado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen con el identificador dado o null si no se encontr� el nodo.
     */
    @Override
    public NodoAlmacen buscarNodo( String pIdentificador )
    {
        NodoAlmacen respuesta = null;
        if( pIdentificador.equals( identificador ) )
        {
            respuesta = this;
        }
        else
        {
            for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
            {
                respuesta = nodosHijos.get( i ).buscarNodo( pIdentificador );
            }
        }
        return respuesta;
    }

    /**
     * Agrega un nuevo nodo del tipo dado a la lista.<br>
     * <b>pre: </b> La lista de nodosHijos est� inicializada.<br>
     * <b>post: </b> Se agreg� un nuevo nodo a la lista con los valores dados.
     * @param pIdPadre Identificador �nico del padre del nodo. pIdPadre != null && pIdPadre != "".
     * @param pTipo Tipo del nodo. pTipo != null && pTipo != "".
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre del nodo. pNombre != null && pNombre != "".
     * @throws AlmacenException Si ya existe un nodo en el sub�rbol con ese identificador.
     */
    public void agregarNodo( String pIdPadre, String pTipo, String pIdentificador, String pNombre ) throws AlmacenException
    {
        NodoAlmacen nuevo = pTipo.equals( Categoria.TIPO ) ? new Categoria( pIdentificador, pNombre ) : new Marca( pIdentificador, pNombre );
        agregarNodo( pIdPadre, nuevo );
    }

    /**
     * Agrega un nuevo nodo a la lista.<br>
     * <b>pre: </b> La lista de nodosHijos est� inicializada.<br>
     * <b>post: </b> Se agreg� el nuevo nodo a la lista.
     * @param pIdPadre Identificador �nico del padre del nodo. pIdPadre != null && pIdPadre != "".
     * @param pNodo NodoAlmacen que se va a agregar. pNodo != null.
     * @return True si agreg� el nodo, False en caso contrario.
     * @throws AlmacenException Si ya existe un nodo en el sub�rbol con ese identificador.
     */
    public boolean agregarNodo( String pIdPadre, NodoAlmacen pNodo ) throws AlmacenException
    {
        if( buscarNodo( pNodo.identificador ) != null )
        {
            throw new AlmacenException( "Ya existe un nodo en el �rbol con el identificador " + pNodo.identificador );
        }
        boolean respuesta = false;
        if( identificador.equals( pIdPadre ) )
        {
            respuesta = nodosHijos.add( pNodo );
        }
        else
        {
            for( int i = 0; i < nodosHijos.size( ) && !respuesta; i++ )
            {
                NodoAlmacen actual = nodosHijos.get( i );
                if( actual instanceof Categoria )
                {
                    respuesta = ( ( Categoria )actual ).agregarNodo( pIdPadre, pNodo );
                }
            }
        }
        return respuesta;
    }

    /**
     * Elimina el nodo con el identificador dado.<br>
     * <b>pre: </b>La lista de nodosHijos est� inicializada. Existe un nodo con el identificador dado en el sub�rbol.<br>
     * <b>post: </b> Se elimin� el nodo con toda su informaci�n y su sub�rbol asociado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen eliminado.
     */
    public NodoAlmacen eliminarNodo( String pIdentificador )
    {
        NodoAlmacen respuesta = null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            NodoAlmacen actual = nodosHijos.get( i );
            if( actual.identificador.equals( pIdentificador ) )
            {
                respuesta = nodosHijos.remove( i );
            }
            else if( actual instanceof Categoria )
            {
                respuesta = ( ( Categoria )actual ).eliminarNodo( pIdentificador );
            }
        }

        return respuesta;
    }

    /**
     * Busca el producto con el c�digo dado.
     * @param pCodigo C�digo del producto. pCodigo != null && pCodigo != "".
     * @return El producto buscado o null si no existe.
     */
    public Producto buscarProducto( String pCodigo )
    {
        Producto respuesta = null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            NodoAlmacen actual = nodosHijos.get( i );
            if( actual instanceof Marca )
            {
                respuesta = ( ( Marca )actual ).buscarProducto( pCodigo );
            }
            else
            {
                respuesta = ( ( Categoria )actual ).buscarProducto( pCodigo );
            }
        }

        return respuesta;

    }

    /**
     * Agrega a la lista acumulada todos los productos del nodo.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.
     * @param pProductos Lista acumulada con los productos. pProductos != null.
     */
    @Override
    public void darProductos( List<Producto> pProductos )
    {
        for( NodoAlmacen nodoAlmacen : nodosHijos )
        {
            nodoAlmacen.darProductos( pProductos );
        }
    }

    /**
     * Retorna una lista con todas las marcas que tiene la categor�a y su sub�rbol.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.<br>
     * @return Lista con todas las marcas de la categor�a y su sub�rbol.
     * 
     */
    public List<Marca> darMarcas( )
    {
        List<Marca> respuesta = new ArrayList<>( );
        for( NodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof Categoria )
            {
                respuesta.addAll( ( ( Categoria )nodo ).darMarcas( ) );
            }
            else
            {
                respuesta.add( ( Marca )nodo );
            }
        }
        return respuesta;

    }

    /**
     * Retorna todos los nodosHijos del �rbol que tiene como ra�z este nodo. Los nodosHijos se agregan en preorden.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.
     * @return Lista con todos los nodosHijos del �rbol.
     */
    public List<NodoAlmacen> darPreorden( )
    {
        List<NodoAlmacen> respuesta = new ArrayList<>( );
        respuesta.add( this );
        for( NodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof Categoria )
            {
                respuesta.addAll( ( ( Categoria )nodo ).darPreorden( ) );
            }
            else
            {
                respuesta.add( nodo );
            }
        }
        return respuesta;
    }

    /**
     * Retorna todos los nodosHijos del �rbol que tiene como ra�z este nodo. Los nodosHijos se agregan en posorden.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.
     * @return Lista con todos los nodosHijos del �rbol.
     */
    public List<NodoAlmacen> darPosorden( )
    {
        List<NodoAlmacen> respuesta = new ArrayList<>( );
        for( NodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof Categoria )
            {
                respuesta.addAll( ( ( Categoria )nodo ).darPosorden( ) );
            }
            else
            {
                respuesta.add( nodo );
            }
        }
        respuesta.add( this );
        return respuesta;
    }

    /**
     * Retorna el valor total de las ventas de la categor�a.
     * @return Valor de las ventas de la categor�a.
     */
    public double darValorVentas( )
    {
        double valorVentas = 0;
        for( NodoAlmacen nodoAlmacen : nodosHijos )
        {
            valorVentas += nodoAlmacen.darValorVentas( );
        }
        return valorVentas;
    }

    public static void main( String[] args ) throws AlmacenException
    {

    	Categoria categoria = new Categoria("88", "Calzado");
    	categoria.agregarNodo("88", "Calzado", "89", "Calzado Nike");
    	System.out.println(categoria.darNodos().get(0).darIdentificador());
    	
    	
    }


}
