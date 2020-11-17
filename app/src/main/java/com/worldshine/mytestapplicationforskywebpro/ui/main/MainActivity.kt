package com.worldshine.mytestapplicationforskywebpro.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.worldshine.mytestapplicationforskywebpro.R
import com.worldshine.mytestapplicationforskywebpro.databinding.ActivityMainBinding
import com.worldshine.mytestapplicationforskywebpro.di.component.DaggerActivityComponent
import com.worldshine.mytestapplicationforskywebpro.di.module.NetworkModule
import com.worldshine.mytestapplicationforskywebpro.network.Rest
import com.worldshine.mytestapplicationforskywebpro.utils.setupWithNavController
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    @Inject
    lateinit var rest: Rest
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNavigationBar()
        injectDependency()

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun injectDependency() {
        val component =
            DaggerActivityComponent.builder().networkModule(NetworkModule("https://picsum.photos"))
                .build()
        component.inject(this)
        job?.cancel()
        job = lifecycleScope.launch {
            q()
        }
    }

    suspend fun q() {
        val s = rest.getPictures(1).body()
        Log.d("QWEQWE", s!!.joinToString())
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