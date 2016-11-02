package com.time2desenho.wikalendario.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.time2desenho.wikalendario.model.Class;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassReader {

    private static final String TAG = "ClassReader";

    public static ArrayList<Class> getSubjects(Context context, int fileId){
        InputStream csv = readFile(context, fileId);

        ArrayList<Class> classes = new ArrayList<>();
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",|\n");

        while(scanner.hasNext()){
            String code = scanner.next();
            String letter = scanner.next();
            String teacher = scanner.next().trim();

            Log.v(TAG, "Subject = " +  code + ", " + letter + ", " + teacher);

            Long numCode = Long.parseLong(code);

            classes.add(new Class(letter, teacher));
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
