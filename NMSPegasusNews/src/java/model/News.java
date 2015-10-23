/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Dilip
 */

public class News 
{
    private int newsID ;
    private int journalistID ;
    private int receptionistID ;
    private int editorID;
    private int approverID;
    private String title;
    private String description;
    private int state;
    private String link;
    private Timestamp publishedTime; 
    private String tempLink;
    private String comment;
    private ArrayList<String> imageList;
    private ArrayList<String> videoList;

  
    public News()
    {
        imageList = new ArrayList<String>();
        videoList = new ArrayList<String>();
    }
    
    public News(String title, String description,String comment,String tempLink, int state,int journalistID ) 
    {
        this.journalistID = journalistID;
        this.title = title;
        this.comment = comment;
        this.description = description;
        this.tempLink = tempLink;
        this.state = state;
        this.imageList = new ArrayList<String>();
        this.videoList = new ArrayList<String>();
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getTempLink() {
        return tempLink;
    }

    public void setTempLink(String tempLink) {
        this.tempLink = tempLink;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public int getJournalistID() {
        return journalistID;
    }

    public void setJournalistID(int journalistID) {
        this.journalistID = journalistID;
    }

    public int getReceptionistID() {
        return receptionistID;
    }

    public void setReceptionistID(int receptionistID) {
        this.receptionistID = receptionistID;
    }

    public int getEditorID() {
        return editorID;
    }

    public void setEditorID(int editorID) {
        this.editorID = editorID;
    }

    public int getApproverID() {
        return approverID;
    }

    public void setApproverID(int approverID) {
        this.approverID = approverID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublishedDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(publishedTime);
    }
    
    public String getPublishedTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(publishedTime);
    }
    
    public String getPublishedDateTime() {
        DateFormat date_timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date_timeFormat.format(publishedTime);
    }

    public void setPublishedTime(Timestamp publishedTime) {
        this.publishedTime = publishedTime;
    }

    public void addImage(String image)
    {
        this.imageList.add(image);
    }
    
    public void addVideo(String video)
    {
        this.videoList.add(video);
    }
    
    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public ArrayList<String> getVideoList() {
        return videoList;
    }

    public void setVideoList(ArrayList<String> videoList) {
        this.videoList = videoList;
    }
       
}
