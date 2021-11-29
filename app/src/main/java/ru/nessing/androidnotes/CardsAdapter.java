package ru.nessing.androidnotes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private final NoteSource noteSource;
    private final Activity activity;

    private int menuPosition = -1;
    private OnCardClickListener clickListener;

    public CardsAdapter(Activity activity, NoteSource noteSource) {
        this.noteSource = noteSource;
        this.activity = activity;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    public void setClickListener(OnCardClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(noteSource.getNote(position));
    }

    @Override
    public int getItemCount() {
        return noteSource.size();
    }


    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView textView = itemView.findViewById(R.id.text_view);

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            activity.registerForContextMenu(itemView);
        }

        void bind(Note note) {
            textView.setText(note.getTitle());
            textView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onCardClick(v, getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(view -> {
                menuPosition = getLayoutPosition();
                itemView.showContextMenu(10f, 10f);
                return false;
            });
        }
    }

    interface OnCardClickListener {
        void onCardClick(View view, int position);
    }
}
