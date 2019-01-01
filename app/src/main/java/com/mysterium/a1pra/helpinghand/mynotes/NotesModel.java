package com.mysterium.a1pra.helpinghand.mynotes;
/*
 * Author: Pratik Bhirud
 */

public class NotesModel {
    private String noteTitle;
    private String noteContent;

    public NotesModel(String noteTitle, String noteContent) {
        this.noteTitle=noteTitle;
        this.noteContent=noteContent;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }


}


