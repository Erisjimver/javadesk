package Modelo;

import Controlador.SettersAndGetters;

import static Vista.EntornoAdmin.LabelEstado;
import Vista.Login;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class CRUD {
static Statement s;
static ResultSet rs;
private static PreparedStatement ps;
private static CallableStatement sc;
Conexion cn=new Conexion(); 
Connection c= cn.conexion();

////////////////////////////////////////////////////////////////////////////////
/////////////////////////////CRUD///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

//metodos para registrar sucursal_empresa
    public void registrarSucursal(SettersAndGetters to) throws Exception{
        try
        {
            ps= c.prepareCall("CALL RegistrarEmpresa(?,?,?,?,?)");

            ps.setInt(1, to.getIdEmpresa());  
            ps.setString(2, to.getRuc());   
            ps.setString(3, to.getSucursal());  
            ps.setInt(4, to.getTelefonoEmpresa());  
            ps.setString(5, to.getDireccionEmpresa()); 

            ps.execute();
            
            LabelEstado.setText("Registro exitoso de la nueva sucursal"); 
            
        }
        catch(HeadlessException | SQLException e)
        {
            LabelEstado.setText("Error de registro de la sucursal: "+e); 
        }

    }
    
//metodos registrar vendedor
    public void registrarEmpleado(SettersAndGetters to) throws Exception{
        
        try
        {
            ps = c.prepareCall("CALL RegistrarVendedor(?,?,?,?,?,?,?,?)");
            
            ps.setInt(1, to.getIdTipoUsuarioV());
            ps.setInt(2, to.getIdEmpresaV());
            ps.setString(3, to.getContrasenaV());      
            ps.setString(4, to.getCedulaV());
            ps.setString(5, to.getNombresV());
            ps.setString(6, to.getApellidosV());
            ps.setString(7, to.getTelefonoV());
            ps.setString(8, to.getDireccionV());

            ps.execute();
            
            LabelEstado.setText("Registro exitoso del empleado"); 
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro del Empleado: "+e);
        }
        
    }

//metodos para registrar categorias
    public void registrarCategorias(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL RegistrarCategorias(?)");
            
            ps.setString(1, to.getDescripcion());  
            
            ps.execute();
            
            LabelEstado.setText("Registro exitoso de la categoria"); 
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error de registro de la categoria: "+e); 
        }

    } 
   
//metodos para registrar productos
    public void registrarProductos(SettersAndGetters to) throws Exception{
 
        try
        { 
            ps = c.prepareCall("CALL RegistrarProductos(?,?,?,?,?,?)");

            ps.setInt(1, to.getIdcategorias());
            ps.setString(2, to.getNombreproducto());      
            ps.setString(3, to.getMarca());
            ps.setDouble(4, to.getCosto());
            ps.setDouble(5, to.getPrecio());
            ps.setInt(6, to.getStock()); 
            
            ps.execute();
            
            LabelEstado.setText("Registro del producto exitoso"); 
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro del Producto: "+e);
        }
    }

//Metodo para registrar clientes
    public void registrarCliente(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL RegistrarClientes(?,?,?,?)");
            
            ps.setString(1, to.getCedula());
            ps.setString(2, to.getNombre());
            ps.setString(3, to.getTelefono());
            ps.setString(4, to.getDireccion()); 
            
            ps.execute();  
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro del Cliente: "+e);
        }
        
    }

//metodo para registrar factura    
    public void registrarFactura(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL RegistrarFactura(?,?)");
            
            ps.setInt(1, to.getIdvendedor());
            ps.setInt(2, to.getIdcliente());
            
            ps.execute();
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro de ka factura: "+e);
        }
    }
    
//metodos para ingresar los detalles de la de la venta factura
    public void registrarDetalleFactura(SettersAndGetters to) throws Exception{
        
        try
        {
            ps = c.prepareCall("CALL RegistrarDetalleV(?,?,?,?,?)");
            
            ps.setInt(1, to.getIdfactura());   
            ps.setInt(2, to.getIdproducto()); 
            ps.setInt(3, to.getCantidad());
            ps.setDouble(4, to.getValorunitario());
            ps.setDouble(5, to.getValortotal()); 

            ps.execute();
            
        }catch(SQLException e){
            LabelEstado.setText("Error de registro del detalle de la venta y factura: "+e);
        }
    }
   

