Feature: Tinh doanh thu theo gia cong bo

  @feature=doanhthu
  Scenario Outline: Tinh doanh thu theo gia cong bo
    Given Get data bill tu database
      | startDate   | endDate   | buucuc   |
      | <startDate> | <endDate> | <buucuc> |
    When Tinh gia doanh thu cac dich vu VCBA,VCBO,VBK
#    Then Tinh gia doanh thu cac dich vu con lai
    Then Tinh doanh thu theo gia cong bo
    Examples:
      | startDate | endDate  | buucuc |
      | 01-01-23  | 31-01-23 | TN2    |


