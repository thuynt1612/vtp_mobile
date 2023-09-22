package common.File;

import lombok.Getter;
import lombok.Setter;


public class Book {
    private String manv;
    private String tennv;
    private String nhomluong;
    private Double namthamnien;
    private Double luongcb;

    public Book() {
        super();
    }

    public Book(String manv, String tennv, String nhomluong, double namthamnien) {
        super();
        this.manv = manv;
        this.tennv = tennv;
        this.nhomluong = nhomluong;
        this.namthamnien = namthamnien;
    }

    @Override
    public String toString() {
        return "Book [manv=" + manv + ", tennv=" + tennv + ", nhomluong=" + nhomluong + ", namthamnien=" + namthamnien + ", luongcb="
                + luongcb + "]";
    }

    public String getmanv() {
        return manv;
    }

    public void setmanv(String manv) {
        this.manv = manv;
    }

    public String gettennv() {
        return tennv;
    }

    public void settennv(String tennv) {
        this.tennv = tennv;
    }

    public String getnhomluong() {
        return nhomluong;
    }

    public void setnhomluong(String nhomluong) {
        this.nhomluong = nhomluong;
    }

    public Double getnamthamnien() {
        return namthamnien;
    }

    public void setnamthamnien(Double namthamnien) {
        this.namthamnien = namthamnien;
    }

    public Double getluongcb() {
        return luongcb;
    }

    public void setluongcb(Double luongcb) {
        this.luongcb = luongcb;
    }

}
