/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Downloader;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reader for the main index file and for the manifest
 * @author tzuhod
 */
public class Reader {

    protected ArrayList<Manifest> manifestUrlsArr;
    protected ArrayList<Segment> segUrlsArr;
    protected String[] filesNames;
    
    public Reader() throws Exception{
        this.segUrlsArr = new ArrayList<>();
    }
    
     /**
      * Read the manifest file from its location that is written in the index.txt file,
      * then parse it and store all segments locations in segUrlsArr:ArrayList<Segment>
      * @param maniLocation: Manifest URL that is written in the index.txt
      * @throws IOException
      * @throws Exception 
      */
     public void readManifest(String maniLocation) throws IOException, Exception{
        this.segUrlsArr = new ArrayList<>();
        Segment segmentUrlObj;
        Scanner bufferReader;
        try{
            File url = new File(maniLocation);
            bufferReader = new Scanner(new FileReader(url));
            String inputLine, prev = "";
            while(bufferReader.hasNext()){
                inputLine = bufferReader.nextLine();
                
                if(inputLine.equals("**")){
                    prev = inputLine;
                    continue;
                }
                
                if(prev.equals("") || prev.equals("**")){
                    
                    segmentUrlObj = new Segment();
                    
                    segmentUrlObj.setSegmentURL(inputLine);
                    
                    segUrlsArr.add(segmentUrlObj);
                    
                } else {
                    segUrlsArr.get(segUrlsArr.size() - 1).setSegmentURL(inputLine);
                } 
                prev = inputLine;
            }
            bufferReader.close();
        } catch(FileNotFoundException e){
            throw new Exception("Cannot read the Manifest.txt file", e);
        }
    }
     
}
