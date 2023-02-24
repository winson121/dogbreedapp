package com.winson121.demo.mydogbreedapp.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.entity.Subbreed;

public class SheepdogProcessor implements BreedProcessor {

    private Map<String, Breed> breedMap;

    public SheepdogProcessor(Map<String, Breed> breedMap){
        this.breedMap = breedMap;
    }

    public Map<String, Breed> getBreedMap() {
        return breedMap;
    }

    public void setBreedMap(Map<String, Breed> breedMap) {
        this.breedMap = breedMap;
    }

    @Override
    public void process(String breedName) {
        Breed sheepdog = this.breedMap.get(breedName);
        List<Subbreed> subbreeds = sheepdog.getSubbreeds();
        
        for (Subbreed sb: subbreeds) {
        	String newBreedName = breedName+"-"+sb.getSubbreedName();
        	Breed newSheepdog = new Breed(newBreedName, new ArrayList<>());
            this.breedMap.put(newBreedName, newSheepdog);
        }
        
        this.breedMap.remove(breedName);
    }
}
