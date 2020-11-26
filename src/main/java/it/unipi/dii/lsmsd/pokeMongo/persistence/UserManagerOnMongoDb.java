package it.unipi.dii.lsmsd.pokeMongo.persistence;


import com.google.gson.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.*;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.security.PasswordEncryptor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class UserManagerOnMongoDb extends MongoDbDatabase implements UserManager{
    private final String collectionName = "user";

    private Document UserToDocument(User u){
        return Document.parse(new Gson().toJson(u));
    }

    private User DocumentToUser(Document doc){
        return new Gson().fromJson(doc.toJson(), User.class);
    }

    @Override
    public boolean insert(ArrayList<Object> toInsert) {
        if(toInsert.size()==0)
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection

        //TODO: Added Password Encryption, watch effects
        for(Object o: toInsert){
            User userToInsert = (User)o;
            userToInsert.setPassword(PasswordEncryptor.encryptPassword(userToInsert.getPassword()));
        }

        if(toInsert.size()==1){
            Document doc = UserToDocument((User)toInsert.get(0));
            collection.insertOne(doc);
        }
        else{
            List<Document> l = new ArrayList<>();
            for(Object o:toInsert){
                Document doc = UserToDocument((User)o);
                l.add(doc);
            }
            collection.insertMany(l);
        }
        closeConnection();
        return true;
    }

    @Override
    public boolean insert(Object toInsert) {
        if(toInsert==null)
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection

        //TODO: Added Password Encryption, watch effects
        User userToInsert = (User)toInsert;
        userToInsert.setPassword(PasswordEncryptor.encryptPassword(userToInsert.getPassword()));
        Document doc = UserToDocument(userToInsert);
        collection.insertOne(doc);
        closeConnection();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        MongoCollection<Document> collection = getCollection(collectionName);
        DeleteResult dr;
        if (o instanceof User){
            dr = collection.deleteOne(eq("username", ((User) o).getUsername()));
        }
        else if(o instanceof Bson){
            dr = collection.deleteMany((Bson)o);
        }
        else {
            closeConnection();
            return false;
        }
        closeConnection();
        return dr.getDeletedCount()>0;
    }

    @Override
    public ArrayList<Object> getAll() {
        List<Document> docs= getCollection(collectionName).find().into(new ArrayList<>());
        ArrayList<Object> users = new ArrayList<>();
        for(Document d:docs){
            users.add(DocumentToUser(d));
        }
        closeConnection();
        return users;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        List<Document> docs= getCollection(collectionName).find((Bson)filter).into(new ArrayList<>());
        ArrayList<Object> users = new ArrayList<>();
        for(Document d:docs){
            users.add(DocumentToUser(d));
        }
        closeConnection();
        return users;
    }

    @Override
    public boolean update(Object target, Object newValue) {
        MongoCollection<Document> collection = getCollection(collectionName);
        UpdateResult ur;
        if (target instanceof User){
            ur = collection.updateOne(eq("username", ((User) target).getUsername()),(Bson)newValue);
        }
        else if(target instanceof Bson){
            ur = collection.updateMany((Bson)target, (Bson)newValue);
        }
        else {
            closeConnection();
            return false;
        }
        closeConnection();
        return ur.getModifiedCount()>0;
    }

    @Override
    public User login(String username, String password) {
        //TODO: Added Password Encryption, watch effects
        Bson query = and(eq("username", username), eq("password", PasswordEncryptor.encryptPassword(password)));
        ArrayList<Object> matched = getWithFilter(query);
        if(matched.size()!=1)
            return null;
        Date now = new Date();
        update(query, set("lastLogin", now));
        return (User)matched.get(0);
    }

    @Override
    public User login(User toLog) {
        return login(toLog.getUsername(), toLog.getPassword());
    }

    @Override
    public boolean register(User toRegister) {
        Bson query = eq("username", toRegister.getUsername());
        ArrayList<Object> alreadyPresent=getWithFilter(query);
        if(alreadyPresent.size()>0)
            return false;
        return insert(toRegister);
    }

    @Override
    public boolean changeEmail(User target, String newEmail) {
        return update(target, set("email", newEmail));
    }

    @Override
    public boolean changePassword(User target, String newPassword) {
        //TODO: Added Password Encryption, watch effects
        return update(target, set("password", PasswordEncryptor.encryptPassword(newPassword)));
    }

    @Override
    public boolean changeCountry(User target, String newCountry) {
        return update(target, set("country", newCountry));
    }

    @Override
    public boolean removeUser(User target) {
        return remove(target);
    }

    @Override
    public boolean removeUser(String username) {
        Bson query = eq("username", username);
        return remove(query);
    }

    @Override
    public boolean verifyOldPassword(User involved, String password) {
        Bson query = eq("username", involved.getUsername());
        ArrayList<Object> matches=getWithFilter(query);
        if(matches.size()!=1)
            return false;
        return ((User)matches.get(0)).getPassword().equals(PasswordEncryptor.encryptPassword(password));
    }
}