/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Downloader;

import java.util.ArrayList;

/**
 * Segment attributes and operations needed for segment entry
 * @author tzuhod
 */
public class Segment {
    
    private ArrayList<String> segmentURL;
    private String segmentName;
    private Manifest manifestUrl;

    public Segment() {
        segmentURL = new ArrayList();
        manifestUrl = new Manifest();
    }

    public ArrayList<String> getSegmentURL() {
        return segmentURL;
    }

    public void setSegmentURL(String segmentURL) {
        this.segmentURL.add(segmentURL);
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentUrl) {
        String[] name = segmentUrl.split("\\/|\\\\");
        this.segmentName = name[name.length - 1];
    }

    public Manifest getManifestUrl() {
        return manifestUrl;
    }

    public void setManifestUrl(Manifest manifestUrl) {
        this.manifestUrl = manifestUrl;
    }
    
}
