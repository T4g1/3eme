package Vues;

import Bean.BeanDBAccessMySQL;
import Bean.BeanDBAccessORA;
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author T4g1
 */
public class Vues {
    /**
     * Affiche la page de login echoué
     * 
     * @param out   Sortie du code HTML
     */
    public static void showLoginFailed(PrintWriter out) {
        out.println("<p>Login échoué</p>");
    }
    
    /**
     * Affiche une erreur d'ajout d'utilisateur
     * 
     * @param out   Sortie du code HTML
     */
    public static void showAddUserFailed(PrintWriter out) {
        out.println("<p>Ajout d'utilisateur impossible, nom déjà prit</p>");
    }
    
    /**
     * Ajoute un message sur la page
     * 
     * @param out           La page sur laquelle on affiche
     * @param message       Message désiré
     */
    public static void addMessage(PrintWriter out, String message) {
        out.println("<p>Trace Message: "+message+ "</p>");    
    }
    
    /**
     * Place les entête HTML du début de la page
     * 
     * @param out           La page sur laquelle on affiche
     */
    public static void begin(PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");        
    }
    
    /**
     * Place les fermetures des balises HTML pour terminer la
     * génération de la page.
     * 
     * @param out           La page sur laquelle on affiche
     */
    public static void end(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");        
    }
    
