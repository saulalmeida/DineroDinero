package almeida.saul.com.pdfcreation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elite desk 700g1 on 15/01/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ContactViewHolder> {

    private List<Gasto> listGasto;

    public CardAdapter(List<Gasto> listGasto){
        this.listGasto = listGasto;
    }

    @Override
    public int getItemCount() {
        return listGasto.size();
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout,parent,false);
        return new ContactViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Gasto  g = listGasto.get(position);
        holder.vFecha.setText(g.getFecha());
        holder.vTipo.setText(g.getTipo());
        holder.vImporte.setText(String.valueOf(g.getImporte()));

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{


        protected TextView vFecha;
        protected TextView vTipo;
        protected TextView vImporte;

        public ContactViewHolder(View itemView) {
            super(itemView);
            vFecha = itemView.findViewById(R.id.item_fecha);
            vTipo = itemView.findViewById(R.id.item_tipo);
            vImporte = itemView.findViewById(R.id.item_importe);

        }
    }
}
