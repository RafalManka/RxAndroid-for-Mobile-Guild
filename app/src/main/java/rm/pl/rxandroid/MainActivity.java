package rm.pl.rxandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import rm.pl.rxandroid.model.Message;
import rm.pl.rxandroid.model.Notification;
import rm.pl.rxandroid.model.User;
import rm.pl.rxandroid.utils.ClickUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    private TextView textView;
    @Nullable
    private Button button;
    @Nullable
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        RxView.clicks(button)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        Log.d(TAG, "Filter called");
                        return !ClickUtils.isDoubleClick();
                    }
                })
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showProgress(true);
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        performActionButtonClicked();
                    }
                });


    }

    private void performActionButtonClicked() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(API.requestFacebookTokenSync());
            }
        })
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<String, User>() {
                    @Override
                    public User call(String string) {
                        Log.d(TAG, "User request called");
                        return API.requestUserSync();
                    }
                })
                .concatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(final User user) {
                        Observable<Message[]> messagesCall = Observable.create(new Observable.OnSubscribe<Message[]>() {
                            @Override
                            public void call(Subscriber<? super Message[]> subscriber) {
                                Log.d(TAG, "Messages request called");
                                subscriber.onNext(API.requestMessagesSync(user));
                                subscriber.onCompleted();
                            }
                        });
                        Observable<Notification[]> notificationsCall = Observable.create(new Observable.OnSubscribe<Notification[]>() {
                            @Override
                            public void call(Subscriber<? super Notification[]> subscriber) {
                                Log.d(TAG, "Notifications request called");
                                subscriber.onNext(Notification.requestNotificationsSync());
                                subscriber.onCompleted();
                            }
                        });
                        return Observable.zip(messagesCall, notificationsCall, new Func2<Message[], Notification[], User>() {
                            @Override
                            public User call(Message[] messages, Notification[] notifications) {
                                Log.d(TAG, "Zip messages and notifications called");
                                user.setMessages(messages);
                                user.setNotifications(notifications);
                                return user;
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User aVoid) {
                        showProgress(false);
                    }
                })
                .subscribe(
                        new Action1<User>() {
                            @Override
                            public void call(User o) {
                                if (textView != null) {
                                    textView.setText(o.toString());
                                }
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e(TAG, "Error called", throwable);
                                onError(throwable);
                            }
                        }
                );
    }

    private void showProgress(boolean show) {
        Log.d(TAG, "SHow progress: " + show);
        if (button != null) {
            button.setEnabled(!show);
        }

        if (show && progress == null) {
            progress = new ProgressDialog(MainActivity.this);
            progress.setMessage("Loading...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
        } else if (!show && progress != null) {
            progress.dismiss();
            progress = null;
        }
    }

    private void onError(Throwable throwable) {
        Toast.makeText(MainActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
