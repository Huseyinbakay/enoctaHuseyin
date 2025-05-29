1-Bu projeyi, amazon web uygulaması için oluşturdum.
2-Projemi Page Object Model dizaynına göre tasarladım
3-Projemi Cucumber Frameworkte yaptım
4-Configuration.properties dosyasıyla verilerimi daha kolay yönetilebilir hale getirdim
5-Testlerin çalışabilmesi için gerekli datalar Configuration.properties içinde yer almaktadır.
6-Cucumber.properties ile raporları paylaşılabilir duruma getirdim.
7-Hook class içinde ui senaryoların fail olması durumunda tearDown metodu ile
  screenShot alıp attach ile cucumber rapor sonuna ekledim.
6-runners package üç farklı sınıf oluşturdum
    Runner: Tüm testleri çalıştırmak için.
    FailedRunner: Sadece başarısız olan senaryoları tekrar çalıştırmak için.
    ParallelRunner: Testleri paralel olarak çalıştırmak için.
7. Yapıyla, projeye paralel test çalıştırma özelliği kazandırdım.
10-Feature larda taglar oluşturdum ve tagları kullanarak testleri
   runner claslardan yönettim.
11-UI testleri için @UI, API tesleri için ise @API taglarını runner classlarından çalıştırdım.

