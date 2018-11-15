package com.example.crist.logind.Modelo;

import com.example.crist.logind.DB.SlugDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Cristine Aguirre on 25/10/2018.
 * Company: Mobility Apps Inc
 * Insist, Persist, Resist and Never Give Up
 */

    // 1. CREAR PRIMERO LA DB(NOMBRE Y VERSION)
    // 2. CREAR LA APPLICATION, INICIARLA Y DECLARARLA EN MANIFEST
    // 3. CREAR LA TABLA
@Table(database = SlugDB.class)  // SE INIDICA EN QUE BASE DE DATOS ESTARA LA TABLA

public class Slug extends BaseModel{
    // BASEMODEL = Permite Insertar, Modificar, Eliminar Datos de forma fácil

    public static final String ORDEN ="orden" ;
    public static final String ID = "id" ;

    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    private String nombre;
    @Column
    private String babosaFamosa;
    @Column
    private String habitat;  // a vces
    @Column
    private String tipoPoder; //tipoPoder
    @Column
    private String versionMalvada;
    @Column
    private String elemento;
    @Column
    private String rareza;
    @Column
    private String notas;
    @Column
    private int orden;
    @Column
    private String fotoURL;

    @Column
    private String fotoURL2;
    @Column
    private String fotoURL3;





    public Slug() {
    }


    /// CONSTRUCTOS SIN ID YA QUE ES AUTOINCREMENTABLE, PARA USARLO EN DB
    public Slug(String nombre, String babosaFamosa, String habitat, String tipoPoder,
                String versionMalvada, String elemento, String rareza, String notas, int orden,
                String fotoURL) {
        this.nombre = nombre;
        this.babosaFamosa = babosaFamosa;
        this.habitat = habitat;
        this.tipoPoder = tipoPoder;
        this.versionMalvada = versionMalvada;
        this.elemento = elemento;
        this.rareza = rareza;
        this.notas = notas;
        this.orden = orden;
        this.fotoURL = fotoURL;
    }



    public Slug(long id, String nombre, String babosaFamosa, String habitat, String tipoPoder,
                String versionMalvada, String elemento, String rareza, String notas, int orden,
                String fotoURL, String fotoURL2, String fotoURL3) {
        this.id = id;
        this.nombre = nombre;
        this.babosaFamosa = babosaFamosa;
        this.habitat = habitat;
        this.tipoPoder = tipoPoder;
        this.versionMalvada = versionMalvada;
        this.elemento = elemento;
        this.rareza = rareza;
        this.notas = notas;
        this.orden = orden;
        this.fotoURL = fotoURL;

        this.fotoURL2 = fotoURL2;
        this.fotoURL3 = fotoURL3;


    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBabosaFamosa() {
        return babosaFamosa;
    }

    public void setBabosaFamosa(String babosaFamosa) {
        this.babosaFamosa = babosaFamosa;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getTipoPoder() {
        return tipoPoder;
    }

    public void setTipoPoder(String tipoPoder) {
        this.tipoPoder = tipoPoder;
    }

    public String getVersionMalvada() {
        return versionMalvada;
    }

    public void setVersionMalvada(String versionMalvada) {
        this.versionMalvada = versionMalvada;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }



    public String getFotoURL2() {
        return fotoURL2;
    }

    public void setFotoURL2(String fotoURL2) {
        this.fotoURL2 = fotoURL2;
    }

    public String getFotoURL3() {
        return fotoURL3;
    }

    public void setFotoURL3(String fotoURL3) {
        this.fotoURL3 = fotoURL3;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slug slug = (Slug) o;

        return id == slug.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }





}
