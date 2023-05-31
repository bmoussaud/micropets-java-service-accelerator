package com.renamethis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetKindSummary {

    static private String CONTEXT = "lowercasePetKind";

    @JsonProperty(value = "Total")
    int total = 0;

    @JsonProperty(value = "Hostname")
    String hostname;

    @JsonProperty(value = "Pets")
    List<PetKind> pets = new ArrayList<>();

    public void addPetKind(String name, String type, Integer age, String url) {
        this.hostname = getHostname();
        total = total + 1;
        var aipet = new PetKind(null, name, type, age, url, this.hostname,
                String.format("/%s/v1/data/%d", CONTEXT, total));

        pets.add(aipet);
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            return "Unknown";
        }
    }

    @Override
    public String toString() {
        return "PetKindSummary [hostname=" + hostname + ", pets=" + pets + ", total=" + total + "]";
    }

    public PetKindSummary filter() {
        Collections.shuffle(this.pets);
        Random random = new Random();
        int number = random.nextInt(pets.size());
        this.pets.removeIf(new Predicate<PetKind>() {
            @Override
            public boolean test(PetKind PetKind) {
                return PetKind.index() > number + 1;
            }
        });
        this.total = pets.size();
        return this;
    }

    public void clear() {
        this.total = 0;
        pets.clear();
    }

    public void addPet(PetKind petKind) {
        this.hostname = getHostname();
        pets.add(petKind);
        total = total + 1;
    }

}
