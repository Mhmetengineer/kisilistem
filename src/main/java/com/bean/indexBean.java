
package com.bean;

import com.data.indexJPA;
import com.entity.Kisiler;
import com.entity.Numara;
import com.entity.User;
import com.filters.Util;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;


@ManagedBean(name="indexBean")
@SessionScoped

public class indexBean implements Serializable {

   private static List<Kisiler> kisilerr = new ArrayList<>();
   indexJPA ijpa = new indexJPA();
   private static String degisken;
 
   private static String ad;
   private static String sifre;
   static private Kisiler kisi=new Kisiler();
   static private Numara numara=new Numara();
   
   @ManyToOne(fetch = FetchType.EAGER)
   List<Numara> numaraList = new ArrayList<Numara>();

    public Kisiler getKisi() {
        return kisi;
    }

 
    public String getDegisken() {
        return degisken;
    }

    public void setDegisken(String degisken) {   
        indexBean.degisken = degisken;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void nuid(int id,String tel,String telaciklama){
        degisken=Integer.toString(id);
        bildiri("Değiştirilecek Telefon\n"+tel+"-"+telaciklama);
  
    }
    
   public void noSil(int id, int kisiid) throws IOException{
   
   kisi=ijpa.KisiKim(kisiid);
      numaraList = kisi.getNumaraList();
      numara=ijpa.NoCek(id);
      numara.setKisi(kisi);
       numaraList.remove(numara);
       kisi.setNumaraList(numaraList);
        ijpa.KisiNoDuzenle(kisi);

   ijpa.NumarayiSil(id);
   }
    
  

    public void setKisi(Kisiler kisi) {
        this.kisi = kisi; 
    }

    public Numara getNumara() {
        return numara;
    }

    public void setNumara(Numara numara) {
        this.numara = numara;
    }
   
    public List<Kisiler> getKisilerr() {
        return kisilerr;
    }

    public void setKisilerr(List<Kisiler> kisilerr) {
        this.kisilerr = kisilerr;
    }
   
    public List<Kisiler> getir(){
    kisilerr = ijpa.getKisiler();
    return kisilerr;
    }
    
  
   
    public void kisiKaydet() throws IOException{
 
 numaraList.add(numara);    
    kisi.setNumaraList(numaraList);
    ijpa.KisiKaydet(kisi);
    kisi=new Kisiler();
    numara = new Numara();

    }
    
     public void bildiri(String x){
    RequestContext.getCurrentInstance().update("bildiri");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", x));
    }
    
    public void Sil(int silid) throws IOException{
       ijpa.KisiSil(silid);
    }
    
    public void Duzenle(int id,int tip){
  
   ijpa.KisiDuzenle(degisken,id,tip);
    degisken="";
    }
    
    public void NoDuzenle(int id){
    ijpa.NoDuzenle(numara.getNuTel(),numara.getNuAciklama(),id);
    degisken=""; 
    numara.setNuAciklama("");
    numara.setNuTel("");
    }
    public void NoEkle(int id,String tel,String aciklama) throws IOException{
        
        
      kisi=ijpa.KisiKim(id);
      numaraList = kisi.getNumaraList();
      numara.setKisi(kisi);
       numaraList.add(numara);
       kisi.setNumaraList(numaraList);
        ijpa.KisiNoDuzenle(kisi);
   ijpa.NoEkle(numara);
   
   kisi=new Kisiler();
    numara.setNuAciklama("");
    numara.setNuTel("");
   
    }

    public List<Numara> getNumaraList() {
        return numaraList;
    }

    public void setNumaraList(List<Numara> numaraList) {
        this.numaraList = numaraList;
    }
    
    public User adminGetir(){
  return ijpa.AdminBilgileri();
    }
    
    public void adminDuzenle(int tip) throws IOException{
      
        ijpa.AdminDuzenle(degisken,sifre,tip);
       
        sifre="";degisken="";
    }
    public String cikisYap(){
    
    HttpSession hs= Util.getSession();
		hs.invalidate();
		return "/login?faces-redirect=true";
    }
    
    
    
    public String giris(){
       
    boolean durum=ijpa.GirisKontrol(ad, sifre);
    
    if(durum){ 
        HttpSession hs= Util.getSession();
        hs.setAttribute("username", ad);
        sifre="";ad="";
    return "/index?faces-redirect=true";
    }else{
        bildiri("Kullanıcı adınız veya şifreniz hatalı.");
        return "login";}
}
    
         public void mailyolla(){
          
    try {
       String email = ijpa.mail().getUserEmail();
       String sifre = ijpa.mail().getUserSifre();
       String ad = ijpa.mail().getUserAd();
          
			String from = "admin@rehberim.com";
			String pass = "1a2b3cd";
			String[] to = { email};
			String host = "mail.smtp2go.com";
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject("Şifre hatırlatma");
			message.setText("Kullanıcı adınız: "+ad +" şifreniz: "+sifre +". İyi günler dileriz.");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
                         bildiri("Lütfen sistemde kayıtlı mailinizi kontrol ediniz.(Spamlara da bakınız.)");
		} catch (Exception e) {
			e.printStackTrace();
		}
    
    
    }
    public indexBean() {
    }
    
}
