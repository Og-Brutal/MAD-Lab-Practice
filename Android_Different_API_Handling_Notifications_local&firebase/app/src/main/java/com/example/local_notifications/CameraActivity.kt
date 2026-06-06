package com.example.local_notifications

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CameraActivity : AppCompatActivity() {


    private val TAKE_CAMERA_PERMISSION=101
    private val TAKE_PICTURE_REQUEST=100
    private lateinit var  imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_camera)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageView=findViewById<ImageView>(R.id.photoImageView)


        val captureImage=findViewById<Button>(R.id.takePhotoButton)


        captureImage.setOnClickListener {
            if(hasCameraPermission()){
                takePhoto()
            }
            else{
                reuestCameraPermission()
            }
        }

    }

    private fun reuestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            TAKE_CAMERA_PERMISSION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==TAKE_CAMERA_PERMISSION){
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                takePhoto()
            }
            else{
                Toast.makeText(this,"Permission Denied !",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )== PackageManager.PERMISSION_GRANTED
    }

    private fun takePhoto() {
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager)!=null){
            startActivityForResult(intent,TAKE_PICTURE_REQUEST)
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==TAKE_PICTURE_REQUEST && resultCode==RESULT_OK){
            val photo=data?.extras?.getString("data") as? Bitmap

            if(photo!=null) {
                imageView.setImageBitmap(photo)
            }
            else{

            }
        }
    }
}