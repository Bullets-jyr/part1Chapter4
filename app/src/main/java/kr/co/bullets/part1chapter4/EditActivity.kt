package kr.co.bullets.part1chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.bullets.part1chapter4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("intentMessage") ?: "없음"
        Log.d("intentMessage", message)
    }
}