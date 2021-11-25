package ru.nessing.androidnotes;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int pos = -1;

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos1) {
        pos = pos1;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_menu, new MainFragment())
                .commit();

        List<Note> noteList = Arrays.asList(
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
        );

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CardsAdapter adapter = new CardsAdapter(noteList);
        adapter.setClickListener(((view, position) -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.action_container, MoreNoteFragment.newInstance(noteList.get(position)))
                    .addToBackStack(null)
                    .commit();
            Toast.makeText(this, "Click on position " + position, Toast.LENGTH_SHORT).show();
        }));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

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
}