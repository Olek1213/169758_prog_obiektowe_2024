public class Book {
    private String title;
    private String producer;
    double[] reviews;
    public Book(String title, String producer, double[] reviews) {
        this.title = title;
        this.producer = producer;
        this.reviews = reviews;
    }
    public void addReview(double review) {
        double[] tablica = new double[reviews.length+1];
        for(int i=0;i<reviews.length;i++) {
            tablica[i]=reviews[i];
        }
        tablica[reviews.length]=review;
        this.reviews = tablica;
    }
    public void removeReview() {
        double[] tablica = new double[reviews.length-1];
        for(int i=0;i<tablica.length;i++) {
            tablica[i]=reviews[i];
        }
        this.reviews = tablica;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getProducer() {
        return this.producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }
    public double[] getReviews() {
        return reviews;
    }
    public void setReviews(double[] reviews) {
        this.reviews = reviews;
    }
    @Override
    public String toString() {
        return this.title + " " + this.producer;
    }
    public boolean equals(Book book) {
        if(this.title != book.title) {
            return false;
        }
        if(this.producer != book.producer) {
            return false;
        }
        if(this.reviews != book.reviews) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        return title.hashCode() + producer.hashCode();
    }
}
