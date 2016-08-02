/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahorave.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "tipo_establecimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstablecimiento.findAll", query = "SELECT t FROM TipoEstablecimiento t"),
    @NamedQuery(name = "TipoEstablecimiento.findByIdtipoestablecimiento", query = "SELECT t FROM TipoEstablecimiento t WHERE t.idtipoestablecimiento = :idtipoestablecimiento"),
    @NamedQuery(name = "TipoEstablecimiento.findByTipoestablecimiento", query = "SELECT t FROM TipoEstablecimiento t WHERE t.tipoestablecimiento = :tipoestablecimiento")})
public class TipoEstablecimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoestablecimiento")
    private Integer idtipoestablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipoestablecimiento")
    private String tipoestablecimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoestablecimiento")
    private List<Establecimiento> establecimientoList;

    public TipoEstablecimiento() {
    }

    public TipoEstablecimiento(Integer idtipoestablecimiento) {
        this.idtipoestablecimiento = idtipoestablecimiento;
    }

    public TipoEstablecimiento(Integer idtipoestablecimiento, String tipoestablecimiento) {
        this.idtipoestablecimiento = idtipoestablecimiento;
        this.tipoestablecimiento = tipoestablecimiento;
    }

    public Integer getIdtipoestablecimiento() {
        return idtipoestablecimiento;
    }

    public void setIdtipoestablecimiento(Integer idtipoestablecimiento) {
        this.idtipoestablecimiento = idtipoestablecimiento;
    }

    public String getTipoestablecimiento() {
        return tipoestablecimiento;
    }

    public void setTipoestablecimiento(String tipoestablecimiento) {
        this.tipoestablecimiento = tipoestablecimiento;
    }

    @XmlTransient
    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoestablecimiento != null ? idtipoestablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstablecimiento)) {
            return false;
        }
        TipoEstablecimiento other = (TipoEstablecimiento) object;
        if ((this.idtipoestablecimiento == null && other.idtipoestablecimiento != null) || (this.idtipoestablecimiento != null && !this.idtipoestablecimiento.equals(other.idtipoestablecimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yahorave.entidades.TipoEstablecimiento[ idtipoestablecimiento=" + idtipoestablecimiento + " ]";
    }
    
}
