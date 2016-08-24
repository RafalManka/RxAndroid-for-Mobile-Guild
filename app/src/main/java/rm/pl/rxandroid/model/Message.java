package rm.pl.rxandroid.model;

/**
 * Created by rafal on 8/23/16.
 */
public class Message {
    private String title;
    private String content;

    private Message(Builder builder) {
        title = builder.title;
        content = builder.content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
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

    public static Message[] makeSome() {
        return new Message[]{
                new Message.Builder()
                        .title("Hello!")
                        .content("How are you doing today?")
                        .build(),
                new Message.Builder()
                        .title("Bye!")
                        .content("Have a nice day!")
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

        public Message build() {
            return new Message(this);
        }
    }
}
