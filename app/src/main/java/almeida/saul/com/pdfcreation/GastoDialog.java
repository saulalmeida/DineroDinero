package almeida.saul.com.pdfcreation;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;

import com.itextpdf.text.pdf.languages.ArabicLigaturizer;

/**
 * Created by Elite desk 700g1 on 16/01/2018.
 */

public class GastoDialog extends DialogFragment {

    private TextInputEditText Fecha,Importe,Subtotal, IVA, Tipo, Empleado, Descripcion, Referencia;
    private Spinner  IE,Mes;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_item_dialog, container);
        getDialog().setCanceledOnTouchOutside(false);
        Fecha = view.findViewById(R.id.dialog_fecha);
        Importe = view.findViewById(R.id.dialog_importe);
        Subtotal = view.findViewById(R.id.dialog_subtotal);
        IVA = view.findViewById(R.id.dialog_iva);
        Tipo = view.findViewById(R.id.dialog_tipo);
        Empleado = view.findViewById(R.id.dialog_empleado);
        Descripcion = view.findViewById(R.id.dialog_descrip);
        Referencia = view.findViewById(R.id.dialog_referencia);
        IE = view.findViewById(R.id.spin_IE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.IE,R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        IE.setAdapter(adapter);
        Mes = view.findViewById(R.id.spin_mes);
        ArrayAdapter<CharSequence> adapterMes = ArrayAdapter.createFromResource(view.getContext(),R.array.meses,R.layout.custom_spinner_item);
        adapterMes.setDropDownViewResource(R.layout.custom_spinner_item);
        Mes.setAdapter(adapterMes);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{

        }catch (Exception e){
            throw new ClassCastException(activity.toString()+ " must implement OnGastoProvided");
        }
    }
}
