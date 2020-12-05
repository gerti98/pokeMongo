package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class UserNetworkManagerOnNeo4j extends Neo4jDbDatabase {

    public boolean deleteUser(User u){
        return deleteUser(u.getUsername());
    }

    // TODO: eliminare poi anche tutte le relazioni di follow
    public boolean deleteUser(String username){
        String query = "MATCH (u:User) WHERE u.username = $username " +
                "OPTIONAL MATCH (u)-[h:HAS]->(:Pokemon) OPTIONAL MATCH (:User)-[fo:FOLLOW]->(u)-[f:FOLLOW]->(:User) " +
                "OPTIONAL MATCH (:User)-[fo:FOLLOW]->(u) DELETE u, h, f, fo";
        return remove(query, parameters("username", username));
    }

    //Eventualmente se il bean non è stato ancora creato si può passare direttamente lo username proposto in fase di
    //registrazione
    public boolean addUser(User u){
        String query = "MERGE (u:User { username: $username})";
        return insert(query, parameters("username", u.getUsername()));
    }

    public boolean addFollow(User from, User to){
        String query = "MATCH (from:User) WHERE from.username = $username" +
                "MATCH (to:User) WHERE to.username = $username2 MERGE (from)-[:FOLLOW]->(to)";
        return insert(query, parameters("username", from.getUsername(), "username2", to.getUsername()));
    }

    public boolean removeFollow(User from, User to){
        String query = "MATCH (from:User)-[w:FOLLOW]->(to:User) WHERE to.username = $username and from.username = $username2 DELETE w";
        return remove(query, parameters("username", from.getUsername(), "username2", to.getUsername()));
    }

    //TODO: non genera ancora i bean, necessita di essere processata su mongoDb
    public List<String> getFollowersUsernames(User target){
        List<String> followersUsernames = new ArrayList<String>();
        String query = "MATCH (to:User)-[h:FOLLOW]->(from:User) WHERE from.username = $username RETURN to.username";
        ArrayList<Object> res = getWithFilter(query, parameters("username", target.getUsername()));
        for(Object o: res){
            Record r =(Record)o;
            String username = r.get("to.username").asString();
            followersUsernames.add(username);
        }
        return followersUsernames;
    }
}
