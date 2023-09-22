package model;

public class ValueConfigModel {
    public double nguoipt;
    public double pcantrua;
    public double pcdt;
    public double luongcoban;
    public String loaihdd;
    public String nhomtinhluong;
    public double hesotv;
    public static class Builder {
        public double nguoipt;
        public double pcantrua;
        public double pcdt;
        public double luongcoban;
        public String loaihdld;
        public String nhomtinhluong;
        public double hesotv;
        public Builder setnguoipt(double nguoipt) {
            this.nguoipt = nguoipt;
            return this;
        }
        public Builder setpcantrua(double pcantrua) {
            this.pcantrua = pcantrua;
            return this;
        }
        public Builder setpcdt(double pcdt) {
            this.pcdt = pcdt;
            return this;
        }
        public Builder setluongcoban(double luongcoban) {
            this.luongcoban = luongcoban;
            return this;
        }
        public Builder setloaihdld(String loaihdld) {
            this.loaihdld = loaihdld;
            return this;
        }
        public Builder setnhomtinhluong(String nhomtinhluong) {
            this.nhomtinhluong = nhomtinhluong;
            return this;
        }

        public Builder sethesotv(double hesotv) {
            this.hesotv = hesotv;
            return this;
        }

        public ValueConfigModel build() {
            ValueConfigModel tc = new ValueConfigModel();
            tc.loaihdd = this.loaihdld;
            tc.nguoipt = this.nguoipt;
            tc.luongcoban = this.luongcoban;
            tc.pcdt = this.pcdt;
            tc.pcantrua = this.pcantrua;
            tc.nhomtinhluong = this.nhomtinhluong;
            tc.hesotv = this.hesotv;
            return tc;
        }
    }
}
