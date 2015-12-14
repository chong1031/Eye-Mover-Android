package com.example.chongryong.friendly_eye_mover;

import android.os.Bundle;

/**
 * Created by duanyl on 2015/9/21.
 */
public class APPIntroTwo extends APPIntroViewPager  {

    @Override
    protected void OnCreatePager(Bundle savedInstanceState) {
        setContext(this);
        showSkipButton(true) ;
        setSeparatorColor(getResources().getColor(R.color.background_separator));
        setBottonBarColor(getResources().getColor(R.color.background_buttonbar));
        addOnPageChangeListener(new APPIntroViewPager.MyPageChangeListener()) ;
        setTransformerType(APPIntroViewPager.TransformerType_TEXTANDIMG);
        addFramgent(APPIntroFragment.newInstance(R.layout.welcome_fragment01));
        addFramgent(APPIntroFragment.newInstance(R.layout.welcome_fragment02));
        addFramgent(APPIntroFragment.newInstance(R.layout.welcome_fragment04));
        addFramgent(APPIntroFragment.newInstance(R.layout.welcome_fragment03));
    }

}
