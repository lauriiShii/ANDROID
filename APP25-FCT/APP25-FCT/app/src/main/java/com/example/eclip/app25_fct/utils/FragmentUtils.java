package com.example.eclip.app25_fct.utils;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.mainActivity.Business.BusinessDetailFragment;
import com.example.eclip.app25_fct.mainActivity.Business.ListBusinessFragment;
import com.example.eclip.app25_fct.mainActivity.MainActivity;
import com.example.eclip.app25_fct.mainActivity.Students.ListStudentsFragment;
import com.example.eclip.app25_fct.mainActivity.Students.StudentDetailFragment;
import com.example.eclip.app25_fct.mainActivity.Students.ViewPager.StudentViewFragment;
import com.example.eclip.app25_fct.mainActivity.Visit.ListNextVisitFragment;

/**
 * Created by eclip on 24/11/2017.
 */

public class FragmentUtils {

    private FragmentUtils() {
    }

    public static void replaceFragment(FragmentManager fragmentManager,
                                       int parentResId, Fragment fragment, String tag) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).commit();
    }

    public static void replaceFragmentAddToBackstack(FragmentManager fragmentManager,
                                                     int parentResId, Fragment fragment, String tag, int transition) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).setTransition(
                transition).addToBackStack(String.format("%s", transition) + tag).commit();
    }

    public static void openBack(Context mContext) {
        ((MainActivity) mContext).getSupportFragmentManager().popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public static void openCompanyDetail(Context mContext) {
        FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                BusinessDetailFragment.newInstance(), Constantes.TAG_COMPANY_DETAIL, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }

    public static void openCompanyDetail(Context mContext, Company mCompany) {
        FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                BusinessDetailFragment.newInstance(mCompany), Constantes.TAG_COMPANY_DETAIL_EXTRA, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }

    public static void openStudentDetail(Context mContext) {
        FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                StudentDetailFragment.newInstance(), Constantes.TAG_STUDENT_DETAIL, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }

    public static void openStudentDetail(Context mContext, Student mStudent) {
        FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                StudentDetailFragment.newInstance(mStudent), Constantes.TAG_STUDENT_DETAIL_EXTRA, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }

    public static void openCompanyList(Context mContext) {
        if (((MainActivity) mContext).getSupportFragmentManager().getBackStackEntryCount() == 0) {
            FragmentUtils.replaceFragment(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                    ListBusinessFragment.newInstance(), Constantes.TAG_COMPANY);
        } else {
            FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                    ListBusinessFragment.newInstance(), Constantes.TAG_COMPANY, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        }
    }

    public static void openStudentList(Context mContext) {
        if (((MainActivity) mContext).getSupportFragmentManager().getBackStackEntryCount() == 0) {

            FragmentUtils.replaceFragment(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                    ListStudentsFragment.newInstance(), Constantes.TAG_STUDENT);
        } else {
            FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                    ListStudentsFragment.newInstance(), Constantes.TAG_STUDENT, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        }
    }

    public static void openVisitList(Context mContext) {
        if (((MainActivity) mContext).getSupportFragmentManager().getBackStackEntryCount() == 0) {
            FragmentUtils.replaceFragment(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                    ListNextVisitFragment.newInstance(), Constantes.TAG_VISIT_LIST);
        } else {
            FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                    ListNextVisitFragment.newInstance(), Constantes.TAG_VISIT_LIST, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        }
    }

    public static void openStudentView(Context mContext) {
        FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                StudentViewFragment.newInstance(), Constantes.TAG_STUDENT_VIEW, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }

    public static void openStudentView(Context mContext, Student mStudent) {
        FragmentUtils.replaceFragmentAddToBackstack(((MainActivity) mContext).getSupportFragmentManager(), R.id.frgSpace,
                StudentViewFragment.newInstance(mStudent), Constantes.TAG_STUDENT_VIEW_EXTRA, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }
}
