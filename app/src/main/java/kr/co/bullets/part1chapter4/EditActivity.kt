package kr.co.bullets.part1chapter4

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import kr.co.bullets.part1chapter4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val message = intent.getStringExtra("intentMessage") ?: "없음"
//        Log.d("intentMessage", message)

        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        binding.birthDateLayer.setOnClickListener {
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthDateValueTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }

        binding.warningCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.warningEditText.isVisible = isChecked
        }

        binding.warningEditText.isVisible = binding.warningCheckBox.isChecked

        binding.saveButton.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
        /*
        val editor = getSharedPreferences("UserInformation", Context.MODE_PRIVATE).edit()
        editor.putString("name", binding.nameEditText.text.toString())
        editor.putString("birthDate", binding.birthDateTextView.text.toString())
        editor.putString("bloodType", binding.nameEditText.text.toString())
        editor.putString("emergencyContact", binding.emergencyContactEditView.text.toString())
        editor.putString("warning", binding.nameEditText.text.toString())
        // apply() : 비동기 | commit() : 동기
        editor.apply()
        */

        // 별도의 return값이 필요가 없는 경우
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.nameEditText.text.toString())
            putString(BIRTH_DATE, binding.birthDateTextView.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(EMERGENCY_CONTACT, binding.emergencyContactEditView.text.toString())
            putString(WARNING, getWarning())
            apply()
        }

        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String {
        val bloodSigned = if (binding.bloodTypePlus.isChecked) "+" else "-"
        val bloodAlphabet = binding.bloodTypeSpinner.selectedItem.toString()
        return "$bloodSigned$bloodAlphabet"
    }

    private fun getWarning(): String {
        return if (binding.warningCheckBox.isChecked) {
            binding.warningEditText.text.toString()
        } else {
            ""
        }
    }
}