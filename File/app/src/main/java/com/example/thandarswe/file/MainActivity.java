package com.example.thandarswe.file;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
    private  final static String FILE_NAME="note.txt";
    private EditText txtMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsg= (EditText) findViewById(R.id.txtMsg);
        Button btnFinish= (Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            InputStream inputStream= openFileInput(FILE_NAME);
            if (inputStream!=null){
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader reader=new BufferedReader(inputStreamReader);
                String str="READING FROM EXISTING DISK\n";
                StringBuffer stringBuffer=new StringBuffer();
                while ((str=reader.readLine())!=null){
                    stringBuffer.append(str+"\n");
                }
                inputStream.close();
                txtMsg.setText(stringBuffer.toString());

            }

        }catch (java.io.FileNotFoundException e){

        }catch (Throwable t){
            Toast.makeText(this, "Exception" + txtMsg.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            OutputStreamWriter out=new OutputStreamWriter(openFileOutput(FILE_NAME,0));
            }
        catch (Throwable t){
            txtMsg.setText(t.getMessage());

        };
    }
    private void deleteFile(){
        String path="/data/data/cis470.matosfilewriteread/files/"+FILE_NAME;
        File f1=new File(path);
        Toast.makeText(getApplication(),"Exist"+f1.exists(),Toast.LENGTH_SHORT).show();
        boolean success=f1.delete();
        if (!success){
            Toast.makeText(getApplication(),"delete fail",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplication(),"ok file delete",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
