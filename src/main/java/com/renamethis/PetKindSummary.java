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

    @JsonProperty(value = "Total")
    int total = 0;

    @JsonProperty(value = "Hostname")
    String hostname;

    @JsonProperty(value = "Pets")
    List<PetKind> pets = new ArrayList<>();

    public void addPetKind(PetKind PetKind) {
        pets.add(PetKind);
        total = total + 1;
        this.hostname = getHostname();
        PetKind.hostname = this.hostname;
        PetKind.uri = String.format("/lowercasePetKind/v1/data/%d", PetKind.index);
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
                return PetKind.index > number;
            }
        });
        this.total = pets.size();
        return this;
    }

}
