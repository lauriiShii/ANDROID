package com.example.eclip.app25_fct.mainActivity.Students.ViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.mainActivity.Students.StudentDetailFragment;
import com.example.eclip.app25_fct.mainActivity.Students.StudentListVisitFragment;

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private int numPages = 2;
    private Student mStudent;

    public AdapterViewPager(FragmentManager fm, Student student) {
        super(fm);
        mStudent = student;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StudentDetailFragment.newInstance(mStudent);
            case 1:
                return StudentListVisitFragment.newInstance(mStudent);
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numPages;
    }

    // Retorna el título de una página. Recibe el número de página.
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 1)
            return "STUDENT";
        else
            return "VISIT";
    }
}