    /**
     * Redirige sur la page donnée
     * 
     * @param request       Requete recue
     * @param response      Reponse recue
     * @param page          Page voulue
     */
    public static void redirect(
            HttpServletRequest request, HttpServletResponse response, 
            String page) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page + ".jsp");
        rd.forward(request, response);
    }
    
    /**
     * Donne le titre de la page donnée
     * 
     * @param page          Page dont on veut le titre
     * 
     * @return              Titre de la page
     */
    public static String getPageTitle(String page) {
        return "Mon super site de cinema";
    }
    
    /**
     * Donne la liste des produits disponnible
     * 
     * @return              Code HTML du listing
     */
    public static String getListing(HttpServletRequest request, HttpServletResponse response) {
        String requete = "";
        int i = -1;
        String html = "<table border='1' id=\"tablepaging\" class=\"yui\" align=\"center\">" +
        "    <thead>"+
        "    <tr>\n" +
        "        <th>Date</th>" +
        "        <th>Salle</th>" +
        "        <th>Sceances</th>" +
        "        <th>Places restantes</th>" +
        "        <th>Titre</th>" +
        "        <th>détails</th>" +
        "        <th>nombre de place</th>" +
        "        <th>commander</th>" +
        "    </tr>"+
        "    <thread/>";
        String criteres = request.getParameter("criteres");
        
        if( criteres.equals("sceances") ){
            String titre = null;
            if( !request.getParameter("titre").equals("na") )
                titre = request.getParameter("titre");
            String heure = null;
            int iheure = -1;
            if(!request.getParameter("heure").equals("na")){
                heure = request.getParameter("heure");
                iheure = Integer.parseInt(heure);
            }
            
            String placemin = null;
            int iplacemin = -1;
            if( !request.getParameter("placemin").equals("na")){
                placemin = request.getParameter("placemin");
                iplacemin = Integer.parseInt(placemin);
            }
            
            String placemax = null;
            int iplacemax = -1;
            if( !request.getParameter("placemax").equals("na")){
                placemax = request.getParameter("placemax");
                iplacemax = Integer.parseInt(placemax);
            }
            
            
            // on crée l'objet en passant en paramétre une chaîne representant le format 
            SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy" ); 
            //récupération de la date courante 
            java.util.Date currentTime_1 = new java.util.Date(); 
            //on crée la chaîne à partir de la date  
            String dateinf = formatter.format(currentTime_1);
            if( !request.getParameter("dateinf").equals("na")){
                dateinf = request.getParameter("dateinf");
            }
            
            // on crée l'objet en passant en paramétre une chaîne representant le format 
            SimpleDateFormat formatter2 = new SimpleDateFormat ("dd/MM/yyyy" );
            //récupération de la date courante 
            java.util.Date currentTime_2 = new java.util.Date(); 
            //on crée la chaîne à partir de la date  
            String datesup = formatter2.format(currentTime_2);
            String dt = datesup;  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException ex) {
                Logger.getLogger(Vues.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.add(Calendar.DATE, 7);  
            datesup = sdf.format(c.getTime());
            
            if( !request.getParameter("datesup").equals("na")){
                datesup = request.getParameter("datesup");
            }
            
            try {
                // Ouvre la BDD
                BeanDBAccessORA dba = (BeanDBAccessORA)Beans.instantiate(
                            null, "Bean.BeanDBAccessORA");

                if(dba.init()) {
                    //METTRE BOUCLE
                    do{
                        // Sélectionne les chambres non réservées
                        //ResultSet result = dba.executeQuery(requete);
                        
                        ResultSet result = dba.SearchSceances( titre,  
                                                               iheure,  
                                                               iplacemin,  
                                                               iplacemax,  
                                                               dateinf);
                        //ResultSet result = array.getResultSet();
                        if(result == null)
                            html += "Aucun résultat trouvé<br/>";
                        int couleur = 0;
                        while(result.next())
                        {   
                            if( couleur%2 == 0)
                                html += "<tr class=\"even\">";
                            else
                                html += "<tr class=\"odd\">";
                        
                            html += "<td>" + dateinf + "</td>";
                            html += "<td>" + result.getInt("idsalle") + "</td>";
                            html += "<td>" + getHeureSceances(result.getInt("numsceance")) + "</td>";
                            html += "<td>" + result.getInt("placerestantes") + "</td>";
                            html += "<td>" + result.getString("titre") + "</td>";
                            html += "<td><a href=\"Search?action=details&id="+result.getInt("idfilm")+"\">détails</a></td>";
                            html += "<td><a href=\"Search?action=commander&id="+result.getInt("idfilm")
                                                                               +"&sceance="+result.getInt("numsceance")
                                                                               +"&salle="+result.getInt("idsalle")
                                                                               +"&datejour="+dateinf+"\">Commander" + "</td>";
                            html += "</tr>";
                            
                        }
                        i++;
                        //mise à jour de la date
                        String dt2 = dateinf;  // Start date
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar c2 = Calendar.getInstance();
                        c.setTime(sdf.parse(dt2));
                        c.add(Calendar.DATE, 1);  
                        dateinf = sdf.format(c.getTime());
                        
                    }while(!dateinf.equals(datesup));
                    
                }
                else
                {
                    html = "dba.init() error2<br/>";
                }
            } catch (ParseException ex) {
                html += "ParseException: "+ ex.getMessage();
            } catch (SQLException ex) {
                html += "SQLException: "+ ex.getMessage();
            } catch (IOException ex) {
                html += "IOException: "+ ex.getMessage();
            } catch (ClassNotFoundException ex) {
                html += "ClassNotFoundException: "+ ex.getMessage();
            } catch(NullPointerException ex){
                html += "nullPointerException: "+ ex.getMessage();
            }
            html += "</table>";
            
        } 
        return html;
    }
    
    /**
     * Donne le code HTML du contenu du caddie donné
     * 
     * @param caddie        Caddie voulu
     * 
     * @return              Code HTML du contenu
     */
    public static String getCaddieContent(HttpServletRequest request, HttpServletResponse response) {
        int session = 0;
        session = Integer.parseInt(request.getAttribute("session").toString());
        String html = "<table border=1px>\n" +
        "    <tr>\n" +
        "        <th>salle</th>\n" +
        "        <th>sceance</th>\n" +
        "        <th>film</th>\n" +
        "        <th>date</th>\n" +
        "        <th>nombre de place</th>\n" +
        "    </tr>";
            
        try {
            // Ouvre la BDD
            BeanDBAccessORA dba = (BeanDBAccessORA)Beans.instantiate(
                        null, "Bean.BeanDBAccessORA");
            
            
            
            if(dba.init()) {
                
                
                ResultSet result = dba.getcaddie(session);
                
                // Parcourt le caddie
                if(result != null){
                    while( result.next()){
                        html += "<tr>";
                        html += "<td>" + result.getInt("idsalle") + "</td>";
                        html += "<td>" + getHeureSceances(result.getInt("numsceance")) + "</td>";
                        html += "<td>" + result.getString("titre") + "</td>";
                        html += "<td>" + result.getString("datejour") + "</td>";
                        html += "<td align=\"center\">" + result.getInt("nbplace") + "</td>";
                        html += "</tr>";
                    }
                }
                
                
            }
            else
            {
                html = "dba.init() error";
            }
        } catch (SQLException ex) {
            html = "SQLException: "+ ex.getMessage();
        } catch (IOException ex) {
            html = "IOException: "+ ex.getMessage();
        } catch (ClassNotFoundException ex) {
            html = "ClassNotFoundException: "+ ex.getMessage();
        }
        
        html += "</table>";
        html += "<a href=\"Search?action=payer&id="+session+" \">Payer</a>  ";
        html += "<a href=\"Search?action=annuler&id="+session+"\">Annuler</a>";
        return html;
    }
    
    public static String payement(HttpServletRequest request, HttpServletResponse response){
        String html = "";        
        try {
            // Ouvre la BDD
            BeanDBAccessORA dba = (BeanDBAccessORA)Beans.instantiate(
                        null, "Bean.BeanDBAccessORA");
            
            
            
            if(dba.init()) {
                
                int session = 0;
                session = Integer.parseInt(request.getAttribute("session").toString());
                ResultSet result = dba.getcaddie(session);
                
                if( request.getParameter("action").equals("payer")){
                    dba.Paycaddie(session, 1);
                    html += "paiement réussis !!!";
                }
                else{
                    dba.Paycaddie(session, 0);
                    html += "Annulation acceptée :( ";
                }
                
                
                
            }
            else
            {
                html = "dba.init() error";
            }
        } catch (SQLException ex) {
            html += "SQLException: "+ ex.getMessage();
        } catch (IOException ex) {
            html += "IOException: "+ ex.getMessage();
        } catch (ClassNotFoundException ex) {
            html += "ClassNotFoundException: "+ ex.getMessage();
        }
        
            
        return html;
    }
    
    
    /**
     * permet de retourner l'heure d'une scéance par rapport à son numéro
     * @param numsceance
     * @return 
     */
    public static String getHeureSceances(int numsceance){
        
        switch( numsceance){
            case 1:
                return "14H00";
            case 2:
                return "16H00";
            case 3:
                return "20H00";
            case 4:
                return "22H00";
        }
        return null;
    }
    
    /**
     * retourne toutes les infos sur un film
     * @param request
     * @param response
     * @return 
     */
    public static String getFilmInfo(HttpServletRequest request, HttpServletResponse response) {
        int i = -1;
        String html = "";
        Blob image = null;
        byte[] imgData = null;
        //SearchSignaletique(ident films.idfilm%type)
        try {
                // Ouvre la BDD
                BeanDBAccessORA dba = (BeanDBAccessORA)Beans.instantiate(
                            null, "Bean.BeanDBAccessORA");

                if(dba.init()) { 
                    String param = request.getParameter("id");
                    int idfilm = Integer.parseInt(param);                    
                    
                    ResultSet result = dba.SearchSignaletique( idfilm);
                    //ResultSet result = array.getResultSet();
                    if(result == null)
                        html += "Aucun résultat trouvé<br/>";
                    int count = 0;
                    while(result.next())
                    {      
                        if(count == 0){
                            html += "<p>Titre: "+result.getString("Titre") +"</p>";
                            html += "<p>Durée: "+result.getString("Duree") +"</p>";
                            html += "<p>Cote: "+result.getString("Cote") +"</p>";
                            html += "<p>Date de sortie: "+result.getString("Datesortie") +"</p>";
                            html += "<p>Tagline: "+result.getString("Tagline") +"</p>";
                            html += "<p>Votes: "+result.getString("Votes") +"</p>";
                            //récupération de l'image
                            final String path = "..\\webapps\\Web_Applic_Reservations\\" + idfilm+".jpg";
                            
                            InputStream instream = result.getBlob("POSTER").getBinaryStream();
                            BufferedInputStream bis = new BufferedInputStream(instream);

                            if (!new File(path).exists()) {
                                FileOutputStream fos = new FileOutputStream(path);
                                BufferedOutputStream bos = new BufferedOutputStream(fos);
                                IOUtils.copy(bis, bos);
                                bos.close();
                            }
                            //html += "<p>Affiche: <img src=\"D:\\Serveur\\apache-tomcat-7.0.33-windows-x64\\apache-tomcat-7.0.33\\bin\\75.jpg\" /></p>";
                            html += "<p>current dir:"+ System.getProperty("user.dir") +" </p>";
                            html += "<p>Affiche: <img src=\"./"+idfilm+".jpg\" width=200 height=300/></p>";
                            html += "<p>Producteur: "+result.getString("Producteur") +"</p>";
                            html += "<p>Certification: "+result.getString("Certification") +"</p>";
                            html += "<p>Acteurs: </p>";
                        }
                        else{
                            html += "<dd>"+result.getString("acteur")+"</br>";
                        }
                        count++;
                    }
                }
                else
                {
                    html = "dba.init() error2<br/>";
                }
            } catch(NumberFormatException ex){
                html += "NumberFormatException: "+ ex.getMessage();
            } catch (SQLException ex) {
                html += "SQLException: "+ ex.getMessage();
            } catch (IOException ex) {
                html += "IOException: "+ ex.getMessage();
            } catch (ClassNotFoundException ex) {
                html += "ClassNotFoundException: "+ ex.getMessage();
            } catch(NullPointerException ex){
                html += "nullPointerException: "+ ex.getMessage();
            }
        
        
        
        return html;
    }
}
