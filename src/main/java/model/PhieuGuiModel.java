package model;


public class PhieuGuiModel {
    public String buucuc_goc;
    public String buucuc_phat;
    public String loai_hh;
    public String ma_dv;
    public String ma_dv_congthem;
    public int thu_ho;
    public int tien_kg;
    public long trong_luong;
    public String vung_phat;
    public String vung_phat_h;
    public long tongtien;


    public static class Builder {
        public String buucuc_goc;
        public String buucuc_phat;
        public String loai_hh;
        public String ma_dv;
        public String ma_dv_congthem;
        public int thu_ho;
        public int tien_kg;
        public long trong_luong;
        public String vung_phat;
        public String vung_phat_h;
        public long tongtien;

        public Builder setbuucucgoc(String buucuc_goc) {
            this.buucuc_goc = buucuc_goc;
            return this;
        }

        public Builder setbuucucgphat(String buucuc_phat) {
            this.buucuc_phat = buucuc_phat;
            return this;
        }

        public Builder setloaihh(String loai_hh) {
            this.loai_hh = loai_hh;
            return this;
        }

        public Builder setmadv(String ma_dv) {
            this.ma_dv = ma_dv;
            return this;
        }

        public Builder setmadvct(String ma_dv_congthem) {
            this.ma_dv_congthem = ma_dv_congthem;
            return this;
        }

        public Builder setthuho(int thu_ho) {
            this.thu_ho = thu_ho;
            return this;
        }

        public Builder settienkg(int tien_kg) {
            this.tien_kg = tien_kg;
            return this;
        }

        public Builder settrongluong(long trong_luong) {
            this.trong_luong = trong_luong;
            return this;
        }

        public Builder setvung_phat(String vung_phat) {
            this.vung_phat = vung_phat;
            return this;
        }

        public Builder setvung_phat_h(String vung_phat_h) {
            this.vung_phat_h = vung_phat_h;
            return this;
        }

        public Builder settongtien(long tongtien) {
            this.tongtien = tongtien;
            return this;
        }

        public PhieuGuiModel build() {
            PhieuGuiModel pg = new PhieuGuiModel();
            pg.buucuc_goc = this.buucuc_goc;
            pg.buucuc_phat = this.buucuc_phat;
            pg.ma_dv_congthem = this.ma_dv_congthem;
            pg.ma_dv = this.ma_dv;
            pg.thu_ho = this.thu_ho;
            pg.loai_hh = this.loai_hh;
            pg.tien_kg = this.tien_kg;
            pg.tongtien = this.tongtien;
            pg.trong_luong = this.trong_luong;
            pg.vung_phat = this.vung_phat;
            pg.vung_phat_h = this.vung_phat_h;
            return pg;
        }
    }
}
