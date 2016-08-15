package com.yahorave.vista.administracion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.yahorave.entidades.Menu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yahorave.clientes.MenuClientee;
import com.yahorave.entidades.Establecimiento;
import com.yahorave.entidades.Producto;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "menuVista")
@ViewScoped
public class MenuVista implements Serializable {

    private List<Producto> lstProductos;
    private List<Producto> lstProductosMenu;
    private List<Establecimiento> lstEstablecimiento;
    private List<Menu> lstMenus;
    private Menu menu;
    private Establecimiento establecimiento;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.setDateFormat("yyyy-MM-dd").create();

    private MenuClientee menuCliente = new MenuClientee();

    private ProductoVista productoVista = new ProductoVista();
    private EstablecimientoVista establecimientoVista = new EstablecimientoVista();

    @PostConstruct
    public void init() {
        establecimiento = new Establecimiento();
        lstEstablecimiento = establecimientoVista.consultarEstablecimientos();
        lstProductos = productoVista.consultarProductos();
        lstMenus=consultarMenus();
    }

    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public List<Producto> getLstProductosMenu() {
        return lstProductosMenu;
    }

    public void setLstProductosMenu(List<Producto> lstProductosMenu) {
        this.lstProductosMenu = lstProductosMenu;
    }

    public List<Establecimiento> getLstEstablecimiento() {
        return lstEstablecimiento;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public List<Menu> getLstMenus() {
        return lstMenus;
    }

    private List<Menu> consultarMenus(){
        String strListaMenus = menuCliente.findAll(String.class);
        return gson.fromJson(strListaMenus, new TypeToken<List<Producto>>() {
        }.getType());
    }
    public void guardarMenu() {
        for (Producto prd : lstProductosMenu) {
            menu = new Menu();
            menu.setIdproducto(prd);
            menu.setIdestablecimiento(establecimiento);
            menuCliente.create(menu);
        }
        establecimiento=new Establecimiento();
        setLstProductosMenu(new  ArrayList<Producto>());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido guardado excitosamente", null));
    }

    public void modificarMenu() {
        RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido modificado", null));
    }

    public void eliminarMenu(Menu te) {
        menuCliente.remove(te.getIdmenu().toString());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido elimindo", null));
    }
}
