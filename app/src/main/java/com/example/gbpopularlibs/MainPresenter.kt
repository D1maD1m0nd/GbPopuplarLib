package com.example.gbpopularlibs

import com.example.gbpopularlibs.model.CountersModel

class MainPresenter : Contract.Presenter {
    private val model = CountersModel()
    private var _view: Contract.MainView? = null
    private val view get() = _view!!
    override fun onAttach(view: Contract.MainView) {
        _view = view
        initTextData()
    }

    private fun initTextData() {
        view.setButtonText(Numeric.ZERO, model.getCurrent(Numeric.ZERO.value).toString())
        view.setButtonText(Numeric.ONE, model.getCurrent(Numeric.ONE.value).toString())
        view.setButtonText(Numeric.TWO, model.getCurrent(Numeric.TWO.value).toString())
    }

    override fun counterClick(id: Numeric) {
        val value = id.value
        when (id) {
            Numeric.ZERO -> {
                val nextValue = model.next(value)
                view.setButtonText(id, nextValue.toString())
            }
            Numeric.ONE -> {
                val nextValue = model.next(value)
                view.setButtonText(id, nextValue.toString())
            }
            Numeric.TWO -> {
                val nextValue = model.next(value)
                view.setButtonText(id, nextValue.toString())
            }
        }
    }

    override fun onDetach() {
        _view = null
    }
}