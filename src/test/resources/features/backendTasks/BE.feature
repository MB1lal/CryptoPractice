Feature: Backend tests of Coin Market Cap

  Scenario: Convert currency IDs to Bolivian Boliviano
    Given User gets the response from cryptocurrency call
    Then Save Ids of BTC,ETH and USDT
    And Convert the IDs to Bolivian Boliviano


  Scenario: Perform data verifications on Etherium
    Given User gets info response for Etherium ID 1027
    Then Verify logo URL is present
    And Verify technical_doc URI is present
    And Verify symbol of currency is ETH
    And Verify date added field
    And Verify platform is null
    And Verify currency has mineable tag associated with it


  Scenario: Verify mineable tag on top 10 cryptocurrencies
    Given User gets IDs for top 10 cryptocurrencies
    Then  Verify which currencies have mineable tag present
    Then  Verify correct cryptocurrencies have been printed out
