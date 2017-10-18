package dri.example.com.colordetector2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by takuyamorimatsu on 2017/09/27.
 */

public class SettingActivity extends AppCompatActivity {

    private EditText filename_input, border_input, interval_input, x_pos_input, y_pos_input, format_input;
    private Switch use5points_input, printRGB_input, randomPoints_input;
    private int resultCode = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //filename_input = (EditText) findViewById(R.id.filename_input);
        border_input = (EditText) findViewById(R.id.border_input);
        interval_input = (EditText) findViewById(R.id.interval_input);
        x_pos_input = (EditText) findViewById(R.id.x_pos_input);
        y_pos_input = (EditText) findViewById(R.id.y_pos_input);
        format_input = (EditText) findViewById(R.id.format_input);

        use5points_input = (Switch) findViewById(R.id.use5points_input);
        printRGB_input = (Switch) findViewById(R.id.print_rgb_input);
        randomPoints_input = (Switch) findViewById(R.id.random_points_input);


        final SharedPreferences preferences = getSharedPreferences("setting", MODE_PRIVATE);
        //filename_input.setText(preferences.getString("filename_input", "filename.txt"));
        border_input.setText(String.valueOf(preferences.getInt("border_input", 100)));
        interval_input.setText(String.valueOf(preferences.getInt("interval_input", 1000)));
        x_pos_input.setText(String.valueOf(preferences.getInt("x_pos_input", 100)));
        y_pos_input.setText(String.valueOf(preferences.getInt("y_pos_input", 100)));
        format_input.setText(preferences.getString("format_input", "yyyy/MM/dd HH:mm:ss.SSS"));



        use5points_input.setChecked(preferences.getBoolean("use5points_input", false));
        if(use5points_input.isChecked()){
            use5points_input.setText("使う");
        }else{
            use5points_input.setText("使わない");
        }

        use5points_input.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(use5points_input.isChecked()){
                    use5points_input.setText("使う");
                }else{
                    use5points_input.setText("使わない");
                }
            }
        });

        printRGB_input.setChecked(preferences.getBoolean("printRGB_input", false));
        if(printRGB_input.isChecked()){
            printRGB_input.setText("する");
        }else{
            printRGB_input.setText("しない");
        }

        printRGB_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(printRGB_input.isChecked()){
                    printRGB_input.setText("する");
                }else{
                    printRGB_input.setText("しない");
                }
            }
        });


        randomPoints_input.setChecked(preferences.getBoolean("randomPoints_input", false));
        if(randomPoints_input.isChecked()){
            randomPoints_input.setText("する");
        }else{
            randomPoints_input.setText("しない");
        }

        randomPoints_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomPoints_input.isChecked()){
                    randomPoints_input.setText("する");
                }else{
                    randomPoints_input.setText("しない");
                }
            }
        });

        Button done_btn = (Button) findViewById(R.id.done_btn);
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                //editor.putString("filename_input", filename_input.getText().toString());
                editor.putInt("border_input", Integer.parseInt(border_input.getText().toString()));
                editor.putInt("interval_input", Integer.parseInt(interval_input.getText().toString()));
                editor.putInt("x_pos_input", Integer.parseInt(x_pos_input.getText().toString()));
                editor.putInt("y_pos_input", Integer.parseInt(y_pos_input.getText().toString()));
                editor.putString("format_input", format_input.getText().toString());

                editor.putBoolean("use5points_input", use5points_input.isChecked());
                editor.putBoolean("printRGB_input", printRGB_input.isChecked());
                editor.putBoolean("randomPoints_input", randomPoints_input.isChecked());

                editor.commit();

                resultCode = 1;
                setResult(resultCode);
                finish();
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
