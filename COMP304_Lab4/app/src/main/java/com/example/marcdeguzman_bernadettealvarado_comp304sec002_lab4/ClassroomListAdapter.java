package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassroomListAdapter extends RecyclerView.Adapter<ClassroomListAdapter.MyViewHolder>  {

    private Context context;
    private List<Classroom> classrooms = new ArrayList<>();

    public ClassroomListAdapter(Context context) {
        //initialize the context
        this.context = context;
    }

    @NonNull
    @Override
    public ClassroomListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.classroom_recycler_row, parent, false); //inflate layout created for recyclerview

        return new ClassroomListAdapter.MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ClassroomListAdapter.MyViewHolder holder, int position) {

        Classroom currentClassroom = classrooms.get(position);
        String classroomId = "Classroom: " + currentClassroom.getClassroomId();
        String floor = "Floor: " + currentClassroom.getFloor();
        String aircondition = "Airconditioned: " + currentClassroom.getAirConditioned();

        holder.tvRcTestClassRoomID.setText(classroomId);
        holder.tvRcFloor.setText(floor);
        holder.tvRcAC.setText(aircondition);
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
        for(int i = 0; i < this.classrooms.size(); i++) {
            Classroom classroom = this.classrooms.get(i);

            Log.d("ClassroomListAdapter", "student id " + classroom.getStudentId());
            Log.d("ClassroomListAdapter", "professor id: " + classroom.getProfessorId());
            Log.d("ClassroomListAdapter", "classroom id: " + classroom.getClassroomId());
            Log.d("ClassroomListAdapter", "floor: " + classroom.getFloor());
            Log.d("ClassroomListAdapter", "air conditioned : " + classroom.getAirConditioned());
//            Log.d("ClassroomListAdapter", "test name: " + classroom.getTestName());
//            Log.d("ClassroomListAdapter", "test grade: " + classroom.getTestGrade());
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvRcTestClassRoomID;
        TextView tvRcFloor;
        TextView tvRcAC;

        public MyViewHolder(View view) {
            super(view);

            tvRcTestClassRoomID = view.findViewById(R.id.tvRcTestClassRoomID);
            tvRcFloor = view.findViewById(R.id.tvRcFloor);
            tvRcAC = view.findViewById(R.id.tvRcAC);
        }
    }
}
