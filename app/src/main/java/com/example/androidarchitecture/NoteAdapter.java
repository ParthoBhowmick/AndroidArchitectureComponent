package com.example.androidarchitecture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> mNotes = new ArrayList<>();
    private OnItemClickListener mlistener;

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView desView;
        private TextView priorityView;


        public NoteHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tvTitle);
            desView = itemView.findViewById(R.id.tvDescription);
            priorityView = itemView.findViewById(R.id.tvPrioriy);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(mlistener!=null && position!=RecyclerView.NO_POSITION)  {
                        mlistener.onItemClick(mNotes.get(position));

                    }

                }
            });

        }
    }


    @NonNull
    @Override
    public NoteAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteHolder holder, int position) {
        Note currentNote = mNotes.get(position);
        holder.titleView.setText(currentNote.getTitle());
        holder.desView.setText(currentNote.getDescription());
        holder.priorityView.setText(Integer.toString(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setNotes(List<Note> notes) {
        this.mNotes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return mNotes.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mlistener = listener;
    }

}
