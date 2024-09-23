package model;

import java.io.Serializable;

public class DirectoryPathProvider implements Serializable {
    private String directoryPath;

    public DirectoryPathProvider(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
