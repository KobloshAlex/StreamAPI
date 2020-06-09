package com.foxminded.stream.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Racer {
    private final String name;
    private final String team;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Racer(Builder builder) {
        this.name = builder.name;
        this.team = builder.team;
        this.start = builder.start;
        this.end = builder.end;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public static class Builder {

        private String name;
        private String team;
        private LocalDateTime start;
        private LocalDateTime end;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTeam(String team) {
            this.team = team;
            return this;
        }

        public Builder withStart(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public Builder withEnd(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public Racer build() {
            return new Racer(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Racer)) return false;
        Racer racer = (Racer) o;
        return Objects.equals(name, racer.name) &&
                Objects.equals(team, racer.team) &&
                Objects.equals(start, racer.start) &&
                Objects.equals(end, racer.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, start, end);
    }
}
