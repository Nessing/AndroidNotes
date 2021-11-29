package ru.nessing.androidnotes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

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
        } else if (id == R.id.action_remove) {
            int countBackStack = requireActivity().getSupportFragmentManager().getBackStackEntryCount();
            while (countBackStack > 0) {
                countBackStack--;
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        }

        return super.onOptionsItemSelected(item);
    }

}