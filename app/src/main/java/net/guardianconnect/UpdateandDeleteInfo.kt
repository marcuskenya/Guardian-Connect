package net.guardianconnect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import net.guardianconnect.navigation.ROUTE_VIEW

class UpdateDetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            firebaseUI(
                LocalContext.current,
                intent.getStringExtra("incident type"),
                intent.getStringExtra("location"),
                intent.getStringExtra("description"),

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun firebaseUI(context: Context,incidenttype: String?, location: String?, description: String?) {

    val incidenttype = remember { mutableStateOf(incidenttype) }
    val location = remember { mutableStateOf(location) }
    val description = remember { mutableStateOf(description) }

    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(Color.White),
    ) {



        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = incidenttype.value.toString(),
                onValueChange = { incidenttype.value = it },
                placeholder = { Text(text = " incident type") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = location.value.toString(),
                onValueChange = { location.value = it },
                placeholder = { Text(text = "location") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = description.value.toString(),
                onValueChange = { description.value = it },
                placeholder = { Text(text = "Enter your course duration") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(10.dp))



            Button(
                onClick = {
                    if (TextUtils.isEmpty(incidenttype.value.toString())) {
                        Toast.makeText(context, "Please enter incident type", Toast.LENGTH_SHORT)
                            .show()
                    } else if (TextUtils.isEmpty(location.value.toString())) {
                        Toast.makeText(
                            context,
                            "Please enter the location",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (TextUtils.isEmpty(description.value.toString())) {
                        Toast.makeText(
                            context,
                            "Please enter the description",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        updateDataToFirebase(
                            incidenttype.value,
                            location.value,
                            description.value,
                            context
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text(text = "Update Data", modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { deleteDataFromFirebase(incidenttype,context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text(text = "Delete Report", modifier = Modifier.padding(8.dp))
            }
        }

    }
}

private fun deleteDataFromFirebase(incidenttype:MutableState<String?> , context: Context) {
    val db = FirebaseFirestore.getInstance();
    db.collection("Courses").document(incidenttype.toString()).delete().addOnSuccessListener {
        Toast.makeText(context, "Report Deleted successfully..", Toast.LENGTH_SHORT).show()
        context.startActivity(Intent(context, ROUTE_VIEW::class.java))
    }.addOnFailureListener {
        Toast.makeText(context, "Fail to delete report..", Toast.LENGTH_SHORT).show()
    }

}


private fun updateDataToFirebase(
    incidenttype: String?,
    location: String?,
    description: String?,
    context: Context
) {
    val updatedReport = AddDatas(incidenttype, location, description)
    val db = FirebaseFirestore.getInstance();
    db.collection("Report").document(incidenttype.toString()).set(updatedReport)
        .addOnSuccessListener {
            Toast.makeText(context, "Report Updated successfully..", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context,ROUTE_VIEW::class.java))

        }.addOnFailureListener {
            Toast.makeText(context, "Fail to update report : " + it.message, Toast.LENGTH_SHORT)
                .show()
        }
}