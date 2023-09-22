Feature: Tinh san luong giao thanh cong

  @feature=G00
  Scenario: Tinh G00
    Given Tinh san luong G00 tu 01-02-23 den 28-02-23 buu cuc TN2


  @feature=G01
  Scenario Outline: Tinh G01
    Given Tinh san luong giao thanh cong don COD
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 01-02-23   | 28-02-23 | TN2     | 1       |
      | 01-02-23   | 28-02-23 | TN2     | 2       |
      | 01-02-23   | 28-02-23 | TN2     | 3       |
      | 01-02-23   | 28-02-23 | TN2     | 4       |
      | 01-02-23   | 28-02-23 | TN2     | 5       |
      | 01-02-23   | 28-02-23 | TN2     | 6       |
      | 01-02-23   | 28-02-23 | TN2     | 7       |
      | 01-02-23   | 28-02-23 | TN2     | 8       |
      | 01-02-23   | 28-02-23 | TN2     | 9       |
      | 01-02-23   | 28-02-23 | TN2     | 10      |
    When Tinh san luong giao thanh cong don NCOD
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 01-02-23   | 28-02-23 | TN2     | 1       |
      | 01-02-23   | 28-02-23 | TN2     | 2       |
      | 01-02-23   | 28-02-23 | TN2     | 3       |
      | 01-02-23   | 28-02-23 | TN2     | 4       |
      | 01-02-23   | 28-02-23 | TN2     | 5       |
      | 01-02-23   | 28-02-23 | TN2     | 6       |
      | 01-02-23   | 28-02-23 | TN2     | 7       |
      | 01-02-23   | 28-02-23 | TN2     | 8       |
      | 01-02-23   | 28-02-23 | TN2     | 9       |
      | 01-02-23   | 28-02-23 | TN2     | 10      |
    Then Tinh san luong giao thanh cong don QTE
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 01-02-23   | 28-02-23 | TN2     | 1       |
      | 01-02-23   | 28-02-23 | TN2     | 2       |
      | 01-02-23   | 28-02-23 | TN2     | 3       |
      | 01-02-23   | 28-02-23 | TN2     | 4       |
      | 01-02-23   | 28-02-23 | TN2     | 5       |
      | 01-02-23   | 28-02-23 | TN2     | 6       |
      | 01-02-23   | 28-02-23 | TN2     | 7       |
      | 01-02-23   | 28-02-23 | TN2     | 8       |
      | 01-02-23   | 28-02-23 | TN2     | 9       |
      | 01-02-23   | 28-02-23 | TN2     | 10      |
    And Tinh san luong giao thanh cong cua vung <He_so>
    Examples:
      | He_so      |
      | 1          |
      | 1          |
      | 1          |
      | 1.1        |
      | 1.1        |
      | 1.1        |
      | 1.2        |
      | 1.2        |
      | 1.5        |
      | 1.5        |


  @feature=G02
  Scenario: Tinh G02
    Given Tinh san luong giao don COD tu 01-02-23 den 28-02-23 buu cuc TN2
    When Tinh san luong giao don NCOD tu 01-02-23 den 28-02-23 buu cuc TN2
    Then Tinh san luong giao don QTE tu 01-02-23 den 28-02-23 buu cuc TN2
    And Tinh san luong G02

  @feature=G03
  Scenario: Tinh G03
    Given Tinh san luong giao G03 tu 01-02-23 den 28-02-23 buu cuc TN2

  @feature=G04
  Scenario Outline: Tinh G04
    Given Tinh san luong giao G04 tung vung
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 01-02-23   | 01-02-23 | TN2     | 1       |
      | 01-02-23   | 01-02-23 | TN2     | 2       |
      | 01-02-23   | 01-02-23 | TN2     | 3       |
      | 01-02-23   | 01-02-23 | TN2     | 4       |
      | 01-02-23   | 01-02-23 | TN2     | 5       |
      | 01-02-23   | 01-02-23 | TN2     | 6       |
      | 01-02-23   | 01-02-23 | TN2     | 7       |
      | 01-02-23   | 01-02-23 | TN2     | 8       |
      | 01-02-23   | 01-02-23 | TN2     | 9       |
      | 01-02-23   | 01-02-23 | TN2     | 10      |
    Then Tinh san luong giao G04 with <He_so>
    Examples:
      | He_so |
      | 1     |
      | 1     |
      | 1     |
      | 1.1   |
      | 1.1   |
      | 1.1   |
      | 1.2   |
      | 1.2   |
      | 1.5   |
      | 1.5   |


