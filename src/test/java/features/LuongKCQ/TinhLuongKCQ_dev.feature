Feature: Tinh luong khoi co quan


  @feature=new
  Scenario Outline: Tinh luong khoi co quan
    Given Get Organization <getOrg> from SAP
    When Get Employee from SAP with <EffectiveDate>
#    Then Write manv, tennv into excel import file <fileInput>
#    And Sync organizations with <periodId>
#    Then Sync employee with <periodId>
#    And Get start_date and end_date of <periodId>
#    Then Verify sync employee from SAP to database of <periodId>
    Then Call api import <periodId> with fileName <fileInput>
    And Verify import employee to database with <fileInput> <periodId>
    Then Call api KHM import <periodId> with fileName <fileInput>
    Then Verify import KHM to database with <fileInputKHM> <periodId>
    And Call api arrears-salary import <periodId> with fileName <fileInput>
    Then Verify import KHM to database with <fileInputKHM> <periodId>
#    And Call api create period <namePeriod> <startDate> <endDate>
    Examples:
      | fileInput                | fileInputKHM              | namePeriod           | startDate  | endDate    | getOrg   | EffectiveDate | periodId |
      | Luong_nhan_vien_new.xlsx | Luong_khach_hang_moi.xlsx | Ky luong autotest146 | 2023-06-14 | 2023-06-14 | 00196961 | 2022-11-22    | 1        |