//----------------------------------------------------------------------------//   
//---------------------- Actualizaciones -------------------------------------//
//----------------------------------------------------------------------------//
    
//metodos para actualizar Sucursal
    public void actualizarSucursal(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL ActualizarSucursal(?,?,?,?,?)");
            ps.setInt(1, to.getIdEmpresa());
            ps.setString(2, to.getRuc());  
            ps.setString(3, to.getSucursal()); 
            ps.setInt(4, to.getTelefonoEmpresa());
            ps.setString(5, to.getDireccionEmpresa()); 
            
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Actualizacion exitosa de la sucursal");       
            } 
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al actualizar la sucursal: "+e); 
        }

    } 
    
//metodo para actualizar Empleado      
    public void actualizaEmpleado(SettersAndGetters to) throws Exception{
 
        try{
            ps = c.prepareCall("CALL ActualizarEmpleados(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, to.getIdvendedor());
            ps.setInt(2, to.getIdTipoUsuarioV());
            ps.setInt(3, to.getIdEmpresaV());
            ps.setString(4, to.getContrasenaV()); 
            //cs.setString(5, to.getCedulaV());
            ps.setInt(5, Integer.parseInt(to.getCedulaV()));
            ps.setString(6, to.getNombresV());
            ps.setString(7, to.getApellidosV());
            ps.setString(8, to.getTelefonoV());
            ps.setString(9, to.getDireccionV());
        
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Actualizacion de empleado exitosa");      
            }    
            
        }catch(SQLException e)
        {
            LabelEstado.setText("Error al actualizar los datos del empleado: "+e); 
        }
    }

//metodos para actualizar Categoria
    public void actualizarCategoria(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL ActualizarCategoria(?,?)");
            ps.setInt(1, to.getIdCategoria());
            ps.setString(2, to.getDescripcion());  
            
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Actualizacion exitosa de la categoria");      
            }            
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al actualizar la categoria: "+e); 
        }

    } 
    
//metodos para actualizar productos
    public void actualizaProductos(SettersAndGetters to){
 
        try
        { 
            ps = c.prepareCall("call ActualizarProductos(?,?,?,?,?,?)");

            ps.setInt(1, to.getIdproducto());
            ps.setString(2, to.getNombreproducto());      
            ps.setString(3, to.getMarca());
            ps.setDouble(4, to.getCosto());
            ps.setDouble(5, to.getPrecio());
            ps.setInt(6, to.getStock());
            
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Actualizacion del producto exitosa");     
            }

        }catch(SQLException e){
            LabelEstado.setText("Error al actualizar el Producto: "+e);
        }
    }

//Metodo para actualizar Clientes
    public void actualizarCliente(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL ActualizarClientes(?,?,?,?)");
            
            ps.setString(1, to.getCedula());
            ps.setString(2, to.getNombre());
            ps.setString(3, to.getTelefono());
            ps.setString(4, to.getDireccion()); 
            
            ps.execute();
            
            if(ps.execute()==false){
            LabelEstado.setText("Actualizacion del cliente se realizo con exito");     
            }
            
        }catch(SQLException e){
            LabelEstado.setText("Error al actualizar el cliente: "+e);
        }
        
    }


        
//----------------------------------------------------------------------------//   
//------------------------------ Eliminar ------------------------------------//
//----------------------------------------------------------------------------//

//Eliminar Sucursal
    public void eliminarSucursal(int idsucursal){
 
        try
        {
            ps = c.prepareCall("call EliminarSucursal(?)");
            ps.setInt(1, idsucursal);
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Sucursal eliminada con exito ");      
            }
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Sucursal: "+e); 
        }
       
    }
    
//Eliminar Empleados
    public void eliminarEmpleado(int idempleado){
 
        try
        {
            ps = c.prepareCall("call EliminarEmpleado(?)");
            ps.setInt(1, idempleado);
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Empleado eliminado con exito ");      
            }
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Empleado: "+e); 
        }
       
    }      

//metodos para eliminar categorias
    public void eliminaCategoria(int idcategoria){
        
        try
        {
            ps = c.prepareCall("call EliminarCategoria(?)");
            ps.setInt(1, idcategoria);
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Categoria eliminada con exito ");       
            }
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Categoria: "+e); 
        }
        
    } 
    
//metodos para eliminar productos
    public void eliminaProducto(int idproducto){
        
        try
        {
            ps = c.prepareCall("call EliminarProducto(?)");
            ps.setInt(1, idproducto);
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Producto eliminado con exito ");       
            }          
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Producto: "+e); 
        }
        
    } 
    
    
