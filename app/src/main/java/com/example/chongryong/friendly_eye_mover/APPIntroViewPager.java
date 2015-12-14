package com.example.chongryong.friendly_eye_mover;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;

import java.util.List;
import java.util.Vector;

/**
 * Created by duanyl on 2015/9/21.
 */
public abstract class APPIntroViewPager extends AppCompatActivity {

    public final static int TransformerType_TEXT = 1;
    //public final static int TransformerType_IMG = 2;
    public final static int TransformerType_TEXTANDIMG = 3;//ͼƬ�����ֽ��䣬��������ͼƬλ�ơ�
    private Context context;
    private ViewPager pager;
    private View view_speletor;
    private RelativeLayout bottomBar;
    private PagerAdapter pagerAdapter;
    private LinearLayout circles;
    private Button skip;
    private Button done;
    private ImageButton next;
    private List<APPIntroFragment> fragmentlist = new Vector();
    private boolean isOpaque = true;
    private int transformerType = 1;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    /**
     * onCreate
     *
     * @param savedInstanceState
     */
    protected abstract void OnCreatePager(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_tutorial);

        next = ImageButton.class.cast(findViewById(R.id.next));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
        });

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTutorial();
            }
        });

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentlist);
        pager.setAdapter(pagerAdapter);


        addFramgent(APPIntroFragment.newInstance(Color.TRANSPARENT, 0, "", ""));

        pager.setPageTransformer(true, new CrossfadePageTransformer());

        OnCreatePager(savedInstanceState);

        buildCircles();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pager != null) {
            pager.clearOnPageChangeListeners();
        }
    }

    private void buildCircles() {
        circles = (LinearLayout) findViewById(R.id.circles);

        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (5 * scale + 0.5f);

        for (int i = 0; i < fragmentlist.size() - 1; i++) {
            ImageView circle = new ImageView(this);
            circle.setImageResource(R.drawable.ic_swipe_indicator_white_18dp);
            circle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            circle.setAdjustViewBounds(true);
            circle.setPadding(padding, 0, padding, 0);
            circles.addView(circle);
        }

        setIndicator(0);
    }

    private void setIndicator(int index) {
        if (index < fragmentlist.size() - 1) {
            for (int i = 0; i < fragmentlist.size() - 1; i++) {
                ImageView circle = (ImageView) circles.getChildAt(i);
                if (i == index) {
                    circle.setColorFilter(getResources().getColor(R.color.text_selected));//��ǰPagerԲ������
                } else {
                    circle.setColorFilter(getResources().getColor(android.R.color.transparent));
                }
            }
        }
    }

    private void endTutorial() {
        finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    public void addFramgent(APPIntroFragment fragment) {
        this.fragmentlist.add(fragment);
        this.pagerAdapter.notifyDataSetChanged();

    }

    public class PagerAdapter extends FragmentPagerAdapter {
        private List<APPIntroFragment> fragments;

        public PagerAdapter(FragmentManager fm, List<APPIntroFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        public Fragment getItem(int position) {
            return (Fragment) this.fragments.get(position);
        }

        public int getCount() {
            return this.fragments.size();
        }
    }

    public class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == fragmentlist.size() - 2 && positionOffset > 0) {
                if (isOpaque) {
                    pager.setBackgroundColor(Color.TRANSPARENT);
                    isOpaque = false;
                }
            } else {
                if (!isOpaque) {
                    pager.setBackgroundColor(getResources().getColor(R.color.primary_material_light));
                    isOpaque = true;
                }
            }
        }

        @Override
        public void onPageSelected(int position) {
            setIndicator(position);
            if (position == fragmentlist.size() - 2) {
                skip.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
                done.setVisibility(View.VISIBLE);
            } else if (position < fragmentlist.size() - 2) {
                skip.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                done.setVisibility(View.GONE);
            } else if (position == fragmentlist.size() - 1) {
                endTutorial();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public class CrossfadePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();

            View backgroundView = page.findViewById(R.id.welcome_fragment);
            View text_head = page.findViewById(R.id.heading);
            View text_content = page.findViewById(R.id.content);
            View imgview = page.findViewById(R.id.images);
            Log.d("duanyl", "xxxxxxxxxxx" + position);

            if (0 < position && position < 1) {
                ViewHelper.setTranslationX(page, pageWidth * -position);
            }
            if (-1 < position && position < 0) {
                ViewHelper.setTranslationX(page, pageWidth * -position);
            }

            if (position <= -1.0f || position >= 1.0f) {
            } else if (position == 0.0f) {
            } else {
                if (backgroundView != null) {
                    ViewHelper.setAlpha(backgroundView, 1.0f - Math.abs(position));

                }

                if (text_head != null) {
                    ViewHelper.setTranslationX(text_head, pageWidth * position);
                    ViewHelper.setAlpha(text_head, 1.0f - Math.abs(position));
                }

                if (text_content != null) {
                    ViewHelper.setTranslationX(text_content, pageWidth * position);
                    ViewHelper.setAlpha(text_content, 1.0f - Math.abs(position));
                }
                if (transformerType == TransformerType_TEXTANDIMG) {
                    if (imgview != null) {
                        ViewHelper.setTranslationX(imgview, (float) (pageWidth / 2 * position));
                        ViewHelper.setAlpha(imgview, 1.0f - Math.abs(position));

                    }
                }

            }
        }

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTransformerType(int type) {
        transformerType = type;
    }

    public void setSeparatorColor(int color) {
        view_speletor = findViewById(R.id.vp_separator);
        view_speletor.setBackgroundColor(color);
    }

    public void setBottonBarColor(int color) {
        bottomBar = (RelativeLayout) findViewById(R.id.button_layout);
        bottomBar.setBackgroundColor(color);

    }

    public void showSkipButton(boolean is) {

        skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTutorial();
            }
        });

        if (is) {
            skip.setVisibility(View.VISIBLE);
        } else {
            skip.setVisibility(View.INVISIBLE);
        }

    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        if (pager != null) {
            if (listener != null) {
                onPageChangeListener = listener;
            } else {
                onPageChangeListener = new MyPageChangeListener();
            }
            pager.addOnPageChangeListener(onPageChangeListener);
        }
    }
}
