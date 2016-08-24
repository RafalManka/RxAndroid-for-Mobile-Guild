package rm.pl.rxandroid.model;

import rm.pl.rxandroid.utils.RandomUtils;

/**
 * Created by rafal on 8/23/16.
 */
public class Notification {
    private String title;
    private String content;

    private Notification(Builder builder) {
        title = builder.title;
        content = builder.content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static Notification[] makeSome() {
        return new Notification[]{
                new Notification.Builder()
                        .title("Someone liked your product!")
                        .content("User Pokemon likes your product")
                        .build(),
                new Notification.Builder()
                        .title("Someone Poked you!")
                        .content("User Ash pokes you.")
                        .build()
        };
    }

    public static final class Builder {
        private String title;
        private String content;

        public Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }
}
