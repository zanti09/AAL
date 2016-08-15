/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahorave.servicios;

import com.yahorave.entidades.Establecimiento;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author santi
 */
@Stateless
@Path("com.yahorave.entidades.establecimiento")
public class EstablecimientoFacadeREST extends AbstractFacade<Establecimiento> {

    @PersistenceContext(unitName = "com.mycompany_YAhoraVe-Web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EstablecimientoFacadeREST() {
        super(Establecimiento.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Establecimiento entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Establecimiento entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Establecimiento find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Establecimiento> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Establecimiento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("findByTipoEstablecimiento/{idtipoestablecimiento}")
    @Produces({"application/json"})
    public List<Establecimiento> findByTipoEstablecimiento(@PathParam("idtipoestablecimiento") Integer idtipoestablecimiento) {
        try {//             
            TypedQuery<Establecimiento> query = em.createNamedQuery("Establecimiento.findByTipoEstablecimiento", Establecimiento.class);
            query.setParameter("idtipoestablecimiento", idtipoestablecimiento);
            List<Establecimiento> result = query.getResultList();
            return result;
        } catch (javax.persistence.NoResultException nr) {
            Logger.getLogger(EstablecimientoFacadeREST.class.getName()).severe(nr.getMessage());
        }
        return null;
    }

    @GET
    @Path("findByUbicacionestablecimiento/{ubicacionestablecimiento}")
    @Produces({"application/json"})
    public List<Establecimiento> findByUbicacionestablecimiento(@PathParam("ubicacionestablecimiento") String ubicacionestablecimiento) {
        try {//             
            TypedQuery<Establecimiento> query = em.createNamedQuery("Establecimiento.findByUbicacionestablecimiento", Establecimiento.class);
            query.setParameter("ubicacionestablecimiento", ubicacionestablecimiento);
            List<Establecimiento> result = query.getResultList();
            return result;
        } catch (javax.persistence.NoResultException nr) {
            Logger.getLogger(EstablecimientoFacadeREST.class.getName()).severe(nr.getMessage());
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
