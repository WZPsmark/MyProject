package app.zhujie;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.zhujie.dataBind.Employee;
import app.zhujie.dataBind.EmployeeAdapter;
import app.zhujie.databinding.ActivityListBinding;

public class ListActivity extends AppCompatActivity {

    ActivityListBinding mBinding;
    EmployeeAdapter mEmployeeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        mBinding.setPresenter(new Presenter());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEmployeeAdapter = new EmployeeAdapter(this);
        mBinding.recyclerView.setAdapter(mEmployeeAdapter);
        mEmployeeAdapter.setListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onEmployeeClick(Employee employee) {
                Toast.makeText(ListActivity.this,
                        employee.getFirstName(), Toast.LENGTH_SHORT).show();
            }
        });
        List<Employee> demoList = new ArrayList<>();
        demoList.add(new Employee("Zhai", "Mark", false));
        demoList.add(new Employee("Zhai2", "Mark2", false));
        demoList.add(new Employee("Zhai3", "Mark3", true));
        demoList.add(new Employee("Zhai4", "Mark4", false));
        mEmployeeAdapter.addAll(demoList);
    }




    public class Presenter {
        public void onClickAddItem(View view) {
            mEmployeeAdapter.add(new Employee("haha", "1", false));
        }

        public void onClickRemoveItem(View view) {
            mEmployeeAdapter.remove();
        }
    }





}
