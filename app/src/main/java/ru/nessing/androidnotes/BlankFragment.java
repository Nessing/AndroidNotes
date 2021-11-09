package ru.nessing.androidnotes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlankFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layout = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notes);

        for (int i = 0; i < notes.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(notes[i]);
            textView.setTextSize(30);

            final int position = i;
            textView.setOnClickListener(v -> {
                showImage(position);
            });

            layout.addView(textView);
        }
    }

    private boolean isLand() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void showImage(int position) {
        if (isLand()) {
            showImageLand(position);
        } else {
            showImagePort(position);
        }
    }

    private void showImagePort(int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.blank_Fragment, MoreNoteFragment.newInstance(position))
                .addToBackStack(null)
                .commit();
    }

    private void showImageLand(int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.blank_Fragment_img, MoreNoteFragment.newInstance(position))
                .addToBackStack(null)
                .commit();
    }
}