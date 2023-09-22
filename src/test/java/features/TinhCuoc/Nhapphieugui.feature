Feature: Nhap phieu gui de kiem tra cuoc

#  @feature=inputphieu
#  Scenario Outline: Nhap phieu gui on web
##    Given Login he thong
#    When Nhap phieu gui
#      | maKhNG   | sdtNN   | tenNN   | trongluong   | city   | district   | wards   | village   | no   |
#      | <maKhNG> | <sdtNN> | <tenNN> | <trongluong> | <city> | <district> | <wards> | <village> | <no> |
#    Examples:
#      | maKhNG | sdtNN      | tenNN           | trongluong | city                  | district | wards           | village        | no   |
#      | 82     | 0123456789 | Nguyễn Ngọc Anh | 250        | THÀNH PHỐ HỒ CHÍ MINH | QUẬN 1   | PHƯỜNG BẾN NGHÉ | Đ.Hai Bà Trưng | Số 2 |


  @feature=endtoend
  Scenario Outline: Nhap phieu gui on web
    Given Gia cuoc config trong database
      | ma_dv   | loai_hh   | vung_phat   | vung_phat_h   |
      | <ma_dv> | <loai_hh> | <vung_phat> | <vung_phat_h> |
    And Lay trong luong trong Database with case = <TestCase>
    Given Get dvct va cach tinh dvct trong database
      | loai_hh   | ma_dv   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <vung_phat_h> |
    And Lay gia tri with cach tinh <TC> <ma_dv> <vung_phat_h> <loai_hh> <Dec> trong database
    When Tinh gia dich vu chinh
      | loai_hh   | ma_dv   | ma_dv_congthem   | thu_ho   | tien_kg   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <ma_dv_congthem> | <thu_ho> | <tien_kg> | <vung_phat> | <vung_phat_h> |
    Then Verify gia cuoc nhan duoc
    Then Verify gia dich vu cong them voi cach tinh <TestCase>
    When Nhap phieu gui
      | maKhNG   | sdtNN   | tenNN   | city   | district   | wards   | village   | no   |
      | <maKhNG> | <sdtNN> | <tenNN> | <city> | <district> | <wards> | <village> | <no> |
    Examples:
      | TestCase        | loai_hh | ma_dv | ma_dv_congthem | thu_ho | tien_kg | vung_phat | vung_phat_h | TC | Dec     | maKhNG | sdtNN      | tenNN           | city                  | district | wards           | village        | no   |
      | betweenStartEnd | HH      | VCN   | GDK            | 0      | 0       | hcm       | 7100        | FF | default | 82     | 0123456789 | Nguyễn Ngọc Anh | THÀNH PHỐ HỒ CHÍ MINH | QUẬN 1   | PHƯỜNG BẾN NGHÉ | Đ.Hai Bà Trưng | Số 2 |