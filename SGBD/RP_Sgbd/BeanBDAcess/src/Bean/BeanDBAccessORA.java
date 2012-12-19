/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Tables.Movies;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.BLOB;

/**
 * Gére les connexions a une base de données MySQL
 * 
 * @author T4g1
 */
public class BeanDBAccessORA extends BeanDBAccess {
    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String USERNAME = "CC1";
    private final String PASSWORD = "azerty";
    private final String HOST = "127.0.0.1";
    private final String PORT = "1521";
    private final String DBNAME = "ORACLE";
    
    private CallableStatement csmt;
    
    /**
     * Constructeur par défaut
     */
    public BeanDBAccessORA() {
    }
    
    /**
     * Initialise la connection MySQL
     */
    @Override
    public boolean init() {
        String url = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + DBNAME;
        // Initialise le driver
        if(!initDriver(DRIVER)) {
            return false;
        }
        // Connection a la BDD
        if(!connect(url, USERNAME, PASSWORD)) {
            return false;
        }
        return true;
    }

    /**
     * Donne la connection a la BDD
     * 
     * @param url           URL de la BDD
     * @param username      Nom d'utilisateur
     * @param password      Mot de passe
     * 
     * @return              Connection a la BDD
     */
    public boolean connect(String url, String username, String password) {
        try {
            setConnection(DriverManager.getConnection(url, username, password));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Exécute la requête donnée
     * 
     * @param query         Requête que l'on souhaite exectuer
     * @param args          Arguments de la requête
     * 
     * @return              Résultat de la requête
     */
    public ResultSet executeQuery(String query, ArrayList args) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            
            // Applique les arguments à la query
            for(int i=0; i<args.size(); i++) {
                String arg = (String)args.get(i);
                statement.setString(i+1, arg);
            }
            
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public ResultSet executeQuery(String query) {
        ArrayList args = new ArrayList();
        
        return executeQuery(query, args);
    }
    
    public int inserFilm(Movies film, int nb_physique, int nb_digitale) throws Exception{
       
        ArrayDescriptor tabVarchar = ArrayDescriptor.createDescriptor("VARCHAR2_T", getConnection());
        //création du tuple
        System.out.println("création de tuple en cours...");
        //id
        csmt.setInt(1, film.getId());
        //nom
        csmt.setString(2, film.getName());
        //durée
        csmt.setInt(3, film.getRuntime());
        //cote
        csmt.setFloat(4, film.getRating());
        //datesortie
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateSql;
        java.util.Date dateJava = null;
        try {
            dateJava = sdf.parse(film.getReleased());
        } catch (ParseException ex) {
            Logger.getLogger(BeanDBAccessORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        dateSql = new java.sql.Date(dateJava.getTime());            
        csmt.setDate(5, dateSql);
        //tagline
        csmt.setString(6, film.getTagline());
        //votes
        csmt.setInt(7, film.getVotes());
        //poster
        System.out.println("Ajout de l'image");
        BLOB blobs = CatchImage(film.getId());
        if(blobs == null){
            csmt.setNull(8, java.sql.Types.BLOB);
        }else{
            csmt.setBlob(8, blobs);
        }
        System.out.println(".....image ajoutée");
        //producteur
        String[] nomDirecteurs = film.getDirecteurs();
        csmt.setString(9, nomDirecteurs[0]);
        //Acteurs
        String[] nomActeurs = film.getActeurs();
        if(nomActeurs == null){
            csmt.setNull(10, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), nomActeurs);
            csmt.setArray(10, sqlArray);
            sqlArray.free();
        }
        //getTabStudios
        String[] nomStudios = film.getTabStudios();
        if(nomStudios == null){
            csmt.setNull(11, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), nomStudios);
            csmt.setArray(11, sqlArray);
            sqlArray.free();
        }
        //getTabGenres
        String[] nomGenres = film.getTabGenres();
        if(nomGenres == null){
            csmt.setNull(12, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), nomGenres);
            csmt.setArray(12, sqlArray);
            sqlArray.free();
        }
        //certification
        if(film.getCertification() == null){
            csmt.setNull(13, OracleTypes.VARCHAR);
        }else{
            String certification = getCertification(film.getCertification());
            csmt.setString(13, certification);
        }
        //getTabCountries
        String[] nomCountries = film.getTabCountries();
        if(nomCountries == null){
            csmt.setNull(14, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), nomCountries);
            csmt.setArray(14, sqlArray);
            sqlArray.free();
        }
        //getTabLangues
        String[] nomLangues = film.getTabLangues();;
        if(nomLangues == null){
            csmt.setNull(15, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), nomLangues);
            csmt.setArray(15, sqlArray);
            sqlArray.free();
        }
        //nombre physique
        csmt.setInt(16, nb_physique);
        //nombre digitale
        csmt.setInt(17, nb_digitale);
        System.out.println("requète prète: Exécution...");
        System.out.println("Ajout du film "+ film.getId()+": "+ film.getName());
        csmt.execute();
        System.out.println("...requète exécuté");

