Feature: Artist funcionalities



  Scenario: Add new Artist with below details
    Given no artist registered
    When I register an artist
      | brandName | description           | phrase              | specialty | firstName | lastName |
      | SebSasaki | Nuevo Artista Musical | Vivo en el presente | 1         | Sebastian | Sasaki   |
    Then there should contains 1 artist registered



 # Scenario: