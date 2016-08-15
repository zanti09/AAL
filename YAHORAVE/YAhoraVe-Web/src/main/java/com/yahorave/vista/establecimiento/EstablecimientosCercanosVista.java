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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author santi
 */
@ManagedBean(name = "establecimientosCercanosVista")
@ApplicationScoped
public class EstablecimientosCercanosVista implements Serializable {

    private List<Establecimiento> lstEstablecimiento;
    private Establecimiento est;
    private TipoEstablecimiento tipoEstablecimiento;
    private String salida="";
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
    private static final String URL_UBICACION_ESTABLECIMIENTOS = "/ui/ubicacion/establecimientosCercanos?faces-redirect=true";

    private MapModel advancedModel;
    private Marker marker;

    private EstablecimientoCliente establecimientoCliente = new EstablecimientoCliente();

    @PostConstruct
    public void init() {
        tipoEstablecimiento = new TipoEstablecimiento();
        advancedModel = new DefaultMapModel();
        lstEstablecimiento = new ArrayList<>();
        for (Establecimiento est : lstEstablecimiento) {
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

    public TipoEstablecimiento getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }    

    public Establecimiento getEst() {
        return est;
    }

    public void setEst(Establecimiento est) {
        this.est = est;
    }
    
    public String filtrarPorTipoEstablecimiento() {
        String strListaEstablecimientos = establecimientoCliente.findByTipoEstablecimiento(String.class, tipoEstablecimiento.getIdtipoestablecimiento().toString());
        lstEstablecimiento = gson.fromJson(strListaEstablecimientos, new TypeToken<List<Establecimiento>>() {
        }.getType());
        addMarkers(lstEstablecimiento);
        return URL_UBICACION_ESTABLECIMIENTOS;
    }
    
    public String filtrarPorSalida() {
        String strListaEstablecimientos = establecimientoCliente.findByUbicacionestablecimiento(String.class, salida);
        lstEstablecimiento = gson.fromJson(strListaEstablecimientos, new TypeToken<List<Establecimiento>>() {
        }.getType());
        addMarkers(lstEstablecimiento);
        return URL_UBICACION_ESTABLECIMIENTOS;
    }

    public String ubicarTodosEstablecimientos() {
        String strListaEstablecimientos = establecimientoCliente.findAll(String.class);
        lstEstablecimiento = gson.fromJson(strListaEstablecimientos, new TypeToken<List<Establecimiento>>() {
        }.getType());
        addMarkers(lstEstablecimiento);
        return URL_UBICACION_ESTABLECIMIENTOS;
    }

    public void addMarkers(List<Establecimiento> lstEstablecimientos) {
        advancedModel.getMarkers().clear();
        for (Establecimiento est : lstEstablecimiento) {
            LatLng coord = new LatLng(est.getLatitud().doubleValue(), est.getLongitud().doubleValue());
            advancedModel.addOverlay(new Marker(coord, est.getNombreestablecimiento(),est));
        }
    }
    public Establecimiento casEstablecimiento(Object obj){
        return (Establecimiento) obj;
    }
}
