package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.config.*;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class PokemonManagerFactory {
    public static PokemonManager buildManager(){
        String technology = getConfiguration();
        Logger.vlog("Obtaining technology: " + technology);
        switch (technology){
            case "MongoDb":
                return new PokemonManagerOnMongoDb();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                    //log error
                };
                return null;
        }
    }

    public static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.pokemonDbArchitecture;
    }
}