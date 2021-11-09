package ru.nessing.androidnotes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MoreNoteFragment extends Fragment {

    private static final String ARG_POS = "ARG_POS";

    private int position = -1;

    public MoreNoteFragment() {
    }

    public static MoreNoteFragment newInstance(int position) {
        MoreNoteFragment fragment = new MoreNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POS, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TypedArray images = getResources().obtainTypedArray(R.array.images);

        ImageView imageView = view.findViewById(R.id.image_view);
        imageView.setImageResource(images.getResourceId(position, 0));

        images.recycle();
    }
}