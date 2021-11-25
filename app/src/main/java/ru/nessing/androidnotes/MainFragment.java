package ru.nessing.androidnotes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
//    private ArrayNotes arrayNotes = ArrayNotes.getInstance();
//    private int pos = MainActivity.getPos();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        if (pos != -1) {
//            Note note = arrayNotes.getNoteById(pos);
//            showImage(pos, note.getTitle(), note.getDate(), note.getDescription());
//            System.out.println(pos);
//        }
//        initList(view);
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuEdit = menu.findItem(R.id.action_edit);
        MenuItem menuRemove = menu.findItem(R.id.action_remove);
        if (menuEdit != null) menuEdit.setVisible(false);
        if (menuRemove != null) menuRemove.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            new AlertDialog.Builder(getContext())
                    .setCancelable(true)
                    .setTitle("Выход")
                    .setMessage("Выйти из приложения?")
                    .setPositiveButton("Да", ((dialogInterface, i) -> {
                        getActivity().finish();
                        Toast.makeText(getContext(), "Программа закрыта", Toast.LENGTH_SHORT).show();
                    }))
                    .setNegativeButton("нет", ((dialogInterface, i) -> {
                    }))
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }


//    @SuppressLint("SetTextI18n")
//    private void initList(View view) {
//        LinearLayout layout = (LinearLayout) view;
//        int font_size = (int) getResources().getDimension(R.dimen.font_size_list);
//
//        for (int i = 0; i < arrayNotes.size(); i++) {
//            String titleNote = arrayNotes.getNoteById(i).getTitle();
//            String date = arrayNotes.getNoteById(i).getDate();
//            String desc = arrayNotes.getNoteById(i).getDescription();
//
//            TextView textView = new TextView(getContext());
//            textView.setPadding(font_size, font_size, font_size, font_size);
//            if (desc.length() > 50) {
//                textView.setText(titleNote + "\n" + date + "\n" + desc.substring(0, 50) + "...");
//            } else {
//                textView.setText(titleNote + "\n" + date + "\n" + desc);
//            }
//
//            textView.setTextSize(getResources().getDimension(R.dimen.font_size_list));
//
//            final int position = i;
//            textView.setOnClickListener(v -> {
//                MainActivity.setPos(position);
//                showImage(position, titleNote, date, desc);
//            });
//            layout.addView(textView);
//        }
//    }
//
//    private boolean isLand() {
//        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
//    }
//
//    private void showImage(int position, String title, String date, String desc) {
//        if (isLand()) {
////            showImageLand(position, title, date, desc);
//        } else {
//            showImagePort(position, title, date, desc);
//        }
//    }

//    private void showImagePort(int position, String title, String date, String desc) {
//        requireActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.action_container, MoreNoteFragment.newInstance(new Note(position, title, date, desc)))
//                .addToBackStack(null)
//                .commit();
//    }

//    private void showImageLand(int position, String title, String date, String desc) {
//        requireActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.blank_Fragment_img, MoreNoteFragment.newInstance(new Note(position, title, date, desc)))
////                .addToBackStack(null)
//                .commit();
//    }
}