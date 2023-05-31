package com.renamethis;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PetKindBean {

        @Id
        @JsonProperty(value = "Index")
        Integer index;

        @JsonProperty(value = "Name")
        String name;

        @JsonProperty(value = "Kind")
        String type;

        @JsonProperty(value = "Age")
        Integer age;

        @JsonProperty(value = "URL")
        String url;

        @JsonProperty(value = "Hostname")
        String hostname;

        @JsonProperty(value = "URI")
        String uri;

        public PetKindBean(Integer index, String name, String type, Integer age, String url, String hostname,
                String uri) {
            this.index = index;
            this.name = name;
            this.type = type;
            this.age = age;
            this.url = url;
            this.hostname = hostname;
            this.uri = uri;
        }

        

}