//----------------------------------------------------------------------------//
//--------------------------- CONSULTAS --------------------------------------//
//----------------------------------------------------------------------------//

//consulta del tipo de usuario para el LOGIN    
    public String TipoUsuario(String usuario, String contrasena) throws Exception {
        
        String sql="select tp.Tipo_Usuario from Vendedor v inner join Tipo_Usuario tp on v.IdTipo_Usuario = tp.IdTipo_Usuario where v.cedula='"+usuario+"' and v.Contrasena='"+contrasena+"'";         
        String tipo_usuario="";
        try{       
            s = c.createStatement();
            rs = s.executeQuery(sql);
            rs.next();
            tipo_usuario=rs.getString(1);
        
        }catch(SQLException e){
            Login.lblestadoLogin.setText("");
        }
        return tipo_usuario;
    }   
               
//Consulta todos los indices y facturas
    public ResultSet buscaLlenarIndiceFactura() throws Exception {
        String sql="select f.IdFactura, dv.Cantidad, p.NombreProducto,dv.ValorTotal,f.FechaEmision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto";            
        return consultar(sql);
    }
    
//se buscan todas las facturas por ID
    public ResultSet buscaLlenarIndiceFacturaID(int idfactura) throws Exception {
        String sql= "select f.IdFactura, dv.Cantidad, p.NombreProducto,dv.ValorTotal,f.FechaEmision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto where f.IdFactura='"+idfactura+"'";
        return consultar(sql);
    }
    
//se buscan todas las facturas pode fecha
    public ResultSet buscaLlenarIndiceFacturaFecha(String fecha) throws Exception {
        String sql= "select f.IdFactura, dv.Cantidad, p.NombreProducto,dv.ValorTotal,f.FechaEmision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto where f.FechaEmision='"+fecha+"'";  
        return consultar(sql);
    }                          

//se buscan todos los productos buscados para llenar indice de BuscarProductoFactura
    public ResultSet LlenarIndiceBurcarProductoCategoria(String clave) throws Exception {
        String sql= "select p.IdProducto,p.NombreProducto,p.Marca,p.Precio,p.Stock from Producto p inner join Categoria c on p.IdCategoria = c.IdCategoria where c.descripcion='"+clave+"'";  
        return consultar(sql);
    }  
    
//Consulta de los productos por nombre usando like   
    public ResultSet buscarProducto(String nombre) throws Exception {
        nombre = '%' + nombre + '%';
        ps = c.prepareStatement("SELECT * FROM producto where nombreproducto like ?");
        ps.setString(1, nombre);
        rs = ps.executeQuery();
        return rs;
    }
//Consulta de los productos por nombre usando like   
    public ResultSet buscarTodosProducto() throws Exception {
        String sql = "select * from producto";
        return consultar(sql);
    }    
//consulta cantidad de productos
    public int consultarCantidad(int idproducto) throws Exception {
       int bdcantidad=0;
        s = c.createStatement();
        rs = s.executeQuery("select stock from Producto where IdProducto="+idproducto+"");
        while(rs.next()){
        bdcantidad=rs.getInt(1);
        }
        return bdcantidad;
    }
    
//Consulta de los clientes    
    public ResultSet buscarClientes(String cedula) throws Exception {
        
        ps = c.prepareStatement("SELECT * FROM Clientes where cedula= ?");
        ps.setString(1, cedula);
        rs = ps.executeQuery();
        return rs;
    }
    
//consulta id del cliente
    public String obteneriIDcliente() throws Exception {
    String idcliente="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdCliente) from Clientes");
        if(rs.next()){
        idcliente=rs.getString(1);
        }
        return idcliente;
    }
//consulta id del cliente por cedula
    public int obteneriIDclienteParametro(String cedula) throws Exception {
    int idcliente=0;
        s = c.createStatement();
        rs = s.executeQuery("select IdCliente from Clientes where cedula='"+cedula+"'");
        if(rs.next()){
        idcliente=rs.getInt(1);
        }
        return idcliente;
    }
//consulta booleano cliente por cedula
    public String compruebaCliente(String cedula) throws Exception {
    String cedula_cliente="";
        s = c.createStatement();
        rs = s.executeQuery("select cedula from Clientes where cedula='"+cedula+"'");
        if(rs.next()){            
            cedula_cliente=rs.getString(1);
        }
        return cedula_cliente;
    }    
