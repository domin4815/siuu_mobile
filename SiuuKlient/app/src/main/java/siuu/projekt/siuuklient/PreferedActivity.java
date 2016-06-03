package siuu.projekt.siuuklient;

public class PreferedActivity {
    private String category;
    private String comment;

    public PreferedActivity() {}

    public PreferedActivity(String category, String comment) {
        this.category = category;
        this.comment = comment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}