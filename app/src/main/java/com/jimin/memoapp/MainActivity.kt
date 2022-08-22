package com.jimin.memoapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimin.memoapp.databinding.ActivityMainBinding
import com.jimin.memoapp.databinding.ActivityWriteBinding
import javax.net.ssl.SSLSessionBindingEvent

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var getResultText : ActivityResultLauncher<Intent>
    private lateinit var data : String

    private val itemList : ArrayList<Item> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        itemList.apply {
//            add(Item("tsdjkfj"))
//            add(Item("tsdjkfj"))
//            add(Item("tsdjkfj"))
//            add(Item("tsdjkfj"))
//            add(Item("tsdjkfj"))
//            add(Item("tsdjkfj"))
//            add(Item("tsdjkfj"))
//
//
//        }
        //val text = intent.extras!!.getString("data")





    }

    override fun onStart() {
        super.onStart()
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("data")
                Log.d(TAG, "onCreate : good to tp : $mString")
                itemList.apply {
                    add(Item(mString!!))
                }

            }



        }

        binding.mainMemoAddTv.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            getResultText.launch(intent)


        }


        val listRVAdapter = ListRVAdpater(itemList)
        binding.mainRvlist.adapter = listRVAdapter
        binding.mainRvlist.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
    }
}