package com.example.crist.logind;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crist.logind.Adapter.SlugAdapter;
import com.example.crist.logind.Modelo.Slug;
import com.example.crist.logind.Modelo.Slug_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.containerMain)
    CoordinatorLayout containerMain;


    public SlugAdapter adapter;

    //-- public static  final Slug sSlug = new Slug();


    GridLayoutManager mGridLayoutManager;
    //mRecyclerView.setLayoutManager(mGridLayoutManager);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        configToolbar();
        configAdapter();
        configRecyclerView();
        generategSlug(); // SE COMENTA YA QUE SE GENERA EL LISTADO PERO SE TRIPLICA LA INFROMACION



    }


    ///////  ---->>>  METODO PARA GUARDAR LA INFORMACION <<<<<-----
    ///////  ---->>>  METODO PARA GUARDAR LA INFORMACION <<<<<-----
    private void generategSlug() {
        String[] nombres = {"Enigma", "Hypnogriff", "Infierno", "Flautista", "Bubbaleone", "Estropeada", "Versátil", "Terror", "Novato", "Cristálida", "Boon Doc Blanca", "Boon Doc","Tornado"};
        String[] babosaFamosa = {"Mo", "Dazzer", "Burpy, Joo-Joo", "Flautista", "Jabonosa", "Estropeada", "Loki", "Spooker", "Novato", "Digger", "Ping","Doc", "Tornado (Trixie)"};
        String[] habitat = {"Jungla", "Humedales", "Lagos de lava", "", "Ríos de agua dulce y arroyos rocosos", "", "Cimas de montañas y prados alpinos", "Caverna Nefasta", "Desconocido", "Debajo de la tierra", "Desconocido","Cavernas Jungla", "Caverna Campo Ehólico"};
        String[] tipoPoder = {"", "", "", "Ráfagas sónicas", "", "", "", "", "Bombas", "", "Sanación", "",""};

        String[] versionMalvada = {"", "Cryptogriff", "Darkfurnus", "", "Bubbalash", "", "Smugglet", "Nocturna", "Burspede", "Cristálida Malvada", "","Goon Doc", "Tempesto"};
        String[] elemento = {"Luz", "Psíquico", "Fuego", "Aire", "Agua", "", "Psíquico", "Psíquico", "Fuego", "Tierra", "Luz","Luz", "Aire"};
        String[] rareza = {"Ultra Rara", "Ultra Rara", "Ultra Rara", "Ultra Rara", "Poco Común", "", "Poco Común", "Rara", "Poco Común", "Ultra Rara", "Extremadamente Rara","Extremadamente Rara", "Común"};

        String[] notas = {"", "", "", "", "", "", "", "", "", "", "", "",""};

        //https://www.flickr.com/photos/perlucha/16664166666
        String[] photos = {"https://vignette.wikia.nocookie.net/bajoterra-slug/images/6/68/Enigmo%282%29.png/revision/latest?cb=20181024190853&path-prefix=es", "https://vignette.wikia.nocookie.net/bajoterra-slug/images/8/89/Babosa_hypnogriff.png/revision/latest?cb=20131223150853&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/8/86/Lala.png/revision/latest?cb=20170312182655&path-prefix=es", "https://vignette.wikia.nocookie.net/bajoterra-slug/images/b/b9/Pieper_proto.png/revision/latest?cb=20170608224659&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/f/ff/Babosa_burbuja_original.png/revision/latest?cb=20131107233707&path-prefix=es", "https://pm1.narvii.com/6352/876a21c006823f7c4e509e9f8d19b664648298e9_hq.jpg",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/9/95/Babosa_adversatil.png/revision/latest?cb=20131223192204&path-prefix=es", "https://vignette.wikia.nocookie.net/bajoterra-slug/images/8/82/Spectre%282%29.png/revision/latest?cb=20181020224658&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/c/cd/ST_SG_SLUGS_BlastipedeProto.png/revision/latest?cb=20131225183812&path-prefix=es", "https://vignette.wikia.nocookie.net/bajoterra-slug/images/7/78/Cristalida_protoforma.png/revision/latest?cb=20150214180932&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/a/ae/White_Boon_Doc_Protoform.png/revision/latest?cb=20171011232734&path-prefix=es","https://vignette.wikia.nocookie.net/bajoterra-slug/images/d/d1/Babosas-Sanadoras-BajoTerra-275x300.png/revision/latest?cb=20130911233919&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/c/c9/Babosa_tornado_original.png/revision/latest?cb=20131108015327&path-prefix=es"};

            /// Megamorph
        String[] photos2={"https://i.ytimg.com/vi/H6TItBDyBMs/hqdefault.jpg","",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/f/f4/Infurnusmega-proto.png/revision/latest?cb=20160208002327&path-prefix=es","https://vignette.wikia.nocookie.net/bajoterra-slug/images/2/2c/Pieper_Transformation.png/revision/latest?cb=20171225191311&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/6/64/Babosa_burbuja_transformada.png/revision/latest?cb=20131107233731&path-prefix=es","",
                "","https://vignette.wikia.nocookie.net/bajoterra-slug/images/d/d8/Frightgeistmega-proto.png/revision/latest/scale-to-width-down/310?cb=20181020224758&path-prefix=es",
                "","https://vignette.wikia.nocookie.net/bajoterra-slug/images/9/98/Crisalida_Megamorph%28Protoforma%29.png/revision/latest/scale-to-width-down/310?cb=20160121011524&path-prefix=es",
                "","https://vignette.wikia.nocookie.net/bajoterra-slug/images/e/e2/Doc_MegaMorph/revision/latest/scale-to-width-down/310?cb=20141023013751&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/c/ca/Tornado_Megamorph%28Protoforma%29.png/revision/latest/scale-to-width-down/310?cb=20160121012413&path-prefix=es"};

        //Transformada
        String[] photos3={"https://vignette.wikia.nocookie.net/bajoterra-slug/images/c/c1/Babosa_enigma_.png/revision/latest?cb=20131108001400&path-prefix=es","https://vignette.wikia.nocookie.net/bajoterra-slug/images/8/81/ST_SG_SLUGS_HypnogrifVeloTrail.png/revision/latest/scale-to-width-down/310?cb=20131221102015&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/d/d7/Burpy_transformmatte2.png/revision/latest/scale-to-width-down/310?cb=20181015215527&path-prefix=es","https://vignette.wikia.nocookie.net/bajoterra-slug/images/8/8a/Pieper_MM.png/revision/latest?cb=20171225191234&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/3/39/Bubaleone_Megamorph%28Protoforma%29.png/revision/latest/scale-to-width-down/310?cb=20160121012726&path-prefix=es","",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/c/c5/Babosa_versatil.png/revision/latest/scale-to-width-down/310?cb=20140114213323&path-prefix=es","https://vignette.wikia.nocookie.net/bajoterra-slug/images/5/50/Spectre_transformmatte2.png/revision/latest/scale-to-width-down/310?cb=20181020224739&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/c/cd/Blastipede_TransformedwTrail.png/revision/latest/scale-to-width-down/310?cb=20131225183831&path-prefix=es","https://vignette.wikia.nocookie.net/bajoterra-slug/images/5/54/Cristaloda_transformada.png/revision/latest/scale-to-width-down/310?cb=20150214180941&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/b/b6/Sonica_%28Transformada%29.png/revision/latest/scale-to-width-down/310?cb=20160215225541&path-prefix=es","https://vignette.wikia.nocookie.net/bajoterra-slug/images/9/9d/Sanadora.png/revision/latest?cb=20130911234026&path-prefix=es",
                "https://vignette.wikia.nocookie.net/bajoterra-slug/images/f/f4/Babosa_tornado_transformada.png/revision/latest/scale-to-width-down/310?cb=20131108015349&path-prefix=es"};



        /// COMPARAR CON EL METODO EQUALS Y HASHCODE
        for (int i = 0; i < nombres.length; i++) {
                                    // i + 1 // SE QUITA ID PORQUE YA ES AUTO INCREMENTABLE EN EL MODELO
            Slug slug = new Slug( i + 1,nombres[i], babosaFamosa[i], habitat[i], tipoPoder[i],
                    versionMalvada[i], elemento[i], rareza[i], notas[i], i + 1,
                    photos[i], photos2[i], photos3[i]);


            try {
                /* adapter.add(slug); // SE AÑADE EL ARTISTA AL ADAPTADOR */

                slug.save();// SQL VERIFICA SI ES UNA ACTUALIZACIION O INSERCION
                Log.i("DBFlow: ", "Insercion Correcta de Datos");
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("DBFlow: ", "Error Al insertar  Datos: " + e.getMessage());
            }

        }


    }

    //// ----->>>>  CONFIGUS   <<<<< ------////

    private void configToolbar() {
        setSupportActionBar(toolbar);
    }

    private void configAdapter() {

        adapter = new SlugAdapter(new ArrayList<Slug>(), this);
    }

    private void configRecyclerView() {
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        /* mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setAdapter(adapter);*/

    }





    @Override
    protected void onResume() {
        super.onResume();
       adapter.setList(getSlugFromDB());
    }


   private List<Slug> getSlugFromDB(){
        return SQLite.select() // se selecciona todo
                .from(Slug.class) // de la clase
                .orderBy(Slug_Table.orden,true)  // de la tabla, que es el modelo
                .queryList(); // regresa un listado de tipo Slug

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /***
     * METODOS IMPLEMENTADOS POR LA INTERFAZ ONITEMCLICK LISTENER
     **/
    @Override
    public void onItemCLick(Slug slug) {

        // coment - usar sin base de datos
      /*  sSlug.setId(slug.getId());
        sSlug.setNombre(slug.getNombre());
        sSlug.setBabosaFamosa(slug.getBabosaFamosa());
        sSlug.setHabitat(slug.getHabitat());
        sSlug.setTipoPoder(slug.getTipoPoder());
        sSlug.setVersionMalvada(slug.getVersionMalvada());
        sSlug.setElemento(slug.getElemento());
        sSlug.setRareza(slug.getRareza());
        sSlug.setOrden(slug.getOrden());
        sSlug.setNotas(slug.getNotas());
        sSlug.setFotoURL(slug.getFotoURL());*/

        Intent intent = new Intent(MainActivity.this,DetalleActivity.class);
        intent.putExtra(Slug.ID, slug.getId());
        intent.putExtra("isEdit",false);
        startActivity(intent);


    }

    @Override
    public void onLongItemClick(final Slug slug) {
//añadir vibracion
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator != null){
            vibrator.vibrate(60);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.main_dialog_delete_title)
                .setMessage(String.format(Locale.ROOT, getString(R.string.main_dialog_delete_message), slug.getBabosaFamosa()))
                .setPositiveButton(R.string.label_dialog_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            slug.delete();
                            adapter.remove(slug);
                            showMessage(R.string.main_mesagge_delete_success);
                        } catch (Exception e) {
                            e.printStackTrace();
                            showMessage(R.string.main_mesagge_delete_fail);

                        }

                    }
                })
                .setNegativeButton(R.string.label_dialog_cancel,null);

        builder.show();
    }


    // METODO INECESARIO YA QUE SE ESTA REFRESCANDO EN EL METODO ON RESUME
 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode==1){
            adapter.add(sSlug);

        }
    }*/

    @OnClick(R.id.fab)
    public void onAddSlug() {

        Intent intent = new Intent(MainActivity.this,AddSlugActivity.class);
        intent.putExtra(Slug.ORDEN,adapter.getItemCount() + 1);
        startActivityForResult(intent, 1);
    }



    private void showMessage(int resource) {
        Snackbar.make(containerMain, resource, Snackbar.LENGTH_SHORT).show();
    }
}
