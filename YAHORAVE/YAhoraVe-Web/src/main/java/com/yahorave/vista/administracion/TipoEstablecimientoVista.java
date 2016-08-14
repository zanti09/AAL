package com.yahorave.vista.administracion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.yahorave.entidades.TipoEstablecimiento;
import com.yahorave.clientes.TipoEstablecimientoCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "tipoEstablecimientosVista")
@ViewScoped
public class TipoEstablecimientoVista implements Serializable {

    private List<TipoEstablecimiento> lstEstablecimientos;
    private TipoEstablecimiento tipoEstablecimientos;
    private TipoEstablecimiento tipoEstablecimientosModificar;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.setDateFormat("yyyy-MM-dd").create();

    private TipoEstablecimientoCliente tipoEstablecimientoCliente = new TipoEstablecimientoCliente();

    @PostConstruct
    public void init() {
        tipoEstablecimientos = new TipoEstablecimiento();
        lstEstablecimientos = consultarEstablecimientos();
    }

    public List<TipoEstablecimiento> getTiposEstablecimientos() {
        return lstEstablecimientos;
    }

    public TipoEstablecimiento getTipoEstablecimiento() {
        return tipoEstablecimientos;
    }

    public void establecerTipoEstablecimientosModificar(TipoEstablecimiento tipoEstablecimientosModificar) {
        this.tipoEstablecimientosModificar = tipoEstablecimientosModificar;
    }

    public TipoEstablecimiento getTipoEstablecimientosModificar() {
        return tipoEstablecimientosModificar;
    }

    public List<TipoEstablecimiento> consultarEstablecimientos() {
        String strListaEstablecimientos = tipoEstablecimientoCliente.findAll(String.class);
        return gson.fromJson(strListaEstablecimientos, new TypeToken<List<TipoEstablecimiento>>() {
        }.getType());
    }

    public void guardarTipoEstablecimiento() {
        tipoEstablecimientoCliente.create(tipoEstablecimientos);
        lstEstablecimientos = consultarEstablecimientos();
        tipoEstablecimientos = new TipoEstablecimiento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido guardado excitosamente", null));
    }

    public void modificarTipoEstablecimiento() {
        tipoEstablecimientoCliente.edit(tipoEstablecimientosModificar, tipoEstablecimientosModificar.getIdtipoestablecimiento().toString());
        lstEstablecimientos = consultarEstablecimientos();
        tipoEstablecimientosModificar=new TipoEstablecimiento();        
        RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido modificado", null));
    }

    public void eliminarTipoEstablecimiento(TipoEstablecimiento te) {
        tipoEstablecimientoCliente.remove(te.getIdtipoestablecimiento().toString());
        lstEstablecimientos.remove(te);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido elimindo", null));
    }
}