//consulta id+1 del producto
    public String obteneriIdProducto() throws Exception {
        
        String idproducto="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdProducto)+1 from Producto");
        if(rs.next()){
        idproducto=rs.getString(1);
        }
        return idproducto;
    }
//consulta id del cliente
    public String obteneriIdCategoria() throws Exception {
        
        String idcategoria="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdCategoria)+1 from Categoria");
        if(rs.next()){
        idcategoria=rs.getString(1);
        }
        return idcategoria;
    }  
//consulta id del cliente
    public String obteneriIdSucursal() throws Exception {
        
        String idsucursal="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdEmpresa)+1 from Empresa");
        if(rs.next()){
        idsucursal=rs.getString(1);
        }
        return idsucursal;
    }
//consulta id del empleado con parametro  
    public int obteneriIDEmpleado(String usuario) throws Exception 
    {
    int idempleado=0;
        s = c.createStatement();
        rs = s.executeQuery("select IdVendedor from Vendedor where nombres='"+usuario+"'");
        if(rs.next()){
        idempleado=rs.getInt(1);
        }
        return idempleado;
    }

//consulta id del empleado con parametro  
    public ResultSet obtenerDatosEmpresa() throws Exception 
    {        
        String sql= "select NombreEmpresa,Ruc,Telefono from Empresa";  
        return consultar(sql);
    }
//consulta Codigo de la factura
    public String obtenerCodigoFactura() throws Exception 
    {
    String codigofactura="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdFactura)+1 from Factura");
        while(rs.next()){
        codigofactura=rs.getString(1);
        }
        return codigofactura;
    }     
//consulta id del empleado sin parametro
    public String obteneriIDEmpleadoNoParametro() throws Exception 
    {
    String idempleado="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdVendedor)+1 from Vendedor");
        if(rs.next()){
        idempleado=rs.getString(1);
        }
        return idempleado;
    }  
    
//Consulta todos los datos del empleado
    public ResultSet buscaTodosEmpleados() throws Exception {
        String sql="select * from Vendedor order by IdVendedor";            
        return consultar(sql);
    } 
//Consulta todos los datos del empleado
    public ResultSet buscaSucursales() throws Exception {
        String sql="select IdEmpresa,Ruc,NombreEmpresa,Telefono,Direccion from Empresa order by IdEmpresa ";            
        return consultar(sql);
    }
           
//Consultar id_emopleado y Tipo Empleado de Empleado para el jcombobox de TipoEmpleado    
    public ResultSet llenarComboTipoEmpleado() throws Exception {
        String sql="select IdTipo_Usuario,Tipo_Usuario from Tipo_Usuario";             
        return cn.consultar(sql);
    }
    
//Consultar id_empresa nombre empresa para el combo sucursal    
    public ResultSet llenarComboSucursal() throws Exception {
        String sql="select IdEmpresa, NombreEmpresa from Empresa";             
        return cn.consultar(sql);
    }

//Consultar id_categoria y descripcion de la ca categoria para el jcombobox de productos    
    public ResultSet llenarComboCategoria() throws Exception {
        String sql="select IdCategoria, descripcion from Categoria order by idcategoria";             
        return cn.consultar(sql);
    }
   
//consulta el nombre de usuario
    public String obtenerNombreUsuario(String cedula) throws Exception {
    String nombre="";
        s = c.createStatement();
        rs = s.executeQuery("select nombres from vendedor where cedula='"+cedula+"'");
        if(rs.next()){
        nombre=rs.getString(1);
        }
        c.close();
        return nombre;
    }
    
//Busqueda de reportes  
    public void Reportes(String Cadena){
    try{
         JasperReport reporte = JasperCompileManager.compileReport(Cadena); 
         JasperPrint print = JasperFillManager.fillReport(reporte,null,this.c);
         JasperViewer.viewReport(print,false);   
            
       }catch(JRException e){
          System.out.println("error"+e);   
        }
    }    
////////////////////////////////////////////////////////////////////////////////
///////////////////Metodo generico para consultas resul set ////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    public ResultSet consultar(String sql) { 
            try 
            { 
                s = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                rs = s.executeQuery(sql); 

            } catch (SQLException e) { 
                return null; 
            } 
            return rs; 
    }        
    
    
}//fin de la clase
