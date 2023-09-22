Feature: Tinh gia cuoc chinh

  @feature=dvchinh
  Scenario Outline: Tinh gia cho dich vu chinh
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
      | betweenStartEnd | HH      | NCOD  | PXD            | 0      | 0       | hcm       | 7100        |
      | equalEnd        | HH      | NCOD  | PXD            | 0      | 0       | hcm       | 7100        |
      | equalEnd        | HH      | NCOD  | PXD            | 0      | 0       | hcm       | 7100        |

  @feature=luyke
  Scenario Outline: Tinh gia cho dich vu chinh
    Given Gia cuoc config trong database
      | loai_hh   | ma_dv   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <vung_phat> | <vung_phat_h> |
    And Lay trong luong trong Database with case = <TestCase>
    Given Tinh gia dich vu chinh
      | loai_hh   | ma_dv   | ma_dv_congthem   | thu_ho   | tien_kg   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <ma_dv_congthem> | <thu_ho> | <tien_kg> | <vung_phat> | <vung_phat_h> |
    Then Verify gia cuoc co luy ke
    Examples:
      | TestCase | loai_hh | ma_dv | ma_dv_congthem | thu_ho | tien_kg | vung_phat | vung_phat_h |
      | luyke    | HH      | NCOD  | COD,PXD        | 0      | 0       | hcm       | 7100        |


  @feature=dvchinhfail
  Scenario Outline: Tinh gia cho dich vu chinh
    And Gia cuoc config trong database
      | loai_hh   | ma_dv   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <vung_phat> | <vung_phat_h> |
    And Lay trong luong trong Database with case = <TestCase>
    Given Tinh gia dich vu chinh
      | loai_hh   | ma_dv   | ma_dv_congthem   | thu_ho   | tien_kg   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <ma_dv_congthem> | <thu_ho> | <tien_kg> | <vung_phat> | <vung_phat_h> |
    Then Verify response co error = true
    Examples:
      | TestCase    | loai_hh | ma_dv | ma_dv_congthem | thu_ho | tien_kg | vung_phat | vung_phat_h |
      | lesserStart | HH      | NCOD  | COD,PXD        | 0      | 0       | hcm       | 7100        |
      | biggerEnd   | HH      | NCOD  | COD,PXD        | 0      | 0       | hcm       | 7100        |
