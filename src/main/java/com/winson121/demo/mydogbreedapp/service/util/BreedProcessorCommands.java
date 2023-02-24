package com.winson121.demo.mydogbreedapp.service.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winson121.demo.mydogbreedapp.entity.Breed;

@Component
public class BreedProcessorCommands {

    private Map<String, BreedProcessor> processorMap;

    public BreedProcessorCommands(Map<String, Breed> breedMap) {
        this.processorMap = new HashMap<>();
        this.processorMap.put("sheepdog", new SheepdogProcessor(breedMap));
    }

    public Map<String, BreedProcessor> getProcessorMap() {
        return processorMap;
    }

    public void getBreedProcessor(String breedName) {
        this.processorMap.get(breedName).process(breedName);
    }
}
