package ru.nessing.androidnotes;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteSourceImp implements NoteSource {

    private final List<Note> noteList;

    public NoteSourceImp(@NonNull Context context) {
        this.noteList = new ArrayList<>(Arrays.asList(
            new Note(0, "Заметка Москвы", "17.08.2020", "Москва", R.drawable.msc),
            new Note(1, "Заметка Екатеринбурга", "22.09.2020", "Екатеринбург", R.drawable.ebrg),
            new Note(2, "Заметка Норильска", "17.08.2020", "Норильск (вроде)", R.drawable.nsk),
            new Note(3, "Заметка Самары", "22.09.2020", "Самара", R.drawable.sam),
            new Note(4, "Заметка Питера и Москвы", "17.08.2020", "Санкт-Петербург – русский портовый " +
                    "город на побережье Балтийского моря, который в течение двух веков служил столицей Российской империи. " +
                    "Он был основан в 1703 году Петром I, которому воздвигнут знаменитый памятник \"Медный всадник\". Город " +
                    "по праву считается культурным центром страны. У туристов пользуются популярностью Мариинский театр, где " +
                    "проходят оперные и балетные спектакли, и Государственный Русский музей с коллекцией русского искусства, " +
                    "которая включает как православные иконы, так и работы художника-абстракциониста Василия Кандинского.", R.drawable.spb, R.drawable.msc),
            new Note(5, "Заметка Москвы", "17.08.2020", "Москва опять", R.drawable.msc),
            new Note(6, "Заметка Екатеринбурга", "22.09.2020", "Екатеринбург снова", R.drawable.ebrg),
            new Note(7, "Заметка Норильска", "17.08.2020", "Норильск (вроде)", R.drawable.nsk),
            new Note(8, "Заметка Самары", "22.09.2020", "Самара", R.drawable.sam),
            new Note(9, "Заметка только Питера", "17.08.2020", "Питер", R.drawable.spb)
        ));
    }

    @Override
    public Note getNote(int position) {
        return noteList.get(position);
    }

    @Override
    public int size() {
        return noteList.size();
    }

    @Override
    public void deleteNote(int position) {
        noteList.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        noteList.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        noteList.add(note);
    }

    @Override
    public void clearNotes() {
        noteList.clear();
    }
}
