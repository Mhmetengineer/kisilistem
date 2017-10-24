# kisilistem
---
***Özet*** 
*JSF,JPA,MAVEN kullanarak adres defteri yapılması. *

----------
####  **Giriş** 
Uygulama da her bir kişinin ad,soyad,e-mail,adres ve telefon bilgisi(bir veya daha fazla) tutulmaktadır. Bu veriler üzerinde ekleme, düzenleme ve silme işlemleri yapılabilmektedir.
####  **Sistemler ve Temel Çalışma Prensibi** 
>- Kullanıcı giriş sayfasından girişi başarılı yapmadığı sürece kişilerin bilgilerinin bulunduğu ana sayfaya giriş yapamaz. 
>- Kullanıcı şifresini unuttuğu taktirde giriş ekranından "şifremi unuttum" seçeneğini kullandığı anda sistemde kayıtlı mail adresine giriş bilgileri gönderilecektir.
>- Kullanıcı başarılı bir şekilde giriş yaptıktan sonra yeni kişi ekleyebilir,var olan kişinin bilgilerini düzenleyebilir ve silebilir. 
>- Kullanıcı Ad-Soyad bilgileri ile arama yapabilir.
>- Daha fazla detay için [videoyu](https://www.youtube.com/watch?v=wtgCObFX6Ks&t=7s) izleyebilirsiniz. 
#### **Teknik Seçimler**
Uygulamada **JSF**,**JPA**,**MAVEN**,**PRIMEFACES**,**HTML 5**,**CSS** ve **GLASSFISH SERVER** sistemleri kullanılmıştır. 
> **JSF** Uygulamadaki ön yüz için kullanılmıştır. **PRIMEFACES** ile kullanımda oldukça pratik ve kullanışlıdır. 
> **JPA** kullanmak bizi sql yüklerinden kurtarıyor. Nesnelerin ilişkilendirilmesini sağlıyor ve sql ile bağlantılarımı class'lar üzerinden gerçekleştirebiliyoruz.
> **MAVEN** kullanımındaki amaç ise kütüphane ve IDE bağımlılığını ortadan kaldırmaktı. 
> **GLASSFISH SERVER** 'i tercih ettim. Daha az sorun çıkartması ve yönetici panelinin kullanılabilirliği göz önüne alındığında diğerlerinden bir adım öne çıktı.

>*Uygulama içinde kullanılan kısıtlamalar ve tercihler.
>- Default olarak giriş bilgileri kullanıcı adı mehmet şifre 1234 'dür.
> - Girilen verilerin uzunluğu en fazla 255 karakterden oluşabilir.
> - Telefon numarası (yurt içi ve yurt dışı kayıtlar göz önüne alındığından dolayı) en az 10 karakterden oluşmalıdır. Telefon numarası eklerken "+ -" gibi sembol ve boşluk kullanımına izin verilmiştir.
> - İsim - Soyisim ekleme ve değiştirme kısımlarında minimum 2 karakter girilmesi gerekmektedir.
> - Yönetici şifresi en az 3 karakterden oluşmalıdır.
> - Eklenecek veya değiştirilecek veri boş olamaz. 
>-  İsme ve soysime göre arama yapılırken primefacesin sağladığı imkanlardan faydalandım. Böylece sorgu çalıştırmadan aradığımız veriye ulaştım. 
####<i class="icon-wrench"></i> **Kurulum ve Sistem Gereksinimleri**
> Glassfish Server ile kurulum.
> - Sistemimizde Glassfish Server 4.1 veya üstü kurulu olması gerekir.
> - Glassfish başlatılır ve yönetini paneline giriş yapılır.(Default olarak 4848 portunu kullanarak yönetini paneline giriş yapabiliriz. http://localhost:4848 )
> - Panelden solda Applications ardından deploy seçeneği seçilir. 
> - Deploy seçeneği seçildikten sonra projelerimizin içinde bulunan "kisilistem-1.0-SNAPSHOT.war" adlı dosya seçilir. Burdaki menüde Virtual Servers: seçeneğinden server seçeneği seçilir. Başka işlem yapmamıza gerek yoktur.
> -  Projeyi yükleme işleminden sonra projenin veritabanı belgesini yüklememiz gerekmektedir.  (Bunun için phpmyadmin veya mysql workbench kullanabiliriz.)
> - ajanda adında bir Database (karşılaştırma kısmı utf8_turkish_ci)  oluşturup  içerisine  kisilistem.sql dosyasını import ediyoruz. 

*Uygulama Mehmet Kayar (mehmtkyar@gmail.com ) tarafından yazılmıştır.  Soru ve sorunlarınızı iletebilirsiniz.

