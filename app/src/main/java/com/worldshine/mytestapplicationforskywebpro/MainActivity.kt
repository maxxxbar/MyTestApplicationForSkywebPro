package com.worldshine.mytestapplicationforskywebpro

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.worldshine.mytestapplicationforskywebpro.data.PicturesDataSource
import com.worldshine.mytestapplicationforskywebpro.data.PicturesRepository
import com.worldshine.mytestapplicationforskywebpro.databinding.ActivityMainBinding
import com.worldshine.mytestapplicationforskywebpro.network.Connection
import com.worldshine.mytestapplicationforskywebpro.network.Rest
import com.worldshine.mytestapplicationforskywebpro.utils.setupWithNavController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNavigationBar()


        testFun()
    }



    private fun testFun() {
/*        val retrofit = Connection.create
        retrofit.getPictures(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    Log.d(TAG, "Error: ${it.localizedMessage}")
                },
                onSuccess = {
                    Log.d(TAG, "Completed: ${it.joinToString()}")
                }
            )*/

    val picturesRepository = PicturesRepository(Connection.getPicturesWithRetrofit())
        picturesRepository.getResultAsLiveData().observe(this) {

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setNavigationBar() {
        val bottomNavigationBar = binding.navView
        val navGraphIds = listOf(R.navigation.pictures_graph, R.navigation.authorization_graph)
        val controller = bottomNavigationBar.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        currentNavController = controller
    }
}