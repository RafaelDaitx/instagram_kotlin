package co.tiagoaguiar.course.instagram.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import co.tiagoaguiar.course.instagram.R

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.insetsController?.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
        ///MUDA A COR DOS ICONES NA BARRA DE SATUS

        window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        ///MUDA A COR DA BARRA DE STATUS DO TELEFONE,M O WINDWOW PEGA O CONTEXTO DO APRELHO

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        ///ADICIONANDO ICONE DE CAMERA NA TELA DE LOGIN
    }


}