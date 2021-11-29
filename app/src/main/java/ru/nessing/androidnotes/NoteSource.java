package ru.nessing.androidnotes;

public interface NoteSource {
    Note getNote(int position);

    int size();

    void deleteNote(int position);

    void updateNote(int position, Note note);

    void addNote(Note note);

    void clearNotes();
}
