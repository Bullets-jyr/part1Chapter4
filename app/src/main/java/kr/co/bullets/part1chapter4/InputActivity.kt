package kr.co.bullets.part1chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.bullets.part1chapter4.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}