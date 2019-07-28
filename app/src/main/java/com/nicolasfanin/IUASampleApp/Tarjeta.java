package com.nicolasfanin.IUASampleApp;

import android.os.Parcel;
import android.os.Parcelable;

public class Tarjeta implements Parcelable {

    private Long idPago;
    private String numero;
    private String nombre;

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

    protected Tarjeta(Parcel in) {
        this.idPago = (Long) in.readValue(Long.class.getClassLoader());
        this.numero = in.readString();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<Tarjeta> CREATOR = new Parcelable.Creator<Tarjeta>() {
        @Override
        public Tarjeta createFromParcel(Parcel source) {
            return new Tarjeta(source);
        }

        @Override
        public Tarjeta[] newArray(int size) {
            return new Tarjeta[size];
        }
    };
}
