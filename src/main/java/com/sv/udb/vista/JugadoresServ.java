/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.JugadoresCtrl;
import com.sv.udb.modelo.Jugadores;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author DanielWilfredo
 */
@MultipartConfig //Este CODIGO ES IMPORTANTE AGREGARLO SIN ESTO NO GUARDA LA IMAGEN
@WebServlet(name = "JugadoresServ", urlPatterns = {"/JugadoresServ"})
public class JugadoresServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        if(!esValido){
            response.sendRedirect(request.getContextPath() + "/jugadores.jsp");
        }
        else{
            String CRUD = request.getParameter("btnJuga");
            if(CRUD.equals("Guardar"))
            {
                Jugadores obje = new Jugadores();
                obje.setCodiEqui(Integer.parseInt(request.getParameter("cmbEqui")));
                obje.setNombJuga(request.getParameter("nombJuga"));
                obje.setEdadJuga((request.getParameter("edadJuga")));
                obje.setAltuJuga(Integer.parseInt((request.getParameter("altuJuga"))));
                obje.setPesoJuga((request.getParameter("pesoJuga")));
                //codigo para guardar la imagen
                Part filepart = request.getPart("foto"); //obtiene la foto
                int SizeImg = (int)filepart.getSize();//el tamaño de la foto
                byte[] img = null; //declaramos variable para guardar la foto
                if(filepart !=null)
                {
                    img = new byte[SizeImg];
                    try(DataInputStream dataImg = new DataInputStream(filepart.getInputStream()))
                    {
                        dataImg.readFully(img);
                    }
                }
                if(SizeImg > 0)
                {
                    obje.setImgJuga(img);
                }
                if(new JugadoresCtrl().guar(obje))
                {
                    mens="Datos Guardados";
                }
                else
                {
                    mens="Error al guardar";
                }
            }
            else if(CRUD.equals("Consultar"))
            {
                
                int codi = Integer.parseInt(
                        request.getParameter("codiJugaRadi").isEmpty() 
                        ? "-1" : request.getParameter("codiJugaRadi"));//Creamos una variable que me almacene
                //el codigo seleccionado dependiendo del radio buton seleccionado
               Jugadores obje = new JugadoresCtrl().consTodo2(codi);
               if(obje != null)
                {
                    request.setAttribute("codiJuga", obje.getCodiJuga());
                    request.setAttribute("nombJuga", obje.getNombJuga());
                    request.setAttribute("edadJuga", obje.getEdadJuga());
                    request.setAttribute("altuJuga", obje.getAltuJuga());
                    request.setAttribute("pesoJuga", obje.getPesoJuga());
                    request.setAttribute("cmbEqui", obje.getCodiEqui());
                   
                }
                else
                {
                    mens = "Error al consultar";
                }
                
            }
            else if (CRUD.equals("Eliminar"))
            {
                Jugadores obje = new Jugadores();
                int codi= Integer.parseInt(request.getParameter("codiJugaRadi").isEmpty() ? "-1" : request.getParameter("codiJugaRadi"));
                obje.setCodiJuga(codi);
                if(new JugadoresCtrl().elim(obje))
                {
                    mens="Datos Eliminados";
                }
                else
                {
                    mens="Error al eliminar";
                }
            }
            else if(CRUD.equals("Modificar"))
            {
                Jugadores obje = new Jugadores();
                obje.setCodiJuga(Integer.parseInt(request.getParameter("codiJuga")));
                obje.setNombJuga(request.getParameter("nombJuga"));
                obje.setEdadJuga(request.getParameter("edadJuga"));
                obje.setAltuJuga(Integer.parseInt(request.getParameter("altuJuga")));
                obje.setPesoJuga(request.getParameter("pesoJuga"));
                obje.setCodiEqui(Integer.parseInt(request.getParameter("cmbEqui")));
                 //codigo para guardar la imagen
                Part filepart = request.getPart("foto"); //obtiene la foto
                int SizeImg = (int)filepart.getSize();//el tamaño de la foto
                byte[] img = null; //declaramos variable para guardar la foto
                if(filepart !=null)
                {
                    img = new byte[SizeImg];
                    try(DataInputStream dataImg = new DataInputStream(filepart.getInputStream()))
                    {
                        dataImg.readFully(img);
                    }
                }
                if(SizeImg > 0)
                {
                    obje.setImgJuga(img);
                }
                
                if(new JugadoresCtrl().modi(obje))
                {
                   mens="Datos Actualizados"; 
                }
                else
                {
                   mens="Error al Actualizar"; 
                }
                
            }
             request.setAttribute("mensAler",mens);
            request.getRequestDispatcher("/jugadores.jsp").forward(request, response);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
