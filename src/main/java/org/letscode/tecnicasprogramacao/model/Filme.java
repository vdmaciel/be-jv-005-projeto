package org.letscode.tecnicasprogramacao.model;

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

    public Filme(Long rank, String title, String genre, String description, String director, String actors, Long year,
            Long runtime, Double rating, Long votes, Double revenue, Long metascore) {
        Rank = rank;
        Title = title;
        Genre = genre;
        Description = description;
        Director = director;
        Actors = actors;
        Year = year;
        Runtime = runtime;
        Rating = rating;
        Votes = votes;
        Revenue = revenue;
        Metascore = metascore;
    }

    @Override
    public String toString() {
        return "Filme [Actors=" + Actors + ", Description=" + Description + ", Director=" + Director + ", Genre="
                + Genre + ", Metascore=" + Metascore + ", Rank=" + Rank + ", Rating=" + Rating + ", Revenue=" + Revenue
                + ", Runtime=" + Runtime + ", Title=" + Title + ", Votes=" + Votes + ", Year=" + Year + "]";
    }

    // private Filme(Builder builder) {
    //     this.Rank = builder.Rank;
    //     this.Title = builder.Title;
    //     this.Genre = builder.Genre;
    //     this.Description = builder.Description;
    //     this.Director = builder.Director;
    //     this.Actors = builder.Actors;
    //     this.Year = builder.Year;
    //     this.Runtime = builder.Runtime;
    //     this.Rating = builder.Rating;
    //     this.Votes = builder.Votes;
    //     this.Revenue = builder.Revenue;
    //     this.Metascore = builder.Metascore;
    // }

    // public static class Builder {
    //     private Long Rank;
    //     private String Title;
    //     private String Genre;
    //     private String Description;
    //     private String Director;
    //     private String Actors;
    //     private Long Year;
    //     private Long Runtime;
    //     private Double Rating;
    //     private Long Votes;
    //     private Double Revenue;
    //     private Long Metascore;

    //     public Builder withRank(Long rank) {
    //         Rank = rank;
    //         return this;
    //     }

    //     public Builder withTitle(String title) {
    //         Title = title;
    //         return this;
    //     }

    //     public Builder withGenre(String genre) {
    //         Genre = genre;
    //         return this;
    //     }

    //     public Builder withDescription(String description) {
    //         Description = description;
    //         return this;
    //     }

    //     public Builder withDirector(String director) {
    //         Director = director;
    //         return this;
    //     }

    //     public Builder withActors(String actors) {
    //         Actors = actors;
    //         return this;
    //     }

    //     public Builder withYear(Long year) {
    //         Year = year;
    //         return this;
    //     }

    //     public Builder withRuntime(Long runtime) {
    //         Runtime = runtime;
    //         return this;
    //     }

    //     public Builder withRating(Double rating) {
    //         Rating = rating;
    //         return this;
    //     }

    //     public Builder withVotes(Long votes) {
    //         Votes = votes;
    //         return this;
    //     }

    //     public Builder withRevenue(Double revenue) {
    //         Revenue = revenue;
    //         return this;
    //     }

    //     public Builder withMetascore(Long metascore) {
    //         Metascore = metascore;
    //         return this;
    //     }

    //     public Filme build() {
    //         return new Filme(this);
    //     }
    // }
}
