<%@page import="java.util.Base64"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.sv.udb.controlador.EquiposCtrl"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.me.jsp.bundle.Idiomas" />

<html>
    <head>
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
     </form>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/materialize/0.97.3/dist/css/materialize.min.css'>
            <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
            <script type="text/javascript" src="webjars/materialize/0.97.3/dist/js/materialize.min.js"></script>
            <title><fmt:message key="label.titulo" /></title>
    </head>
    <body>
    <div class="container">
      <div class="row">
        <div class="col l12 s12 m12">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                     <fmt:message key="label.titulo" />: 
                 <ul>
                     <li><h2><a href="index.jsp"><h4><fmt:message key="label.manto"/></h4></a></h2></li>
                     <li><h2><a href="jugadores.jsp"><h4><fmt:message key="label.manto2"/></h4></a></h2></li>
                </ul>
            </div>
          </div>
        </div>
      </div>
        
        
        
    <div class="row">
        <div class="col l12 s12 m12">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title"><fmt:message key="label.ingreso"/></span>
              <h1>${mensAler}</h1>
              <form method="POST" action="EquiposServ" name="Demo" enctype="multipart/form-data">
                  <!--Importante agregarle el enctype"multipart/form-data"-->
            <input hidden type="text" name="codi2" id="codi2" value="${codi}"/><br/>
                <h5><fmt:message key="label.id"/></h5>
            <input disabled class="white-text" type="text" name="codi" id="codi" value="${codi}"/><br/>
                <h5><fmt:message key="label.nombre"/></h5>
            <input type="text" name="nomb" id="nomb" value="${nomb}"/><br/>
                <h5><fmt:message key="label.desc"/></h5>
            <input type="text" name="desc" id="desc" value="${desc}"/><br/><br/>
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
        </form>
          </div>
            <div class="card-action">
             <input class="btn waves-effect waves-light" type="submit" name="btnEqui" value="<fmt:message key="label.guardar"/>"/>
             <input class="btn waves-effect waves-light" type="submit" name="btnEqui" value="<fmt:message key="label.modificar"/>"/>
            </div>
          </div>
        </div>
      </div>   
    
         <form method="POST" action="EquiposServ" name="Tabla">                                                                                  
       <div class="row">
        <div class="col l12 s12 m12">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title"><center><fmt:message key="label.equi"/></center></span>
              <table border="1">
                <tr>
                    <th>Cons</th>
                    <th><fmt:message key="label.nombre"/></th>
                    <th><fmt:message key="label.desc"/></th>
                     <th><fmt:message key="label.img"/></th>
                </tr>
                <%
                 for(Equipos temp : new EquiposCtrl().consTodo())
                 {
                     byte[] img = temp.getImg();
                     String imgConv = Base64.getEncoder().encodeToString(img);
            %>
                   
                <tr>
                    <td><input id="<%=temp.getCodiEqui()%>" name="codiEquiRadi" type="radio" value="<%=temp.getCodiEqui()%>"/>
                     <label for="<%=temp.getCodiEqui()%>"></label></td>
                    <td><%=temp.getNombreEqui()%></td>
                    <td><%=temp.getDescEqui()%></td>
                    <td><img src="data:image/*;base64,<%=imgConv%>" class="materialboxed" width="100" height="100"></td>
            </tr>
            <%
                }
            %>
               
            </table>
            </div>
            <div class="card-action">
            <input class="btn waves-effect waves-light" type="submit" name="btnEqui" value="<fmt:message key="label.cons"/>"/>
            <input class="btn waves-effect waves-light" type="submit" name="btnEqui" value="<fmt:message key="label.eliminar"/>"/>
            </div>
          </div>
        </div>
      </div>
         </form>
    </div>
    </body>
        <script>
       $(document).ready(function() {
    $('select').material_select();
  }); 
    </script>
  
</html>
