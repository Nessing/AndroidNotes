package ru.nessing.androidnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private final List<Note> noteList;
    private OnCardClickListener clickListener;

    public void setClickListener(OnCardClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public CardsAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }



    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView textView = itemView.findViewById(R.id.text_view);

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind (Note note) {
            textView.setText(note.getTitle());
            textView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onCardClick(v, getAdapterPosition());
                }
            });
        }
    }

    interface OnCardClickListener {
        void onCardClick(View view, int position);
    }
}
