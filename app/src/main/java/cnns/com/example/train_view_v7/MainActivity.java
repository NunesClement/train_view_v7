package cnns.com.example.train_view_v7;


import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static final int ADD_TASK_REQUEST = 1; // Code pour identifier l'activité secondaire à son retour
    private TextView m_tv_tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_tv_tasks = (TextView) findViewById(R.id.tv_tasks);

        String[] task_list = FakeData.get_tasks();
        // Parcours les listes des tâches fictives
        // et les ajoute dans le TextView
        for (String task : task_list) {
            m_tv_tasks.append(task + "\n");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_add_task:
                Intent addTaskIntent = new Intent(MainActivity.this, AddTask.class);
                startActivityForResult(addTaskIntent, ADD_TASK_REQUEST);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Vérifie que c'est bien la bonne activité qui nous renvoie un résultat
        if (requestCode == ADD_TASK_REQUEST) {
            // Vérifie que le code de résultat est bien RESULT_OK
            if (resultCode == RESULT_OK) {
                String task_description = data.getStringExtra("TASK_DESCRIPTION");
                int task_priority = data.getIntExtra("TASK_PRIORITY", 1);
                m_tv_tasks.append("<" + task_priority + "> " + task_description + "\n");
            }
        }
    }

}
