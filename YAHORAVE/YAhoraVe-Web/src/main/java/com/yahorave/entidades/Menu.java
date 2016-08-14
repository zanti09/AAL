/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahorave.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByIdmenu", query = "SELECT m FROM Menu m WHERE m.idmenu = :idmenu"),
    @NamedQuery(name = "Menu.findByDescmenu", query = "SELECT m FROM Menu m WHERE m.descmenu = :descmenu"),
    @NamedQuery(name = "Menu.findByTiempopreparacion", query = "SELECT m FROM Menu m WHERE m.tiempopreparacion = :tiempopreparacion")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmenu")
    private Integer idmenu;
    @Size(max = 100)
    @Column(name = "descmenu")
    private String descmenu;
    @Column(name = "tiempopreparacion")
    private BigInteger tiempopreparacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmenu")
    private List<Comentario> comentarioList;
    @JoinColumn(name = "idestablecimiento", referencedColumnName = "idestablecimiento")
    @ManyToOne(optional = false)
    private Establecimiento idestablecimiento;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmenu")
    private List<RankingMenu> rankingMenuList;

    public Menu() {
    }

    public Menu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getDescmenu() {
        return descmenu;
    }

    public void setDescmenu(String descmenu) {
        this.descmenu = descmenu;
    }

    public BigInteger getTiempopreparacion() {
        return tiempopreparacion;
    }

    public void setTiempopreparacion(BigInteger tiempopreparacion) {
        this.tiempopreparacion = tiempopreparacion;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public Establecimiento getIdestablecimiento() {
        return idestablecimiento;
    }

    public void setIdestablecimiento(Establecimiento idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    @XmlTransient
    public List<RankingMenu> getRankingMenuList() {
        return rankingMenuList;
    }

    public void setRankingMenuList(List<RankingMenu> rankingMenuList) {
        this.rankingMenuList = rankingMenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yahorave.entidades.Menu[ idmenu=" + idmenu + " ]";
    }
    
}
