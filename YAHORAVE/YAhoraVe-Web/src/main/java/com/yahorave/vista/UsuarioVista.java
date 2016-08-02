package com.yahorave.vista;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.yahorave.entidades.Usuario;
import com.yahorave.clientes.UsuarioCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
 
@ManagedBean(name="usuarioVista")
@ViewScoped
public class UsuarioVista implements Serializable {
     
    private List<Usuario> lstUsuarios;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
    
    private UsuarioCliente usuarioCliente=new UsuarioCliente();
 
    @PostConstruct
    public void init() {
        String strListaUsuarios= usuarioCliente.findAll(String.class);
        lstUsuarios=gson.fromJson(strListaUsuarios, new TypeToken<List<Usuario>>() {
        }.getType());
    }
     
    public List<Usuario> getUsuarios() {
        return lstUsuarios;
    }
}
