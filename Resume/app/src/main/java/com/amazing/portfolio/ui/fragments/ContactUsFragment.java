package com.amazing.portfolio.ui.fragments;

import android.animation.LayoutTransition;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.amazing.portfolio.R;

public class ContactUsFragment extends BaseFragment {

    private final static String LONG_TEXT = "Lorem ipsum dolor sit amet, et" +
            " alienum inciderint efficiantur nec, posse causae molestie" +
            " eos in. Ea vero praesent vix, nam soleat recusabo id." +
            " Qui ut exerci option laboramus. In habeo posse ridens quo," +
            " eligendi volutpat interesset ut est, mel nibh accusamus no." +
            " Te eam consulatu repudiare adipiscing, usu et choro quodsi euripidis.";

    private final static String SHORT_TEXT = " For the 2009 model " +
            "the G35 sedan was replaced by the G37 sedan.";

    private final static int MAX_LINES_COLLAPSED = 3;
    private final boolean INITIAL_IS_COLLAPSED = true;

    private static final int IDLE_ANIMATION_STATE = 1;
    private static final int EXPANDING_ANIMATION_STATE = 2;
    private static final int COLLAPSING_ANIMATION_STATE = 3;

    private int mCurrentAnimationState = IDLE_ANIMATION_STATE;

    private boolean isCollapsed = INITIAL_IS_COLLAPSED;

    private TextView mExpandableTV;
    private ConstraintLayout mParentLayout;
    private View v;

    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_contact_us, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mParentLayout = v.findViewById(R.id.root_container);
        mExpandableTV = v.findViewById(R.id.expandable_tv);

        mExpandableTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning()) {
                    mParentLayout.setLayoutTransition(mParentLayout.getLayoutTransition());
                }
                if (isCollapsed) {
                    mCurrentAnimationState = EXPANDING_ANIMATION_STATE;
                    mExpandableTV.setMaxLines(Integer.MAX_VALUE);
                } else {
                    mCurrentAnimationState = COLLAPSING_ANIMATION_STATE;
                    mExpandableTV.setMaxLines(MAX_LINES_COLLAPSED);
                    mExpandableTV.post(new Runnable() {
                        @Override
                        public void run() {
                            mExpandableTV.setMaxLines(Integer.MAX_VALUE);
                        }
                    });
                }
                isCollapsed = !isCollapsed;
            }
        });

        if (isCollapsed) {
            mExpandableTV.setMaxLines(MAX_LINES_COLLAPSED);
        } else {
            mExpandableTV.setMaxLines(Integer.MAX_VALUE);
        }

        updateWithNewText(LONG_TEXT);
        applyLayoutTransition();
    }
    private void updateWithNewText(String text) {
        mExpandableTV.setText(text);
        mExpandableTV.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver
                        .OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (isTextUnlimited()) {
                            if (canBeCollapsed()) {
                                mExpandableTV.setClickable(false);
                                mExpandableTV.setEllipsize(null);
                            } else {
                                mExpandableTV.setClickable(true);
                                mExpandableTV.setEllipsize(TextUtils.TruncateAt.END);
                            }
                        } else {
                            if (isTrimmedWithLimitLines()) {
                                mExpandableTV.setClickable(false);
                                mExpandableTV.setEllipsize(null);
                            } else {
                                mExpandableTV.setClickable(true);
                                mExpandableTV.setEllipsize(TextUtils.TruncateAt.END);
                            }
                        }
                        mExpandableTV.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                    }
                });
    }

    private boolean isTextUnlimited() {
        return mExpandableTV.getMaxLines() == Integer.MAX_VALUE;
    }

    private boolean canBeCollapsed() {
        return mExpandableTV.getLineCount() <= MAX_LINES_COLLAPSED;
    }

    private boolean isTrimmedWithLimitLines() {
        return mExpandableTV.getLineCount() <= mExpandableTV.getMaxLines();
    }


    private void applyLayoutTransition() {
        LayoutTransition transition = new LayoutTransition();
        transition.setDuration(900);
        transition.enableTransitionType(LayoutTransition.CHANGING);
        mParentLayout.setLayoutTransition(transition);

        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition,
                                        ViewGroup container, View view, int transitionType) {
                //todo
            }

            @Override
            public void endTransition(LayoutTransition transition,
                                      ViewGroup container, View view, int transitionType) {
                if (COLLAPSING_ANIMATION_STATE == mCurrentAnimationState) {
                    mExpandableTV.setMaxLines(MAX_LINES_COLLAPSED);
                }
                mCurrentAnimationState = IDLE_ANIMATION_STATE;
            }
        });
    }

    private boolean isIdle() {
        return mCurrentAnimationState == IDLE_ANIMATION_STATE;
    }

    private boolean isRunning() {
        return !isIdle();
    }
}