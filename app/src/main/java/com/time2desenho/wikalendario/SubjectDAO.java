package com.time2desenho.wikalendario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

public class SubjectDAO extends SQLiteOpenHelper {

        public SubjectDAO(Context context) {
            super(context, "Wikalendario", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE Subjects (id INTEGER PRIMARY KEY, name TEXT NOT NULL, code TEXT NOT NULL)";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            String sql = "DROP TABLE IF EXISTS Wikalendario";
            db.execSQL(sql);
            onCreate(db);
        }


        public Subject findSubject(Subject subject) {
            Cursor c = null;
            Subject subjectFinded;

            String sql = "SELECT * FROM Subjects WHERE code=?";
            SQLiteDatabase db = getReadableDatabase();

            try {
                c = db.rawQuery(sql, new String[] {subject.getName()});

                if(c.moveToFirst()) {
                    subjectFinded = new Subject();

                    subjectFinded.setId(c.getLong(c.getColumnIndex("id")));
                    subjectFinded.setName(c.getString(c.getColumnIndex("name")));
                    subjectFinded.setCode(c.getString(c.getColumnIndex("code")));
                } else{
                    subjectFinded = new Subject("INVALID", "INVALID");
                }
            } finally {
                c.close();
            }

            return subjectFinded;
        }

        public void insert(Subject subject) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues data = getSubjectData(subject);

            db.insert("Subjects", null, data);
        }

        public void delete(Subject subject) {
            SQLiteDatabase db = getWritableDatabase();

            String[] params = {subject.getId().toString()};
            db.delete("Subjects", "id = ?", params);
        }

        public void update(Subject subject) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues data = getSubjectData(subject);

            String[] params = {subject.getId().toString()};
            db.update("Subjects", data, "id = ?", params);
        }

        @NonNull
        private ContentValues getSubjectData(Subject subject) {
            ContentValues data = new ContentValues();
            data.put("name", subject.getName());
            data.put("code", subject.getCode());
            return data;
        }
}

