package com.spottechnicians.caudit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spottechnicians.caudit.models.Visit;

import java.util.ArrayList;
import java.util.List;

public class CT_Questions extends AppCompatActivity {

    Visit visit;

    String ansewers[]={"1","2","3","4","5","6","7","8","9","10","11","12"};


    String otherText[]={"","","","","","","","","","","",""};
    int currentButtonPressed;
    int buttonType;
    String questionEnglishArray[];
    String questionHindiArray[];
    int textViewIds[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct__questions);
        visit=new Visit();
        questionEnglishArray=getResources().getStringArray(R.array.ct_question);
        textViewIds=getResourceQuestion();
        setEnglishQuestion(questionEnglishArray,textViewIds);
        assignPopupToNOButton();
        assignPopupToYesButton();

    }


    public void onNext(View view) {
        String result = "";
        for (int i = 0; i < ansewers.length; i++) {
            result = result + ansewers[i] + otherText[i] + "/";

        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        visit.setCt(ansewers);
        if (visit.checkCTComplete()) {
            Toast.makeText(this, "complete", Toast.LENGTH_LONG).show();
        } else {
             Toast.makeText(this,"Answer all the question",Toast.LENGTH_LONG).show();
        }
    }






    public int getCurrentButtonPressed() {
        return currentButtonPressed;
    }

    public void setCurrentButtonPressed(int currentButtonPressed) {
        this.currentButtonPressed = currentButtonPressed;
    }

    public int getButtonType() {
        return buttonType;
    }

    public void setButtonType(int buttonType) {
        this.buttonType = buttonType;
    }


    public void assignPopupToYesButton()
    {

        final int btnYesArray[]=getYesButtonIdsArray();
        int btnNoArray[]=getNoButtonIdsArray();
        for(int i=0;i<12;i++)
        {

            if(btnYesArray[i]==btnYesArray[1]||btnYesArray[i]==btnYesArray[5]||btnYesArray[i]==btnYesArray[11])
            {


                ((Button)findViewById(btnYesArray[i])).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setButtonType(1);
                        setCurrentButtonPressed(getPositionOfYesButton(v.getId()));
                        //Toast.makeText(getBaseContext(),getCurrentButtonPressed()+"", Toast.LENGTH_SHORT).show();
                        ((Button)findViewById((getNoButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.black));
                        ((Button)findViewById((getYesButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.red));

                        showPopup(getCurrentButtonPressed());
                    }
                });
            }
            else
            {

                ((Button)findViewById(btnYesArray[i])).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setButtonType(1);
                        setCurrentButtonPressed(getPositionOfYesButton(v.getId()));
                       // Toast.makeText(getBaseContext(), "This is normal button", Toast.LENGTH_SHORT).show();
                        ((Button)findViewById((getYesButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.green));
                        ((Button)findViewById((getNoButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.black));

                        ansewers[currentButtonPressed]="yes";
                        otherText[currentButtonPressed]="";
                        Toast.makeText(getBaseContext(),ansewers[currentButtonPressed]+"", Toast.LENGTH_SHORT).show();

                    }
                });
            }


        }

    }

    public int getPositionOfYesButton(long id)
    {;
        int array[];
        if(getButtonType()==1)
        {
            array=getYesButtonIdsArray();
        }
        else {
             array=getNoButtonIdsArray();
        }

        int position=0;
        for(int i=0;i<array.length;i++)
        {
            if(array[i]==id)
            {
                position=i;
                return position;
            }
        }
        return position;
    }



    public void assignPopupToNOButton()
    {
       // int btnYesArray[]=getYesButtonIdsArray();
        int btnNoArray[]=getNoButtonIdsArray();
        for(int i=0;i<12;i++)
        {
            setCurrentButtonPressed(i);
            if(btnNoArray[i]==btnNoArray[1]||btnNoArray[i]==btnNoArray[5]||btnNoArray[i]==btnNoArray[11])
            {

                ((Button)findViewById(btnNoArray[i])).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getBaseContext(), "This is normal button", Toast.LENGTH_SHORT).show();
                        setButtonType(0);
                        setCurrentButtonPressed(getPositionOfYesButton(v.getId()));
                        ((Button)findViewById((getYesButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.black));
                        ((Button)findViewById((getNoButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.green));

                        ansewers[currentButtonPressed]="no";
                        otherText[currentButtonPressed]="";
                        Toast.makeText(getBaseContext(),ansewers[currentButtonPressed]+"", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else
            {
                ((Button)findViewById(btnNoArray[i])).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setButtonType(0);
                        setCurrentButtonPressed(getPositionOfYesButton(v.getId()));
                            //showTextPopUp();
                        showPopup(getCurrentButtonPressed());
                        ((Button)v).setTextColor(getResources().getColor(R.color.red));
                        ((Button)findViewById((getYesButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.black));
                        ((Button)findViewById((getNoButtonIdsArray()[currentButtonPressed]))).setTextColor(getResources().getColor(R.color.red));


                    }
                });

            }


        }


    }

    public void showPopup(final int buttonPressed)
    {

        Dialog dialog;

         String array[];
        if(getButtonType()==1)
        {
            array=getYesSubQuestion();
        }
        else
        {
            array=getNoSubQuestion();
        }


        final String[] items=array[buttonPressed].split("-");
        final List<String> itemsSelected = new ArrayList();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Reasons");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
                        if (isSelected) {
                            // Toast.makeText(getBaseContext(),items[selectedItemId].toString()+"",Toast.LENGTH_SHORT).show();
                            itemsSelected.add(items[selectedItemId]);

                        } else if (itemsSelected.contains(items[selectedItemId])) {
                            itemsSelected.remove(items[selectedItemId]);
                        }


                    }
                })
                .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String oneAnswer;
                        if(getButtonType()==1)
                        {
                            oneAnswer="yes";
                        }
                        else
                        {
                            oneAnswer="no";
                        }


                        if(itemsSelected.contains("Other"))
                        {
                            //Toast.makeText(getBaseContext(),"other is selected", Toast.LENGTH_SHORT).show();
                            showTextPopUp();
                            for(int i=0;i<itemsSelected.size();i++)
                            {
                                oneAnswer=oneAnswer+","+itemsSelected.get(i);
                            }
                            ansewers[buttonPressed]=oneAnswer;
                            Toast.makeText(getBaseContext(),ansewers[buttonPressed]+"", Toast.LENGTH_SHORT).show();


                        }
                        else
                        {

                            for(int i=0;i<itemsSelected.size();i++)
                            {
                                oneAnswer=oneAnswer+","+itemsSelected.get(i);
                            }
                            ansewers[buttonPressed]=oneAnswer;
                            Toast.makeText(getBaseContext(),ansewers[buttonPressed]+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        dialog = builder.create();
        dialog.show();


    }

    int[] getResourceQuestion()
    {
        int array[]={
                    R.id.tvCTQuestion1,R.id.tvCTQuestion2,R.id.tvCTQuestion3,R.id.tvCTQuestion4,R.id.tvCTQuestion5,
                    R.id.tvCTQuestion6,R.id.tvCTQuestion7,R.id.tvCTQuestion8,R.id.tvCTQuestion9,
                    R.id.tvCTQuestion10,R.id.tvCTQuestion11,R.id.tvCTQuestion12
                     };
        return array;
    }

    int[] getYesButtonIdsArray()
    {
        int array[]={R.id.btnCTQuestionYes1,R.id.btnCTQuestionYes2,R.id.btnCTQuestionYes3,R.id.btnCTQuestionYes4,R.id.btnCTQuestionYes5,
                R.id.btnCTQuestionYes6,R.id.btnCTQuestionYes7,R.id.btnCTQuestionYes8,R.id.btnCTQuestionYes9,
                R.id.btnCTQuestionYes10,R.id.btnCTQuestionYes11,R.id.btnCTQuestionYes12};
        return array;
    }
    int[] getNoButtonIdsArray()
    {
        int array[]={R.id.btnCTQuestionNo1,R.id.btnCTQuestionNo2,R.id.btnCTQuestionNo3,R.id.btnCTQuestionNo4,R.id.btnCTQuestionNo5,
                R.id.btnCTQuestionNo6,R.id.btnCTQuestionNo7,R.id.btnCTQuestionNo8,R.id.btnCTQuestionNo9,R.id.btnCTQuestionNo10,
                R.id.btnCTQuestionNo11,R.id.btnCTQuestionNo12};
        return array;
    }

        public void showTextPopUp()
        {
            AlertDialog dialog2;
            final EditText editView=new EditText(this);
            AlertDialog.Builder builder2=new AlertDialog.Builder(this);
            builder2.setTitle("Enter the reason");
            builder2.setView(editView);
            builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    otherText[currentButtonPressed]=(editView.getText().toString());
                   // Toast.makeText(getBaseContext(),getOtherText(),Toast.LENGTH_SHORT).show();

                }
            });


            dialog2=builder2.create();
            dialog2.show();
        }

    public String[] getYesSubQuestion()
    {
        String Yes[]={"null", "ID expired-ID is valid","null","null","null",
                        "Reliever not come-CT on leave-Other","null","null","null","null","null",
                        "Power disconnection-Load shedding-Electrical fault-ATM is oout of service-Other"};

        return Yes;
    }
    public String[] getNoSubQuestion()
    {
        String No[]={"Went for nature call-went for lunch/dinner-medical emergency-Other","Not Provided-ID misplaced-Forgot to bring",
                "Not provided-Under washing-Torn-Other","Not provided-Technical issue-Other", "Damaged-Not provided-Other","null",
                "Attendence reg n/a-Break reg n/a-Vendor reg n/a-LogBook n/a-Other", "New Caretaker-Other","New Caretaker-Other",
                "New Caretaker-Other","Not provided by bank-Other","null"};
        return No;
    }



    public void setEnglishQuestion(String questionArray[],int textViewIds[])
    {

        for(int i=0;i<textViewIds.length;i++)
        {
            ((TextView)findViewById(textViewIds[i])).setText(questionArray[i]);
        }

    }
}
