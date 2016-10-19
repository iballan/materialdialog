package com.mbh.materialdialog.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.mbh.materialdialog.MD;
import com.mbh.materialdialog.OnMDButtonClicked;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean checkForRating() {
        RateDialog.Config config = new RateDialog.Config();
        config.setInstallDays(2); // after installation with 7 days show it
        config.setLaunchTimes(2); // after launch times show it
        config.setMessage(R.string.rate_message);
        config.setmNoThanks(R.string.rate_no_thanks);
        config.setmOkButton(R.string.rate_ok);
        config.setmRemindMeLater(R.string.rate_remind_me);
        config.setTitle(R.string.rate_title);
        return RateDialog.onStart(config, MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void showDialogSimpleMessage(View view) {
        new MD.Builder(this)
                .title("This is Title")
                .message("This is Message")
                .positiveText("OK")
                .build().show();
    }

    public void showDialogSimpleMessage2(View view) {
        new MD.Builder(this)
                .title("This is Title")
                .message("This is Message")
                .positiveText("OK")
                .positiveListener(new OnMDButtonClicked() {
                    @Override
                    public void onClick(@NonNull MD md) {
                        Toast.makeText(MainActivity.this, "Positive", Toast.LENGTH_SHORT).show();
                    }
                })
                .negativeText("Cancel")
                .autoDismiss(true)
                .cancalable(true)
                .build().show();
    }

    public void showDialogWithIcon(View view) {
        new MD.Builder(this)
                .title("This is Title")
                .message("This is Message With Icon")
                .positiveText("OK")
                .positiveListener(new OnMDButtonClicked() {
                    @Override
                    public void onClick(@NonNull MD md) {
                        Toast.makeText(MainActivity.this, "Positive With Icon", Toast.LENGTH_SHORT).show();
                    }
                })
                .negativeText("Cancel")
                .negativeListener(new OnMDButtonClicked() {
                    @Override
                    public void onClick(@NonNull MD md) {
                        Toast.makeText(MainActivity.this, "Negative With Icon", Toast.LENGTH_SHORT).show();
                    }
                })
                .icon(R.drawable.ic_launcher)
                .autoDismiss(true)
                .cancalable(true)
                .build().show();
    }

    public void showDialogCustomView(View view) {
        new MD.Builder(this)
                .title("This is Title")
                .message("This is Message With Icon")
                .positiveText("I Agree")
                .positiveListener(new OnMDButtonClicked() {
                    @Override
                    public void onClick(@NonNull MD md) {
                        Toast.makeText(MainActivity.this, "Agreed", Toast.LENGTH_SHORT).show();
                    }
                })
                .icon(R.drawable.ic_launcher)
                .customView(R.layout.custom_view1)
                .autoDismiss(true)
                .cancalable(true)
                .build().show();
    }

    public void showDialogCustomView2(View view) {
        new MD.Builder(this)
                .positiveText("I Agree")
                .positiveListener(new OnMDButtonClicked() {
                    @Override
                    public void onClick(@NonNull MD md) {
                        Toast.makeText(MainActivity.this, "Agreed", Toast.LENGTH_SHORT).show();
                    }
                })
                .customView(R.layout.custom_view1)
                .autoDismiss(true)
                .cancalable(true)
                .build().show();
    }

    public void showDialogStyled(View view) {
        new MD.Builder(this)
                .title("This is Title")
                .titleColor(Color.GREEN)
                .message("This is Message With Icon")
                .messageColor(Color.CYAN)
                .positiveText("OK")
                .positiveColor(Color.BLUE)
                .positiveListener(new OnMDButtonClicked() {
                    @Override
                    public void onClick(@NonNull MD md) {
                        Toast.makeText(MainActivity.this, "Positive With Icon", Toast.LENGTH_SHORT).show();
                    }
                })
                .negativeText("Cancel")
                .negativeColor(Color.RED)
                .negativeListener(new OnMDButtonClicked() {
                    @Override
                    public void onClick(@NonNull MD md) {
                        Toast.makeText(MainActivity.this, "Negative With Icon", Toast.LENGTH_SHORT).show();
                    }
                })
                .backgroundColor(Color.DKGRAY)
                .autoDismiss(true)
                .cancalable(true)
                .build().show();
    }
}
