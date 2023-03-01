package com.example.phungviethung_t2011e_dawd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phungviethung_t2011e_dawd.adepter.EmployeeAdapter;
import com.example.phungviethung_t2011e_dawd.database.AppDatabase;
import com.example.phungviethung_t2011e_dawd.database.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edName, edDes, edSalary;
    Button btAdd, btUp, btDele;
    AppDatabase db;

    RecyclerView rvEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getAppDatabase(this);

        edName = findViewById(R.id.edName);
        edDes = findViewById(R.id.edDes);
        edSalary = findViewById(R.id.edSalary);
        btAdd = findViewById(R.id.btAdd);
        btUp= findViewById(R.id.btUp);
        btDele = findViewById(R.id.btDele);
        btAdd.setOnClickListener(this);
        btUp.setOnClickListener(this);
        btDele.setOnClickListener(this);

        List<Employee> list = db.employeeDao().getAllEmployee();

        EmployeeAdapter adapter = new EmployeeAdapter(this, list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rvEmployee = findViewById(R.id.rvEmployee);
        rvEmployee.setLayoutManager(layoutManager);
        rvEmployee.setAdapter(adapter);
    }

    private void onAddEmployee(){
        Employee employee = new Employee();
        employee.nameEmployee = edName.getText().toString();
        employee.des= edDes.getText().toString();
        employee.salary = edSalary.getText().toString();
        long id = db.employeeDao().insertEmployee(employee);
        if (id > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAdd:
                onAddEmployee();
                break;
            default:
                break;
        }
    }
}