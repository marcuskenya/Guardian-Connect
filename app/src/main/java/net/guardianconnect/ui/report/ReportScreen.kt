package net.guardianconnect.ui.report

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.guardianconnect.AddData
import net.guardianconnect.navigation.ROUTE_HOME
import net.guardianconnect.ui.auth.AuthViewModel
import java.io.ByteArrayOutputStream


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(viewModel: AuthViewModel?, navController: NavController) {

    Surface(

        modifier = Modifier.fillMaxSize(),
    ) {

        Scaffold(

            topBar = {

                SmallTopAppBar(

                    title = {

                        Text(

                            text = "REPORT FORM",

                            modifier = Modifier.fillMaxWidth(),

                            textAlign = TextAlign.Center,

                            color = Color.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(ROUTE_HOME)
                        }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },


                    )
            }) {
Spacer(modifier = Modifier.height(15.dp))
    reportUI(LocalContext.current)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun reportUI(context: Context) {


    val incidenttype = remember {
        mutableStateOf("")
    }

    val location = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }


    Column(

        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White),

        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextField(

            value = incidenttype.value,


            onValueChange = { incidenttype.value = it },


            placeholder = { Text(text = "Enter the incident type") },


            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),


            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),


            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(

            value = location.value,


            onValueChange = { location.value = it },


            placeholder = { Text(text = "Enter the location") },


            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),


            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),


            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(

            value = description.value,


            onValueChange = { description.value = it },


            placeholder = { Text(text = "Enter the description") },


            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),


            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),



            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))


        Button(
            onClick = {

                if (TextUtils.isEmpty(incidenttype.value.toString())) {
                    Toast.makeText(context, "Please enter incident type", Toast.LENGTH_SHORT).show()
                } else if (TextUtils.isEmpty(location.value.toString())) {
                    Toast.makeText(context, "Please enter location", Toast.LENGTH_SHORT)
                        .show()
                } else if (TextUtils.isEmpty(description.value.toString())) {
                    Toast.makeText(context, "Please enter description", Toast.LENGTH_SHORT)
                        .show()

                } else {

                    addDataToFirebase(
                        incidenttype.value,
                        location.value,
                        description.value,context
                    )
                }
            },


            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(text = "SUBMIT", modifier = Modifier.padding(8.dp),color = Color.Black)
        }


    }






}



fun addDataToFirebase(
    incidenttype: String,
    location: String,
    description: String,
    context: Context
) {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val dbCourses: CollectionReference = db.collection("Report")

    val courses = AddData(incidenttype, location, description)


    dbCourses.add(courses).addOnSuccessListener {

        Toast.makeText(
            context,
            "Your report has been submitted successfully.",
            Toast.LENGTH_SHORT
        ).show()

    }.addOnFailureListener { e ->

        Toast.makeText(context, "Fail to  report \n$e", Toast.LENGTH_SHORT).show()
    }

}