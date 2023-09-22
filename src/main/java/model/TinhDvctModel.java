package model;

public class TinhDvctModel {
    public String cach_tinh;
    public String gia_tri;
    public String ma_dv_congthem;
    public String muc_dau;
    public String muc_cuoi;
    public String min;
    public String max;
    public String ID_CUOC_CONGTHEM;
    public static class Builder {
        public String cach_tinh;
        public String gia_tri;
        public String ma_dv_congthem;
        public String muc_dau;
        public String muc_cuoi;
        public String min;
        public String max;
        public String ID_CUOC_CONGTHEM;

        public Builder setcachtinh(String cach_tinh) {
            this.cach_tinh = cach_tinh;
            return this;
        }

        public Builder setgiatri(String gia_tri) {
            this.gia_tri = gia_tri;
            return this;
        }
        public Builder setdvcongthem(String ma_dv_congthem) {
            this.ma_dv_congthem = ma_dv_congthem;
            return this;
        }
        public Builder setmucdau(String muc_dau) {
            this.muc_dau = muc_dau;
            return this;
        }

        public Builder setmuccuoi(String muc_cuoi) {
            this.muc_cuoi = muc_cuoi;
            return this;
        }
        public Builder setmin(String min) {
            this.min = min;
            return this;
        }
        public Builder setmax(String max) {
            this.max = max;
            return this;
        }

        public Builder setid(String id) {
            this.ID_CUOC_CONGTHEM = id;
            return this;
        }
        public TinhDvctModel build() {
            TinhDvctModel tc = new TinhDvctModel();
            tc.cach_tinh = this.cach_tinh;
            tc.gia_tri = this.gia_tri;
            tc.ma_dv_congthem = this.ma_dv_congthem;
            tc.muc_dau = this.muc_dau;
            tc.muc_cuoi = this.muc_cuoi;
            tc.min = this.min;
            tc.max = this.max;
            tc.ID_CUOC_CONGTHEM = this.ID_CUOC_CONGTHEM;
            return tc;
        }
    }

}
