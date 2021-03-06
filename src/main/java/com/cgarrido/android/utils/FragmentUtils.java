package com.cgarrido.android.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public abstract class FragmentUtils {


    public static void remove(Fragment fragment) {
        FragmentManager manager = fragment.getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commitNow();
//        trans.commit();
//        manager.popBackStack();
    }

    public enum AnimType {
        SLIDE_UP,
        SLIDE_LEFT,
        FADE_IN,
        NONE
    }
    public static boolean sDisableFragmentAnimations = false;


    //vide la pile de fragments et ¿previent les animations?
    public static void clearBackStack(FragmentActivity ctx) {
        FragmentUtils.sDisableFragmentAnimations = true;
        ctx.getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentUtils.sDisableFragmentAnimations = false;
    }
    public static void clearBackStack(FragmentActivity ctx, int remained) {
        FragmentUtils.sDisableFragmentAnimations = true;
        FragmentManager fm = ctx.getSupportFragmentManager();
        int total = fm.getBackStackEntryCount()-remained >= 0 ? fm.getBackStackEntryCount()-remained: 0;
        for (int i = 0; i < total; i++) {
            fm.popBackStackImmediate();
        }
        FragmentUtils.sDisableFragmentAnimations = false;
    }

    public static void popBackStack(FragmentActivity ctx, boolean animations) {
        popBackStack(ctx,1,animations);
    }


    public static void popBackStack(FragmentActivity ctx, int steps, final boolean animations) {
        if (!animations) FragmentUtils.sDisableFragmentAnimations = true;
        FragmentManager fm = ctx.getSupportFragmentManager();
        for (int i = 0; i < steps; i++)
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (!animations) FragmentUtils.sDisableFragmentAnimations = false;
    }

    public static void slide(Fragment origin, Fragment newFragment, int idContainer) {
        slide(origin.getFragmentManager(),newFragment,idContainer);
    }

    public static void slide(FragmentManager fm, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                com.cgarrido.android.utils.R.anim.enter,
                com.cgarrido.android.utils.R.anim.exit,
                com.cgarrido.android.utils.R.anim.pop_enter,
                com.cgarrido.android.utils.R.anim.pop_exit);
        ft.replace(idContainer, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void slideRightTransaction(FragmentManager fm, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.nothing,
                R.anim.nothing);
        ft.replace(idContainer, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    public static void slideLeft(FragmentManager fm, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                R.anim.pop_enter,
                R.anim.pop_exit,
                R.anim.nothing,
                R.anim.nothing);
        ft.replace(idContainer, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void crossSlideLeft(FragmentManager fm, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        ft.replace(idContainer, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void fadeIn(FragmentManager fm, Fragment newFragment, int idContainer, boolean addToBackStack) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.nothing,
                R.anim.nothing);
        ft.replace(idContainer, newFragment);
        if (addToBackStack)
            ft.addToBackStack(null);
        ft.commit();
    }


    public static void fadeIn(FragmentManager fm, Fragment newFragment, int idContainer) {
        fadeIn(fm,newFragment,idContainer, true);
    }

    public static void slideUp(Fragment origin, Fragment newFragment, int idContainer) {
        slideUp(origin.getFragmentManager(), newFragment, idContainer);
    }

    public static void slideUp(FragmentManager fm, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                R.anim.slide_up_enter,
                R.anim.slide_up_exit,
                R.anim.pop_slide_up_enter,
                R.anim.pop_slide_up_exit);
        ft.addToBackStack(null);
        ft.add(idContainer, newFragment);
        ft.commit();
    }

    public static void set(FragmentActivity origin, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        ft.replace(idContainer, newFragment);
        ft.commit();
    }

    public static void set(FragmentManager supportFragmentManager, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.replace(idContainer, newFragment);
        ft.commit();
    }

    public static void addToStack(FragmentActivity origin, Fragment newFragment, int idContainer) {
        addToStack(origin.getSupportFragmentManager(), newFragment, idContainer);
    }


    public static void addToStack(FragmentManager supportFragmentManager, Fragment newFragment, int idContainer) {
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.add(idContainer, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}