        return 0;
        
    }
    
    public ResultSet SearchFilms(String minCopie, String minNote){
        
        try {
            CallableStatement call = getConnection().prepareCall ("{ ? = call PACKAGE_GESTCOMMANDE.SearchFilm(?, ?)}");
            call.registerOutParameter(1, OracleTypes.CURSOR);
            call.setInt(2, Integer.parseInt(minCopie));
            call.setInt(3, Integer.parseInt(minNote));
            
            System.out.println("requète prète: Exécution...");
            call.execute ();
            
            ResultSet rset = (ResultSet)call.getObject(1);
            System.out.println("...requète exécuté");
            return rset;
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccessORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    
    public ResultSet SearchSceances(String titre, int sceances, int placemin, int placemax, String datemin) throws SQLException{
        
        CallableStatement call = getConnection().prepareCall ("{ ? = call recherchesceances.Searchbycriterion(?, ?, ?, ?, ?)}");
        //CallableStatement call = getConnection().prepareCall ("{ ? = call recherchesceances.Searchbycriterion()}");
        call.registerOutParameter(1, OracleTypes.CURSOR);
        //call.registerOutParameter(1, OracleTypes.ARRAY, "CC1.RECHERCHESCEANCES.TSCEANCES");
        
        if(titre != null)
            call.setString(2, titre);
        else
            call.setNull(2, OracleTypes.NULL);
        if( sceances != -1)
            call.setInt(3, sceances);
        else
            call.setNull(3, OracleTypes.NULL );

        if(placemin != -1)
            call.setInt(4, placemin);
        else
            call.setNull(4, OracleTypes.NULL );

        if(placemax != -1)
            call.setInt(5, placemax);
        else
            call.setNull(5, OracleTypes.NULL );
        
        if(datemin != null)
            call.setString(6, datemin);
        else
            call.setNull(6, OracleTypes.NULL);

        System.out.println("requète prète: Exécution...");
        call.execute();

        ResultSet rset = (ResultSet)call.getObject(1);
        //Array Aset = call.getArray(1);
        System.out.println("...requète exécuté");
        
        return rset;
    
    }
    
    public ResultSet SearchSignaletique(int idfilm) throws SQLException{
        CallableStatement call = getConnection().prepareCall ("{ ? = call recherchesceances.SearchSignaletique(?)}");
        //CallableStatement call = getConnection().prepareCall ("{ ? = call recherchesceances.Searchbycriterion()}");
        call.registerOutParameter(1, OracleTypes.CURSOR);
        //call.registerOutParameter(1, OracleTypes.ARRAY, "CC1.RECHERCHESCEANCES.TSCEANCES");

        call.setInt(2, idfilm);        

        System.out.println("requète prète: Exécution...");
        call.execute();

        ResultSet rset = (ResultSet)call.getObject(1);
        //Array Aset = call.getArray(1);
        System.out.println("...requète exécuté");

        return rset;
    }
    
    public int insertCommands(HashMap<String, Vector<String>> toCommand) throws Exception{
       
        ArrayDescriptor tabVarchar = ArrayDescriptor.createDescriptor("VARCHAR2_T", getConnection());
        //création du tuple
        System.out.println("création de tuple en cours...");
        //idFilm
        String[] idFilm;
        idFilm = new String[toCommand.get("ident").size()];
        
        for (int i = 0; i < toCommand.get("ident").size(); i++) {
            idFilm[i] = toCommand.get("ident").get(i).replace("'", "''");
        }
        if(idFilm == null){
            csmt.setNull(1, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), idFilm);
            csmt.setArray(1, sqlArray);
            sqlArray.free();
        }
        //Type
        String[] type;
        type = new String[toCommand.get("type").size()];
        
        for (int i = 0; i < toCommand.get("type").size(); i++) {
            type[i] = toCommand.get("type").get(i).replace("'", "''");
        }
        if(type == null){
            csmt.setNull(2, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), type);
            csmt.setArray(2, sqlArray);
            sqlArray.free();
        }
        
        //quantite
        String[] quantite;
        quantite = new String[toCommand.get("quantite").size()];
        
        for (int i = 0; i < toCommand.get("quantite").size(); i++) {
            quantite[i] = toCommand.get("quantite").get(i).replace("'", "''");
        }
        if(quantite == null){
            csmt.setNull(3, OracleTypes.ARRAY, "VARCHAR2_T");
        }else{
            Array sqlArray = new ARRAY(tabVarchar, getConnection(), quantite);
            csmt.setArray(3, sqlArray);
            sqlArray.free();
        }
        
        //ajout du nombre de film à commander
        csmt.setInt(4, toCommand.get("ident").size());
        
        System.out.println("requète prète: Exécution...");
        csmt.execute();
        System.out.println("...requète exécuté");

        return 0;
        
    }
    public int inserFilm(Movies film){
        try {
            ArrayDescriptor tabVarchar = ArrayDescriptor.createDescriptor("VARCHAR2_T", getConnection());
            //création du tuple
            System.out.println("création de tuple en cours...");
            //id
            if(film.getCertification() == null){
                csmt.setNull(1, OracleTypes.VARCHAR);
            }else{
                String certification = getCertification(film.getCertification());
                csmt.setString(1, certification);
            }
            System.out.println("...Certification OK");
            
            System.out.println("requète prète: Exécution...");
            csmt.execute();
            System.out.println("...requète exécuté");
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccessORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public ResultSet getcaddie(int session) throws SQLException{
        
        CallableStatement call = getConnection().prepareCall ("{ ? = call recherchesceances.SearchCaddie(?)}");
        //CallableStatement call = getConnection().prepareCall ("{ ? = call recherchesceances.Searchbycriterion()}");
        call.registerOutParameter(1, OracleTypes.CURSOR);
        //call.registerOutParameter(1, OracleTypes.ARRAY, "CC1.RECHERCHESCEANCES.TSCEANCES");

        call.setInt(2, session);        

        System.out.println("requète prète: Exécution...");
        call.execute();

        ResultSet rset = (ResultSet)call.getObject(1);
        //Array Aset = call.getArray(1);
        System.out.println("...requète exécuté");
        return rset;
    }
    
    public void Paycaddie(int session, int status) throws SQLException{
        
        CallableStatement call = getConnection().prepareCall ("{ call recherchesceances.PayCaddie(?, ?)}");

        call.setInt(1, session);
        call.setInt(2, status);

        System.out.println("requète prète: Exécution...");
        call.execute();
        //Array Aset = call.getArray(1);
        System.out.println("...requète exécuté");
    }
    
    private String getCertification(String certification) {  
        switch(certification){
            case "":
            case " ":
            case "-":
            case "unrated":
            case "Unrated":
            case "12":
            case "N/A":
            case "Not Rated":
            case "Not Yet Rated":
            case "None":
            case "UR":      return "NR";
            case "G":       return "G";
            case "MA":      return "MA";
            case "NC-17":   return "NC-17";
            case "PG":      return "PG";
            case "pg-13":   
            case "PG13":    
            case "PG-13":   return "PG-13";
            case "R":       return "R";
            case "TV-14":
            case "TV14": return "TV-14";
            case "X": return "XXX";
            case "XXX": return "XXX";
        }
        return "NR";
    }
    
    
    public void initCall(String namePackage, String nameProcedure, int nbParam){
        String request = "{ call " + namePackage + "." + nameProcedure + "(";
        for(int i=0; i<nbParam; i++){
            request += (i<(nbParam-1) ? "?," : "?)}");
        }
        try {
            csmt = getConnection().prepareCall(request);
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccessORA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * recup une image sur serveur web
     * @param idFilm
     * @return
     * @throws Exception 
     */
    public BLOB CatchImage(int idFilm) throws Exception{
        BLOB img[];
        String urlImage = "http://localhost:80/Image/" + idFilm + ".jpg";
        URL url;
        
        url = new URL(urlImage);
        try{
            BufferedImage image = ImageIO.read(url);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            img = new BLOB[1];

            img[0] = BLOB.createTemporary(this.getConnection(), false, BLOB.DURATION_SESSION);
            OutputStream outputStream = img[0].setBinaryStream(0L);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageInByte);
            byte[] buffer = new byte[img[0].getBufferSize()];
            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,bytesRead);
            }
            outputStream.close();
            inputStream.close();
        }
        catch(IOException e){
            return null;
        }
        
        return img[0];
    }
    
    
}
