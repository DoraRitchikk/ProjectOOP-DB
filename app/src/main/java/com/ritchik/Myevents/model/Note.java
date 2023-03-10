package com.ritchik.Myevents.model;

public class Note {

    public String title;
    public String noteText;
    public String noteDate;
    public byte[] byteImage;

    public Note(String title, String noteText, String noteDate, byte[] byteImage) {
        this.title = title;
        this.noteText = noteText;
        this.noteDate = noteDate;
        this.byteImage = byteImage;
    }

    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public String getNoteText() {
        return noteText;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public byte[] getByteImage() {
        return byteImage;
    }

}
