import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by macher1 on 07/06/2016.
 */
public class ConnexionTest {


    private static final Logger _log = Logger.getLogger("ConnexionTest");



    @Test
    public void init() throws ClassNotFoundException, SQLException {

        ConfigSQL config = new ConfigSQL("diverse");
        String connexionInitializer =
                config.getUrl()
                        + config.getPort()
                        + "/" + config.getDb()
                        + "?user=" + config.getUser()
                        + "&password=" + config.getPass()
                        //+ "&port=" + config.getPort()
                        + "&rewriteBatchedStatements=true";

        Class.forName(config.getDriver());

        _log.info(connexionInitializer);

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;

        try {
            fw = new FileWriter("log.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);

        } catch (IOException e) {
            assertTrue(false); // FAIL
        }

        assertNotNull(fw);
        assertNotNull(bw);
        assertNotNull(out);


        Connection connexion = DriverManager.getConnection(connexionInitializer);
        connexion.setAutoCommit(true);
        _log.info("Connected!");




        Statement stmt = connexion.createStatement();

        int LIMIT = 60000;

        ResultSet rs = stmt.executeQuery("SELECT id, log FROM FEN LIMIT " + LIMIT);
        int i = 0;
        while(rs.next()) {
            i++;
            String log = rs.getString("log");

            if (log == null || log.isEmpty())
                continue;

            String idFEN = rs.getString("id");

            //_log.info("idFEN=" + idFEN + "\n" + "log=" + log);
            out.println(idFEN + "\t" + log);
        }
        _log.info("Query succesful!");
        assertEquals(LIMIT, i);
    }


}
