package com.example.chongryong.friendly_eye_mover;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by duanyl on 2015/9/21.
 */


public class APPIntroFragment extends Fragment {
    private ViewGroup rootView;
    private ImageView imageView;
    private TextView txt_head;
    private TextView txt_content;


    final static String LAYOUT_ID = "layoutid";
    final static String BACKGROUND = "background";
    final static String IMG_ID = "imgid";
    final static String TXT_HEAD_ID = "txt_headid";
    final static String TXT_CONTENT_ID = "txt_contentid";

    public static APPIntroFragment newInstance(int color, int imgid, String head, String content) {
        APPIntroFragment pane = new APPIntroFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_ID, 0);
        args.putInt(BACKGROUND, color);
        args.putInt(IMG_ID, imgid);
        args.putString(TXT_HEAD_ID, head);
        args.putString(TXT_CONTENT_ID, content);
        pane.setArguments(args);
        return pane;
    }

    public static APPIntroFragment newInstance(int id) {
        APPIntroFragment pane = new APPIntroFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_ID, id);
        pane.setArguments(args);
        return pane;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments().getInt(LAYOUT_ID) == 0) {
            rootView = (ViewGroup) inflater.inflate(R.layout.welcome_fragment, container, false);
            txt_head = (TextView) rootView.findViewById(R.id.heading_00);
            txt_content = (TextView) rootView.findViewById(R.id.content_welcome_00);
            txt_content.setMovementMethod(LinkMovementMethod.getInstance());
            if (MainActivity.language_index == 1) {
                txt_content.setText(Html.fromHtml(getString(R.string.welcom_content_00)));
                txt_head.setText("Description");
            }
            if (MainActivity.language_index == 2) {
                txt_content.setText(Html.fromHtml(getString(R.string.welcom_content_10)));
                txt_head.setText("Beschreibung");
            }
//            filiter(getArguments().getInt(BACKGROUND), getArguments().getInt(IMG_ID), getArguments().getString(TXT_HEAD_ID), getArguments().getString(TXT_CONTENT_ID));

        } else {
            rootView = (ViewGroup) inflater.inflate(getArguments().getInt(LAYOUT_ID), container, false);
            if (getArguments().getInt(LAYOUT_ID) == R.layout.welcome_fragment01) {
                txt_head = (TextView) rootView.findViewById(R.id.heading_01);
                TextView text = (TextView) rootView.findViewById(R.id.content_welcome_01);
                text.setMovementMethod(LinkMovementMethod.getInstance());
                if (MainActivity.language_index == 1) {
                    txt_head.setText("How to use it");
                    text.setText(Html.fromHtml(getString(R.string.welcom_content_01)));
                }
                if (MainActivity.language_index == 2) {
                    txt_head.setText("Anwendung");
                    text.setText(Html.fromHtml(getString(R.string.welcom_content_11)));
                }
            }
            if (getArguments().getInt(LAYOUT_ID) == R.layout.welcome_fragment02) {
                txt_head = (TextView) rootView.findViewById(R.id.heading_02);
                TextView text = (TextView) rootView.findViewById(R.id.content_welcome_02);
                text.setMovementMethod(LinkMovementMethod.getInstance());
                if (MainActivity.language_index == 1) {
                    txt_head.setText("Background information: How and why it works");
                    text.setText(Html.fromHtml(getString(R.string.welcom_content_02)));
                }
                if (MainActivity.language_index == 2) {
                    txt_head.setText("Hintergrundinformation: Wie und warum es funktioniert");
                    text.setText(Html.fromHtml(getString(R.string.welcom_content_12)));
                }
            }
            if (getArguments().getInt(LAYOUT_ID) == R.layout.welcome_fragment04) {
                TextView text = (TextView) rootView.findViewById(R.id.content_welcome_04);
                text.setMovementMethod(LinkMovementMethod.getInstance());
                if (MainActivity.language_index == 1) {
                    text.setText(Html.fromHtml(getString(R.string.welcom_content_04)));
                }
                if (MainActivity.language_index == 2) {
                    text.setText(Html.fromHtml(getString(R.string.welcom_content_14)));
                }

                ImageButton coachmylife_mark = (ImageButton) rootView.findViewById(R.id.coachmylife_mark);
                coachmylife_mark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("http://www.coachmylife.de");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                ImageButton wingwave_mark = (ImageButton) rootView.findViewById(R.id.wingwave_mark);
                wingwave_mark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("http://www.wingwave.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                ImageButton coachmylife_mark1 = (ImageButton) rootView.findViewById(R.id.coachmylife_mark1);
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

        return rootView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void filiter(int color, int imgId, String head, String content) {
        if (rootView != null) {
            rootView.setBackgroundColor(color);
        }
        if (imageView != null && imgId != 0) {
            imageView.setImageResource(imgId);
        }
        if (txt_head != null) {
            txt_head.setText(head);
        }
        if (txt_content != null) {
            txt_content.setText(content);
        }
    }
}
