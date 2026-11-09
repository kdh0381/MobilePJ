package com.appweek10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appweek10.data.Student
import com.appweek10.databinding.ItemStudentBinding

class StudentAdapter(
    private val studentList: List<Student>,
    private val onItemClick: (Student, Int) -> Unit,
    private val onItemLongClick: (Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(
        private val binding: ItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.apply {
                textViewName.text = student.name
                textViewDept.text = student.department
                textViewGrade.text = student.grade
                textViewEmail.text = student.email

                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClick(studentList[position], position)
                    }
                }

                root.setOnLongClickListener {
                    val position = bindingAdapterPosition
                    if (position == RecyclerView.NO_POSITION) {
                        false
                    } else {
                        onItemLongClick(position)
                        true
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position])
    }

    override fun getItemCount(): Int = studentList.size
}
