package com.example.gbpopularlibs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gbpopularlibs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Contract.MainView {

    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!
    private var presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        if (savedInstanceState != null) {
            presenter = lastCustomNonConfigurationInstance as MainPresenter
        }
        presenter.onAttach(this)
        initView()
    }

    private fun initView() = with(vb) {
        vb.btnCounter1.setOnClickListener {
            presenter.counterClick(Numeric.ZERO)
        }

        vb.btnCounter2.setOnClickListener {
            presenter.counterClick(Numeric.ONE)
        }
        vb.btnCounter3.setOnClickListener {
            presenter.counterClick(Numeric.TWO)
        }
    }

    override fun onRetainCustomNonConfigurationInstance(): MainPresenter {
        return presenter
    }

    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(index: Numeric, text: String) = with(vb) {
        when (index) {
            Numeric.ZERO -> btnCounter1.text = text
            Numeric.ONE -> btnCounter2.text = text
            Numeric.TWO -> btnCounter3.text = text
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}

