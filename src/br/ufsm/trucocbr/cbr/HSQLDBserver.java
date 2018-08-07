package br.ufsm.trucocbr.cbr;

import jcolibri.test.database.SqlFile;
import jcolibri.util.FileIO;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.Server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

/**
 * Comando da 3ª Divisão de Exército
 * Adjunto da Seção de Informática
 * 1º Ten Vargas
 * Criado em 01/10/2017.
 */


public class HSQLDBserver {

    static boolean initialized = false;
    private static Server server;

    public HSQLDBserver() { }

    public static void init() {
        if(!initialized) {
            LogFactory.getLog(HSQLDBserver.class).info("Creating data base ...");
            server = new Server();
            server.setDatabaseName(0, "dbtruco");
            server.setDatabasePath(0, "mem:dbtruco;sql.enforce_strict_size=true");
            server.setLogWriter((PrintWriter)null);
            server.setErrWriter((PrintWriter)null);
            server.setSilent(true);
            server.start();
            initialized = true;

            try {
                Class.forName("org.hsqldb.jdbcDriver");
                PrintStream e = new PrintStream(new ByteArrayOutputStream());
                Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbtruco", "sa", "");
                SqlFile file = new SqlFile(new File(FileIO.findFile("/br/ufsm/trucocbr/cbr/truco.sql").getFile()), false, new HashMap());
                file.execute(conn, e, e, Boolean.valueOf(true));
                LogFactory.getLog(HSQLDBserver.class).info("Data base generation finished");
            } catch (Exception var5) {
                LogFactory.getLog(HSQLDBserver.class).error(var5);
            }

        }
    }

    public static void shutDown() {
        if(initialized) {
            server.stop();
            initialized = false;
        }

    }

    public static void main(String[] args) {
        init();
        shutDown();
        System.exit(0);
    }
}
