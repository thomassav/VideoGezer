package data.bdd;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringLogger;

System.out.println("test");

public class Bdd {
	private static GraphDatabaseService bdd = null;
        private static ExecutionEngine requete;
        private static ExecutionResult result;
        
	public static GraphDatabaseService getBdd(){
		//if(bdd == null){
                  //  System.out.println("ici");
                    createBdd();
		//}
		return bdd;
	}

	private static void createBdd(){
		bdd =  new GraphDatabaseFactory().newEmbeddedDatabase( "C:\\Users\\Ervin\\Documents\\Neo4j\\videogezerTestV1.graphdb" );
                requete= new ExecutionEngine(bdd, StringLogger.SYSTEM);
		/*bdd = new GraphDatabaseFactory().
        newEmbeddedDatabaseBuilder( "db/test" ).
        setConfig( GraphDatabaseSettings.node_keys_indexable, "name" ).
        setConfig( GraphDatabaseSettings.relationship_keys_indexable, "name" ).
        setConfig( GraphDatabaseSettings.node_auto_indexing, "true" ).
        setConfig( GraphDatabaseSettings.relationship_auto_indexing, "true" ).
        newGraphDatabase();*/
	}

	public static Transaction beginTransaction(){
		return bdd.beginTx();
	}

        public static ExecutionEngine getRequete(){
            return requete;
        }
        
        public static ExecutionResult getReponse(){
            return result;
        }
	private static void registerShutdownHook( final GraphDatabaseService graphDb )
	{
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running application).
		Runtime.getRuntime().addShutdownHook( new Thread()
		{
			@Override
			public void run()
			{
				graphDb.shutdown();
			}
		} );
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ok");
		// TODO Auto-generated method stub
				GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( "db/test" );
				/*  GraphDatabaseService graphDb = new GraphDatabaseFactory().
		                newEmbeddedDatabaseBuilder( "http://localhost:7474/test" ).
		                setConfig( GraphDatabaseSettings.node_keys_indexable, "name" ).
		                setConfig( GraphDatabaseSettings.relationship_keys_indexable, "name" ).
		                setConfig( GraphDatabaseSettings.node_auto_indexing, "true" ).
		                setConfig( GraphDatabaseSettings.relationship_auto_indexing, "true" ).
		                newGraphDatabase();*/
				//GraphDatabaseService graphDb = new RestGraphDatabase("http://localhost:7474/db/data");
				//GraphDatabaseService gds = new RestGraphDatabase("http://localhost:7474/db/data",username,password);
				System.out.println("Yes");
	}
        
}