package ru.nessing.androidnotes;

import java.util.HashMap;
import java.util.Map;

public class ArrayNotes {

    private static ArrayNotes instance;

    private static int keys = 3;
    private Map<Integer, Note> noteList = new HashMap<>();

    public ArrayNotes() {
    }

    public static ArrayNotes getInstance() {
        if (instance == null) {
            instance = new ArrayNotes();
            instance.noteList.put(0, new Note("заметка 1", "21.01.20", "Новый год прошел так себе"));
            instance.noteList.put(1, new Note("заметка 2", "05.03.20", "Начался март, а я все без денег"));
            instance.noteList.put(2, new Note("заметка 3", "17.08.20", "Уже лето, вроде солнце, а все равно тоскливо, может из-за дождя"));
        }
        return instance;
    }

    public void deleteNote(String title) {
        keys--;
        noteList.remove(title);
    }

    public void addNote(String title, String date, String description) {
        noteList.put(keys++, new Note(title, date, description));
    }

    public int size() {
        return noteList.size();
    }

    public Note getNoteById(int id) {
        return noteList.get(id);
    }

}
