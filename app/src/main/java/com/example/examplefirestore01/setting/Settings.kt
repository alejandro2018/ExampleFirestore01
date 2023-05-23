package com.example.example01.setting

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavHostController,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Settings(context)
    val userScore = dataStore.getScore.collectAsState(initial = "dele")
    Column (
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text=userScore.value!!.toString())
        Button(
            onClick = {
                scope.launch {
                    dataStore.increaseScore(1)
                }
            }
        ) {
            Text(text=" Add 1")
        }
        Button(
            onClick = {
                scope.launch {
                    dataStore.deleteScore()
                }
            }
        ) {
            Text(text="Leave at 0 userscore")
        }
    }
}

class Settings(private val context: Context){

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userScore")
        val USER_SCORE = intPreferencesKey("user_email")
    }

    val getScore: Flow<Int?> = context.dataStore.data
        .map { preferences->
            preferences[USER_SCORE] ?: 8
        }

    suspend fun increaseScore(tun: Int){
        context.dataStore.edit { preferences->
            val currentCounterValue = preferences[USER_SCORE] ?: 1
            preferences[USER_SCORE] = currentCounterValue + tun
        }
    }

    suspend fun deleteScore(){
        context.dataStore.edit { preferences->
            val deleteScoreZero = 1
            preferences[USER_SCORE] = deleteScoreZero
        }
    }

}