public class FantasyBook extends Book{
    private String fantasySubgenre;
    public FantasyBook(String title, String producer, double[] reviews, String fantasySubgenre) {
        super(title,producer,reviews);
        this.fantasySubgenre = fantasySubgenre;
    }
    public String getFantasySubgenre() {
        return this.fantasySubgenre;
    }
    public void setFantasySubgenre(String fantasySubgenre) {
        this.fantasySubgenre = fantasySubgenre;
    }
}
