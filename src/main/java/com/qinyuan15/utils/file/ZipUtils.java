package com.qinyuan15.utils.file;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * Utility class to zip and unzip file
 * Created by qinyuan on 15-6-1.
 */
public class ZipUtils {
    public final static String DEFAULT_ENCODING = "UTF-8";

    public static void zip(String srcPath, String destPath) {
        File file = new File(srcPath);
        if (!file.exists()) {
            throw new RuntimeException("source file or directory " + srcPath + " does not exist.");
        }

        Project project = new Project();
        FileSet fileSet = new FileSet();
        fileSet.setProject(project);

        if (file.isDirectory()) {
            fileSet.setDir(file);
        } else {
            fileSet.setFile(file);
        }

        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(new File(destPath));
        zip.addFileset(fileSet);
        zip.setEncoding(DEFAULT_ENCODING);
        zip.execute();
    }

    public static void unzip(String zipPath, String extractPath) {
        if (!new File(zipPath).exists()) {
            throw new RuntimeException("zip file " + zipPath + " does not exist.");
        }

        Project project = new Project();
        Expand expand = new Expand();
        expand.setProject(project);
        expand.setTaskType("unzip");
        expand.setTaskName("unzip");
        expand.setEncoding(DEFAULT_ENCODING);

        expand.setSrc(new File(zipPath));
        expand.setDest(new File(extractPath));
        expand.execute();
    }
}
