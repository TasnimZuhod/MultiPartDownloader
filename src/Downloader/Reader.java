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
        this.manifestUrlsArr = new ArrayList<>();
        this.segUrlsArr = new ArrayList<>();
        readIndex();
    }
    
    /**
     * Read the main index.txt in order to extract all manifests URLs,
     * then store them in manifestUrlsArr:ArrayList<Manifest>
     * @throws Exception 
     */
    private void readIndex() throws Exception{
        Manifest indexObj = null;
        Scanner bufferReader;
        try{
            File url = new File("index.txt");
            bufferReader = new Scanner(new FileReader(url));
            String inputLine;
            
//            URL url = new URL("http://www.jamesbooth.com/OOPBasics.htm");
//            URLConnection httpConn = (URLConnection) url.openConnection();
//            
//            BufferedReader bufferReader = new BufferedReader(
//                    new InputStreamReader(httpConn.getInputStream()));
//            while ((inputLine = bufferReader.readLine()) != null){ 
//                System.out.println(inputLine);
//                filesArr.add(inputLine);
//                
//                indexObj = new FilesIndex();
//                indexObj.setFileName(inputLine);
//                indexObj.setLocation(inputLine);
//                filesInd.add(indexObj);
//            }
//            bufferReader.close();

            while(bufferReader.hasNext()){
                inputLine = bufferReader.nextLine();
                
                indexObj = new Manifest();
                indexObj.setFileName(inputLine);
                indexObj.setLocation(inputLine);
                manifestUrlsArr.add(indexObj);
            }
            bufferReader.close();
        } catch (IOException e){
            throw new Exception("Cannot read the index.txt file", e);
        }
        
        setIndexList(manifestUrlsArr);
    }
    
    /**
     * Store the manifest names in filesNames:String[]
     * in order to display them in the GUI
     * @param list : The manifests URLs list
     */
     public void setIndexList(ArrayList<Manifest> list) {
        filesNames = new String[list.size()];
        for (int i=0; i<list.size(); i++){
            filesNames[i] = manifestUrlsArr.get(i).getFileName();
        }
    }
    
     /**
      * Read the manifest file from its location that is written in the index.txt file,
      * then parse it and store all segments locations in segUrlsArr:ArrayList<Segment>
      * @param maniLocation: Manifest URL that is written in the index.txt
      * @throws IOException
      * @throws Exception 
      */
     public void readManifest(String maniLocation) throws IOException, Exception{
        Segment segmentUrlObj;
        Scanner bufferReader;
        try{
            File url = new File(maniLocation);
            bufferReader = new Scanner(new FileReader(url));
            String inputLine, prev = "";
            while(bufferReader.hasNext()){
                boolean duplicate = false;
                inputLine = bufferReader.nextLine();
                
                segmentUrlObj = new Segment();
                
                segmentUrlObj.setSegmentName(inputLine);
                
                if(!prev.equals("")){
                    duplicate = checkAlternative(prev, segmentUrlObj.getSegmentName());
                    if(duplicate)
                        segUrlsArr.get(segUrlsArr.size() - 1).setSegmentURL(inputLine);
                } 
                if(prev.equals("") || !duplicate){
                    prev = segmentUrlObj.getSegmentName();
                    
                    segmentUrlObj.setSegmentURL(inputLine);
                    
                    segUrlsArr.add(segmentUrlObj);
                }
            }
            bufferReader.close();
        } catch(FileNotFoundException e){
            throw new Exception("Cannot read the Manifest.txt file", e);
        }prints();
    }
     
    void prints(){
        for(int i=0; i<segUrlsArr.size(); i++){
            System.out.println("MANIFEST NAME "+ segUrlsArr.get(i).getSegmentName());
            for(int j=0; j<segUrlsArr.get(i).getSegmentURL().size(); j++){
                System.out.println("MANIFEST URL "+ segUrlsArr.get(i).getSegmentURL().get(j));
            }
        }
    }
 
    /**
     * Check if the two URLs are for the same segment that available in different location
     * @param previous: The previous segment URL
     * @param current: The current segment URL
     * @return true if the current URL is an alternative for the previous one
     */
    private boolean checkAlternative(String previous, String current){
        return previous.equals(current);
    }
     
}
