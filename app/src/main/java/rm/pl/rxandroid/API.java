package rm.pl.rxandroid;

import rm.pl.rxandroid.model.Message;
import rm.pl.rxandroid.model.User;
import rm.pl.rxandroid.utils.RandomUtils;

/**
 * Created by rafal on 8/23/16.
 */
public class API {
    public static User requestUserSync() {
        try {
            Thread.sleep(RandomUtils.makeRandom());
        } catch (InterruptedException ignored) {
        }
        return User.makeOne();
    }

    public static Message[] requestMessagesSync(User user) {
        try {
            Thread.sleep(RandomUtils.makeRandom());
        } catch (InterruptedException ignored) {
        }
        return Message.makeSome();
    }

    public static String requestFacebookTokenSync() {
        try {
            Thread.sleep(RandomUtils.makeRandom());
        } catch (InterruptedException ignored) {
        }
        return "MOCK_FACEBOOK_TOKEN";
    }
}
