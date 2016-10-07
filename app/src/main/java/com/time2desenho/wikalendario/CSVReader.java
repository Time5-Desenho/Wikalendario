package com.time2desenho.wikalendario;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    private static final String TAG = "CSVReader";

    public static ArrayList<Subject> getSubjects(Context context, int fileId){
        InputStream csv = readFile(context, fileId);

        ArrayList<Subject> subjects = new ArrayList<>();
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",|\n");

        while(scanner.hasNext()){
            String code = scanner.next();
            String name = scanner.next().trim();

            Log.v(TAG, "Subject = " +  code + ", " + name);

            subjects.add(new Subject(code, name));
        }

        return subjects;
    }

    private static InputStream readFile(Context context, int fileId){
        Resources resources = context.getResources();
        InputStream file;
        file = resources.openRawResource(fileId);

        return file;
    }
}
