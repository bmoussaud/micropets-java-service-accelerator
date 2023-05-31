package com.renamethis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PetKindGenerator {

    static Logger logger = LoggerFactory.getLogger(PetKindGenerator.class);

    private PetKindSummary data = new PetKindSummary();

    PetKindSummary generate() {

        logger.debug("--- generate");
        data.clear();

        data.addPetKind("Tweety", "Yellow Canary", 2,
                "https://upload.wikimedia.org/wikipedia/en/0/02/Tweety.svg");
        data.addPetKind("Hector", "African Grey Parrot", 5,
                "https://petkeen.com/wp-content/uploads/2020/11/African-Grey-Parrot.webp");
        data.addPetKind("Micheline", "Budgerigar", 3,
                "https://petkeen.com/wp-content/uploads/2020/11/Budgerigar.webp");
        data.addPetKind("Piplette", "Cockatoo", 1,
                "https://petkeen.com/wp-content/uploads/2020/11/Cockatoo.webp");

        return data;

    }
}
