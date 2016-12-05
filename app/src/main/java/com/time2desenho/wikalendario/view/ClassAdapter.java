package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.model.Class;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassesViewHolder> {

    private Context context;
    private ArrayList<Class> classes;
    private ClassAdapter.ClassOnClickListener classOnClickListener;

    private final String TAG = "SubAdapter";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public ClassOnClickListener getClassOnClickListener() {
        return classOnClickListener;
    }

    public void setClassOnClickListener(ClassOnClickListener subjectOnClickListener) {
        this.classOnClickListener = subjectOnClickListener;
    }

    public ClassAdapter(Context context, ArrayList<Class> classes, ClassOnClickListener subjectOnClickListener){
        setContext(context);
        setClasses(classes);
        setClassOnClickListener(subjectOnClickListener);
    }

    @Override
    public int getItemCount(){
        int numberOfClasses;
        if(classes != null){
            numberOfClasses = classes.size();
        }else{
            numberOfClasses = 0;
        }

        return numberOfClasses;
    }

    @Override
    public void onBindViewHolder(final ClassesViewHolder viewHolder, final int position){
        Class mclass = classes.get(position);

        String codeText = "Turma: " + mclass.getLetter();
        Log.d(TAG, codeText);
        viewHolder.textClassLetter.setText(codeText);

        String nameText = mclass.getTeacher();
        Log.d(TAG, nameText);
        viewHolder.textProfessorName.setText(nameText);

        if(classOnClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    classOnClickListener.onClickClass(viewHolder.itemView, position);
                }
            } );
        }
    }

    @Override
    public ClassesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        //Inflating view and creating viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_class, viewGroup, false);
        ClassesViewHolder classesViewHolder = new ClassesViewHolder(view);

        return classesViewHolder;
    }

    // Callback interface to show list events
    public interface ClassOnClickListener{
        public void onClickClass(View view, int position);
    }

    // ViewHolder that has reference to layout views
    public class ClassesViewHolder extends RecyclerView.ViewHolder{
        private TextView textProfessorName;
        private TextView textClassLetter;
        private CardView cardView;

        public ClassesViewHolder(View view){
            super(view);

            textProfessorName = (TextView) view.findViewById(R.id.professor_name);
            textClassLetter = (TextView) view.findViewById(R.id.class_letter);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
