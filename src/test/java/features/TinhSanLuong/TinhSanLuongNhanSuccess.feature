Feature: Tinh san luong nhan nhan thanh cong

  @feature=G00
  Scenario: Tinh G00
    Given Tinh san luong nhan G00 tu 02-02-23 den 28-02-23 buu cuc TN2

  @feature=G01
  Scenario Outline: Tinh G01
    Given Tinh san luong nhan thanh cong don COD
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 02-02-23   | 28-02-23 | TN2     | 1       |
      | 02-02-23   | 28-02-23 | TN2     | 2       |
      | 02-02-23   | 28-02-23 | TN2     | 3       |
      | 02-02-23   | 28-02-23 | TN2     | 4       |
      | 02-02-23   | 28-02-23 | TN2     | 5       |
      | 02-02-23   | 28-02-23 | TN2     | 6       |
      | 02-02-23   | 28-02-23 | TN2     | 7       |
      | 02-02-23   | 28-02-23 | TN2     | 8       |
      | 02-02-23   | 28-02-23 | TN2     | 9       |
      | 02-02-23   | 28-02-23 | TN2     | 10      |
    When Tinh san luong nhan thanh cong don NCOD
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 02-02-23   | 28-02-23 | TN2     | 1       |
      | 02-02-23   | 28-02-23 | TN2     | 2       |
      | 02-02-23   | 28-02-23 | TN2     | 3       |
      | 02-02-23   | 28-02-23 | TN2     | 4       |
      | 02-02-23   | 28-02-23 | TN2     | 5       |
      | 02-02-23   | 28-02-23 | TN2     | 6       |
      | 02-02-23   | 28-02-23 | TN2     | 7       |
      | 02-02-23   | 28-02-23 | TN2     | 8       |
      | 02-02-23   | 28-02-23 | TN2     | 9       |
      | 02-02-23   | 28-02-23 | TN2     | 10      |
    Then Tinh san luong nhan thanh cong don QTE
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 02-02-23   | 28-02-23 | TN2     | 1       |
      | 02-02-23   | 28-02-23 | TN2     | 2       |
      | 02-02-23   | 28-02-23 | TN2     | 3       |
      | 02-02-23   | 28-02-23 | TN2     | 4       |
      | 02-02-23   | 28-02-23 | TN2     | 5       |
      | 02-02-23   | 28-02-23 | TN2     | 6       |
      | 02-02-23   | 28-02-23 | TN2     | 7       |
      | 02-02-23   | 28-02-23 | TN2     | 8       |
      | 02-02-23   | 28-02-23 | TN2     | 9       |
      | 02-02-23   | 28-02-23 | TN2     | 10      |
    And Tinh san luong nhan thanh cong cua vung <He_so>
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

  @feature=G02
  Scenario: Tinh G02
    Given Tinh san luong nhan don COD tu 02-02-23 den 28-02-23 buu cuc TN2
    When Tinh san luong nhan don NCOD tu 02-02-23 den 28-02-23 buu cuc TN2
    Then Tinh san luong nhan don QTE tu 02-02-23 den 28-02-23 buu cuc TN2
    And Tinh san luong nhan G02

  @feature=G03
  Scenario: Tinh G03
    Given Tinh san luong nhan G03 tu 02-02-23 den 28-02-23 buu cuc TN2

  @feature=G04
  Scenario Outline: Tinh G04
    Given Tinh san luong nhan G04 tung vung
      | Start_date | End_date | Buu_cuc | Ma_vung |
      | 02-02-23   | 02-02-23 | TN2     | 1       |
      | 02-02-23   | 02-02-23 | TN2     | 2       |
      | 02-02-23   | 02-02-23 | TN2     | 3       |
      | 02-02-23   | 02-02-23 | TN2     | 4       |
      | 02-02-23   | 02-02-23 | TN2     | 5       |
      | 02-02-23   | 02-02-23 | TN2     | 6       |
      | 02-02-23   | 02-02-23 | TN2     | 7       |
      | 02-02-23   | 02-02-23 | TN2     | 8       |
      | 02-02-23   | 02-02-23 | TN2     | 9       |
      | 02-02-23   | 02-02-23 | TN2     | 10      |
    Then Tinh san luong nhan G04 with <He_so>
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