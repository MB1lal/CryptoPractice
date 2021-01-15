Feature: coinmarket basic tests
  This feature deals with three basic tasks


  Scenario: Verify 100 cryptocurrencies are displayed
    Given User navigates to the homepage
    Then Verify 100 entries are displayed



  Scenario: Verify favourite marked currencies are appearing correctly
    Given User navigates to the homepage
    And Add random 5-10 currencies to watchlist
    Then Verify marked currencies are appearing in watchlist


  Scenario:  Verify filtered data
    Given User navigates to the homepage
    And User hovers over cryptocurrencies
    And User clicks Coins button
    Then Save the table
    And  Apply the mineable filter
    Then Compare some data