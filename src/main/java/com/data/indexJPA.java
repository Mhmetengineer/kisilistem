/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;


import com.entity.Kisiler;
import com.entity.Numara;
import com.entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;


public class indexJPA {

    public void bildiri(String x){
    RequestContext.getCurrentInstance().update("bildiri");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", x));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
  
    private EntityManager em;
    private EntityManagerFactory emf;
    User user=null;
    
    private List<Kisiler> kisiler = new ArrayList<>();
    private List<Numara> numaralar = new ArrayList<>();
    
     public indexJPA() {
        emf = Persistence.createEntityManagerFactory("kisilistem");
        em = emf.createEntityManager();
        em.getTransaction().begin();
   
    }
   
     
        public List<Kisiler> getKisiler(){
        try {
            kisiler = em.createNamedQuery("Kisiler.findAll",Kisiler.class).getResultList();
            return kisiler;
        } catch (Exception e) {
            return kisiler;
        }}

        public void KisiKaydet(Kisiler kisi) throws IOException{
        try { 
            em.persist(kisi);
            em.getTransaction().commit();
            bildiri("Kişi başarıyla kaydedildi.");reload();
            em.flush();
        } catch (Exception e) {    
        }finally{
        em.close();emf.close();   
        }}
             
            
        public void KisiSil(int kisiid) throws IOException{
        try {
            Kisiler silkisi=em.createQuery("Select k from Kisiler k where k.kisiId = "+kisiid, Kisiler.class).getSingleResult();
            em.remove(silkisi);
            em.getTransaction().commit();
            bildiri("Kişi silindi.");
              } catch (Exception e) {
              }finally{
                 em.close();
                 emf.close();
                 reload();
                 }}
          
          
          public void reload() throws IOException {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
}

    public void KisiDuzenle(String veri,int id, int tip) {
      
        try {            
            Kisiler duzenle=em.createQuery("Select k from Kisiler k where k.kisiId = "+id, Kisiler.class).getSingleResult();
            switch(tip){
                case 1:duzenle.setKisiAd(veri);break;
                case 2:duzenle.setKisiSoyad(veri);break;
                case 3:duzenle.setKisiMail(veri);break;
                case 4:duzenle.setKisiAdres(veri);break;    
            }
            bildiri("Düzenleme işleminiz başarılı.");
            em.getTransaction().commit();
            reload();
        } catch (IOException e) {
           
        }
    }
    
    public void KisiNoDuzenle(Kisiler kisi) throws IOException{
    em.getTransaction().commit();
          
    }
    
    public void NoDuzenle(String tel,String aciklama,int id){
        try {
            Numara noduzenle = em.createQuery("Select n from Numara n where n.nuId = "+id, Numara.class).getSingleResult();
           boolean flag=false;
           if(tel.length()>=10) { noduzenle.setNuTel(tel); flag=true;}
            if(aciklama.length()>=1) { noduzenle.setNuAciklama(aciklama); flag=true;}
             if(!flag){bildiri("Düzenleme işlemi başarısız.");}
                em.getTransaction().commit();
                reload();
        } catch (IOException e) {
        }
    
    }
    
        public Numara NoCek(int id){
            Numara no = em.createQuery("Select n from Numara n where n.nuId = "+id, Numara.class).getSingleResult();
     return no;
    
    }
    
    
    public void NoEkle(Numara nu) throws IOException {
        try {
          
             em.persist(nu);
            em.getTransaction().commit();
       
        } catch (Exception e) {
        }finally{
                 em.close();
                 emf.close();
                 reload();
                 }
    }
    
   public Kisiler KisiKim(int id){
     
     Kisiler kisim=em.createQuery("Select k from Kisiler k where k.kisiId = "+id, Kisiler.class).getSingleResult();
     return kisim;
    }
   
   public boolean GirisKontrol(String ad,String sifre){
       
       try {  
            user=em.createQuery("SELECT u FROM User u WHERE u.userAd = \""+ad+"\" and u.userSifre =\""+sifre+"\"",User.class).getSingleResult();
            if(user != null) 
            { return true;}
            else 
            { return false;}
       } catch (Exception e) {
           return false;
       }
   }
   
      public User mail(){  
        try {
          user=em.createQuery("SELECT u FROM User u WHERE u.userId = 1",User.class).getSingleResult(); 
        return user;
        } catch (Exception e) {
       return user;
        }
    }

    public User AdminBilgileri() {
        user = em.createNamedQuery("User.findAll",User.class).getSingleResult();
        return user;
        
    }

    public void AdminDuzenle(String veri,String Sifre,int tip) throws IOException {
        
        if(Sifre.equals(AdminBilgileri().getUserSifre())){
        if(tip==1)AdminBilgileri().setUserAd(veri);
        if(tip==2)AdminBilgileri().setUserEmail(veri);
        if(tip==3)AdminBilgileri().setUserSifre(veri);
        bildiri("İşleminiz başarılı.");
            em.getTransaction().commit();
             reload();     
        }
        else 
            bildiri("İşleminiz başarısız.Şifreniz yanlış.");
             reload();     
    }

    public void NumarayiSil(int id) throws IOException {
 
  try {       
        Numara silnumara=em.createQuery("Select n from Numara n where n.nuId = "+id, Numara.class).getSingleResult();
            em.remove(silnumara);
            em.getTransaction().commit();
            bildiri("Numara Silindi");
            em.close();
            emf.close();
            } catch (Exception e) {     
            }finally{
             reload();
                 }}
 
}
