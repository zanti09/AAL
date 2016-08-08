/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahorave.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "establecimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Establecimiento.findAll", query = "SELECT e FROM Establecimiento e"),
    @NamedQuery(name = "Establecimiento.findByIdestablecimiento", query = "SELECT e FROM Establecimiento e WHERE e.idestablecimiento = :idestablecimiento"),
    @NamedQuery(name = "Establecimiento.findByNombreestablecimiento", query = "SELECT e FROM Establecimiento e WHERE e.nombreestablecimiento = :nombreestablecimiento"),
    @NamedQuery(name = "Establecimiento.findByUbicacionestablecimiento", query = "SELECT e FROM Establecimiento e WHERE e.ubicacionestablecimiento = :ubicacionestablecimiento"),
    @NamedQuery(name = "Establecimiento.findByDescestablecimiento", query = "SELECT e FROM Establecimiento e WHERE e.descestablecimiento = :descestablecimiento"),
    @NamedQuery(name = "Establecimiento.findByLatitud", query = "SELECT e FROM Establecimiento e WHERE e.latitud = :latitud"),
    @NamedQuery(name = "Establecimiento.findByLongitud", query = "SELECT e FROM Establecimiento e WHERE e.longitud = :longitud"),
    @NamedQuery(name = "Establecimiento.findByCategoria", query = "SELECT e FROM Establecimiento e WHERE e.categoria = :categoria")})
public class Establecimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestablecimiento")
    private Integer idestablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombreestablecimiento")
    private String nombreestablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ubicacionestablecimiento")
    private String ubicacionestablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descestablecimiento")
    private String descestablecimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Size(max = 50)
    @Column(name = "categoria")
    private String categoria;
    @JoinColumn(name = "idtipoestablecimiento", referencedColumnName = "idtipoestablecimiento")
    @ManyToOne(optional = false)
    private TipoEstablecimiento idtipoestablecimiento;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Establecimiento() {
    }

    public Establecimiento(Integer idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public Establecimiento(Integer idestablecimiento, String nombreestablecimiento, String ubicacionestablecimiento, String descestablecimiento, BigDecimal latitud, BigDecimal longitud) {
        this.idestablecimiento = idestablecimiento;
        this.nombreestablecimiento = nombreestablecimiento;
        this.ubicacionestablecimiento = ubicacionestablecimiento;
        this.descestablecimiento = descestablecimiento;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getIdestablecimiento() {
        return idestablecimiento;
    }

    public void setIdestablecimiento(Integer idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public String getNombreestablecimiento() {
        return nombreestablecimiento;
    }

    public void setNombreestablecimiento(String nombreestablecimiento) {
        this.nombreestablecimiento = nombreestablecimiento;
    }

    public String getUbicacionestablecimiento() {
        return ubicacionestablecimiento;
    }

    public void setUbicacionestablecimiento(String ubicacionestablecimiento) {
        this.ubicacionestablecimiento = ubicacionestablecimiento;
    }

    public String getDescestablecimiento() {
        return descestablecimiento;
    }

    public void setDescestablecimiento(String descestablecimiento) {
        this.descestablecimiento = descestablecimiento;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public TipoEstablecimiento getIdtipoestablecimiento() {
        return idtipoestablecimiento;
    }

    public void setIdtipoestablecimiento(TipoEstablecimiento idtipoestablecimiento) {
        this.idtipoestablecimiento = idtipoestablecimiento;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestablecimiento != null ? idestablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.idestablecimiento == null && other.idestablecimiento != null) || (this.idestablecimiento != null && !this.idestablecimiento.equals(other.idestablecimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yahorave.entidades.Establecimiento[ idestablecimiento=" + idestablecimiento + " ]";
    }
    
}
