package com.example.examplefirestore01.testsone

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.examplefirestore01.setting.Settings
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch

@Composable
fun TestOne(
    showAd: () -> Unit,
    navController: NavHostController,
    //viewModel: MainViewModel = hiltViewModel()
) {
    // fetches data from firebase when querying
    var data: Data
    // DataStore Preference
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Settings(context)
    // Value to be sent to query firebase
    val userScore = dataStore.getScore.collectAsState(initial = 1)
    // Logica app, no change here
    var valida by remember { mutableStateOf(true) }
    var verifica by remember { mutableStateOf(false) }


    //Viewmodel Factory section
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).mainViewModelFactory()

    val viewModel: MainViewModel = viewModel(factory =
    MainViewModel.provideMainViewModelFactory(factory, userScore.value as Int
    ))
    //End of Viewmodel Factory Section

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        // Valor que consulta la data
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "The Value UserStore: ${userScore.value!!}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 25.sp,
        )

        viewModel.data.value.numero.toString().let {
            Text(modifier = Modifier.padding(vertical = 5.dp),
                text = "The number value is: $it",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 25.sp,
            )
        }
        //

        Spacer(modifier = Modifier.padding(vertical = 25.dp))

        if (valida) {
            Column(
                modifier = Modifier

                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(70.dp),
                    onClick = {
                        valida = false
                        verifica = viewModel.data.value.op1 == viewModel.data.value.correct
                    }) {
                    viewModel.data.value.op1?.let {
                        Text(it,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 25.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(70.dp),
                    onClick = {
                        valida = false
                        verifica = viewModel.data.value.op2 == viewModel.data.value.correct
                    }) {
                    viewModel.data.value.op2?.let {
                        Text(it,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 25.sp,
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(70.dp),
                    onClick = {
                        valida = false
                        verifica = viewModel.data.value.op3 == viewModel.data.value.correct
                    }) {
                    viewModel.data.value.op3?.let {
                        Text(it,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 25.sp,
                        )
                    }
                }

            }
        } else {
            if (verifica){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Correct",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 25.sp,
                    )

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    viewModel.data.value.correct?.let {
                        Text(it,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 25.sp,
                        )
                    }

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(70.dp),
                        onClick = {

                            if (userScore.value as Int >= 4) {
                                scope.launch {
                                    dataStore.deleteScore()
                                }
                            } else {
                                data = viewModel.data.value
                                // a este metodo intento pasar el UserScore, pero no se envia
                                viewModel.fetchDataVariant(userScore.value as Int)
                                scope.launch {
                                    dataStore.increaseScore(1)
                                }
                            }

                            valida = true
                        }) {
                        Text("Next",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 25.sp,
                        )
                    }
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Incorrect",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 25.sp,
                    )

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(70.dp),
                        onClick = {

                            if (userScore.value as Int >= 4) {
                                scope.launch {
                                    dataStore.deleteScore()
                                }
                            } else {
                                data = viewModel.data.value
                                // a este metodo intento pasar el UserScore, pero no se envia
                                viewModel.fetchDataVariant(userScore.value as Int)
                                scope.launch {
                                    dataStore.increaseScore(1)
                                }
                            }

                            valida = true
                        }) {
                        Text("Next",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 25.sp,
                        )
                    }
                }
            }
        }
    }




}