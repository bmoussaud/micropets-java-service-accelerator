package com.renamethis;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PetKindBean_TABLE")
public class PetKindBean {

        @Id
        @JsonProperty(value = "Index")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        public PetKindBean() {

        }

        public PetKindBean(Integer index, String name, String type, Integer age, String url, String hostname,
                        String uri) {
                this.index = null;
                this.name = name;
                this.type = type;
                this.age = age;
                this.url = url;
                this.hostname = hostname;
                this.uri = uri;
        }

        @Override
        public String toString() {
                return "PetKindBean [index=" + index + ", name=" + name + ", type=" + type + ", age=" + age + ", url="
                                + url
                                + ", uri=" + uri + "]";
        }

        public Integer getIndex() {
                return index;
        }

}
