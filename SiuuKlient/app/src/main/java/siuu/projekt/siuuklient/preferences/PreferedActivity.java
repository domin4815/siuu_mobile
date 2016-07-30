package siuu.projekt.siuuklient.preferences;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreferedActivity that = (PreferedActivity) o;

        return category != null ? category.equals(that.category) : that.category == null;

    }

    @Override
    public int hashCode() {
        return category != null ? category.hashCode() : 0;
    }
}