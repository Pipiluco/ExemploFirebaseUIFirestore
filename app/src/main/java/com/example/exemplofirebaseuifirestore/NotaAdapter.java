package com.example.exemplofirebaseuifirestore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class NotaAdapter extends FirestoreRecyclerAdapter<Nota, NotaAdapter.NotaHolder> {
    private OnItemClickListener listener;

    public NotaAdapter(@NonNull FirestoreRecyclerOptions<Nota> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotaHolder holder, int position, @NonNull Nota model) {
        holder.tvTitulo.setText(model.getTitulo());
        holder.tvDescricao.setText(model.getDescricao());
        holder.tvPrioridade.setText(String.valueOf(model.getPrioridade()));
    }

    @NonNull
    @Override
    public NotaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
        return new NotaHolder(view);
    }

    public void deleteNota(int posicao) {
        getSnapshots().getSnapshot(posicao).getReference().delete();

    }

    class NotaHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvPrioridade, tvDescricao;

        public NotaHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvPrioridade = itemView.findViewById(R.id.tvPrioridade);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicao = getAdapterPosition();

                    if (posicao != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(posicao), posicao);
                    }
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int posicao);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
