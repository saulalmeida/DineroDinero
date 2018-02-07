package almeida.saul.com.pdfcreation;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elite desk 700g1 on 17/01/2018.
 */

public class SolicitudDialog extends DialogFragment {
    ImageButton buttonAdd;
    EditText equipo, cantidad, precio;
    TextView totalC;
    String Stotal;
    Button aceptarButton, cancelButton;
    TextInputEditText proyectoText, actividadText, nordenText, fechaText, estanciaText, personasText, totalText;

    ListView listView;
    List<EquiposModel> rowItems = new ArrayList<>();

    public interface addNewSolicitudListener {
        public void addedNewSolicitud(Solicitudbean solicitud);

    }

    addNewSolicitudListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_solicitud_gasto, container, false);
        getDialog().setCanceledOnTouchOutside(true);
        actividadText = view.findViewById(R.id.dialog_solicitud_actividad);
        proyectoText = view.findViewById(R.id.dialog_solicitud_proyecto);
        nordenText = view.findViewById(R.id.dialog_solicitud_norden);
        fechaText = view.findViewById(R.id.dialog_solicitud_fechaejecucion);
        estanciaText = view.findViewById(R.id.dialog_solicitud_estancia);
        personasText = view.findViewById(R.id.dialog_solicitud_personas);
        buttonAdd = view.findViewById(R.id.dialog_solicitud_button_add);
        equipo = view.findViewById(R.id.dialog_solicitud_equipo);
        cantidad = view.findViewById(R.id.dialog_solicitud_cantidad);
        precio = view.findViewById(R.id.dialog_solicitud_precio);
        totalC = view.findViewById(R.id.solicitud_dialog_total);
        aceptarButton = view.findViewById(R.id.dialog_solicitud_aceptar);
        aceptarButton.setOnClickListener(buttonListener);
        cancelButton = view.findViewById(R.id.dialog_solicitud_cancelar);
        cancelButton.setOnClickListener(buttonListener);


        listView = view.findViewById(R.id.list_gasto);
        final ListAdapter adapter = new ListAdapter(rowItems, view.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                rowItems.remove(i);
                if (rowItems.size() != 0) {
                    float total = 0;
                    float cantidad = 0;
                    float precio = 0;
                    for (int x = 0; x < rowItems.size(); x++) {

                        cantidad = Float.valueOf(rowItems.get(x).getCantidad());
                        precio = Float.valueOf(rowItems.get(x).getPrecio());
                        total += cantidad * precio;
                        Stotal = String.valueOf(total);
                        totalC.setText(Stotal);

                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!equipo.getText().toString().equals("") && !cantidad.getText().toString().equals("") && !precio.getText().toString().equals("")) {
                    Toast.makeText(view.getContext(), "Articulo añadido correctamente", Toast.LENGTH_SHORT).show();

                    EquiposModel bean = new EquiposModel();
                    bean.setEquipo(equipo.getText().toString());
                    bean.setCantidad(cantidad.getText().toString());
                    bean.setPrecio(precio.getText().toString());

                    rowItems.add(bean);
                    if (rowItems.size() != 0) {
                        float total = 0;
                        float cantidad = 0;
                        float precio = 0;
                        for (int i = 0; i < rowItems.size(); i++) {

                            cantidad = Float.valueOf(rowItems.get(i).getCantidad());
                            precio = Float.valueOf(rowItems.get(i).getPrecio());
                            total += cantidad * precio;
                            Stotal = String.valueOf(total);
                            totalC.setText(Stotal);

                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(view.getContext(), "Se deben de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.dialog_solicitud_aceptar:
                    validarDatos(view.getContext());
                    getDialog().dismiss();
                    break;
                case R.id.dialog_solicitud_cancelar:
                    getDialog().dismiss();
                    break;

            }
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (addNewSolicitudListener) activity;

        } catch (Exception e) {
            throw new ClassCastException(activity.toString() + " must implement OnGastoProvided");
        }
    }

    public void validarDatos(Context c) {
        boolean res = false;
        String actividad = actividadText.getText().toString();
        String proyecto = proyectoText.getText().toString();
        String norden = nordenText.getText().toString();
        String fecha = fechaText.getText().toString();
        String estancia = estanciaText.getText().toString();
        String personas = personasText.getText().toString();

        boolean act = actividadValida(actividad);
        boolean proy = proyValida(proyecto);
        boolean nord = ordenValida(norden);
        boolean fec = fechValida(fecha);
        boolean estan = estanciaValida(estancia);
        boolean perso = personaValida(personas);
        boolean hasContent = listHasContent(rowItems.size(),c);


        if (act && proy && nord && fec && estan && perso && hasContent) {
            Toast.makeText(c, "Se guardó solicitud", Toast.LENGTH_LONG).show();
            Solicitudbean b = new Solicitudbean();
            b.setActividades(actividad);
            b.setProyecto(proyecto);
            b.setOrden(norden);
            b.setFecha(fecha);
            b.setEstancia(estancia);
            b.setPersonas(personas);
            b.setTotal(Stotal);
            b.setEquipos(rowItems);
            listener.addedNewSolicitud(b);
        }
    }

    public boolean actividadValida(String a) {
        boolean res = false;
        if (a.length() <= 0) {
            actividadText.setError("Este campo es obligatorio");
            res = false;

        } else {
            actividadText.setError(null);
            res = true;

        }
        return res;

    }

    public boolean proyValida(String pro) {
        boolean res = false;
        if (pro.length() <= 0) {
            proyectoText.setError("Este campo es obligatorio");
            res = false;

        } else {
            proyectoText.setError(null);
            res = true;

        }
        return  res;


    }

    public boolean ordenValida(String ord) {
        boolean res = false;
        if (ord.length() <= 0) {
            nordenText.setError("Este campo es obligatorio");
            res = false;

        } else {
            nordenText.setError(null);
            res = true;

        }

        return res;


    }

    public boolean fechValida(String fec) {
        boolean res = false;
        if (fec.length() <= 0) {
            fechaText.setError("Este campo es obligatorio");
            res = false;

        } else {
            fechaText.setError(null);
            res = true;

        }

        return res;


    }

    public boolean estanciaValida(String estancia) {
        boolean res = false;
        if (estancia.length() <= 0) {
            estanciaText.setError("Este campo es obligatorio");
            res = false;

        } else {
            estanciaText.setError(null);
            res = true;

        }

        return  res;


    }

    public boolean personaValida(String persona) {
        boolean res = false;
        if (persona.length() <= 0) {
            personasText.setError("Este campo es obligatorio");
            res = false;

        } else {
            personasText.setError(null);
            res = true;

        }
        return res;


    }
    public boolean listHasContent(int i,Context context){
        boolean res = false;
        if (i  <= 0){
            res = false;
            Toast.makeText(context,"No tienes equipo en estas solicitud",Toast.LENGTH_SHORT).show();

        }else {
            res = true;
        }

        return res;
    }



}
