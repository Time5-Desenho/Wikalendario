package com.time2desenho.wikalendario;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joao on 03/10/16.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectsViewHolder> {

    private Context context;
    private ArrayList<Subject> subjects;
    private SubjectOnClickListener subjectOnClickListener;

    private final String TAG = "SubAdapter";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public SubjectOnClickListener getSubjectOnClickListener() {
        return subjectOnClickListener;
    }

    public void setSubjectOnClickListener(SubjectOnClickListener subjectOnClickListener) {
        this.subjectOnClickListener = subjectOnClickListener;
    }

    public SubjectAdapter(Context context, ArrayList<Subject> subjects, SubjectOnClickListener subjectOnClickListener){
        setContext(context);
        setSubjects(subjects);
        setSubjectOnClickListener(subjectOnClickListener);
    }

    @Override
    public int getItemCount(){
        int numberOfSubjects;
        if(subjects != null){
            numberOfSubjects = subjects.size();
        }else{
            numberOfSubjects = 0;
        }

        return numberOfSubjects;
    }

    @Override
    public void onBindViewHolder(final SubjectsViewHolder viewHolder, final int position){
        Subject subject = subjects.get(position);

        String codeText = "Código: " + subject.getCode();
        Log.d(TAG, codeText);
        viewHolder.textViewCode.setText(codeText);

        String nameText = subject.getName();
        Log.d(TAG, nameText);
        viewHolder.textViewName.setText(nameText);

        if(subjectOnClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view){
                  subjectOnClickListener.onClickSubject(viewHolder.itemView, position);
              }
            } );
        }
    }

    @Override
    public SubjectsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        //Inflating view and creating viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_subject, viewGroup, false);
        SubjectsViewHolder subjectsViewHolder = new SubjectsViewHolder(view);

        return subjectsViewHolder;
    }

    // Callback interface to show list events
    public interface SubjectOnClickListener{
        public void onClickSubject(View view, int position);
    }

    // ViewHolder that has reference to layout views
    public class SubjectsViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewCode;
        private TextView textViewName;
        private CardView cardView;

        public SubjectsViewHolder(View view){
            super(view);

            textViewCode = (TextView) view.findViewById(R.id.text_code);
            textViewName = (TextView) view.findViewById(R.id.text_name);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
