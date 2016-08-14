package com.yahorave.vista.administracion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.yahorave.entidades.Producto;
import com.yahorave.clientes.TipoEstablecimientoCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yahorave.clientes.ProductoCliente;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "productosVista")
@ViewScoped
public class ProductoVista implements Serializable {

    private List<Producto> lstProductos;
    private Producto producto;
    private Producto productoModificar;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.setDateFormat("yyyy-MM-dd").create();

    private ProductoCliente productoCliente = new ProductoCliente();

    @PostConstruct
    public void init() {
        producto = new Producto();
        lstProductos = consultarProductos();
    }

    public List<Producto> getProductos() {
        return lstProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void establecerProductoModificar(Producto tipoEstablecimientosModificar) {
        this.productoModificar = tipoEstablecimientosModificar;
    }

    public Producto getProductoModificar() {
        return productoModificar;
    }

    public List<Producto> consultarProductos() {
        String strListaEstablecimientos = productoCliente.findAll(String.class);
        return gson.fromJson(strListaEstablecimientos, new TypeToken<List<Producto>>() {
        }.getType());
    }

    public void guardarProducto() {
        productoCliente.create(producto);
        lstProductos = consultarProductos();
        producto = new Producto();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido guardado excitosamente", null));
    }

    public void modificarProducto() {
        productoCliente.edit(productoModificar, productoModificar.getIdproducto().toString());
        lstProductos = consultarProductos();
        productoModificar=new Producto();        
        RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido modificado", null));
    }

    public void eliminarProducto(Producto p) {
        productoCliente.remove(p.getIdproducto().toString());
        lstProductos.remove(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido elimindo", null));
    }
}
