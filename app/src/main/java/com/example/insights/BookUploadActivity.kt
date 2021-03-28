package com.example.insights

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class BookUploadActivity : AppCompatActivity() {
    val bookName : EditText=findViewById(R.id.book_name_upload)
    val userName : EditText=findViewById(R.id.book_upload_user_name)
    val comments : EditText=findViewById((R.id.upload_book_comment))

    val bookNameValue = bookName.text
    val userNameValue = userName.text
    val commentsValue = comments.text

    val PDF:Int=0
    val IMAGE:Int=1
    lateinit var uri: Uri
    lateinit var mStorage : StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_upload)
        mStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://insight-d62ec.appspot.com/")

        val uploadBtn : Button =findViewById(R.id.upload_book_btn)
        uploadBtn.setOnClickListener(View.OnClickListener {
                view: View? -> val intent = Intent()
            intent.type="application/pdf"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select Pdf"),PDF)
        })
        val selectImageBtn:Button=findViewById(R.id.select_upload_book_btn)
        selectImageBtn.setOnClickListener(View.OnClickListener {
            view:View?-> val intent =Intent()
            intent.type="image/*"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select Image"),IMAGE)

        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK){
            if(requestCode==PDF){
                if (data != null) {
                    uri= data.getData()!!
                }
                upload()
            }
            if(requestCode==IMAGE){
                if (data != null) {
                    uri= data.getData()!!
                }
                upload()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun upload(){
        var mReference = mStorage.child(uri.toString())
        try {
            if (mReference != null) {
                mReference.putFile(uri).addOnSuccessListener {


                    Toast.makeText(this, "Successfully Uploaded :)", Toast.LENGTH_LONG).show()
                }.addOnProgressListener {

                }
            }
        }catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }

    }


}