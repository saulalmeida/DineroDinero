package almeida.saul.com.pdfcreation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Elite desk 700g1 on 16/01/2018.
 */

public class SolicitudAdpater extends RecyclerView.Adapter<SolicitudAdpater.SolicitudViewHolder> {



    public interface OnItemClickListener{
        void onItemClick(Solicitudbean item);
    }

    public List<Solicitudbean> listSolicitud;
    private OnItemClickListener listener;


    public SolicitudAdpater(List<Solicitudbean> listSolicitud, OnItemClickListener listener){
        this.listSolicitud = listSolicitud;
        this.listener =listener;
    }
    public SolicitudAdpater(List<Solicitudbean> listSolicitud){
        this.listSolicitud = listSolicitud;
    }


    @Override
    public SolicitudViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_solicitud_gasto,parent,false);
        return new SolicitudViewHolder(item);
    }

    @Override
    public void onBindViewHolder(SolicitudViewHolder holder, int position) {
        Solicitudbean solicitudbean = listSolicitud.get(position);
        holder.Norden.setText(solicitudbean.getOrden());
        holder.Presupuesto.setText(solicitudbean.getTotal());
        holder.Fecha.setText(solicitudbean.getFecha());
        holder.bind(listSolicitud.get(position),listener);

    }

    @Override
    public int getItemCount() {
        return listSolicitud.size();
    }

    public static class SolicitudViewHolder extends RecyclerView.ViewHolder{


        protected TextView Norden;
        protected TextView Presupuesto;
        protected TextView Fecha;

        public SolicitudViewHolder(View itemView) {
            super(itemView);
            Norden = itemView.findViewById(R.id.s_N_orden);
            Presupuesto = itemView.findViewById(R.id.s_presupuesto);
            Fecha = itemView.findViewById(R.id.s_fecha);

        }

        public void bind(final Solicitudbean item, final OnItemClickListener listener)
        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
