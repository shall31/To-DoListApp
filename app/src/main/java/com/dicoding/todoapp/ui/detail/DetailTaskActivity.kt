package com.dicoding.todoapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.ui.list.TaskActivity
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailEdTitle: TextInputEditText
    private lateinit var detailEdDesc: TextInputEditText
    private lateinit var detailEdDueDate: TextInputEditText
    private lateinit var btnDeleteTask: Button
    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action

        detailEdTitle = findViewById(R.id.detail_ed_title)
        detailEdDesc = findViewById(R.id.detail_ed_description)
        detailEdDueDate = findViewById(R.id.detail_ed_due_date)
        btnDeleteTask = findViewById(R.id.btn_delete_task)

        detailTaskViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailTaskViewModel::class.java]

        detailTaskViewModel.setTaskId(intent.getIntExtra(TASK_ID, 0))
        detailTaskViewModel.task.observe(this) {
            if (it != null) {
                detailEdTitle.setText(it.title)
                detailEdDesc.setText(it.description)
                detailEdDueDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
            }
        }

        btnDeleteTask.setOnClickListener {
            detailTaskViewModel.deleteTask()
            startActivity(Intent(this@DetailTaskActivity, TaskActivity::class.java))
            finish()
        }


    }
}