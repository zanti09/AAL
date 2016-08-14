package com.yahorave.vista.administracion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.yahorave.entidades.Usuario;
import com.yahorave.clientes.UsuarioCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "usuarioVista")
@ViewScoped
public class UsuarioVista implements Serializable {

    private List<Usuario> lstUsuarios;
    private Usuario usuario;
    private Usuario usuarioModificar;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.setDateFormat("yyyy-MM-dd").create();

    private UsuarioCliente usuarioCliente = new UsuarioCliente();

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        lstUsuarios = consultarUsuarios();    
    }

    public List<Usuario> getUsuarios() {
        return lstUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario getUsuarioModificar() {
        return usuarioModificar;
    }

    public void establecerUsuarioModificar(Usuario usuarioModificar) {
        this.usuarioModificar = usuarioModificar;
    }
    
    public List<Usuario> consultarUsuarios(){
        String strListaUsuarios = usuarioCliente.findAll(String.class);
        return gson.fromJson(strListaUsuarios, new TypeToken<List<Usuario>>() {
        }.getType());
    }
    
    public void eliminarUsuario(Usuario u){
        usuarioCliente.remove(u.getIdusuario().toString());
        lstUsuarios.remove(u);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido elimindo", null));
    }
    
    public void modificarUsuario() {
        usuarioCliente.edit(usuarioModificar, usuarioModificar.getIdusuario().toString());
        lstUsuarios = consultarUsuarios();
        usuarioModificar=new Usuario();
        RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido modificado", null));
    }

    public void guardarUsuario() {
        usuarioCliente.create(usuario);
        lstUsuarios.add(usuario);
        usuario = new Usuario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido guardado excitosamente",null));
    }
}
