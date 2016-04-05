package pl.radekdab.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private static final String TAG = "TaskAdapter";

    private LayoutInflater inflater;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        Log.d(TAG, "constructor");
        inflater = LayoutInflater.from(context);
        this.tasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View itemView = inflater.inflate(R.layout.task, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder at position " + position);
        Task task = tasks.get(position);
        holder.checkBox.setChecked(task.isDone());
        holder.editText.setText(task.getText());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void addItem() {
        Log.d(TAG, "addItem");
        tasks.add(new Task());
        notifyItemInserted(tasks.size() - 1);
    }

    public void addItem(int position, Task task) {
        Log.d(TAG, "addItem at position " + position);
        tasks.add(position, task);
        notifyItemInserted(position);
    }

    public Task removeItem(int position) {
        Log.d(TAG, "removeItem at position " + position);
        Task task = tasks.remove(position);
        notifyItemRemoved(position);
        return task;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "TaskAdapter.ViewHolder";

        private CheckBox checkBox;
        private EditText editText;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "constructor");
            checkBox = (CheckBox) itemView.findViewById(R.id.task_done);
            editText = (EditText) itemView.findViewById(R.id.task_text);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d(TAG, "onCheckedChanged");
                    Task task = tasks.get(getAdapterPosition());
                    task.setDone(checkBox.isChecked());
                }
            });
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    Task task = tasks.get(getAdapterPosition());
                    task.setText(s.toString());
                }
            });
        }
    }
}
