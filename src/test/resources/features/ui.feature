@UI

Feature:Kullanıcı Girişi Yaparak Sepete Ürün Eklenmesi


  Scenario: Giriş yapan kullanıcı cep telefonu arayıp düşük puanlı satıcıdan ürünü sepete ekler


    Given Kullanıcı e-ticaret sitesini ziyaret eder
    And Kullanıcı gecerli kullanıcı adi ve sifre ile giris yapar
    When Kullanıcı arama kutusuna "cep telefonu 128 GB" yazarak arama yapar
    And Arama sonuçlarında fiyat filtresi olarak "15000" ile "20000" TL aralığı seçilir
    And Filtrelenmis ürün listesinden en alttaki rastgele bir ürün seçilerek detay sayfasına gidilir
    And Ürün detayında en düşük puanlı satıcının ürünü sepete eklenir
    Then Ürünün sepete doğru bir şekilde eklendiği doğrulanır


