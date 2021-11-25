package ru.nessing.androidnotes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Map;

public class MoreNoteFragment extends Fragment {

    private static final String ARG_POS = "ARG_POS";

//    private ArrayNotes arrayNotes = ArrayNotes.getInstance();

    private Note note;

    public MoreNoteFragment() {}

    public static MoreNoteFragment newInstance(Note note) {
        MoreNoteFragment fragment = new MoreNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_POS, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_more_note, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuSearch = menu.findItem(R.id.action_search);
        MenuItem menuSort = menu.findItem(R.id.action_sort);
        MenuItem menuEdit = menu.findItem(R.id.action_edit);
        MenuItem menuRemove = menu.findItem(R.id.action_remove);
        if (menuSearch != null) menuSearch.setVisible(false);
        if (menuSort != null) menuSort.setVisible(false);
        if (menuEdit != null) menuEdit.setVisible(true);
        if (menuRemove != null) menuRemove.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_remove) {
            new AlertDialog.Builder(getContext())
                    .setCancelable(false)
                    .setTitle("Удаление")
                    .setMessage("Удалить запись?")
                    .setPositiveButton("Да", ((dialogInterface, i) -> {

                        MainActivity.setPos(-1);
                        int countBackStack = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                        while (countBackStack > 0) {
                            countBackStack--;
                            requireActivity().getSupportFragmentManager().popBackStack();
                        }
//                        arrayNotes.deleteNoteById(this.note.getId());
                        Toast.makeText(getContext(), "Запись удалена", Toast.LENGTH_SHORT).show();
                    }))
                    .setNegativeButton("нет", ((dialogInterface, i) -> {}))
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (note != null) {
            TextView date = view.findViewById(R.id.full_date);
            date.setTextSize(getResources().getDimension(R.dimen.font_size_more_date));
            date.setText(note.getDate());

            TextView title = view.findViewById(R.id.full_title);
            title.setTextSize(getResources().getDimension(R.dimen.font_size_more_title));
            title.setText(note.getTitle());

            TextView description = view.findViewById(R.id.full_description);
            description.setTextSize(getResources().getDimension(R.dimen.font_size_more_description));
            description.setText(note.getDescription());

            TypedArray images = getResources().obtainTypedArray(R.array.images);

            //TODO Тест массива картинок

            LinearLayout layout = view.findViewById(R.id.layout_scroll);

            for (int i = 0; i < note.getImage().length; i++) {
//                ImageView imageView = view.findViewById(R.id.image_view);
                ImageView imageView = new ImageView(getContext());
                int padding = 70;
                imageView.setPadding(padding, padding, padding, padding);
//            imageView.setImageResource(images.getResourceId(note.getId(), 0));
                imageView.setImageResource(note.getImage()[i]);
                layout.addView(imageView);
            }
            images.recycle();

        }

        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> {
            MainActivity.setPos(-1);
            int countBackStack = getActivity().getSupportFragmentManager().getBackStackEntryCount();
            while (countBackStack > 0) {
                countBackStack--;
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}