<%-- 
    Document   : jugadores
    Created on : 03-19-2017, 05:58:26 PM
    Author     : DanielWilfredo
--%>

<%@page import="java.util.Base64"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.sv.udb.controlador.EquiposCtrl"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page import="com.sv.udb.controlador.JugadoresCtrl"%>
<%@page import="com.sv.udb.modelo.Jugadores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.me.jsp.bundle.Idiomas" />
<html>
            <form>
   <div class="container">  
     <div class="row">
        <div class="col s12 m6">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title"><fmt:message key="label.select_language"/>:</span>
  
                 <select id="language" name="language" onchange="submit();">
                    <option value="es_ES" 
                          <c:if test="${language=='es_ES'}">selected</c:if>>
                          <fmt:message key="label.esp" />
                    </option>
                    <option value="en_US" 
                         <c:if test="${language=='en_US'}">selected</c:if>>
                        <fmt:message key="label.ingles" />
                   </option>
                </select>
            </div>
          </div>
        </div>
      </div>
   </div>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/materialize/0.97.3/dist/css/materialize.min.css'>
            <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
            <script type="text/javascript" src="webjars/materialize/0.97.3/dist/js/materialize.min.js"></script>
        <title><fmt:message key="label.manto2"/></title>
    </head>
    <body>
        <div class="container">
        <div class="row">
        <div class="col l12 s12 m12">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title"><fmt:message key="label.titulo"/>:</span>
                 <ul>
                     <li><h2><a href="index.jsp"><h4><fmt:message key="label.manto2"/></h4></a></h2></li>
                   <li><h2><a href="jugadores.jsp"><h4><fmt:message key="label.manto2"/></h4></a></h2></li>
                </ul>
            </div>
          </div>
        </div>
      </div>
            
        <form method="POST" action="JugadoresServ" name="Demo" enctype="multipart/form-data">
                  <!--Importante agregarle el enctype"multipart/form-data"-->
          <div class="row">
        <div class="col l12 m12 s12">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title"><fmt:message key="label.titulo"/></span>
              <h1>${mensAler}</h1>
        <input hidden="" type="text" name="codiJuga" id="codiJuga" value="${codiJuga}">
        <h5><fmt:message key="label.codi"/></h5>
        <input disabled type="text" value="${codiJuga}" class="white-text">
         <h5><fmt:message key="label.nombre2"/></h5>
        <input type="text" name="nombJuga" id="nombJuga" value="${nombJuga}">
         <h5><fmt:message key="label.edad"/></h5>
        <input type="text" name="edadJuga" id="edadJuga" value="${edadJuga}">
         <h5><fmt:message key="label.altura"/></h5>
        <input type="text" name="altuJuga" id="altuJuga" value="${altuJuga}">
        <h5><fmt:message key="label.peso"/></h5>
        <input type="text" name="pesoJuga" id="pesoJuga" value="${pesoJuga}">
        <h5><fmt:message key="label.equipo2"/></h5>
        <select id="cmbEqui" name="cmbEqui" >
      <option value="" disabled selected><fmt:message key="label.equi"/></option>
       <%
                for(Jugadores temp: new JugadoresCtrl().consEqui())
               
                { 
                    int id=-1;
                   if (request.getAttribute("cmbEqui") != null)
                   {                       
                        id =(Integer)request.getAttribute("cmbEqui");
                   }                      
                   
                    if(temp.getCodiEqui() == id)
                    {
                 %>
                        <option value="<%=temp.getCodiEqui()%>" SELECTED><%=temp.getNombEqui()%></option>;
                
               
                <%}
                    else
                    {
                 %>
                        <option value="<%=temp.getCodiEqui()%>" ><%=temp.getNombEqui()%></option>;
                
               
                <%}
                }
                %>
     
    </select>
                <div class="file-field input-field">
                    <div class="btn">
                        <span><fmt:message key="label.img"/></span>
                        <input type="file" name="foto" id="foto" required />
                    </div>
                        <div class="file-path-wrapper">
                          <input class="file-path validate" name="foto" type="text" placeholder='1200x1200 máx., 2MB máx., PNG/JPG/GIF'>
                        </div>
                
                </div>
                <div><img src='data:image/*;base64,$row[foto]' class='materialboxed' width='100px' height='100px'/><div>
            </div>
            <div class="card-action">
             <input class="btn waves-effect waves-light" type="submit" name="btnJuga" value="<fmt:message key="label.guardar"/>"/>
             <input class="btn waves-effect waves-light" type="submit" name="btnJuga" value="<fmt:message key="label.modificar"/>"/>
             
            </div>
          </div>
        </div>
   </div>       
         </form>
       
        <form method="POST" action="JugadoresServ" name="Demo">
                     
         <!--Empieza la tabla de jugadores-->
        
          <div class="row">
        <div class="col l12 m12 s12 ">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title">Jugadores</span>
                <table border="1">
                <tr>
                    <th>Cons</th>
                    <th><fmt:message key="label.nombre2"/></th>
                    <th><fmt:message key="label.edad"/></th>
                    <th><fmt:message key="label.altura"/></th>
                    <th><fmt:message key="label.peso"/></th>
                    <th><fmt:message key="label.equipo2"/></th>
                    <th><fmt:message key="label.img"/></th>
                </tr>
                 <%
                 for(Jugadores temp : new JugadoresCtrl().consTodo())
                     //Foreach para llenar los datos de la tabla jugadores
                 {   byte[] img = temp.getImgJuga();
                     String imgConv = Base64.getEncoder().encodeToString(img);
                %>   
               
                <tr>
                    <td><input id="<%=temp.getCodiJuga()%>" type="radio" name="codiJugaRadi" value="<%=temp.getCodiJuga()%>">
                        <label for="<%=temp.getCodiJuga()%>"></label></td>
                    <td><%=temp.getNombJuga()%></td>
                    <td><%=temp.getEdadJuga()%></td>
                    <td><%=temp.getAltuJuga()%></td>
                    <td><%=temp.getPesoJuga()%></td>
                    <td><%=temp.getNombEqui()%></td>
                    <td><img src="data:image/*;base64,<%=imgConv%>" class="materialboxed" width="100" height="100"></td>
                    
                </tr>
                <%
                 }
                %>
                    </table>
            </div>
            <div class="card-action">
            <input class="btn waves-effect waves-light" type="submit" name="btnJuga" value="<fmt:message key="label.cons"/>"/>
            <input class="btn waves-effect waves-light" type="submit" name="btnJuga" value="<fmt:message key="label.eliminar"/>"/>
            </div>
          </div>
        </div>
   </div>

        </form>

    </body>
    <script>
       $(document).ready(function() {
    $('select').material_select();
  }); 
    </script>
      
    </div>
</html>
