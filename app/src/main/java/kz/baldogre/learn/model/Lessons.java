package kz.baldogre.learn.model;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public final class Lessons {
    private Lessons() {

    }

    public static List<Course> getAllLessons(Context context) throws IOException {
        InputStream ins = context.getResources().openRawResource(
                context.getResources().getIdentifier("data",
                        "raw", context.getPackageName()));
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(ins));
        String line;

        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        // close the streams using close method
        try {
            br.close();
        } catch (IOException ioe) {
            System.out.println("Error while closing stream: " + ioe);
        }

        return getCourses(stringBuilder.toString());
    }

    private static List<Course> getCourses(String toString) {
        return Arrays.asList(new Gson().fromJson(toString, Course[].class));
    }
}
