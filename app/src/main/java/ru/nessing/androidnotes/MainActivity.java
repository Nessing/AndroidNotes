package ru.nessing.androidnotes;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static int pos = -1;

    public static void setPos(int pos1) {
        pos = pos1;
    }

    private NoteSource noteSource;

    private CardsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_menu, new MainFragment())
                .commit();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        noteSource = new NoteSourceImp(this);

        adapter = new CardsAdapter(this, noteSource);
        adapter.setClickListener(((view, position) -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.action_container, MoreNoteFragment.newInstance(noteSource.getNote(position)))
                    .addToBackStack(null)
                    .commit();
        }));
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            String date = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            noteSource.addNote(
                    new Note(10, "", date, "", R.drawable.ic_launcher_background)
            );
            adapter.notifyItemInserted(noteSource.size() - 1);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.action_container, FragmentEditNote.newInstance(noteSource.getNote(noteSource.size() - 1)))
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.action_remove) {
            return true;
        }
//        else if (item.getItemId() == R.id.button_save) {
//            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
//            noteSource.updateNote(adapter.getMenuPosition(), new Note(10, "Новая карта", "17.08.2020", "Москва", R.drawable.ic_launcher_background));
//            adapter.notifyItemInserted(noteSource.size() - 1);
//            int countBackStack = getSupportFragmentManager().getBackStackEntryCount();
//            while (countBackStack > 0) {
//                countBackStack--;
//                getSupportFragmentManager().popBackStack();
//            }
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_element, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
//            noteSource.updateNote(adapter.getMenuPosition(), new Note(10, "Новая карта", "17.08.2020", "Москва", R.drawable.ic_launcher_background));
//            adapter.notifyItemChanged(adapter.getMenuPosition());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.action_container, FragmentEditNote.newInstance(noteSource.getNote(adapter.getMenuPosition())))
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            noteSource.deleteNote(adapter.getMenuPosition());
            adapter.notifyItemRemoved(adapter.getMenuPosition());
            return true;
        }
        return super.onContextItemSelected(item);
    }
}