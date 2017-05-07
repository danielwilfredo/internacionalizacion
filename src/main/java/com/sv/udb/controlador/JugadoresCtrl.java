/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Equipos;
import com.sv.udb.modelo.Jugadores;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DanielWilfredo
 */
public class JugadoresCtrl {
 public List<Jugadores> consTodo()
    {
       List<Jugadores> resp = new ArrayList();
       Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("select codi_juga, jugadores.codi_equi, nomb_equi, nomb_juga, edad_juga, altu_juga, peso_juga , img_juga from jugadores, equipos where jugadores.codi_equi = equipos.codi_equi;");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Jugadores(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7), rs.getBytes(8)));
            }
            //Se carga el 
        }
        catch(Exception err)
        {
            err.printStackTrace();
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    } 
 
 
 public Jugadores consTodo2(int id)
    {
      Jugadores resp = null;
       Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("select codi_juga,jugadores.codi_equi, nomb_equi, nomb_juga, edad_juga, altu_juga, peso_juga\n" +
            "from jugadores, equipos where jugadores.codi_equi = equipos.codi_equi and codi_juga = ? order by codi_juga");
            cmd.setString(1, String.valueOf(id));
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp = (new Jugadores(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7)));
            }
            //Se carga el 
        }
        catch(Exception err)
        {
            err.printStackTrace();
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    } 
 
    
    //Buscar equipos
    public List<Jugadores> consEqui()
    {
       List<Jugadores> resp = new ArrayList();
       Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("select nomb_equi,codi_equi from equipos");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Jugadores(rs.getString(1),rs.getInt(2)));
            }
            //Se carga el 
        }
        catch(Exception err)
        {
            err.printStackTrace();
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    } 
    
   // Para guardar
    
    public boolean guar(Jugadores obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO jugadores VALUES(NULL,?,?,?,?,?,?)");
            cmd.setInt(1, obje.getCodiEqui());
            cmd.setString(2, obje.getNombJuga());
            cmd.setString(3, obje.getEdadJuga());
            cmd.setInt(4, obje.getAltuJuga());
            cmd.setString(5, obje.getPesoJuga());
            cmd.setBytes(6, obje.getImgJuga());
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al guardar Equipos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
    
    //Eliminar juga
    public boolean elim(Jugadores obje)
    {
         boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("Delete from jugadores where codi_juga = ?");
            cmd.setString(1, String.valueOf(obje.getCodiJuga()));
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al eliminar el jugador " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
    
    //Modificar un registro
    public boolean modi(Jugadores obje)
    {
         boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("update jugadores set codi_equi = ?, nomb_juga = ?, edad_juga = ?, "
            + "altu_juga = ?, peso_juga = ?, img_juga = ? where codi_juga = ?");
            cmd.setString(1, String.valueOf(obje.getCodiEqui())); 
            cmd.setString(2, String.valueOf(obje.getNombJuga()));
            cmd.setString(3, String.valueOf(obje.getEdadJuga()));
            cmd.setString(4, String.valueOf(obje.getAltuJuga()));
            cmd.setString(5, String.valueOf(obje.getPesoJuga()));
            cmd.setBytes(6, obje.getImgJuga());
            cmd.setString(7, String.valueOf(obje.getCodiJuga()));
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al modificar el Jugador " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
    
    
}
