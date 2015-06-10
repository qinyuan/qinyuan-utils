package com.qinyuan15.utils.database;

import com.qinyuan15.utils.CommandExecuteResult;
import com.qinyuan15.utils.CommandUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * Class to backup MySQL
 * Created by qinyuan on 15-5-29.
 */
public class MySQLDump {
    private final static Logger LOGGER = LoggerFactory.getLogger(MySQLDump.class);
    public final static String DEFAULT_HOST = "localhost";
    public final static String DEFAULT_USER = "root";
    public final static String DEFAULT_PASSWORD = "";
    public final static String DEFAULT_BIN_PATH = "/usr/bin/mysqldump";

    private String host = DEFAULT_HOST;
    private String user = DEFAULT_USER;
    private String password = DEFAULT_PASSWORD;
    private String binPath = DEFAULT_BIN_PATH;
    private String database;
    private String backupPath;

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    public void setBinPath(String binPath) {
        this.binPath = binPath;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void run() {
        if (!StringUtils.hasText(backupPath)) {
            LOGGER.error("Backup path is not set!");
        } else if (!StringUtils.hasText(database)) {
            LOGGER.error("Database is not set!");
        }

        File dir = new File(backupPath).getParentFile();
        if (!dir.isDirectory()) {
            if (!dir.mkdirs()) {
                LOGGER.error("Fail to create directory {}", dir.getAbsolutePath());
            }
        }

        String command = binPath + " -h " + host + " -u " + user + " -p" + password +
                " " + database + " --result-file=" + backupPath;

        CommandExecuteResult result = CommandUtils.run(command);
        if (result.getExitCode() == 0) {
            LOGGER.info("Backup " + database + " to " + backupPath + " successfully!");
        } else {
            LOGGER.error("Fail to backup {} to {}, command: {}, error info:\n{}",
                    database, backupPath, command, result.getSystemErr());
            FileUtils.deleteQuietly(new File(backupPath));
        }
    }
}
