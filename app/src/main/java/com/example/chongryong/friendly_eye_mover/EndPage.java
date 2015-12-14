package com.example.chongryong.friendly_eye_mover;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ChongRyong on 11/13/2015.
 */
public class EndPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_page);

        TextView text = (TextView) findViewById(R.id.endpage_content);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setText(Html.fromHtml(getString(R.string.endpage_content)));

        ImageButton coachmylife_mark = (ImageButton) findViewById(R.id.coachmylife_mark);
        coachmylife_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.coachmylife.de");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageButton wingwave_mark = (ImageButton) findViewById(R.id.wingwave_mark);
        wingwave_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.wingwave.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        TextView continue_txt = (TextView) findViewById(R.id.continue_txt);
        continue_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EndPage.this, MainActivity.class));
                finish();
            }
        });

        ImageButton coachmylife_mark1 = (ImageButton) findViewById(R.id.coachmylife_mark1);
        coachmylife_mark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.coachmylife.de");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
