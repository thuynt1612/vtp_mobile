Feature: Tinh gia cuoc dich vu cong them

#  dang cho confirm cho cach tinh TT
  @feature=dvct
  Scenario Outline: Tinh gia cho dich vu cong them
    Given Get trong luong trong database
      | ma_dv   | loai_hh   | vung_phat   | vung_phat_h   |
      | <ma_dv> | <loai_hh> | <vung_phat> | <vung_phat_h> |
    Given Get dvct va cach tinh dvct trong database
      | loai_hh   | ma_dv   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <vung_phat_h> |
    And Lay gia tri with cach tinh <TestCase> <ma_dv> <vung_phat_h> <loai_hh> <Dec> trong database
    When Call api tinh cuoc
      | loai_hh   | ma_dv   | thu_ho   | tien_kg   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <thu_ho> | <tien_kg> | <vung_phat> | <vung_phat_h> |
    Then Verify gia dich vu cong them voi cach tinh <TestCase>
    Examples:
      | Dec     | TestCase | loai_hh | ma_dv | thu_ho | tien_kg | vung_phat | vung_phat_h |
#      | default | FF       | HH      | VCN   | 0      | 0       | hcm       | 7100        |
#      | default | CC       | HH      | VCN   | 0      | 0       | hcm       | 7100        |
#      | KG | KG       | HH      | VCN   | 0      | 1000000 | hcm       | 7100        |
#      | default | TH       | HH      | VCN   | 100000 | 0       | hcm       | 7100        |
#      | default | TL       | HH      | VCN   | 0      | 0       | hcm       | 7100        |
      | TT | TT       | HH      | VCN   | 0      | 0       | hcm       | 7100        |


  @feature=gioihan
  Scenario Outline: Test gia dvct theo gioi han config
    Given Get trong luong trong database
      | ma_dv   | loai_hh   | vung_phat   | vung_phat_h   |
      | <ma_dv> | <loai_hh> | <vung_phat> | <vung_phat_h> |
    Given Get dvct va cach tinh dvct trong database
      | loai_hh   | ma_dv   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <vung_phat_h> |
    And Lay gia tri with cach tinh <TestCase> <ma_dv> <vung_phat_h> <loai_hh> <Dec> trong database
    When Call api tinh cuoc
      | loai_hh   | ma_dv   | thu_ho   | tien_kg   | vung_phat   | vung_phat_h   |
      | <loai_hh> | <ma_dv> | <thu_ho> | <tien_kg> | <vung_phat> | <vung_phat_h> |
    Then Verify gia dich vu cong them voi cach tinh <TestCase>
    Examples:
      | Dec     | TestCase | loai_hh | ma_dv | thu_ho | tien_kg | vung_phat | vung_phat_h |
      | min     | KG       | HH      | VCN   | 0      | 0       | hcm       | 7100        |
      | max     | KG       | HH      | VCN   | 0      | 60000   | hcm       | 7100        |
      | mucdau  | KG       | HH      | VCN   | 0      | 900000  | hcm       | 7100        |
      | muccuoi | KG       | HH      | VCN   | 0      | 1000000 | hcm       | 7100        |