/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahorave.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "ranking_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankingMenu.findAll", query = "SELECT r FROM RankingMenu r"),
    @NamedQuery(name = "RankingMenu.findByIdrankingmenu", query = "SELECT r FROM RankingMenu r WHERE r.idrankingmenu = :idrankingmenu")})
public class RankingMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrankingmenu")
    private Integer idrankingmenu;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(optional = false)
    private Menu idmenu;
    @JoinColumn(name = "idranking", referencedColumnName = "idranking")
    @ManyToOne(optional = false)
    private Ranking idranking;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public RankingMenu() {
    }

    public RankingMenu(Integer idrankingmenu) {
        this.idrankingmenu = idrankingmenu;
    }

    public Integer getIdrankingmenu() {
        return idrankingmenu;
    }

    public void setIdrankingmenu(Integer idrankingmenu) {
        this.idrankingmenu = idrankingmenu;
    }

    public Menu getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Menu idmenu) {
        this.idmenu = idmenu;
    }

    public Ranking getIdranking() {
        return idranking;
    }

    public void setIdranking(Ranking idranking) {
        this.idranking = idranking;
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
        hash += (idrankingmenu != null ? idrankingmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankingMenu)) {
            return false;
        }
        RankingMenu other = (RankingMenu) object;
        if ((this.idrankingmenu == null && other.idrankingmenu != null) || (this.idrankingmenu != null && !this.idrankingmenu.equals(other.idrankingmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yahorave.entidades.RankingMenu[ idrankingmenu=" + idrankingmenu + " ]";
    }
    
}
