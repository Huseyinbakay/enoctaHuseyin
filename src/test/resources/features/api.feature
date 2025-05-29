@API
Feature:Mock API lerin test edilmesi



  Scenario: Kullanıcı API üzerinden token  sürecini gerçekleştirir

    Given kullanıcı headera "user" ve "pass" parametrelerini ekler
    When  kullanıcı token için url i düzenler ve token post request gönderir
    Then Response içine "token" olduğu doğrulanır



    Scenario: Kullanıcı API üzerinden viewInvoıce  sürecini gerçekleştirir

      Given kullanıcı "Barcode" parametresi ve viewInvoice endpointi ile get request yapar
      And response içerisinde "success" true değeri olduğu doğrulanır
      Then viewInvoıce başarılı ise verileri txt dosyaya yazılır





      Scenario: Kullanıcı API üzerinden sendInvoice  sürecini gerçekleştirir

    When kullanıcı headera  "token" ekler ve sendInvoice endpointi ile post request yapar
    And sendInvoice çağrısı başarılı olduğu doğrulanır
    Then sendInvoıce başarılı ise verileri txt dosyaya yazılır