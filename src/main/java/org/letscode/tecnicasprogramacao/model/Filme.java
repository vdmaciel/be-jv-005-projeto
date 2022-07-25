package org.letscode.tecnicasprogramacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Filme {
    private Long Rank;

    private String Title;

    private String Genre;

    private String Description;

    private String Director;

    private String Actors;

    private Long Year;

    private Long Runtime;

    private Double Rating;

    private Long Votes;

    private Double Revenue;

    private Long Metascore;

    public Long getRank() {
        return Rank;
    }

    public String getTitle() {
        return Title;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDescription() {
        return Description;
    }

    public String getDirector() {
        return Director;
    }

    public String getActors() {
        return Actors;
    }

    public Long getYear() {
        return Year;
    }

    public Long getRuntime() {
        return Runtime;
    }

    public Double getRating() {
        return Rating;
    }

    public Long getVotes() {
        return Votes;
    }

    public Double getRevenue() {
        return Revenue;
    }

    public Long getMetascore() {
        return Metascore;
    }

}
