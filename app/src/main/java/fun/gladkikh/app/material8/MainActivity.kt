package `fun`.gladkikh.app.material8

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    val themeIndexLd = MutableLiveData<Int>()

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val index = preferences.getString("listTheme", "1")?:"1"
        setMyTheme(index.toInt())

        setContentView(R.layout.activity_main)


        themeIndexLd.observe(this, Observer {
            //setMyTheme(it)
            recreate()
        })


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            showMessage("Replace with your own action")
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



    }

    fun setMyTheme(index: Int) {
        when (index) {
            1 -> setTheme(R.style.AppTheme_NoActionBar_One)
            2 -> setTheme(R.style.AppTheme_NoActionBar_Two)
            3 -> setTheme(R.style.AppTheme_NoActionBar_Three)
            else -> setTheme(R.style.AppTheme_NoActionBar_One)
        }
    }

    fun showMessage(mess: String) {
        Snackbar.make(fab, mess, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
