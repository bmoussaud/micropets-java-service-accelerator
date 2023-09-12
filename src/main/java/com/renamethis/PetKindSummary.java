package com.renamethis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class PetKindSummary {

    static private String CONTEXT = "lowercasePetKind";

    @JsonProperty(value = "Total")
    int total = 0;

    @JsonProperty(value = "Hostname")
    String hostname;

    @JsonProperty(value = "Pets")
    List<PetKindBean> pets = new ArrayList<>();

    @Value("${micropets.from}")
    String from;

    private boolean filter = true;

    public void addPetKind(String name, String type, Integer age, String url) {
        hostname = getHostname();
        total = total + 1;
        var pet = new PetKindBean(total, name, type, age, url, this.hostname,
                String.format("/%s/v1/data/%d", CONTEXT, total), from);

        pets.add(pet);
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
        if (filter) {
            final Random random = new Random();
            final int number = random.nextInt(pets.size());
            this.pets.removeIf(new Predicate<PetKindBean>() {
                @Override
                public boolean test(PetKindBean pet) {
                    return pet.getIndex() > number + 1;
                }
            });
            this.total = pets.size();
        }

        return this;
    }

    public void clear() {
        this.total = 0;
        pets.clear();
    }

    public PetKindBean upgrade(PetKindBean petKind) {
        this.hostname = getHostname();
        petKind.setHostname(hostname);
        petKind.setFromValue(from);
        return petKind;
    }

    public void addPet(PetKindBean petKind) {        
        pets.add(upgrade(petKind));
        total = total + 1;
    }

}
