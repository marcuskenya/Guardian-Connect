package net.guardianconnect.ui.viewdata

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

import net.guardianconnect.AddDatas
import net.guardianconnect.navigation.ROUTE_HOME
import net.guardianconnect.navigation.ROUTE_VIEW
import net.guardianconnect.ui.auth.AuthViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen(viewModel: AuthViewModel?, navController: NavHostController) {

    Surface(

        modifier = Modifier.fillMaxSize(),
    ) {

        Scaffold(

            topBar = {

                SmallTopAppBar(


                    title = {

                        Text(

                            text = "LATEST REPORTS!!",

                            modifier = Modifier.fillMaxWidth(),

                            textAlign = TextAlign.Center,

                            color = Color.Black
                        )

                    },
                    navigationIcon = {
                        IconButton(onClick = {navController.navigate(ROUTE_HOME)}) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },
                    )
            }) {




            var reportList = mutableStateListOf<AddDatas?>()


            val db: FirebaseFirestore = FirebaseFirestore.getInstance()


            db.collection("Report").get()


                .addOnSuccessListener { queryDocumentsnapshot ->

                    if (!queryDocumentsnapshot.isEmpty) {

                        var list = queryDocumentsnapshot.documents

                        for (d in list) {

                            var c: AddDatas? = d.toObject(AddDatas::class.java)
                            if (c != null) {
                                reportList.add(c)
                            }
                        }

                    } else {
//                Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {

//            Toast.makeText(this@View, "An error occurred", Toast.LENGTH_SHORT).show()
                }



            viewUI(LocalContext.current, reportList)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun viewUI(context: Context, reportList: SnapshotStateList<AddDatas?>) {


    Column(

        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn {

            itemsIndexed(reportList) { index, item ->

                Card(
                    onClick = {

                        Toast.makeText(
                            context,
                            reportList[index]?.incidenttype + " selected..",
                            Toast.LENGTH_SHORT
                        ).show()
                    },


                    modifier = Modifier.padding(8.dp),



                ) {

                    Column(

                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {

                        Spacer(modifier = Modifier.width(5.dp))

                        reportList[index]?.incidenttype?.let {
                            Text(

                                text = it,


                                modifier = Modifier.padding(4.dp),



                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 20.sp, fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))


                        reportList[index]?.location?.let {
                            Text(

                                text = it,


                                modifier = Modifier.padding(4.dp),


                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 15.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))


                        reportList[index]?.description?.let {
                            Text(

                                text = it,


                                modifier = Modifier.padding(4.dp),

                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 15.sp)
                            )
                        }





                    }
                }
            }

        }
    }

}