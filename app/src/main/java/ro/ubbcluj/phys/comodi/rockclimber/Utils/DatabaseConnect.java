package ro.ubbcluj.phys.comodi.rockclimber.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 * Created by sboda on 11/8/16.
 */
public class DatabaseConnect {




    public class DatabaseUtilities {


        public Connection getMySqlConnection(String url) throws Exception {
            String driver = "org.gjt.mm.mysql.Driver";
            String username = "RockClimber";
            String password = "ubuntu11&rockclimber";
           // url += "?verifyServerCertificate=false" + "&useSSL=true" + "&requireSSL=true";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
    }

}
