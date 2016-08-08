package com.yahorave.vista;

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
 
@ManagedBean(name="tipoEstablecimientosVista")
@ViewScoped
public class TipoEstablecimientoVista implements Serializable {
     
    private List<TipoEstablecimiento> lstEstablecimientos;
    private TipoEstablecimiento tipoEstablecimientos;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
    
    private TipoEstablecimientoCliente usuarioCliente=new TipoEstablecimientoCliente();
 
    @PostConstruct
    public void init() {
        tipoEstablecimientos=new TipoEstablecimiento();
        String strListaEstablecimientos= usuarioCliente.findAll(String.class);
        lstEstablecimientos=gson.fromJson(strListaEstablecimientos, new TypeToken<List<TipoEstablecimiento>>() {
        }.getType());
    }
     
    public List<TipoEstablecimiento> getTiposEstablecimientos() {
        return lstEstablecimientos;
    }
    
    public TipoEstablecimiento getTipoEstablecimiento(){
        return tipoEstablecimientos;
    }
    
    public void inicializarEstablecimientos(){
        tipoEstablecimientos=new TipoEstablecimiento();
    }
    
    public void guardarTipoEstablecimiento(){
        usuarioCliente.create(tipoEstablecimientos);
    }
}
