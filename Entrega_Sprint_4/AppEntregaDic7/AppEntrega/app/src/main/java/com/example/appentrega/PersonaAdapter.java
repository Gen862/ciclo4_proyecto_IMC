package com.example.appentrega;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appentrega.model.Persona;

import java.util.ArrayList;
public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaVH> {
    private ArrayList<Persona> personas;
    public PersonaAdapter(ArrayList<Persona> ls_personas)
    { this.personas=ls_personas;}

    @NonNull
    @Override
    public PersonaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_layout, parent, false);
        return new PersonaVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaVH holder, int position) {
        Persona persona = personas.get(position);
        holder.mailTextView.setText(persona.getEmail());
        holder.generoTextView.setText(persona.getGenero());
        holder.estaturaTextView.setText(persona.getEstatura()+"");
        holder.pesoTextView.setText(persona.getPeso()+"");
        holder.edadTextView.setText(persona.getEdad()+"");
        holder.imcTextView.setText(persona.getImc()+"");
        boolean isExpanded = personas.get(position).isExpanded();
        holder.ventana_expandible.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.fechaTextView.setText(persona.getFecha()+"");
        holder.comTextView.setText(persona.getTipo());
        holder.notaTextView.setText(persona.getNota());

    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    class PersonaVH extends RecyclerView.ViewHolder {
        ConstraintLayout ventana_expandible;
        TextView mailTextView, generoTextView, estaturaTextView,fechaTextView,
                 edadTextView, imcTextView,pesoTextView,comTextView,notaTextView;

        public PersonaVH(@NonNull final View itemView) {
            super(itemView);
            mailTextView = itemView.findViewById(R.id.mailTextView);
            generoTextView = itemView.findViewById(R.id.genero);
            estaturaTextView = itemView.findViewById(R.id.estatura);
            pesoTextView = itemView.findViewById(R.id.peso);
            edadTextView = itemView.findViewById(R.id.edad);
            imcTextView = itemView.findViewById(R.id.imc);
            ventana_expandible = itemView.findViewById(R.id.ventana_expandible);
            fechaTextView = itemView.findViewById(R.id.fecha);
            comTextView = itemView.findViewById(R.id.com);
            notaTextView = itemView.findViewById(R.id.nota);
            mailTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Persona persona = personas.get( getAdapterPosition());
                    persona.setExpanded(!persona.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
