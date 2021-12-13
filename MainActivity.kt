package com.cookandroid.lotto

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    val BallList = ArrayList<Bitmap>()
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }


        mediaPlayer = MediaPlayer.create(this,R.raw.christmas)
        mediaPlayer?.start()

        playButton.setOnClickListener{
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this,R.raw.christmas)
                mediaPlayer?.start()
            }
        }

        stopButton.setOnClickListener{
                mediaPlayer?.stop()
                mediaPlayer = null
            }



        Toast.makeText(this@MainActivity, "Create lottovall image.", Toast.LENGTH_SHORT).show()
        for (i in 0..44) {
            var bmp: Int =
                getResources().getIdentifier("lottoball" + (i + 1), "drawable", packageName)
            var bitmap: Bitmap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(getResources(), bmp),
                70, 70, false
            )
            BallList.add(bitmap)
        }


        val btn: Button = findViewById<Button>(R.id.btStart)

        btn.setOnClickListener {
            Toast.makeText(this@MainActivity, "button start.", Toast.LENGTH_SHORT).show()

            var set: TreeSet<Int> = TreeSet()

            while (set.size < 6) {
                val random = Random()
                val num = random.nextInt(45)
                set.add(num)
            }

            Toast.makeText(this@MainActivity, "ball check.", Toast.LENGTH_SHORT).show()

            var nCount = 0
            for (i in set) {
                var tmpID: Int = getResources().getIdentifier(
                    "ballView" + (nCount + 1),
                    "id", packageName
                )
                val imgView = findViewById<ImageView>(tmpID)
                imgView.setImageBitmap(BallList.get(i))
                nCount++
            }

            Toast.makeText(this@MainActivity, "button end.", Toast.LENGTH_SHORT).show()



        }

    }
    override fun onStop(){
        super.onStop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.information, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.information -> {
                Toast.makeText(this, "소프트웨어학과 2017848014 김민재", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
    fun startBarcodeReader(view:View){
        IntentIntegrator(this).initiateScan()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result!=null){
         if(result.contents!=null){
             Toast.makeText(this,"scanned: ${result.contents} format: ${result.formatName}",Toast.LENGTH_LONG).show();
         }   else{
             Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show()
         }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
