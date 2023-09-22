package model;

public class TinhCuocModel {
    public int muc_dau;
    public int muc_cuoi;
    public int gia_cuoc;
    public String luy_ke;

    public static class Builder {
        public int muc_dau;
        public int muc_cuoi;
        public int gia_cuoc;
        public String luy_ke;
        public Builder setmucdau(int muc_dau) {
            this.muc_dau = muc_dau;
            return this;
        }

        public Builder setmuccuoi(int muc_cuoi) {
            this.muc_cuoi = muc_cuoi;
            return this;
        }

        public Builder setgiacuoc(int gia_cuoc) {
            this.gia_cuoc = gia_cuoc;
            return this;
        }

        public Builder setluy_ke(String luy_ke) {
            this.luy_ke = luy_ke;
            return this;
        }

        public TinhCuocModel build() {
            TinhCuocModel tc = new TinhCuocModel();
            tc.muc_dau = this.muc_dau;
            tc.muc_cuoi = this.muc_cuoi;
            tc.gia_cuoc = this.gia_cuoc;
            tc.luy_ke = this.luy_ke;
            return tc;
        }
    }
}
