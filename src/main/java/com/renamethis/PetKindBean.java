package com.renamethis;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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

        @Transient
        @JsonProperty(value = "Hostname")
        String hostname;

        @JsonProperty(value = "URI")
        String uri;

        @Transient
        @JsonProperty(value = "From")
        String fromValue;

        public PetKindBean() {

        }

        public PetKindBean(Integer index, String name, String type, Integer age, String url, String hostname,
                        String uri, String from) {
                this.index = null;
                this.name = name;
                this.type = type;
                this.age = age;
                this.url = url;
                this.hostname = hostname;
                this.uri = uri;
                this.fromValue = from;

        }

        @Override
        public String toString() {
                return "PetKindBean [index=" + index + ", name=" + name + ", type=" + type + ", age=" + age + ", url="
                                + url + ", hostname=" + hostname + ", uri=" + uri + ", from=" + fromValue + "]";
        }

        public Integer getIndex() {
                return index;
        }

        public void setHostname(String hostname) {
                this.hostname = hostname;
        }

        public void setFromValue(String fromValue) {
                this.fromValue = fromValue;
        }

        

}
