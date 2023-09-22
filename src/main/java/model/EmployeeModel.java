package model;

public class EmployeeModel {
    public String employeeId;
    public String fullName;
    public String jobDec;
    public String object;
    public String contractDate;
    public double bhxhSalary;
    public String machamcong;
    public double hesopcdt;
    public String bankacc;
    public String position;
    public String position_dec;
    public String contractType;
    public double insurance_salary;
    public double actual_workday;
    public double holidays_absence;
    public double paid_leave_absence;
    public double holidays_workday;
    public double total_workday;
    public double period_workday;
    public double base_salary;
    public double ki;
    public double kpi;
    public double luongbosung;
    public double pcqlkd;
    public double thuonghtkhkdl;
    public double truythubhxh;
    public double thuquydenon;
    public double truythuthue;
    public double truythuccongdoan;
    public double khautru;
    public double giuluong;
    public double luongbaohiem;
    public double hesodangdoan;
    public double luongKHM;


    public static class Builder {
        public String employeeId;
        public String fullName;
        public String jobDec;
        public String object;
        public String contractDate;
        public double bhxhSalary;
        public String machamcong;
        public double hesopcdt;
        public String bankacc;
        public String position;
        public String position_dec;
        public String contractType;
        public double actual_workday;
        public double holidays_absence;
        public double paid_leave_absence;
        public double holidays_workday;
        public double total_workday;
        public double period_workday;
        public double insurance_salary;
        public double base_salary;
        public double ki;
        public double kpi;
        public double luongbosung;
        public double pcqlkd;
        public double thuonghtkhkdl;
        public double truythubhxh;
        public double thuquydenon;
        public double truythuthue;
        public double truythuccongdoan;
        public double khautru;
        public double giuluong;
        public double luongbaohiem;
        public double hesodangdoan;
        public double luongKHM;

        public Builder setemployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder setfullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setjobDec(String jobDec) {
            this.jobDec = jobDec;
            return this;
        }

        public Builder setobject(String object) {
            this.object = object;
            return this;
        }

        public Builder setcontractDate(String contractDate) {
            this.contractDate = contractDate;
            return this;
        }

        public Builder setbhxhSalary(double bhxhSalary) {
            this.bhxhSalary = bhxhSalary;
            return this;
        }

        public Builder setmachamcong(String machamcong) {
            this.machamcong = machamcong;
            return this;
        }

        public Builder sethesopcdt(double hesopcdt) {
            this.hesopcdt = hesopcdt;
            return this;
        }

        public Builder setbankacc(String bankacc) {
            this.bankacc = bankacc;
            return this;
        }

        public Builder setposition(String position) {
            this.position = position;
            return this;
        }

        public Builder setposition_dec(String position_dec) {
            this.position_dec = position_dec;
            return this;
        }
        public Builder setcontractType(String contractType) {
            this.contractType = contractType;
            return this;
        }

        public Builder setinsurance_salary(double insurance_salary) {
            this.insurance_salary = insurance_salary;
            return this;
        }
        public Builder setactual_workday(double actual_workday) {
            this.actual_workday = actual_workday;
            return this;
        }
        public Builder setholidays_absence(double holidays_absence) {
            this.holidays_absence = holidays_absence;
            return this;
        }
        public Builder setpaid_leave_absence(double paid_leave_absence) {
            this.paid_leave_absence = paid_leave_absence;
            return this;
        }
        public Builder setholidays_workday(double holidays_workday) {
            this.holidays_workday = holidays_workday;
            return this;
        }
        public Builder settotal_workday(double total_workday) {
            this.total_workday = total_workday;
            return this;
        }
        public Builder setperiod_workday(double period_workday) {
            this.period_workday = period_workday;
            return this;
        }
        public Builder setbase_salary(double base_salary) {
            this.base_salary = base_salary;
            return this;
        }

        public Builder setki(double ki) {
            this.ki = ki;
            return this;
        }
        public Builder setkpi(double kpi) {
            this.kpi = kpi;
            return this;
        }
        public Builder setluongbosung(double luongbosung) {
            this.luongbosung = luongbosung;
            return this;
        }
        public Builder setpcqlkd(double pcqlkd) {
            this.pcqlkd = pcqlkd;
            return this;
        }
        public Builder setthuonghtkhkdl(double thuonghtkhkdl) {
            this.thuonghtkhkdl = thuonghtkhkdl;
            return this;
        }
        public Builder settruythubhxh(double truythubhxh) {
            this.truythubhxh = truythubhxh;
            return this;
        }
        public Builder setthuquydenon(double thuquydenon) {
            this.thuquydenon = thuquydenon;
            return this;
        }
        public Builder settruythuthue(double truythuthue) {
            this.truythuthue = truythuthue;
            return this;
        }
        public Builder settruythuccongdoan(double truythuccongdoan) {
            this.truythuccongdoan = truythuccongdoan;
            return this;
        }
        public Builder setkhautru(double khautru) {
            this.khautru = khautru;
            return this;
        }
        public Builder setgiuluong(double giuluong) {
            this.giuluong = giuluong;
            return this;
        }
        public Builder setluongbaohiem(double luongbaohiem) {
            this.luongbaohiem = luongbaohiem;
            return this;
        }
        public Builder sethesodangdoan(double hesodangdoan) {
            this.hesodangdoan = hesodangdoan;
            return this;
        }
        public Builder setluongKHM(double luongKHM) {
            this.luongKHM = luongKHM;
            return this;
        }

        public EmployeeModel build() {
            EmployeeModel emp = new EmployeeModel();
            emp.employeeId = this.employeeId;
            emp.machamcong = this.machamcong;
            emp.bhxhSalary = this.bhxhSalary;
            emp.jobDec = this.jobDec;
            emp.contractDate = this.contractDate;
            emp.object = this.object;
            emp.fullName = this.fullName;
            emp.hesopcdt = this.hesopcdt;
            emp.position = this.position;
            emp.position_dec = this.position_dec;
            emp.contractType = this.contractType;
            emp.insurance_salary = this.insurance_salary;
            emp.actual_workday = this.actual_workday;
            emp.holidays_absence = this.holidays_absence;
            emp.paid_leave_absence = this.paid_leave_absence;
            emp.holidays_workday = this.holidays_workday;
            emp.total_workday = this.total_workday;
            emp.period_workday = this.period_workday;
            emp.base_salary = this.base_salary;
            emp.ki = this.ki;
            emp.kpi = this.kpi;
            emp.luongbosung = this.luongbosung;
            emp.pcqlkd = this.pcqlkd;
            emp.thuonghtkhkdl = this.thuonghtkhkdl;
            emp.truythubhxh= this.truythubhxh;
            emp.thuquydenon = this.thuquydenon;
            emp.truythuthue = this.truythuthue;
            emp.truythuccongdoan = this.truythuccongdoan;
            emp.khautru = this.khautru;
            emp.giuluong = this.giuluong;
            emp.luongbaohiem = this.luongbaohiem;
            emp.hesodangdoan = this.hesodangdoan;
            emp.luongKHM = this.luongKHM;
            return emp;
        }
    }

}
