package sai.clickcounter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

public class MainActivity extends Activity implements OnClickListener
{

    Button btn1;
    Button btn2;
    Button btn3;
    TextView textTitle;
    EditText countText;
    View btext;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.addButton);
        btn2 = (Button)findViewById(R.id.resetButton);
        btn3 = (Button)findViewById(R.id.loadbutton);
        countText = (EditText)findViewById(R.id.EditText);
        textTitle = (TextView)findViewById(R.id.myTextTitle);
        btext = findViewById(R.id.myBg);

        //---set on click listeners on the buttons-----
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        // change font size of the text
        textTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);


    }

    @Override
    public void onClick(View v)
    {
        if (v == btn1)
        {
            counter++;
            countText.setText(Integer.toString(counter));
            countText.setBackgroundColor(Color.CYAN);
            btext.setBackgroundColor(Color.rgb(counter*10,255-counter*10,counter*15+counter));

            SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("count",counter);
            editor.commit();
        }
        if (v == btn2)
        {
            counter = 0;
            countText.setText(Integer.toString(counter));
            countText.setBackgroundColor(Color.RED);
            btext.setBackgroundColor(Color.GRAY);
            SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("count",counter);
            editor.commit();
        }
        if(v==btn3)
        {

            SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
            int number= sharedPreferences.getInt("count",-1);
            counter=number;
            countText.setText(Integer.toString(counter));
            btext.setBackgroundColor(Color.CYAN);

        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter=savedInstanceState.getInt("counter");
    }

}