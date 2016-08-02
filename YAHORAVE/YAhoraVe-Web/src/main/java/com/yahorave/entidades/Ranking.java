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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "ranking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ranking.findAll", query = "SELECT r FROM Ranking r"),
    @NamedQuery(name = "Ranking.findByIdranking", query = "SELECT r FROM Ranking r WHERE r.idranking = :idranking"),
    @NamedQuery(name = "Ranking.findByValranking", query = "SELECT r FROM Ranking r WHERE r.valranking = :valranking")})
public class Ranking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idranking")
    private Integer idranking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valranking")
    private BigInteger valranking;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idranking")
    private List<RankingMenu> rankingMenuList;

    public Ranking() {
    }

    public Ranking(Integer idranking) {
        this.idranking = idranking;
    }

    public Ranking(Integer idranking, BigInteger valranking) {
        this.idranking = idranking;
        this.valranking = valranking;
    }

    public Integer getIdranking() {
        return idranking;
    }

    public void setIdranking(Integer idranking) {
        this.idranking = idranking;
    }

    public BigInteger getValranking() {
        return valranking;
    }

    public void setValranking(BigInteger valranking) {
        this.valranking = valranking;
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
        hash += (idranking != null ? idranking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ranking)) {
            return false;
        }
        Ranking other = (Ranking) object;
        if ((this.idranking == null && other.idranking != null) || (this.idranking != null && !this.idranking.equals(other.idranking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yahorave.entidades.Ranking[ idranking=" + idranking + " ]";
    }
    
}
