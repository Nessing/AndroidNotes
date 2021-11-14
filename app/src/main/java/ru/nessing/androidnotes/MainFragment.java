package ru.nessing.androidnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainFragment extends Fragment {
    private ArrayNotes arrayNotes = ArrayNotes.getInstance();
    private int pos = MainActivity.getPos();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (pos != -1) {
            Note note = arrayNotes.getNoteById(pos);
            showImage(pos, note.getTitle(), note.getDate(), note.getDescription());
            System.out.println(pos);
        }
        initList(view);
    }

    @SuppressLint("SetTextI18n")
    private void initList(View view) {
        LinearLayout layout = (LinearLayout) view;
        int font_size = (int) getResources().getDimension(R.dimen.font_size_list);

        for (int i = 0; i < arrayNotes.size(); i++) {
            String titleNote = arrayNotes.getNoteById(i).getTitle();
            String date = arrayNotes.getNoteById(i).getDate();
            String desc = arrayNotes.getNoteById(i).getDescription();

            TextView textView = new TextView(getContext());
            textView.setPadding(font_size, font_size, font_size, font_size);
            if (desc.length() > 50) {
                textView.setText(titleNote + "\n" + date + "\n" + desc.substring(0, 50) + "...");
            } else {
                textView.setText(titleNote + "\n" + date + "\n" + desc);
            }

            textView.setTextSize(getResources().getDimension(R.dimen.font_size_list));

            final int position = i;
            textView.setOnClickListener(v -> {
                MainActivity.setPos(position);
                showImage(position, titleNote, date, desc);
            });
            layout.addView(textView);
        }

//        for (int i = 0; i < noteList.size(); i++) {
//            TextView textView = new TextView(getContext());
//            textView.setText(noteList.get(i).getTitle() + "\n" + noteList.get(i).getDate() + "\n" + noteList.get(i).getDescription());
//            textView.setTextSize(20);
//
//            final int position = i;
//            textView.setOnClickListener(v -> {
//                showImage(position);
//            });
//
//            layout.addView(textView);
//        }
//        String[] notes = getResources().getStringArray(R.array.notes);
//
//        for (int i = 0; i < notes.length; i++) {
//            TextView textView = new TextView(getContext());
//            textView.setText(notes[i]);
//            textView.setTextSize(30);
//
//            final int position = i;
//            textView.setOnClickListener(v -> {
//                showImage(position);
//            });
//
//            layout.addView(textView);
//        }
    }

    private boolean isLand() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void showImage(int position, String title, String date, String desc) {
        if (isLand()) {
            showImageLand(position, title, date, desc);
        } else {
            showImagePort(position, title, date, desc);
        }
    }

    private void showImagePort(int position, String title, String date, String desc) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_menu_vertical, MoreNoteFragment.newInstance(new Note(position, title, date, desc)))
                .addToBackStack(null)
                .commit();
    }

    private void showImageLand(int position, String title, String date, String desc) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.blank_Fragment_img, MoreNoteFragment.newInstance(new Note(position, title, date, desc)))
//                .addToBackStack(null)
                .commit();
    }
}