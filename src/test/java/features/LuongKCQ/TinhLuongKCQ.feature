Feature: Tinh luong khoi co quan


  @feature=first
  Scenario Outline: Tinh luong khoi co quan
    Given Ghi manv tu <fileInput> vao <fileOutput>
    When Tinh luong Khoi co quan <fileInput> <fileOutput> va verify luong output
    Then Tinh luong tham nien <fileInput> <fileOutput> va verify luong output
    And Tinh luong le phep <fileInput> <fileOutput> va verify luong output
    And Tinh luong boi duong truc le <fileInput> <fileOutput> va verify luong output
    Then Tinh tong luong <fileInput> <fileOutput> va verify luong output
    Then Tinh phu cap doan the <fileInput> <fileOutput> va verify luong output
    And Tinh phu cap an ca <fileInput> <fileOutput> va verify luong output
    Then Tinh phu cap dien thoai <fileInput> <fileOutput> <filePhucapdt> va verify luong output
    And Tinh tong phu cap <fileInput> <fileOutput> va verify luong output
    Then Tinh thue thu nhap ca nhan <fileInput> <fileOutput> va verify luong output
    And Tinh luong con lai <fileInput> <fileOutput> va verify luong output
    Then Tinh tong thu nhap <fileInput> <fileOutput> va verify luong output
    And Tinh so tien con lai <fileInput> <fileOutput> va verify luong output
    Examples:
      | fileInput         | fileOutput               | filePhucapdt           |
      | BangluongKcq.xlsx | BangluongKcq-Output.xlsx | BangluongKcq-pcdt.xlsx |