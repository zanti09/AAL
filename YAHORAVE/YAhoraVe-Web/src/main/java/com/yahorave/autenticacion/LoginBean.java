package com.yahorave.autenticacion;

import com.yahorave.entidades.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for the implementation of the user logging
 *
 * @author XXXXX
 * @version 1.0.0
 * @since 1.0.0
 *
 */
@ManagedBean
@ApplicationScoped
public class LoginBean implements Serializable {

    private String strLoggeduser;
    private Usuario usuario;
    private static Logger log = Logger.getLogger(LoginBean.class.getName());
    
    public String getStrLoggeduser() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String strLoggeduser = request.getRemoteUser();
        return strLoggeduser;
    }

    public void setStrLoggeduser(String strLoggeduser) {
        this.strLoggeduser = strLoggeduser;
    }

    @PostConstruct
    public void init() {
        usuario=new Usuario();
    }

    /**
     * Logging method for validating users and profiles
     *
     * @since 1.0.0
     */
    public void login() {
        FacesMessage message;
//        try {
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            try {
//                request.logout();
//            } catch (ServletException e) {
//                log.log(Level.SEVERE, "Failed to logout user!", e);
//            }
//            request.login(user.getLoginUsr(), user.getPasswordUsr());
//            loggedUser = new Users();
//            for (Users usr : lstUsers) {
//                if (this.user.getLoginUsr().equals(usr.getLoginUsr())) {
//                    loggedUser = usr;
//                    break;
//                }
//            }
//            profileLoggedUser = "";
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/ECU-Web/ui/index.xhtml");
//        } catch (ServletException | IOException | SecurityException e) {
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Nombre de usuario o contraseña incorrectos");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//            e.printStackTrace();
//        }
    }
}
