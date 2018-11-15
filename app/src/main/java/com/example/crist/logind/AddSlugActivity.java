package com.example.crist.logind;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.crist.logind.Modelo.Slug;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSlugActivity extends AppCompatActivity {
    private static final int REQUEST_TEXT = -1;

    @BindView(R.id.edtName)
    TextInputEditText edtName;
    @BindView(R.id.edtBabosaFamosa)
    TextInputEditText edtBabosaFamosa;
    @BindView(R.id.edtHabitat)
    TextInputEditText edtHabitat;
    @BindView(R.id.edtTipoPoder)
    TextInputEditText edtTipoPoder;
    @BindView(R.id.edtElemento)
    TextInputEditText edtElemento;
    @BindView(R.id.edtRareza)
    TextInputEditText edtRareza;
    @BindView(R.id.edtVersionMalvada)
    TextInputEditText edtVersionMalvada;
    @BindView(R.id.edtNotas)
    TextInputEditText edtNotas;

    @BindView(R.id.imgFoto)
    ImageView imgFoto;
    @BindView(R.id.imgDeleteFoto)
    AppCompatImageView imgDeleteFoto;
    @BindView(R.id.imgFromGalery)
    AppCompatImageView imgFromGalery;
    @BindView(R.id.imgFromUrl)
    AppCompatImageView imgFromUrl;

    private Slug mSlug;

    //-- public static  final  Slug sSlug = new Slug();
    private static final int RC_PHOTO_PICKER = 21;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_slug);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        configActionBar();

        configSlug(getIntent());

    }


    private void configActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // habilitar flecha de rectoceso en vista
        }
    }

    private void configSlug(Intent intent) {
        mSlug = new Slug();
        mSlug.setOrden(intent.getIntExtra(Slug.ORDEN, 1));
    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()) {
           /*  case android.R.id.home:
                finish();
                break;*/

            case R.id.action_Save:
                saveSlug();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.fab)
    public void saveSlug() {
        try {
            if (validateFields()) {

                mSlug.setNombre(edtName.getText().toString().trim());
                mSlug.setBabosaFamosa(edtBabosaFamosa.getText().toString().trim());
                mSlug.setHabitat(edtHabitat.getText().toString().trim());
                mSlug.setTipoPoder(edtTipoPoder.getText().toString().trim());
                mSlug.setElemento(edtElemento.getText().toString().trim());
                mSlug.setRareza(edtRareza.getText().toString().trim());
                mSlug.setVersionMalvada(edtVersionMalvada.getText().toString().trim());
                mSlug.setNotas(edtNotas.getText().toString().trim());
                try {
                    mSlug.save();   // devuelve 1 cuando la insercion es correcta y devuelve un -1 cuando hubo error
                    Log.i("DBFlow: ", "Insercion add Correcta de Datos");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("DBFlow: ", "Error add Al insertar  Datos: " + e.getMessage());
                }

           /* MainActivity.sSlug.setNombre(edtName.getText().toString().trim());
            MainActivity.sSlug.setBabosaFamosa(edtBabosaFamosa.getText().toString().trim());
            MainActivity.sSlug.setHabitat(edtHabitat.getText().toString().trim());
            MainActivity.sSlug.setTipoPoder(edtTipoPoder.getText().toString().trim());
            MainActivity.sSlug.setVersionMalvada(edtVersionMalvada.getText().toString().trim());
            MainActivity.sSlug.setRareza(edtRareza.getText().toString().trim());
            MainActivity.sSlug.setElemento(edtElemento.getText().toString().trim());
            MainActivity.sSlug.setNotas(edtNotas.getText().toString().trim());
            MainActivity.sSlug.setOrden(mSlug.getOrden());
            MainActivity.sSlug.setFotoURL(mSlug.getFotoURL());
            setResult(RESULT_OK); // BANDERA PARA DECIR QUE TODO SALIO CORRECTAMENTE*/

                finish();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveSlug1() {
        // AÑADIR TODAS LAS PROPIEDADES AL OBJETO LOCAL mArtista
        /*mSlug.setNombre(edtName.getText().toString().trim());
        mSlug.setBabosaFamosa(edtBabosaFamosa.toString().trim());
        mSlug.setHabitat(edtHabitat.toString().trim());
        mSlug.setTipoPoder(edtTipoPoder.toString().trim());
        mSlug.setVersionMalvada(edtVersionMalvada.toString().trim());
        mSlug.setElemento(edtElemento.toString().trim());
        mSlug.setRareza(edtRareza.toString().trim());
        mSlug.setNotas(edtNotas.toString().trim());*/


        try {
            if (validateFields()) {

                mSlug.setNombre(edtName.getText().toString().trim());
                mSlug.setBabosaFamosa(edtBabosaFamosa.getText().toString().trim());
                mSlug.setHabitat(edtHabitat.getText().toString().trim());
                mSlug.setTipoPoder(edtTipoPoder.getText().toString().trim());
                mSlug.setElemento(edtElemento.getText().toString().trim());
                mSlug.setRareza(edtRareza.getText().toString().trim());
                mSlug.setVersionMalvada(edtVersionMalvada.getText().toString().trim());
                mSlug.setNotas(edtNotas.getText().toString().trim());
                try {
                    mSlug.save();   // devuelve 1 cuando la insercion es correcta y devuelve un -1 cuando hubo error
                    Log.i("DBFlow: ", "Insercion add Correcta de Datos");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("DBFlow: ", "Error add Al insertar  Datos: " + e.getMessage());
                }

           /* MainActivity.sSlug.setNombre(edtName.getText().toString().trim());
            MainActivity.sSlug.setBabosaFamosa(edtBabosaFamosa.getText().toString().trim());
            MainActivity.sSlug.setHabitat(edtHabitat.getText().toString().trim());
            MainActivity.sSlug.setTipoPoder(edtTipoPoder.getText().toString().trim());
            MainActivity.sSlug.setVersionMalvada(edtVersionMalvada.getText().toString().trim());
            MainActivity.sSlug.setRareza(edtRareza.getText().toString().trim());
            MainActivity.sSlug.setElemento(edtElemento.getText().toString().trim());
            MainActivity.sSlug.setNotas(edtNotas.getText().toString().trim());
            MainActivity.sSlug.setOrden(mSlug.getOrden());
            MainActivity.sSlug.setFotoURL(mSlug.getFotoURL());
            setResult(RESULT_OK); // BANDERA PARA DECIR QUE TODO SALIO CORRECTAMENTE*/

                finish();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFields() {

        boolean isValid = true;

        if (edtRareza.getText().toString().trim().isEmpty()) {
            edtRareza.setError(getString(R.string.addArtist_error_required));
            edtRareza.requestFocus();
            isValid = false;
        }

        if (edtElemento.getText().toString().trim().isEmpty()) {
            edtElemento.setError(getString(R.string.addArtist_error_required));
            edtElemento.requestFocus();
            isValid = false;
        }
        if (edtBabosaFamosa.getText().toString().trim().isEmpty()) {
            edtBabosaFamosa.setError(getString(R.string.addArtist_error_required));
            edtBabosaFamosa.requestFocus();
            isValid = false;
        }
        if (edtName.getText().toString().trim().isEmpty()) {
            edtName.setError(getString(R.string.addArtist_error_required));
            edtName.requestFocus();
            isValid = false;
        }

        return isValid;


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RC_PHOTO_PICKER:
                    // extraer ubicacion de archivo
                    configImageView(data.getDataString());
                    break;

            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {


            case KeyEvent.KEYCODE_BACK:

                setResult(RESULT_OK);
                finish();
                return true;
        }

        return false;
    }


    @OnClick({R.id.imgDeleteFoto, R.id.imgFromGalery, R.id.imgFromUrl})
    public void imageEvent(View view) {
        switch (view.getId()) {
            case R.id.imgDeleteFoto:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle(R.string.detalle_dialog_delete_title)
                        //.setMessage(getString(R.string.detalle_dialog_delete_message))
                        .setMessage(String.format(Locale.ROOT,
                                getString(R.string.detalle_dialog_delete_message),
                                mSlug.getBabosaFamosa()))
                        .setPositiveButton(R.string.label_dialog_delete, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                configImageView(null);
                            }
                        })
                        .setNegativeButton(R.string.label_dialog_cancel, null);

                builder.show();
                break;
            case R.id.imgFromGalery:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // permite al usuario seleccionar que clase de datos desea retornar
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.detalle_chosser_detalle)), RC_PHOTO_PICKER);

                break;
            case R.id.imgFromUrl:
                showPhotoDialog();
                break;
        }
    }

    private void showPhotoDialog() {
        final EditText editPhotoURL = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.addArtist_dialogUrl_title)
                .setPositiveButton(R.string.label_dialog_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        configImageView(editPhotoURL.getText().toString().trim());
                    }
                })
                .setNegativeButton(R.string.label_dialog_cancel, null);

        builder.setView(editPhotoURL);
        builder.show();


    }

    private void configImageView(String photooUrl) {

        if (photooUrl != null) {
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop();

            Glide.with(this)
                    .load(photooUrl)
                    .apply(options)
                    .into(imgFoto);

        } else {
            imgFoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_photo_size_select_actual));
        }

        mSlug.setFotoURL(photooUrl);
    }





}
