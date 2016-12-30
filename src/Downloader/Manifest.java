/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Downloader;

/**
 * Manifest attributes and operations needed for manifest entry
 * @author tzuhod
 */
public class Manifest {
    
    private String fileName;
    private String location;

    public void setFileName(String fileUrl) {
        String[] url = fileUrl.split(":", 2);
        this.fileName = url[0];
    }

    public void setLocation(String fileUrl) {
        String[] url = fileUrl.split(":", 2);
        this.location = url[1];
    }

    public String getFileName() {
        return fileName;
    }

    public String getLocation() {
        return location;
    }
    
}
