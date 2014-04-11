package data.object;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import data.bdd.Bdd;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.impl.util.StringLogger;

public class Profil {

    public static String table = "Profil";
    private String nom;
    private String email;
    private String mdp;

    public Profil(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Profil() {

    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public void sauvegardeProfil() {
        // TODO Auto-generated method stub
        GraphDatabaseService graphDb = Bdd.getBdd();
        try (Transaction tx = graphDb.beginTx()) {
            Label label = DynamicLabel.label("Profil");
            Node node = graphDb.createNode(label);
            node.setProperty("email", email);
            node.setProperty("mdp", mdp);
            tx.success();
            tx.close();
        } finally {
            graphDb.shutdown();
        }
        //ExecutionEngine requete= new ExecutionEngine(graphDb, StringLogger.SYSTEM);
    }

    public void sauvegardeProfilV2() {
        // TODO Auto-generated method stub
        GraphDatabaseService graphDb = Bdd.getBdd();
        try (Transaction tx = graphDb.beginTx()) {
            ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
            ExecutionResult result;
            result = requete.execute("MERGE (p:Profil { email: '" + email + "', mdp:'" + mdp + "' })RETURN p");
            tx.success();
            tx.close();
        } finally {
            graphDb.shutdown();
        }
        //ExecutionEngine requete= new ExecutionEngine(graphDb, StringLogger.SYSTEM);
    }

    public static boolean connexion(String email, String mdp) {
        GraphDatabaseService graphDb = Bdd.getBdd();
        try (Transaction tx = graphDb.beginTx()) {
            ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
            ExecutionResult result;
            result = requete.execute("match (p:Profil {email : '" + email + "', mdp:'" + mdp + "'}) return p");

            Iterator<Node> ressource = result.columnAs("p");

            for (Node node : IteratorUtil.asIterable(ressource)) {
                tx.success();
                tx.close();
                graphDb.shutdown();
                return true;
            }
            tx.success();
            tx.close();
            graphDb.shutdown();
            return false;
        } finally {
            graphDb.shutdown();
        }
    }

    public void posterVideo() {
        new Video();
    }

    public List<Video> listVideo() {
        return null;
    }

    //Test si le mail est deja dans la base
    public boolean exists(String mail) {
        long total = 0;
        GraphDatabaseService graphDb = Bdd.getBdd();
        try (Transaction tx = graphDb.beginTx()) {
            ExecutionEngine requete = new ExecutionEngine(graphDb, StringLogger.SYSTEM);
            ExecutionResult result;
            result = requete.execute("match (p:Profil {email : '" + mail + "'}) return count(*)");
            for (Map<String, Object> row : result) {
                for (Entry<String, Object> column : row.entrySet()) {
                    total = (long) column.getValue();
                    System.err.println(total);
                }
            }
        } finally {
            graphDb.shutdown();
            if (total == 0) {
                return false;
            }
            return true;
        }
    }

}
