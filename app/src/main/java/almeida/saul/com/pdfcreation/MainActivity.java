package almeida.saul.com.pdfcreation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.BaseFont;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Generate PDF";
    private BaseFont bfbold;
    private TextView Proyecto,Total, Restante;
    Solicitudbean solicitudbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Proyecto = findViewById(R.id.main_project);
        Total = findViewById(R.id.main_presupuest);
        Restante = findViewById(R.id.main_restante);
        FloatingActionButton addButton = findViewById(R.id.addbutton);
        RecyclerView recList = findViewById(R.id.recycler_view);
        recList.setHasFixedSize(true);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayout);
        solicitudbean = (Solicitudbean) getIntent().getSerializableExtra("DATA");
        Proyecto.setText(solicitudbean.getProyecto());
        Total.setText(solicitudbean.getTotal());
        Restante.setText(solicitudbean.getTotal());

        CardAdapter adapter = new CardAdapter(createList(30));
        recList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewItem();
            }
        });
    }

    public void GeneratePDF(View v){
        String filename = "Saul Almeida";
        String filecontent = "Hola desde pdf android ";
        Metod m = new Metod();
        if (m.write(filename,filecontent,this)){
            Toast.makeText(getApplicationContext(),"pdf creado",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "I/O Error",Toast.LENGTH_SHORT).show();
        }


    }


    private List<Gasto> createList (int size){
        List<Gasto> result  = new ArrayList<Gasto>();
        for (int i =1;i <= size;i++){
            Gasto g = new Gasto();
            g.setFecha("fecha "+i);
            g.setDescripcion("descripcion "+i);
            g.setImporte(9.12f+i);

            result.add(g);
        }
        return result;
    }

    private void createNewItem(){
        Toast.makeText(getApplicationContext(),"Hola float button",Toast.LENGTH_LONG).show();
        FragmentManager manger =  getFragmentManager();
        Fragment fr = manger.findFragmentByTag("add_item_dialog");
        if (fr != null){
            manger.beginTransaction().remove(fr).commit();
        }

        GastoDialog dialog = new GastoDialog();
        dialog.show(manger,"add_item_dialog");
    }
}
