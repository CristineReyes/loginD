package com.example.crist.logind.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.crist.logind.Modelo.Slug;
import com.example.crist.logind.OnItemClickListener;
import com.example.crist.logind.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cristine Aguirre on 25/10/2018.
 * Company: Mobility Apps Inc
 * Insist, Persist, Resist and Never Give Up
 */
public class SlugAdapter extends RecyclerView.Adapter<SlugAdapter.ViewHolder> {


    private List<Slug> slugs;
    private Context context;
    private OnItemClickListener listener;




    public SlugAdapter(List<Slug> slugs, OnItemClickListener listener) {
        this.slugs = slugs;
        this.listener = listener;
    }

   // @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slug, parent,false);

       this.context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // se vincula la vvista de cada elemento y se da el valor de los componentes
        final Slug slug = slugs.get(position);

        holder.setListener(slug,listener);

        // CARGAR LAS IMAGENES EN EL LISTADO
        //VALOR NOMBRE Y ORDEN
        holder.txvNombre.setText(slug.getNombre());
        holder.txvOrden.setText(String.valueOf(slug.getOrden()));

        if(slug.getFotoURL()!=null){
            //7 OBJETO PARA PERSONALIAZAR EL USO DE LAS IMAGENES
            RequestOptions options= new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL); // ALMACENAR EN CACHE LA IMAGEN DE ORIGEN Y DESPUES DE HABER SIDO TRANSFORMADA
            options.centerCrop(); // IMAGEN RECORTADA Y CENTRADA
            options.placeholder(R.drawable.github_face); // VALOR QUE SE ESTRE MOSTRADO MIENTRAS LA IMAGEN SE CARGA


            Glide.with(context).
                    load(slug.getFotoURL())
                    .apply(options)
                    .into(holder.imagePhoto);
        }else{
            holder.imagePhoto.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_adb));
        }


    }

    @Override
    public int getItemCount() {

        return this.slugs.size();
    }


    public void add(Slug slug){
        if (!slugs.contains(slug)) {
            slugs.add(slug);
            notifyDataSetChanged();
        }
    }

    public void setList(List<Slug> list){
        this.slugs = list;
        notifyDataSetChanged();// notificar que los dATOS HAN CAMBIADO
    }



    public void remove(Slug slug) {
        // sis el objeto que le estamos mandando existe en el arreglo
        if (slugs.contains(slug)) {
            slugs.remove(slug);
            notifyDataSetChanged();
        }
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePhoto)
        AppCompatImageView imagePhoto;
        @BindView(R.id.txvNombre)
        AppCompatTextView txvNombre;
        @BindView(R.id.txvOrden)
        AppCompatTextView txvOrden;
        @BindView(R.id.containerMain)
        RelativeLayout containerMain;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void setListener(final Slug slug, final OnItemClickListener listener){
            containerMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemCLick(slug);
                }
            });

            containerMain.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongItemClick(slug);
                    return true;
                }
            });
        }

    }


}
