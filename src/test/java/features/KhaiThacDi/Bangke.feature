Feature: Nhap phieu di

  @feature=inputphieudi
  Scenario Outline: Nhap phieu di on web
    Given Nhap phieu di
      | maKhNG   | sdtNN   | tenNN   | trongluong   | city   | district   | wards   | no   | LoaiBP/BK    |
      | <maKhNG> | <sdtNN> | <tenNN> | <trongluong> | <city> | <district> | <wards> | <no> | <LoaiBP/BK>  |
    Examples:
      | maKhNG | sdtNN      | tenNN           | trongluong | city                  | district | wards           | no   | LoaiBP/BK |
      | 82     | 0382678080 | Nguyễn Ngọc Anh | 250        | THÀNH PHỐ HỒ CHÍ MINH | QUẬN 1   | PHƯỜNG BẾN NGHÉ | Số 2 | Hàng nhỏ  |
