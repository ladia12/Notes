package sample.com.notes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sample.com.notes.R;
import sample.com.notes.activity.NoteActivity;
import sample.com.notes.model.Note;

/**
 * Created by ladia on 29/07/16.
 */
public class NotesAdapter extends RecyclerView.Adapter {

    private Context mCtx;
    private ArrayList<Note> notes;
    public NotesAdapter(Context context, ArrayList<Note> notes) {
        mCtx = context;
        this.notes = notes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_card, parent, false);
        return new NoteCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoteCardViewHolder vh = ((NoteCardViewHolder)holder);
        Note note = notes.get(position);
        vh.noteTitle.setText(note.getTitle());
        vh.noteDesc.setText(note.getDesc());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView noteTitle;
        TextView noteDesc;

        public NoteCardViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            noteTitle = (TextView) itemView.findViewById(R.id.title);
            noteDesc = (TextView) itemView.findViewById(R.id.desc);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(mCtx ,NoteActivity.class);
            i.putExtra("note",notes.get(this.getLayoutPosition()));
            mCtx.startActivity(i);
        }
    }
}
