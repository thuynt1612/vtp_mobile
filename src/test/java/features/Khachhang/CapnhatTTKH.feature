Feature: Test upload file

  @feature=uploadFile
  Scenario Outline: Cap nhat thong tin khach hang
    Given Gia cuoc config trong database
      | ma_dv   | loai_hh   | vung_phat   | vung_phat_h   |
      | <ma_dv> | <loai_hh> | <vung_phat> | <vung_phat_h> |
    And Lay trong luong trong Database with case = <TestCase>
    When Tinh gia dich vu chinh
      | loai_hh   | ma_dv   | ma_dv_congthem   | thu_ho   | tien_kg   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <ma_dv_congthem> | <thu_ho> | <tien_kg> | <vung_phat> | <vung_phat_h> |
    Then Verify gia cuoc nhan duoc
    Examples:
      | TestCase        | loai_hh | ma_dv | ma_dv_congthem | thu_ho | tien_kg | vung_phat | vung_phat_h |
      | betweenStartEnd | HH      | NCOD  | PXD        | 0      | 0       | hcm       | 7100        |
      | equalEnd        | HH      | NCOD  | PXD        | 0      | 0       | hcm       | 7100        |
      | equalEnd        | HH      | NCOD  | PXD        | 0      | 0       | hcm       | 7100        |
