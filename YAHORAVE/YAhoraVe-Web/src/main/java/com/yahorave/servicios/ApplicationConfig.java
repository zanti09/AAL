/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahorave.servicios;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author santi
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.yahorave.servicios.ComentarioFacadeREST.class);
        resources.add(com.yahorave.servicios.EstablecimientoFacadeREST.class);
        resources.add(com.yahorave.servicios.MenuFacadeREST.class);
        resources.add(com.yahorave.servicios.PerfilFacadeREST.class);
        resources.add(com.yahorave.servicios.PerfilUsuarioFacadeREST.class);
        resources.add(com.yahorave.servicios.ProductoFacadeREST.class);
        resources.add(com.yahorave.servicios.RankingFacadeREST.class);
        resources.add(com.yahorave.servicios.RankingMenuFacadeREST.class);
        resources.add(com.yahorave.servicios.TipoEstablecimientoFacadeREST.class);
        resources.add(com.yahorave.servicios.UsuarioFacadeREST.class);
    }
    
}
