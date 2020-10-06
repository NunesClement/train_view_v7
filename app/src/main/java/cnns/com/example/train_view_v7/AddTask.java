package cnns.com.example.train_view_v7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class AddTask extends AppCompatActivity {
    private int mPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        // Intialisation de la priorité (mPriority = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;
    }

    public void onPrioritySelected(View v) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }

    public void onClickAddTask(View v) {
        // Vérifie si le composant EditText est vide,
        // Si il est vide, on n'affiche pas le message
        String input = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (input.length() == 0) {
            return;
        }
        Toast msg = Toast.makeText(this, "Tâche : " + input + "\nDe priorité " + mPriority, Toast.LENGTH_SHORT);
        msg.show();

        // Création des données de retour (qui seront renvoyées à l'activité appelante)
        Intent returnIntent = new Intent();
        returnIntent.putExtra("TASK_DESCRIPTION", input);
        returnIntent.putExtra("TASK_PRIORITY", mPriority);

        // Paramètre le code de retour
        setResult(RESULT_OK,returnIntent);

        // Termine l'activité
        finish();
    }
}
