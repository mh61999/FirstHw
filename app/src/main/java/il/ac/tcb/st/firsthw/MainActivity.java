package il.ac.tcb.st.firsthw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import il.ac.tcb.st.firsthw.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        binding.Calculate.setOnClickListener(CalculateButton());
        setSpinner();
    }

    private void setSpinner(){
        List<String> list = new ArrayList<String>();
        list.add("+");
        list.add("-");
        list.add("X");
        list.add("/");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
    }
    View.OnClickListener CalculateButton(){
        return view -> {
                String firstInput = binding.firstNumber.getText().toString();
                String secondInput = binding.secondNumber.getText().toString();
                double firstInputConvert=0;
                double secondInputConvert=0;
                try {
                    String key = binding.spinner.getSelectedItem().toString();
                    firstInputConvert = Double.parseDouble(firstInput);
                    secondInputConvert = Double.parseDouble(secondInput);
                    if(secondInputConvert == 0 && key.equals("/")){
                        throw new NullPointerException("second number is a 0");
                    }
                    switch (key){
                        case "+":
                            binding.Answer.setText(String.valueOf((firstInputConvert+secondInputConvert)));
                            break;
                        case "-":
                            binding.Answer.setText(String.valueOf((firstInputConvert-secondInputConvert)));
                            break;
                        case "/":
                            binding.Answer.setText(String.valueOf((firstInputConvert/secondInputConvert)));
                            break;
                        case "X":
                            binding.Answer.setText(String.valueOf((firstInputConvert*secondInputConvert)));
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception e){
                    if(binding.spinner.getSelectedItem().toString().equals("/"))
                        Toast.makeText(context,"cant divide by 0",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(context,"invalid input :)",Toast.LENGTH_LONG).show();
                    binding.Answer.setText(":)");
                }
        };
    }

}