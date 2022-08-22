package com.jimin.memoapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.jimin.memoapp.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWriteBinding
    var data : String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.writeSubmitBtn.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("data", binding.writeContentEt.text.toString())
//
//            startActivity(intent)
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("data",binding.writeContentEt.text.toString())
            }
            setResult(RESULT_OK,intent)
            if(!isFinishing) finish()
        }


    }



    override fun onPause() { //현재까지 작성한 내용 Activity의 전역변수에 담아두기
        super.onPause()
        data = binding.writeContentEt.text.toString()
        Log.d("onPause", data.toString())

    }

    override fun onResume() { //전역변수 내용으로 edittext내용으로 설정하기
        super.onResume()

        Log.d("onResume", data.toString())
        if (data != null) {
            binding.writeContentEt.setText(data)
        }

    }

    override fun onRestart() { //dialog를 활용하여 다시 작성할거냐고 묻는 창 띄우기
        super.onRestart()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("다시 작성하시겠습니까?")
            .setMessage("")
            .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, id ->

                })
            .setNegativeButton("취소",
                DialogInterface.OnClickListener { dialog, id ->
                    data = null
                    Log.d("onRestart-cancle", data.toString())

                })
        // 다이얼로그를 띄워주기
        builder.show()
        Log.d("onRestart", data.toString())
    }
}