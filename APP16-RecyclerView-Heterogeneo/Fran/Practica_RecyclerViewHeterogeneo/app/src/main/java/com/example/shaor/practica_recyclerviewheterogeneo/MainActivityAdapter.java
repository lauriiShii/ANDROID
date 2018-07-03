package com.example.shaor.practica_recyclerviewheterogeneo;

import android.app.LauncherActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ListElement> data;

    public void setData(ArrayList<ListElement> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.activity_main_classroomitem:
                return onCreateClassroomViewHolder(parent);
            case R.layout.activity_main_studentitem:
                return onCreateStudentViewHolder(parent);
        }
        return null;
    }

    private RecyclerView.ViewHolder onCreateStudentViewHolder(ViewGroup parent) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_studentitem, parent, false);

        final RecyclerView.ViewHolder viewHolder = new StudentViewHolder(itemView);

        return viewHolder;
    }

    private RecyclerView.ViewHolder onCreateClassroomViewHolder(ViewGroup parent) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_classroomitem, parent, false);

        final RecyclerView.ViewHolder viewHolder = new ClassroomViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(position).getType() == R.layout.activity_main_classroomitem) {
            ((ClassroomViewHolder) holder).onBind((Classroom) data.get(position));
        } else {
            ((StudentViewHolder) holder).onBind((Student) data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private class ClassroomViewHolder extends RecyclerView.ViewHolder {

        private TextView lblClassName;

        public ClassroomViewHolder(View itemView) {
            super(itemView);
            lblClassName = (TextView) itemView.findViewById(R.id.lblClassName);
        }

        public void onBind(Classroom elemento) {
            lblClassName.setText(elemento.getName());
        }
    }

    private class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView lblStudentName;
        private TextView lblStudentAge;

        public StudentViewHolder(View itemView) {
            super(itemView);
            lblStudentName = (TextView) itemView.findViewById(R.id.lblStudentName);
            lblStudentAge = (TextView) itemView.findViewById(R.id.lblStudentAge);
        }

        public void onBind(Student elemento) {
            lblStudentName.setText(elemento.getName());
            lblStudentAge.setText(String.valueOf(elemento.getAge()));
        }
    }
}
