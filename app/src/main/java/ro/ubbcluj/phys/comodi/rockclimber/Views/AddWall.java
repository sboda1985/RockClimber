package ro.ubbcluj.phys.comodi.rockclimber.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ro.ubbcluj.phys.comodi.rockclimber.R;



public class AddWall extends AppCompatActivity {

    private EditText gwallname;
    private EditText gwalldescription;
    private EditText gwallapproach;
    private Button savewallbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wall);
        gwallname = (EditText) findViewById(R.id.wallname_editText);
        gwalldescription = (EditText) findViewById(R.id.walldescription_editText);
        gwallapproach = (EditText) findViewById(R.id.wallapproach_editText);
        savewallbutton = (Button) findViewById(R.id.savewall_button);
        savewallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptSave();
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();


            }

            private void attemptSave() {

            }



        });
    }
}
