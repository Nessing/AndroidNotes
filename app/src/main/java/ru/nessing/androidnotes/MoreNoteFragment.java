package ru.nessing.androidnotes;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class MoreNoteFragment extends Fragment {

    private static final String ARG_POS = "ARG_POS";

    private Note note;

    public MoreNoteFragment() {
    }

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
        return inflater.inflate(R.layout.fragment_more_note, container, false);
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ArrayNotes arrayNotes = ArrayNotes.getInstance();
//        Note getNote = arrayNotes.getNoteById(note.getId());

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

            ImageView imageView = view.findViewById(R.id.image_view);
            imageView.setImageResource(images.getResourceId(note.getId(), 0));

            images.recycle();
        }

        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

//        Button buttonRemove = view.findViewById(R.id.button_remove);
//        buttonRemove.setOnClickListener(v -> {
//            final List<Fragment> fragmentList = requireActivity()
//                    .getSupportFragmentManager()
//                    .getFragments();
//
//            for (Fragment fragment : fragmentList) {
//                if (fragment instanceof MoreNoteFragment && fragment.isVisible()) {
//                    requireActivity()
//                            .getSupportFragmentManager()
//                            .beginTransaction()
//                            .remove(fragment)
//                            .commit();
//                }
//            }
//        });
    }
}