package almeida.saul.com.pdfcreation;

import android.app.Activity;
import android.content.Context;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elite desk 700g1 on 17/01/2018.
 */

public class ListAdapter extends ArrayAdapter<EquiposModel>  {

    private List<EquiposModel> beans;
    private Context context;

    public class viewHolder {
        TextView equipo;
        TextView cantidad;
        TextView precio;

    }

    public ListAdapter (List<EquiposModel> data, Context context){
        super(context,R.layout.list_actividades,data);
        beans = data;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_actividades,null);
            holder =  new viewHolder();
            holder.equipo = convertView.findViewById(R.id.list_actividad);
            holder.cantidad = convertView.findViewById(R.id.list_cantidad);
            holder.precio = convertView.findViewById(R.id.list_precio);
            convertView.setTag(holder);

        }else{
            holder  = (viewHolder)convertView.getTag();
        }

        EquiposModel solicitudbean = (EquiposModel) getItem(position);

        holder.equipo.setText(solicitudbean.getEquipo());
        holder.precio.setText(solicitudbean.getPrecio());
        holder.cantidad.setText(solicitudbean.getCantidad());

        return convertView;

    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Nullable
    @Override
    public EquiposModel getItem(int position) {
        return beans.get(position);
    }

}
