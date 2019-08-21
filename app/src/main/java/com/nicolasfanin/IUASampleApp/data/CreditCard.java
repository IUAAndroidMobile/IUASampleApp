package com.nicolasfanin.IUASampleApp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditCard implements Parcelable {

    private Long idPago;
    private String numero;
    private String nombre;

    public CreditCard(Long idPago, String numero, String nombre) {
        this.idPago = idPago;
        this.numero = numero;
        this.nombre = nombre;
    }

    //Getters y Setters
    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Parte de Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idPago);
        dest.writeString(this.numero);
        dest.writeString(this.nombre);
    }

    protected CreditCard(Parcel in) {
        this.idPago = (Long) in.readValue(Long.class.getClassLoader());
        this.numero = in.readString();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<CreditCard> CREATOR = new Parcelable.Creator<CreditCard>() {
        @Override
        public CreditCard createFromParcel(Parcel source) {
            return new CreditCard(source);
        }

        @Override
        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };
}
