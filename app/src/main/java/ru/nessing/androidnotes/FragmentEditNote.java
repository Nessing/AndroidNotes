package ru.nessing.androidnotes;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentEditNote extends Fragment {

    private static final String ARG_POS = "ARG_POS";

    private Note note;

    public FragmentEditNote() {}

    public static FragmentEditNote newInstance(Note note) {
        FragmentEditNote fragment = new FragmentEditNote();
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
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (note != null) {
            EditText date = view.findViewById(R.id.full_date);
            date.setTextSize(getResources().getDimension(R.dimen.font_size_more_date));
            date.setText(note.getDate());

            EditText title = view.findViewById(R.id.full_title);
            title.setTextSize(getResources().getDimension(R.dimen.font_size_more_title));
            title.setText(note.getTitle());

            EditText description = view.findViewById(R.id.full_description);
            description.setTextSize(getResources().getDimension(R.dimen.font_size_more_description));
            description.setText(note.getDescription());

            TypedArray images = getResources().obtainTypedArray(R.array.images);

            //TODO Тест массива картинок

            LinearLayout layout = view.findViewById(R.id.layout_scroll);

            for (int i = 0; i < note.getImage().length; i++) {
                ImageView imageView = new ImageView(getContext());
                int padding = 70;
                imageView.setPadding(padding, padding, padding, padding);
                imageView.setImageResource(note.getImage()[i]);
                layout.addView(imageView);
            }
            images.recycle();

        }

        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> {
            int countBackStack = getActivity().getSupportFragmentManager().getBackStackEntryCount();
            while (countBackStack > 0) {
                countBackStack--;
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button buttonSave = view.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> {
            int countBackStack = getActivity().getSupportFragmentManager().getBackStackEntryCount();
            while (countBackStack > 0) {
                countBackStack--;
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}
