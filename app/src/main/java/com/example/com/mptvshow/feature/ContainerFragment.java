package com.example.com.mptvshow.feature;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.com.mptvshow.R;
import com.example.com.mptvshow.feature.list.ListTvShowsFragment;

import java.util.List;

import butterknife.ButterKnife;

public class ContainerFragment extends Fragment {

    private Fragment currentFragment;
    private View fragmentContainer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentContainer = inflater.inflate(R.layout.fragment_container, container, false);

        ButterKnife.bind(this, fragmentContainer);

        final FragmentManager fragmentManager = getChildFragmentManager();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                currentFragment = fragmentManager.findFragmentById(R.id.container);
            }
        });

        return fragmentContainer;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fragmentManager = getChildFragmentManager();
        currentFragment = fragmentManager.findFragmentById(R.id.container);
        if (currentFragment == null) {
            openFragment(ListTvShowsFragment.Companion.newInstance());
        }
    }

    public void willBeDisplayed() {
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }

    public void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        boolean addToBackStack = currentFragment != null;

        currentFragment = fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.container, currentFragment);

        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

        fragmentManager.executePendingTransactions();
    }



    public void openFragmentTransform(Fragment fragment, Bundle args, List<View> views) {
        FragmentManager fragmentManager = getChildFragmentManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionInflater transitionInflater = TransitionInflater.from(getContext());

            Transition changeTransform = transitionInflater.inflateTransition(R.transition.change_image_transform);
            Transition fadeTransform = transitionInflater.inflateTransition(android.R.transition.fade);

            currentFragment.setSharedElementReturnTransition(changeTransform);
            currentFragment.setExitTransition(fadeTransform);

            fragment.setSharedElementEnterTransition(changeTransform);
            fragment.setEnterTransition(fadeTransform);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack(null);

            for (View view:views) {
                fragmentTransaction.addSharedElement(view, view.getTransitionName());
            }

            fragmentTransaction.commit();

            currentFragment = fragment;
        }
        else {
            openFragment(fragment);
        }
    }

    public boolean popFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();

            return true;
        }

        return false;
    }

    public int getFragmentStackSize() {
        return getChildFragmentManager().getBackStackEntryCount();
    }

    public void cleanStack() {
        while (popFragment());
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
