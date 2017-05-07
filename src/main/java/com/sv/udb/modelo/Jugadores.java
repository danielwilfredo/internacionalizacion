/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

/**
 *
 * @author DanielWilfredo
 */

public class Jugadores {
    private int codiJuga;
    private String nombJuga;
    private String nombEqui;
    private int codiEqui;
    private String edadJuga;
    private int altuJuga;
    private String pesoJuga;
    private byte[] imgJuga;

    public String getPesoJuga() {
        return pesoJuga;
    }

    public void setPesoJuga(String pesoJuga) {
        this.pesoJuga = pesoJuga;
    }
    

    public int getCodiJuga() {
        return codiJuga;
    }

    public void setCodiJuga(int codiJuga) {
        this.codiJuga = codiJuga;
    }

    public String getNombJuga() {
        return nombJuga;
    }

    public void setNombJuga(String nombJuga) {
        this.nombJuga = nombJuga;
    }

    public String getNombEqui() {
        return nombEqui;
    }

    public void setNombEqui(String nombEqui) {
        this.nombEqui = nombEqui;
    }

    public int getCodiEqui() {
        return codiEqui;
    }

    public void setCodiEqui(int codiEqui) {
        this.codiEqui = codiEqui;
    }

    public String getEdadJuga() {
        return edadJuga;
    }

    public void setEdadJuga(String edadJuga) {
        this.edadJuga = edadJuga;
    }

    public int getAltuJuga() {
        return altuJuga;
    }

    public void setAltuJuga(int altuJuga) {
        this.altuJuga = altuJuga;
    }

      public byte[] getImgJuga() {
        return imgJuga;
    }

    public void setImgJuga(byte[] imgJuga) {
        this.imgJuga = imgJuga;
    }
     @Override
    public String toString() {
        return this.nombEqui;
    }
    
    public Jugadores(String nombEqui, int codiEqui) {
        this.nombEqui = nombEqui;
        this.codiEqui = codiEqui;
    }
    
     public Jugadores(int codiJuga, int codiEqui, String nombEqui, String nombJuga, String edadJuga, int altuJuga, String pesoJuga, byte[] imgJuga) {
        this.codiJuga = codiJuga;
        this.nombJuga = nombJuga;
        this.nombEqui = nombEqui;
        this.codiEqui = codiEqui;
        this.edadJuga = edadJuga;
        this.altuJuga = altuJuga;
        this.pesoJuga = pesoJuga;
         this.imgJuga = imgJuga;
    }
     
      public Jugadores(int codiJuga, int codiEqui, String nombEqui, String nombJuga, String edadJuga, int altuJuga, String pesoJuga) {
        this.codiJuga = codiJuga;
        this.nombJuga = nombJuga;
        this.nombEqui = nombEqui;
        this.codiEqui = codiEqui;
        this.edadJuga = edadJuga;
        this.altuJuga = altuJuga;
        this.pesoJuga = pesoJuga;
     
    }
     public Jugadores()
     {}
    
    
}


