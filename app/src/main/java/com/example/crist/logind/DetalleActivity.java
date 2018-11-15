package com.example.crist.logind;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.example.crist.logind.Modelo.Slug;
import com.example.crist.logind.Modelo.Slug_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalleActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
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
    @BindView(R.id.ContainerMain)
    NestedScrollView ContainerMain;
   /* @BindView(R.id.imgFoto)
    AppCompatImageView imgFoto;*/

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.imgFoto)
    CarouselView imgFoto;


    private Slug mSlug; // OBJETO GLOBAL DE TIPO SLUG

    private boolean mIsEdit;

    private MenuItem mMenuItem;

    private static final int RC_PHOTO_PICKER = 21;

    int[] sampleImages = {R.drawable.ic_photo_size_select_actual};
     //ArrayList<String> sampleImages= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        try {
            mIsEdit = extras.getBoolean("isEdit");
            enableUIElements(mIsEdit);

            Log.i("EnableElements: ", "No editables");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("EnableElements: ", "Editables " + e.getMessage());
        }

        //imgFoto.setPageCount(sampleImages.length); // imagenes
        //sampleImages.add(R.drawable.ic_photo_size_select_actual);
        configSlug(getIntent());

        configActionBar();
        configImageView(mSlug.getFotoURL(), mSlug.getFotoURL2(), mSlug.getFotoURL3());


    }

    private void configSlug(Intent intent) {
        //--  mSlug = MainActivity.sSlug;
        getSlug(intent.getLongExtra(Slug.ID, 0));


        edtName.setText(mSlug.getNombre());
        edtBabosaFamosa.setText(mSlug.getBabosaFamosa());
        edtHabitat.setText(mSlug.getHabitat()); //
        edtTipoPoder.setText(mSlug.getTipoPoder());
        edtVersionMalvada.setText(mSlug.getVersionMalvada());
        edtElemento.setText(mSlug.getElemento());
        edtRareza.setText(mSlug.getRareza());
        edtNotas.setText(mSlug.getNotas());


    }


    /// extraer el id del intent que recibe como parametro desde MainActivity y lo busca y si no lo encuentra
    // se le asigna valor popr defecto
    private void getSlug(long id) {
        mSlug = SQLite.select()
                .from(Slug.class)
                .where(Slug_Table.id.is(id))
                .querySingle();// devuleve un objeto de tipo slug

    }


    private void configActionBar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        configTitle();

    }

    private void configTitle() {
        toolbarLayout.setTitle(mSlug.getBabosaFamosa());
    }

   /* private void configImageView(String photooUrl) {
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
    }*/


    /*private void configImageView(String photooUrl,String photooUr2,String photooUr3) {

        final ArrayList<String> photooUrl2= new ArrayList<String>();
        photooUrl2.add(photooUrl);
        photooUrl2.add(photooUr2);
        photooUrl2.add(photooUr3);


        if (photooUrl != null) {
                imgFoto.setPageCount(photooUrl2.size());

                // final String photooUrl2=photooUrl;
                final RequestOptions options = new RequestOptions();
                options.diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop();

                ImageListener imageListener1 = new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {

                        Glide.with(DetalleActivity.this)
                                .load(photooUrl2.get(position))
                                .apply(options)
                                .into(imageView);

                    }
                };

                imgFoto.setImageListener(imageListener1);

            } else {
                imgFoto.setPageCount(sampleImages.length);
                imgFoto.setImageListener(imageListener);
            }

            mSlug.setFotoURL(photooUrl);
    }*/


    private void configImageView(String photooUrl,String photooUr2,String photooUr3) {

        final ArrayList<String> photooUrl2= new ArrayList<String>();
        photooUrl2.add(photooUrl);
        photooUrl2.add(photooUr2);
        photooUrl2.add(photooUr3);


        if (photooUrl != null) {
            imgFoto.setPageCount(photooUrl2.size());

            // final String photooUrl2=photooUrl;
            final RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .fitCenter();

            ImageListener imageListener1 = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {

                    Glide.with(DetalleActivity.this)
                            .load(photooUrl2.get(position))
                            .apply(options)
                            .into(imageView);

                }
            };

            imgFoto.setImageListener(imageListener1);

        } else {
            imgFoto.setPageCount(sampleImages.length);
            imgFoto.setImageListener(imageListener);
        }

        mSlug.setFotoURL(photooUrl);
    }



    ImageListener imageListener  = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            int[] sampleImages = {R.drawable.ic_photo_size_select_actual};
            imageView.setImageResource(sampleImages[position]);

        }
    };


    ViewListener viewListener  = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View imageVieww = getLayoutInflater().inflate(R.layout.view_carousel,null);
            return imageVieww;
        }
    };


   /* private void setUpBanner(final String photooUrl1){
        imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                RequestOptions options = new RequestOptions();
                options.diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop();

                Glide.with(DetalleActivity.this)
                        .load(photooUrl1)
                        .apply(options)
                        .into(imageView);

                imgFoto.setVisibility(View.VISIBLE);
                imgFoto.setImageListener(imageListener);
                imgFoto.setPageCount(photooUrl1.length());
            }
        };
    }*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        mMenuItem = menu.findItem(R.id.action_Save);
        mMenuItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_Save:
                saveOrEdit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RC_PHOTO_PICKER:
                    // extraer ubicacion de archivo
                    savePhotoURLSlug(data.getDataString(),data.getDataString(),data.getDataString());
                    break;

            }
        }
    }

    private void savePhotoURLSlug(String photoURL,String photoURL2,String photoURL3) {
        try {
            mSlug.setFotoURL(photoURL);
            mSlug.update();
            configImageView(photoURL,photoURL2,photoURL3);
            showMessage(R.string.detalle_message_update_succes);
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(R.string.detalle_message_update_fail);

        }
    }


    @OnClick(R.id.fab)
    public void saveOrEdit() {

        try {
            if (mIsEdit) {
                // TODO: 19/10/2018  salvar datos

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
                        mSlug.update();// se actualiza la base de datoos
                        configTitle();
                        showMessage(R.string.detalle_message_update_succes);
                        Log.i("DBFlow: ", "Insercion edd Correcta de Datos");

                        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.square_edit));
                        enableUIElements(false);
                        mIsEdit = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        showMessage(R.string.detalle_message_update_fail);

                        Log.i("DBFlow: ", "Error edd Al insertar  Datos: " + e.getMessage());
                    }
                }
            } else {
                mIsEdit = true;
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check_circle_black_24dp));
                enableUIElements(true);
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


    /// HHABLILITAR O DESABILITAR COMPONENTES
    private void enableUIElements(boolean enable) {
        edtName.setEnabled(enable);
        edtBabosaFamosa.setEnabled(enable);
        edtHabitat.setEnabled(enable);
        edtTipoPoder.setEnabled(enable);
        edtElemento.setEnabled(enable);
        edtRareza.setEnabled(enable);
        edtVersionMalvada.setEnabled(enable);
        edtNotas.setEnabled(enable);

        // mMenuItem.setVisible(enable);
        // appBar.setExpanded(!enable);
        // ContainerMain.setNestedScrollingEnabled(!enable);


    }


    private void showMessage(int resource) {
        Snackbar.make(ContainerMain, resource, Snackbar.LENGTH_SHORT).show();

    }

    @OnClick({R.id.imgDeleteFoto, R.id.imgFromGalery, R.id.imgFromUrl})
    public void photoHandler(View view) {
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
                                savePhotoURLSlug(null,null,null);
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
        final EditText editPhotoURL2 = new EditText(this);
        final EditText editPhotoURL3 = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.addArtist_dialogUrl_title)
                .setPositiveButton(R.string.label_dialog_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        savePhotoURLSlug(editPhotoURL.getText().toString().trim(),editPhotoURL2.getText().toString().trim(),editPhotoURL3.getText().toString().trim());
                    }
                })
                .setNegativeButton(R.string.label_dialog_cancel, null);

        builder.setView(editPhotoURL);
        builder.setView(editPhotoURL2);
        builder.setView(editPhotoURL3);
        builder.show();


    }


    //// BITMAP BI MAP BITMAP
    //// BITMAP BI MAP BITMAP



    public  class  ImageTransform  extends BitmapTransformation{

        public ImageTransform(Context context){
            super();
        }


        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return getCircularBitmapImage(toTransform);
        }

        public  Bitmap getCircularBitmapImage(Bitmap source) {
            int size = Math.min(source.getWidth(),source.getHeight());
            int x =(source.getWidth() -size)/2;
            int y =(source.getHeight() - size)/2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size );

            if (squaredBitmap != source) {
                source.recycle();
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }


}
