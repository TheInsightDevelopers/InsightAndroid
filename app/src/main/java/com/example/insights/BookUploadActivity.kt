package com.example.insights

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class BookUploadActivity : AppCompatActivity() {


    val PDF: Int = 0
    val IMAGE: Int = 1
    lateinit var uriImage: Uri
    lateinit var uriPdf: Uri
    lateinit var mStorage: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_upload)
        findViewById<Button>(R.id.book_upload_btn).setOnClickListener {
            if (uriImage != null && uriPdf != null) {
                val progressBar = Dialog(this)
                progressBar.setContentView(R.layout.progress_bar)
                progressBar.show()

                val dRef: StorageReference = FirebaseStorage.getInstance().reference
                dRef.child(
                    "Book_Pdf" + System.currentTimeMillis()
                        .toString()
                ).putFile(uriImage).addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url1 ->
                        dRef.child("Book_Image" + System.currentTimeMillis().toString())
                            .putFile(uriPdf).addOnSuccessListener { taskSnapshot1 ->
                            taskSnapshot1.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url2 ->
                                val bookName = findViewById<EditText>(R.id.book_name_upload)
                                val userName = findViewById<EditText>(R.id.book_upload_user_name)
                                val comments = findViewById<EditText>((R.id.upload_book_comment))

                                val bookNameValue = bookName.text.toString()
                                val userNameValue = userName.text.toString()
                                val commentsValue = comments.text.toString()
                                val bookData = hashMapOf(
                                    "Book Name" to bookNameValue,
                                    "Uploader Name" to userNameValue,
                                    "Comments" to commentsValue,
                                    "ImageUrl" to url2.toString(),
                                    "PdfUrl" to url1.toString()
                                )
                                FirebaseFirestore.getInstance().collection("Books")
                                    .document(System.currentTimeMillis().toString()).set(bookData)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                this@BookUploadActivity,
                                                "Upload Success :)",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        }
                                        progressBar.hide()
                                    }
                            }
                        }
                    }

                    //creating document

                }
            }
        }
        mStorage =
            FirebaseStorage.getInstance().getReferenceFromUrl("gs://insight-d62ec.appspot.com/")

        val uploadBtn: Button = findViewById(R.id.select_book)
        uploadBtn.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent()
            intent.type = "application/pdf"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF)

        })
        val selectImageBtn: Button = findViewById(R.id.select_upload_book_image)
        selectImageBtn.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Image"), IMAGE)


        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PDF) {
                if (data != null) {
                    uriImage = data.data!!
                }

            }
            if (requestCode == IMAGE) {
                if (data != null) {
                    uriPdf = data.data!!
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}