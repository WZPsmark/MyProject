package app.zhujie;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import app.zhujie.dataBind.Employee;
import app.zhujie.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
//    @ViewInject(R.id.text)
//    private TextView text;
//    @ViewInject(R.id.btn)
//    private Button btn;

      Employee employee = new Employee("Zhai", "Mark");
      ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setEmployee(employee);
        binding.setPresenter(new Presenter());
        binding.viewStub.getViewStub().inflate();
//        AnnotateUtils.injectViews(this);
    }


    public  class Presenter{

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            employee.setFirstName(s.toString());
            employee.setFired(!employee.isFired.get());
//            binding.setEmployee(employee);
        }

        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "点到了", Toast.LENGTH_SHORT).show();
        }

        public void onClickListenerBinding(Employee employee) {
            Toast.makeText(MainActivity.this, employee.getLastName(),
                    Toast.LENGTH_SHORT).show();
        }

    }


}
