package model;

public class PhuCapDienThoaiModel {
    public String chucdanh;
    public String phucap;
    public static class Builder {
        public String chucdanh;
        public String phucap;
        public Builder setchucdanh(String chucdanh) {
            this.chucdanh = chucdanh;
            return this;
        }

        public Builder setphucap(String phucap) {
            this.phucap = phucap;
            return this;
        }
        public PhuCapDienThoaiModel build() {
            PhuCapDienThoaiModel pc = new PhuCapDienThoaiModel();
            pc.chucdanh = this.chucdanh;
            pc.phucap = this.phucap;
            return pc;
        }
    }
}
