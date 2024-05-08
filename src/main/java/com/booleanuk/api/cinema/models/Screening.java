package com.booleanuk.api.cinema.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer movieId;

    @NotNull(message = "'screenNumber' cannot be null")
    public Integer screenNumber;

    @NotNull(message = "'capacity' cannot be null")
    public Integer capacity;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssXXX")
    private OffsetDateTime startsAt;

    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    protected Screening() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public void update(Screening screening) {
        this.screenNumber = screening.screenNumber;
        this.startsAt = screening.startsAt;
        this.capacity = screening.capacity;
    }
}
