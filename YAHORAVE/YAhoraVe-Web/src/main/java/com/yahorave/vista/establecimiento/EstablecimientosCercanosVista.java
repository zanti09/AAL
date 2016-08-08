/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahorave.vista.establecimiento;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yahorave.clientes.EstablecimientoCliente;
import com.yahorave.entidades.Establecimiento;
import com.yahorave.entidades.TipoEstablecimiento;
import com.yahorave.entidades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author santi
 */
@ManagedBean(name="establecimientosCercanosVista")
@ViewScoped
public class EstablecimientosCercanosVista implements Serializable {
    private List<Establecimiento> lstEstablecimiento;
    private Establecimiento establecimiento;
    private TipoEstablecimiento tipoEstablecimiento;
    private Usuario usuario;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.setDateFormat("yyyy-MM-dd").create();

    private MapModel advancedModel; 
    private Marker marker;
    private MapModel emptyModel;
    private String title;
    private double lat;
    private double lng;
    private boolean markerEmpty=true;

    private EstablecimientoCliente establecimientoCliente = new EstablecimientoCliente();

    @PostConstruct
    public void init() {
        advancedModel = new DefaultMapModel();
        
        String strListaEstablecimientos = establecimientoCliente.findAll(String.class);
        lstEstablecimiento = gson.fromJson(strListaEstablecimientos, new TypeToken<List<Establecimiento>>() {
        }.getType());
        for(Establecimiento est:lstEstablecimiento){
            LatLng coord = new LatLng(est.getLatitud().doubleValue(), est.getLongitud().doubleValue());
            advancedModel.addOverlay(new Marker(coord, est.getNombreestablecimiento()));
        }
    }
    
    public MapModel getAdvancedModel() {
        return advancedModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
      
    public Marker getMarker() {
        return marker;
    }

    public List<Establecimiento> getEstablecimientos() {
        return lstEstablecimiento;
    }
    
    public Establecimiento getEstablecimiento(){
        return establecimiento;
    }

    public TipoEstablecimiento getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isMarkerEmpty() {
        return markerEmpty;
    }   

    public void inicializarEstablecimiento() {
        establecimiento = new Establecimiento();
    }

    public void guardarEstablecimiento() {
        establecimiento.setIdusuario(usuario);
        establecimiento.setIdtipoestablecimiento(tipoEstablecimiento);
        establecimientoCliente.create(establecimiento);
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
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
}
