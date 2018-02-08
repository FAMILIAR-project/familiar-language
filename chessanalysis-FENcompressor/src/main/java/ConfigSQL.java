/**
 * Created by macher1 on 07/06/2016.
 */
public class ConfigSQL {

    private String url;

    private String db = "chessdb";

    private String driver = "com.mysql.jdbc.Driver";

    private String user = "root";

    private String pass;

    private String port = "";

    public ConfigSQL (String server) {

        // TODO enumeration
        //if (server.equals("diverse")) {
            url = "jdbc:mysql://10.0.0.4";
            db = "chessanalysis";
            user = "root";
            pass = "diverse";
            port = ":3308";

        //}
    }

	/*
	public ConfigSQL(String mode) {
		if(mode.equals("local")) {
			url = "jdbc:mysql://localhost/";
			pass = "root";
			db = "chessdb";
		}
	}
	*/

    public String getUrl() {
        return url;
    }

    public String getDb() {
        return db;
    }

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getPort() {
        return port;
    }

}