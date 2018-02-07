package almeida.saul.com.pdfcreation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.location.LocationListener;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolicitudGasto extends AppCompatActivity implements SolicitudDialog.addNewSolicitudListener {
    private static final String LOG_TAG = "Solicitud de gasto";
    SolicitudAdpater adpter;
    RecyclerView recycler;
    List<Solicitudbean> items;
    SwippeControl swippeControl;
    SharedPreferences sharedPreferences;
    public static final String DATA_EXTRA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitud_gasto);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        items = new ArrayList<>();
        loadsavedFiles();
        setupRecyclerView();

        FloatingActionButton fabButton = findViewById(R.id.add_solictud);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewItem();

            }
        });

//        recycler = findViewById(R.id.recycler_sol);
//        recycler.setHasFixedSize(true);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recycler.setLayoutManager(manager);
//        SwippeControl swippeControl = new SwippeControl();
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swippeControl);
//        itemTouchHelper.attachToRecyclerView(recycler);
//        adpter = new SolicitudAdpater(items, new SolicitudAdpater.OnItemClickListener() {
//            @Override
//            public void onItemClick(Solicitudbean item) {
//                Toast.makeText(getApplicationContext(), "onClick activado con item " + item.getOrden(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        recycler.setAdapter(adpter);

    }

    private void setupRecyclerView() {

        recycler = findViewById(R.id.recycler_sol);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);

        swippeControl = new SwippeControl(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {

                adpter.listSolicitud.get(position);
                items.remove(position);
                if (items.size() > 0) {
                    sharedPreferences.edit().remove(items.get(position).getOrden()).apply();
                }
                adpter.notifyItemRemoved(position);
                adpter.notifyItemRangeChanged(position, adpter.getItemCount());

            }
        });
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swippeControl.onDraw(c);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swippeControl);
        itemTouchHelper.attachToRecyclerView(recycler);
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swippeControl.onDraw(c);
            }
        });
        adpter = new SolicitudAdpater(items, new SolicitudAdpater.OnItemClickListener() {
            @Override
            public void onItemClick(Solicitudbean item) {
                Toast.makeText(getApplicationContext(), "onClick activado con item " + item.getOrden(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SolicitudGasto.this, MainActivity.class);
                i.putExtra(DATA_EXTRA, item);
                startActivity(i);

            }
        });
        adpter.notifyDataSetChanged();
        recycler.setAdapter(adpter);


    }


    private void createNewItem() {
        FragmentManager manger = getFragmentManager();
        Fragment fr = manger.findFragmentByTag("dialog_solicitud_gasto");
        if (fr != null) {
            manger.beginTransaction().remove(fr).commit();
        }

        SolicitudDialog dialog = new SolicitudDialog();
        dialog.show(manger, "dialog_solicitud_gasto");
    }

    @Override
    public void addedNewSolicitud(Solicitudbean solicitud) {
        items.add(solicitud);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(solicitud);
        editor.putString(solicitud.getOrden(), json);
        editor.apply();
//        adpter = new SolicitudAdpater(items, new SolicitudAdpater.OnItemClickListener() {
//            @Override
//            public void onItemClick(Solicitudbean item) {
//                Toast.makeText(getApplicationContext(),"hola "+item.getOrden(),Toast.LENGTH_SHORT).show();
//            }
//        });
        adpter.notifyDataSetChanged();
        recycler.setAdapter(adpter);
        Metod m = new Metod();
        m.createTable("Solicitud "+solicitud.getOrden(),this,solicitud);

    }

    public void loadsavedFiles() {

        Map<String, ?> prefsMap = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            Log.e("SharedPreferences", entry.getKey() + ":" +
                    entry.getValue().toString());
            Gson gson = new Gson();
            Solicitudbean b = gson.fromJson(entry.getValue().toString(), Solicitudbean.class);
            items.add(b);

        }


    }
}

