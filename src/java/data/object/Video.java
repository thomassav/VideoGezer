/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.object;

import data.bdd.Bdd;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.impl.util.StringLogger;

/**
 *
 * @author Ervin
 */
public class Video {

    private static int nbVideo = 25;
    private static SimpleDateFormat formatDate = new SimpleDateFormat("dd MM yyyy");

    private long id;
    private String nomVideo;
    private String emplacement;
    private String nomBdd;
    private int nbVues;
    private Date dateUpload;
    private List<String> tableauResolution;
    //private Commentaire commentaire;
    public Video() {

    }

    public Video(String n, String e, String nomBdd) {
        nomVideo = n;
        emplacement = "video/" + nomBdd;
        this.nomBdd = nomBdd;
        System.out.println(n + " " + emplacement + " " + nomBdd);
    }

    public Video(long id, String n, String e, String nomBdd) {
        this.id = id;
        nomVideo = n;
        emplacement = "video/" + nomBdd;
        this.nomBdd = nomBdd;
        tableauResolution = new ArrayList<String>();
        System.out.println(n + " " + emplacement + " " + nomBdd);
    }

    public String getdateUpload() {
        return formatDate.format(dateUpload);
    }

    public String getNom() {
        return nomVideo;
    }

    public int getnbVues(){
        return nbVues;
    }
    public String getNomBdd() {
        return nomBdd;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public static void ajouterCommentaire() {

    }

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    
    public static int getNbVideo() {
        return nbVideo;
    }

    /*
     Ajout d'un video après son upload
     */
    public static void ajouterVideo(Profil p, Video v, String[] mots) {
        GraphDatabaseService graphDb = Bdd.getBdd();
        try (Transaction tx = graphDb.beginTx()) {
            ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
            ExecutionResult result;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("nom", v.nomVideo);
            params.put("emplacement", "C:/Users/Ervin/Videos/VideoGezer/upload/" + v.nomBdd);
            params.put("nomBdd", v.nomBdd);
            result = requete.execute("MERGE (v:Video { nom: '" + v.nomVideo + "', emplacement:'C:/Users/Ervin/Videos/VideoGezer/upload/" + v.nomBdd + "' , nomBdd:'" + v.nomBdd + "', dateUpload:timestamp(), prive:false})RETURN v");
            for (String mot : mots) {
                if (mot.length() > 4) {
                    result = requete.execute("MERGE (k:KEYWORD {mot:'" + mot.toLowerCase() + "'}) return k");
                    result = requete.execute("MATCH (v:Video {nom:'" + v.getNom() + "', emplacement:'C:/Users/Ervin/Videos/VideoGezer/upload/" + v.nomBdd + "' }),(k:KEYWORD {mot:'" + mot + "'}) CREATE (v)-[m:MOTCLE]->(k) return v,k");
                }
            }
            tx.success();
            tx.close();
            nbVideo++;
        } finally {
            graphDb.shutdown();
        }
    }

    /*
     Ajoute un mot clé à la vidéo uploader
     Si le mot clé n'est pas présent en base l'ajouter
     */
    public static void ajouterMotCles(String[] mots, Video v) {
        for (String mot : mots) {
            GraphDatabaseService graphDb = Bdd.getBdd();
            if (mot.length() > 4) {
                try (Transaction tx = graphDb.beginTx()) {
                    ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
                    ExecutionResult result;
                    result = requete.execute("MERGE (k:Keyword {mot:'" + mot.toLowerCase() + "'}) return k");
                    //Enregistrer tous les mot en minuscule
                    result = requete.execute("MATCH (v:Video {nom:'" + v.getNom() + "', emplacement:'C:/Users/Ervin/Videos/VideoGezer/upload/" + v.nomBdd + "' }),(k:Keyword {mot:'" + mot + "'}) CREATE (v)-[m:MOTCLE]->(k) return v,k");
                    tx.success();
                    tx.close();
                    nbVideo++;
                } finally {
                    graphDb.shutdown();
                }
            }

        }

    }

    /*
     Ancien recherche par like
     */
    public static List<Video> rechercheVideo(String nom, String tri, String mode) {
        // (?i) non sensible à la casse
        // .* n'importe après
        GraphDatabaseService graphDb = Bdd.getBdd();
        List<Video> listVideo = new ArrayList<Video>();
        String recherche;

        try (Transaction tx = graphDb.beginTx()) {
            ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
            ExecutionResult result;
            System.out.println("Recherche d'une video");

            if (mode.equals("aleatoire")) {
                System.out.println("match (v:Video) " + conditionWhere(nom) + " return count(distinct v) as total");
                result = requete.execute("match (v:Video) " + conditionWhere(nom) + " return count(distinct v) as total");
            //String test = ressource1.next().;
                //System.out.println(ressource1.next());
                long total = 0;
                for (Map<String, Object> row : result) {
                    for (Entry<String, Object> column : row.entrySet()) {
                        total = (long) column.getValue();
                        //System.out.println(column.getValue());
                    }
                }
                System.out.println("total de video trouvees : " + total);
                int random = (int) (Math.random() * (total - 1));
                System.out.println(random);
                recherche = "MATCH (v:Video) " + conditionWhere(nom) + " RETURN v SKIP " + random + " limit 5";
            } else if(mode.equals("recommandation")){
                recherche = "match (video:Video {dateUpload:1396114369398})-[:MOTCLE]->(stuff)<-[:MOTCLE]-(v:Video) where not (video)-[:MOTCLE]->(video)  return v, count(stuff) order by count(stuff) desc";
            }else {
                recherche = "MATCH (v:Video)-[m:MOTCLE]->(k:KEYWORD)" + conditionWhere(nom) + " RETURN v,type(m),count(m),count(v) " + order(tri);
            }
            //result = requete.execute("match (v:Video) where v.nom=~ '.*(?i)" + nom + ".*' RETURN v");
            //result = requete.execute("MATCH (v:Video)-[m:MOTCLE]->(k:KEYWORD)" + conditionWhere(nom) + " RETURN v,type(m),count(m) order by count(m) desc");
            result = requete.execute(recherche);
            System.out.print(" requete : " + recherche);
            Iterator<Node> ressource = result.columnAs("v");
            for (Node node : IteratorUtil.asIterable(ressource)) {
                System.out.println(node.getProperty("nom"));
                System.out.println(node.getProperty("emplacement"));
                System.out.println(node.getProperty("nomBdd"));
                listVideo.add(new Video(node.getId(), (String) node.getProperty("nom"), (String) node.getProperty("emplacement"), (String) node.getProperty("nomBdd")));
            }
            System.out.println(listVideo.size());
            tx.success();
            tx.close();
            graphDb.shutdown();
            return listVideo;
        } finally {
            graphDb.shutdown();
        }

        /*
         MATCH (v:Video)-[m:MOTCLE]->(k:KEYWORD)
         where k.mot='Football' or k.mot='College'
         RETURN v,k
         */
    }

    /*
     Recuperation d'une video par son id
     */
    public static void rechercherVideoParId(Video v, String id) throws ParseException {
        GraphDatabaseService graphDb = Bdd.getBdd();
        Timestamp stamp;
        try (Transaction tx = graphDb.beginTx()) {
            ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
            ExecutionResult result;
            System.out.println("Recherche d'une video");
            result = requete.execute("START v=node(" + id + ") RETURN v");
            Iterator<Node> ressource = result.columnAs("v");
            for (Node node : IteratorUtil.asIterable(ressource)) {
                v.nomVideo = (String) node.getProperty("nom");
                v.emplacement = (String) node.getProperty("emplacement");
                v.nomBdd = (String) node.getProperty("nomBdd");
                stamp = new Timestamp((Long) node.getProperty("dateUpload"));
                v.dateUpload = new Date(stamp.getTime());
                //v.nbVues = (int)(long) node.getProperty("nbVues");
            }
            tx.success();
            tx.close();
        } finally {
            graphDb.shutdown();
        }
    }

    /*
     Ajout de la condition where 
     */
    protected static String conditionWhere(String recherche) {
        if (recherche.compareTo(" ") != 0) {
            String where = " where ";
            String[] mots = recherche.split(" ");
            String tmp = "";
            for (String mot : mots) {
                if (mot.length() >= 4) {
                    if (tmp.compareTo("") != 0 && tmp.compareTo(mot) != 0) {
                        System.out.println("good");
                        where = where.concat(" or ");
                    }
                    where = where.concat(" k.mot =~ '(?i)" + mot + "'");
                    tmp = mot;
                    System.out.println(tmp);
                }
            }
            return where;
        }
        return "";
    }

    private static String order(String type) {
        if (type.equals("Recent")) {
            return "order by v.dateUpload desc";
        }
        return "order by count(m) desc";
    }

    public static String requete(String condition) {
        return "match (v:Video) " + condition + " return count(distinct v) as total";
    }
    
    public void addVue(){
        nbVues++;
    }
    
    public int nbResolution(){
        //return tableauResolution.size();
        return 1;
    }
    public static void ajouterVue(Video v){
        GraphDatabaseService graphDb = Bdd.getBdd();
        try (Transaction tx = graphDb.beginTx()) {
            ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
            ExecutionResult result;
            System.out.println("Ajout des vues");
            result = requete.execute("MATCH  (v:Video {nom:'"+v.nomVideo+"', nomBdd:'"+v.nomBdd+"'}) set v.nbVues = "+v.nbVues+" RETURN v");
            System.out.println("MATCH  (v:Video {nom:'"+v.nomVideo+"', nomBdd:'"+v.nomBdd+"'}) set v.nbVues = "+v.nbVues+" RETURN v");
            tx.success();
            tx.close();
        } finally {
            graphDb.shutdown();
        }
    }

    /*
     Recherche pour la pagination
     result = requete.execute("MATCH (v:Video)-[m:MOTCLE]->(k:KEYWORD)" + conditionWhere(nom) + " RETURN count(distinct v) as total");
     Iterator<Node> ressource1 = result.columnAs("total");
     int total = Integer.parseInt(ressource1.next().toString());
    
     */
}
