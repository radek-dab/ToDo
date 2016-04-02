package pl.radekdab.todo;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleTaskSaver implements TaskSaver {
    private static final String TAG = "SimpleTaskSaver";

    private static final String FILENAME = "tasks.dat";

    private File file;

    public SimpleTaskSaver(Context context) {
        file = new File(context.getFilesDir(), FILENAME);
    }

    @Override
    public void save(List<Task> tasks) {
        Log.d(TAG, "save " + tasks.size() + " tasks");
        try {
            ObjectOutput output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(tasks);
            output.close();
        }
        catch (Exception e) {
            Log.e(TAG, "save error " + e.getMessage());
        }
    }

    @Override
    public List<Task> restore() {
        Log.d(TAG, "restore");
        List<Task> tasks = null;
        if (file.exists()) {
            try {
                ObjectInput input = new ObjectInputStream(new FileInputStream(file));
                tasks = (List<Task>) input.readObject();
                input.close();
            } catch (Exception e) {
                Log.e(TAG, "restore error " + e.getMessage());
            }
        }
        else {
            tasks = new ArrayList<>();
        }
        return tasks;
    }
}
