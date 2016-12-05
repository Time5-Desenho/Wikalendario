package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Subject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassReader {

    private static final String TAG = "ClassReader";
    private static SubjectController subjectController;

    public static ArrayList<Class> getClasses(Context context, int fileId){
        InputStream csv = readFile(context, fileId);

        ArrayList<Class> classes = new ArrayList<>();
        Subject subject;
        subjectController = new SubjectController(context);

        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",|\n");

        while(scanner.hasNext()){
            String code = scanner.next();
            String letter = scanner.next();
            String teacher = scanner.next().trim();

            Log.v(TAG, "Subject = " +  code + ", " + letter + ", " + teacher);

            Long numCode = Long.parseLong(code);

            subject = subjectController.getSubject(numCode);

            classes.add(new Class(letter, teacher, subject));
        }

        return classes;
    }

    private static InputStream readFile(Context context, int fileId){
        Resources resources = context.getResources();
        InputStream file;
        file = resources.openRawResource(fileId);

        return file;
    }
}
