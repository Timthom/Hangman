package com.example.iths.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Play extends AppCompatActivity {

    private Random randomGenerator = new Random();
    private final String[] easylist = {"cat", "sun", "cup","ghost","pie","cow","banana",
            "bug","book","jar",
            "snake","light","tree",
            "lips","slide",
            "socks","swing",
            "coat","shoe","water",
            "heart","hat","ocean",
            "kite","dog","mouth",
            "milk","duck","eyes", "bird","boy",
            "apple","person","girl",
            "mouse","ball","house",
            "star","nose","bed",
            "whale","jacket","shirt",
            "beach","egg",
            "face","cookie","cheese",
            "dance", "skip", "jumping", "jack",
            "shark", "chicken", "alligator",
            "chair", "robot", "head",
            "smile", "baseball", "bird",
             "scissors", "cheek",
            "back", "jump", "drink",
            "ice", "cream", "cone", "car", "airplane",
            "clap", "circle", "pillow",
            "pinch", "kick", "lizard",
            "basketball", "sleep", "camera",
            "prayer", "elephant", "blink",
            "doll", "spider", "point",
            "kite", "homework", "ladybug",
            "bed", "bird", "gum",
            "book", "dress", "queen",
            "puppy", "happy", "doctor"
    };


    private final String[] hardlist = {
            "vision","loiterer" ,"observatory",
            "century", "kilogram", "rotten",
            "neutron", "stowaway", "psychologist",
            "exponential", "aristocrat" ,"eureka",
            "parody" ,"cartography",
            "philosopher" ,"tinting", "overture",
            "opaque", "ironic", "blacksmith",
            "zero", "landfill", "implode",
            "armada" ,"crisp",
            "stockholder", "inquisition", "mooch",
            "gallop", "archaeologist" ,"blacksmith",
            "addendum", "upgrade",
            "acre", "twang", "mine",
            "protestant", "brunette", "stout",
            "quarantine", "tutor", "positive",
            "champion", "pastry", "tournament",
            "rainwater", "random",
            "lyrics", "clue", "meadow",
            "slump", "ligament", "mellow",
            "siesta", "pomp", "hijacker",
            "mine", "shaft", "dismantle", "weed", "killer",
            "unemployed", "portfolio",
            "pomp", "evolution", "apathy",
            "advertise", "roundabout", "sandbox",
            "conversation", "negotiate",
            "silhouette", "aisle", "pendulum",
            "retaliate", "mascot",
            "shipwreck", "comfort", "zone",
            "alphabetize", "application", "college",
            "lifestyle", "invitation",
            "applesauce", "crumb", "loyalty",
            "corduroy",  "shrink", "ray"};


    private final ArrayList<String> easyWords = new ArrayList<String>(Arrays.asList(easylist));
    private final ArrayList<String> hardWords = new ArrayList<String>(Arrays.asList(hardlist));
    private int curlevel;
    private int curMan;
    private ArrayList<Boolean> curAnswer;
    private String key;



    private void inputLetter(char c){
        boolean isContain = false;
        for(int i =0; i < key.length();++i){
            final char ans = key.charAt(i);
            if(c == ans){
                isContain = true;
                curAnswer.set(i, true);
            }
        }
        if(curMan > 0 &&isContain){
            curMan--;
        }
        disableLetter(c);
    }

    private void disableLetter(char c) {
        char C = Character.toUpperCase(c);
        String buttonID = "button" + C;
        int resID = getResources().getIdentifier(buttonID, "id", "com.example.iths.hangman");
        Button b = (Button) findViewById(resID);
        b.setEnabled(false);

    }

    private String getCurAnser() {
        String result = new String();
        for(int i=0;i<curAnswer.size();++i){
            if(curAnswer.get(i)){
                result += (key.charAt(i)+" ");
            }
            else{
                result += "_ ";
            }
        }
        Log.d("test", result);

        return result;
    }

    private void selectKey() {
        int numOfBlanks = curlevel + 3;
        switch (curlevel)
        {
            case 0:
                key = easyWords.get(randomGenerator.nextInt(easyWords.size()));
                break;
            case 1:
                key = easyWords.get(randomGenerator.nextInt(easyWords.size()));
                break;
            case 2:
                key = hardWords.get(randomGenerator.nextInt(hardWords.size()));
                break;
        }

        Log.d("test", key);

        curAnswer = new ArrayList<Boolean>();
        for (int i = 0; i < key.length(); i++) {
            curAnswer.add(false);
        }
        HashSet<Character> letterSet = new HashSet<Character>();
        for(int i=0;i<key.length();++i){
            letterSet.add(key.charAt(i));
        }

        int numOfLetters = letterSet.size();
        int numOfShow = 0;
        if(numOfLetters > numOfBlanks){
            curMan = 0;
            numOfShow = numOfLetters - numOfBlanks;
        }
        else if(numOfLetters < numOfBlanks){
            curMan = numOfBlanks - numOfLetters ;
            numOfShow = 0;
        }


        Log.d("test", "curMan" + curMan);

        Log.d("test", "numOfShow " + numOfShow);

        for(int i=0;i<numOfShow;++i){
            int itemIndex = randomGenerator.nextInt(letterSet.size());
            int j = 0;
            for(Character c : letterSet)
            {
                if (j == itemIndex){
                    inputLetter(c);
                    letterSet.remove(c);
                    break;
                }
                ++j;
            }
        }
    }


    private void checkResult(){
        boolean isComplete = true;
        for(boolean b:curAnswer){
            if(!b){
                isComplete = false;
                break;
            }
        }

        ImageView imageHanging = (ImageView)findViewById(R.id.imageHanging);
        TextView textFill = (TextView)findViewById(R.id.textFill);

        if(isComplete){
            imageHanging.setImageResource(R.drawable.winner);
            for(int i=0;i<26;i++){
                char c = (char) ('a' + i);
                disableLetter(c);
            }
            textFill.setText(getCurAnser());
            return;
        }

        //not complete
        if(curMan < 10){
            textFill.setText(getCurAnser());
        }
        switch (curMan)
        {
            case 0:
                imageHanging.setImageResource(R.drawable.hang10);
                break;
            case 1:
                imageHanging.setImageResource(R.drawable.hang9);
                break;
            case 2:
                imageHanging.setImageResource(R.drawable.hang8);
                break;
            case 3:
                imageHanging.setImageResource(R.drawable.hang7);
                break;
            case 4:
                imageHanging.setImageResource(R.drawable.hang6);
                break;
            case 5:
                imageHanging.setImageResource(R.drawable.hang5);
                break;
            case 6:
                imageHanging.setImageResource(R.drawable.hang4);
                break;
            case 7:
                imageHanging.setImageResource(R.drawable.hang3);
                break;
            case 8:
                imageHanging.setImageResource(R.drawable.hang2);

                break;
            case 9:
                imageHanging.setImageResource(R.drawable.hang1);
                break;
            case 10:
                imageHanging.setImageResource(R.drawable.die);

                for(int i=0;i<26;i++){
                    char c = (char) ('a' + i);
                       disableLetter(c);
                } //game over
                String rightAnswer = new String("");
                for(int i=0;i<curAnswer.size();++i){
                    rightAnswer += key.charAt(i)+" ";
                }
                SpannableString text = new SpannableString(rightAnswer);

                for(int i=0;i<curAnswer.size();++i){
                    if(!curAnswer.get(i)){
                       text.setSpan(new android.text.style.ForegroundColorSpan(android.graphics.Color.GRAY), 2*i, 2*i+1, android.text.Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    }
                }
                textFill.setText(text, BufferType.SPANNABLE);

                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconhangman);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        curlevel = intent.getIntExtra("level",0);
        setContentView(R.layout.activity_play);


        TextView textFill = (TextView)findViewById(R.id.textFill);

        selectKey();


        textFill.setText(getCurAnser());

        checkResult();
    }


    //Guessed letter
    public void clickLetter(View view) {
        curMan++;
        switch (view.getId())
        {
            case R.id.buttonA:  inputLetter('a');
                break;
            case R.id.buttonB:  inputLetter('b');
                break;
            case R.id.buttonC:  inputLetter('c');
                break;
            case R.id.buttonD:  inputLetter('d');
                break;
            case R.id.buttonE:  inputLetter('e');
                break;
            case R.id.buttonF:  inputLetter('f');
                break;
            case R.id.buttonG:  inputLetter('g');
                break;
            case R.id.buttonH:  inputLetter('h');
                break;
            case R.id.buttonI:  inputLetter('i');
                break;
            case R.id.buttonJ:  inputLetter('j');
                break;
            case R.id.buttonK:  inputLetter('k');
                break;
            case R.id.buttonL:  inputLetter('l');
                break;
            case R.id.buttonM:  inputLetter('m');
                break;
            case R.id.buttonN:  inputLetter('n');
                break;
            case R.id.buttonO:  inputLetter('o');
                break;
            case R.id.buttonP:  inputLetter('p');
                break;
            case R.id.buttonQ:  inputLetter('q');
                break;
            case R.id.buttonR:  inputLetter('r');
                break;
            case R.id.buttonS:  inputLetter('s');
                break;
            case R.id.buttonT:  inputLetter('t');
                break;
            case R.id.buttonU:  inputLetter('u');
                break;
            case R.id.buttonV:  inputLetter('v');
                break;
            case R.id.buttonW:  inputLetter('w');
                break;
            case R.id.buttonX:  inputLetter('x');
                break;
            case R.id.buttonY:  inputLetter('y');
                break;
            case R.id.buttonZ:  inputLetter('z');
                break;
        }

        checkResult();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {
            case R.id.action_play:
                // Play action
                Intent i = new Intent(Play.this, LevelSelect.class);
                startActivity(i);
                return true;
            case R.id.info:
                // My presentation activity
                Intent j = new Intent(Play.this, Info.class);
                startActivity(j);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void nextOne(View view) {
        Intent intent = new Intent(this, Play.class);
        intent.putExtra("level", curlevel);
        startActivity(intent);
    }
}
