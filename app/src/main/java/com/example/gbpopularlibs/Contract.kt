package com.example.gbpopularlibs

class Contract {
    interface MainView {
        fun setButtonText(index: Numeric, text: String)
    }

    interface Presenter {
        fun onAttach(view: Contract.MainView)
        fun counterClick(id: Numeric)
        fun onDetach()
    }
}