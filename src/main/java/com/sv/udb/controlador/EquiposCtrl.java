/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Equipos;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JMagoSV
 */
public class EquiposCtrl {
    
    public boolean guar(Equipos obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO equipos VALUES(NULL,?,?,?)");
            cmd.setString(1, obje.getNombreEqui());
            cmd.setString(2, obje.getDescEqui());
            cmd.setBytes(3, obje.getImg());
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
    public boolean elim(Equipos obje)
    {
         boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("Delete from equipos where codi_equi = ?");
            cmd.setString(1, String.valueOf(obje.getCodiEqui()));
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al eliminar el equipo " + ex.getMessage());
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
    public boolean modi(Equipos obje)
    {
         boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("update equipos set nomb_equi = ?, desc_equi = ?, img_equi=? where codi_equi = ?");
            cmd.setString(1, String.valueOf(obje.getNombreEqui())); 
            cmd.setString(2, String.valueOf(obje.getDescEqui()));
             cmd.setBytes(3, obje.getImg());
            cmd.setString(4, String.valueOf(obje.getCodiEqui()));
           
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al modificar el equipo " + ex.getMessage());
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
    
    public List<Equipos> consTodo()
    {
       List<Equipos> resp = new ArrayList();
       Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("SELECT * FROM equipos");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Equipos(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getBytes(4)));
            }
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
    
    public Equipos cons(int id)
    {
       Equipos resp = null;
       Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("SELECT * FROM equipos where codi_equi=?");
            cmd.setString(1, String.valueOf(id)); 
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp = (new Equipos(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
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
    
}
