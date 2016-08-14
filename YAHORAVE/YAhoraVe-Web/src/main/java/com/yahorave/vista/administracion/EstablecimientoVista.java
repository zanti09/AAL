package com.yahorave.vista.administracion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.yahorave.entidades.Establecimiento;
import com.yahorave.clientes.EstablecimientoCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yahorave.entidades.Menu;
import com.yahorave.entidades.Producto;
import com.yahorave.entidades.TipoEstablecimiento;
import com.yahorave.entidades.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean(name = "establecimientoVista")
@ViewScoped
public class EstablecimientoVista implements Serializable {

    private List<Establecimiento> lstEstablecimiento;
    private Establecimiento establecimiento;
    private TipoEstablecimiento tipoEstablecimiento;
    private Menu menu;
    private List<Menu> lstMenu;
    private Producto productoAgregar;
    private Producto productoQuitar;
    private List<Producto> lstProductos;
    private List<Producto> lstProductosMenu;
    private ProductoVista productoVista = new ProductoVista();
    private Usuario usuario;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.setDateFormat("yyyy-MM-dd").create();

    private MapModel emptyModel;
    private String title;
    private double lat;
    private double lng;

    private EstablecimientoCliente establecimientoCliente = new EstablecimientoCliente();

    @PostConstruct
    public void init() {
        establecimiento = new Establecimiento();
        tipoEstablecimiento = new TipoEstablecimiento();
        usuario = new Usuario();
        lstMenu = new ArrayList<>();
        emptyModel = new DefaultMapModel();
        establecimiento.setIdtipoestablecimiento(new TipoEstablecimiento());
        establecimiento.setIdusuario(new Usuario());
        lstEstablecimiento = consultarEstablecimientos();
        lstProductos = productoVista.consultarProductos();
    }

    public List<Establecimiento> getEstablecimientos() {
        return lstEstablecimiento;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public TipoEstablecimiento getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setProductoAgregar(Producto productoAgregar) {
        this.productoAgregar = productoAgregar;
    }

    public void setProductoQuitar(Producto productoQuitar) {
        this.productoQuitar = productoQuitar;
    }

    public Producto getProductoAgregar() {
        return productoAgregar;
    }

    public Producto getProductoQuitar() {
        return productoQuitar;
    }
    
    

    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public List<Producto> getLstProductosMenu() {
        return lstProductosMenu;
    }

    public List<Establecimiento> consultarEstablecimientos() {
        String strListaEstablecimientos = establecimientoCliente.findAll(String.class);
        return gson.fromJson(strListaEstablecimientos, new TypeToken<List<Establecimiento>>() {
        }.getType());
    }

    public void agregarProductoMenu() {
        if (productoAgregar != null) {
            lstProductosMenu.add(productoAgregar);
            lstProductos.remove(productoAgregar);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccione un producto", null));
        }

    }

    public void quitarProductoMenu() {
        if (productoQuitar != null) {
            lstProductos.add(productoQuitar);
            lstProductosMenu.remove(productoQuitar);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccione un producto", null));
        }

    }

    public void guardarEstablecimiento() {
        establecimiento.setIdusuario(usuario);
        establecimiento.setIdtipoestablecimiento(tipoEstablecimiento);
        establecimientoCliente.create(establecimiento);
        lstEstablecimiento = consultarEstablecimientos();
        establecimiento = new Establecimiento();
        emptyModel.getMarkers().clear();
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido guardado excitosamente", null));
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        establecimiento.setLongitud(BigDecimal.valueOf(lng));
        establecimiento.setLatitud(BigDecimal.valueOf(lat));
        emptyModel.addOverlay(marker);
    }
}
