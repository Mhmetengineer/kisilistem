/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hp
 */
@Entity
@Table(catalog = "ajanda", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Numara.findAll", query = "SELECT n FROM Numara n")
    , @NamedQuery(name = "Numara.findByNuId", query = "SELECT n FROM Numara n WHERE n.nuId = :nuId")
    , @NamedQuery(name = "Numara.findByNuTel", query = "SELECT n FROM Numara n WHERE n.nuTel = :nuTel")
    , @NamedQuery(name = "Numara.findByNuAciklama", query = "SELECT n FROM Numara n WHERE n.nuAciklama = :nuAciklama")})
public class Numara implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nu_id", nullable = false)
    private Integer nuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nu_tel", nullable = false, length = 255)
    private String nuTel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nu_aciklama", nullable = false, length = 255)
    private String nuAciklama;
    @JoinColumn(name = "kisi_id", referencedColumnName = "kisi_id", nullable = false)
    @ManyToOne(optional = false)
    private Kisiler kisi;

    public Numara() {
    }

    public Numara(Integer nuId) {
        this.nuId = nuId;
    }

    public Numara(Integer nuId, String nuTel, String nuAciklama) {
        this.nuId = nuId;
        this.nuTel = nuTel;
        this.nuAciklama = nuAciklama;
    }

    public Integer getNuId() {
        return nuId;
    }

    public void setNuId(Integer nuId) {
        this.nuId = nuId;
    }

    public String getNuTel() {
        return nuTel;
    }

    public void setNuTel(String nuTel) {
        this.nuTel = nuTel;
    }

    public String getNuAciklama() {
        return nuAciklama;
    }

    public void setNuAciklama(String nuAciklama) {
        this.nuAciklama = nuAciklama;
    }

    public Kisiler getKisi() {
        return kisi;
    }

    public void setKisi(Kisiler kisi) {
        this.kisi = kisi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nuId != null ? nuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Numara)) {
            return false;
        }
        Numara other = (Numara) object;
        if ((this.nuId == null && other.nuId != null) || (this.nuId != null && !this.nuId.equals(other.nuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Numara[ nuId=" + nuId + " ]";
    }
    
}
