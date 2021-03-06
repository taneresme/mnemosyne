package com.boun.swe.mnemosyne.annotation.annotationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "exact",
        "prefix",
        "suffix",
        "refinedBy",
        "format"
})
@Entity(name = "target")
@GenericGenerator(
        name = "sequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "TARGET_SEQUENCE"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Target {

    @Id
    @GeneratedValue(generator = "sequenceGenerator")
    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    @Column(length = 2000)
    private String targetId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("exact")
    private String exact;

    @JsonProperty("prefix")
    private Long prefix;

    @JsonProperty("suffix")
    private Long suffix;

    @OneToOne(cascade = CascadeType.ALL)
    private RefinedBy refinedBy;

    @JsonProperty("format")
    private String format;
}
