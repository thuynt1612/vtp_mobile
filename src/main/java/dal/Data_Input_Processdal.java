package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Data_Input_Processdal extends BaseMariaseviceDal{
    public ResultSet getEmployeeInfo() throws ParseException {
        String query = "SELECT * FROM data_input_process";
        try {
            super.preStatement = super.conn.prepareStatement(query);
//            super.preStatement.setString(1,maDv);
//            super.preStatement.setString(2,"%"+vung_di_h +"%");
//            super.preStatement.setString(3,"%"+ vung_den_h + "%");
//            super.preStatement.setString(4,hanghoa);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
