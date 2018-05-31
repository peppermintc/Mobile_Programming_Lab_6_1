package org.androidtown.lab_6_1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    EditText editText1;
    Button write_sd_btn;
    Button clear_screen_btn;
    Button read_sd_btn;
    Button finish_app_btn;

    public String getExternalPath(){

        String sdPath ="";
        String ext = Environment.getExternalStorageState();
        if(ext.equals(Environment.MEDIA_MOUNTED)){
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        }else{
            sdPath  = getFilesDir() +"";

        }
        return sdPath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.txtData);
        write_sd_btn = findViewById(R.id.write_sd_file);
        clear_screen_btn = findViewById(R.id.clear_screen);
        read_sd_btn = findViewById(R.id.read_sd_file);
        finish_app_btn = findViewById(R.id.finish_app);

        // write sd 버튼 리스너
        write_sd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String path = getExternalPath();
                    String filename = "text1.txt";

                    BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename, false));
                    bw.write(editText1.getText().toString());
                    bw.close();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Toast.makeText(getApplicationContext(), "Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //read sd 버튼 리스나
        read_sd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String path = getExternalPath();
                    BufferedReader br = new BufferedReader(new FileReader(path+"text1.txt"));
                    String readStr = "";
                    String str = null;

                    while(((str = br.readLine()) != null)){
                        readStr += str +"\n";
                    }

                    editText1.setText(readStr);
                    br.close();

                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Toast.makeText(getApplicationContext(), "Done reading SD 'mysdfile.txt'",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        //clear screen 리스너
        clear_screen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.getText().clear();
            }
        });

        //finish app 리스너
        finish_app_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
