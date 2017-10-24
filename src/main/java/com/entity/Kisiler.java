/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hp
 */
@Entity
@Table(catalog = "ajanda", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kisiler.findAll", query = "SELECT k FROM Kisiler k")
    , @NamedQuery(name = "Kisiler.findByKisiId", query = "SELECT k FROM Kisiler k WHERE k.kisiId = :kisiId")
    , @NamedQuery(name = "Kisiler.findByKisiAd", query = "SELECT k FROM Kisiler k WHERE k.kisiAd = :kisiAd")
    , @NamedQuery(name = "Kisiler.findByKisiSoyad", query = "SELECT k FROM Kisiler k WHERE k.kisiSoyad = :kisiSoyad")
    , @NamedQuery(name = "Kisiler.findByKisiAdres", query = "SELECT k FROM Kisiler k WHERE k.kisiAdres = :kisiAdres")
    , @NamedQuery(name = "Kisiler.findByKisiMail", query = "SELECT k FROM Kisiler k WHERE k.kisiMail = :kisiMail")})
public class Kisiler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kisi_id", nullable = false)
    private Integer kisiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "kisi_ad", nullable = false, length = 255)
    private String kisiAd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "kisi_soyad", nullable = false, length = 255)
    private String kisiSoyad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "kisi_adres", nullable = false, length = 255)
    private String kisiAdres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "kisi_mail", nullable = false, length = 255)
    private String kisiMail;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.EAGER,mappedBy = "kisi")
    private List<Numara> numaraList;

    public Kisiler() {
    }

    @PrePersist
    private void prePersistMethod(){
    for(Numara numara:numaraList){
    numara.setKisi(this);
    }}
    
     @PreUpdate
    private void preUpdateMethod(){
    for(Numara numara:numaraList){
    numara.setKisi(this);
    }}
    
   

    public Kisiler(Integer kisiId) {
        this.kisiId = kisiId;
    }

    public Kisiler(Integer kisiId, String kisiAd, String kisiSoyad, String kisiAdres, String kisiMail) {
        this.kisiId = kisiId;
        this.kisiAd = kisiAd;
        this.kisiSoyad = kisiSoyad;
        this.kisiAdres = kisiAdres;
        this.kisiMail = kisiMail;
    }

    public Integer getKisiId() {
        return kisiId;
    }

    public void setKisiId(Integer kisiId) {
        this.kisiId = kisiId;
    }

    public String getKisiAd() {
        return kisiAd;
    }

    public void setKisiAd(String kisiAd) {
        this.kisiAd = kisiAd;
    }

    public String getKisiSoyad() {
        return kisiSoyad;
    }

    public void setKisiSoyad(String kisiSoyad) {
        this.kisiSoyad = kisiSoyad;
    }

    public String getKisiAdres() {
        return kisiAdres;
    }

    public void setKisiAdres(String kisiAdres) {
        this.kisiAdres = kisiAdres;
    }

    public String getKisiMail() {
        return kisiMail;
    }

    public void setKisiMail(String kisiMail) {
        this.kisiMail = kisiMail;
    }

    @XmlTransient
    public List<Numara> getNumaraList() {
        return numaraList;
    }

    public void setNumaraList(List<Numara> numaraList) {
        this.numaraList = numaraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kisiId != null ? kisiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kisiler)) {
            return false;
        }
        Kisiler other = (Kisiler) object;
        if ((this.kisiId == null && other.kisiId != null) || (this.kisiId != null && !this.kisiId.equals(other.kisiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Kisiler[ kisiId=" + kisiId + " ]";
    }
    
}
