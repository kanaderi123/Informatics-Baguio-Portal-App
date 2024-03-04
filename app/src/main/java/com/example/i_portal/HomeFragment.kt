package com.example.i_portal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btn_Grade: Button? = view?.findViewById(R.id.btn_Grade)

        btn_Grade?.setOnClickListener {

            it.animate()
                .rotationBy(180f)
                .setDuration(500)
                .withEndAction {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(600).start()

                    val intent = Intent(requireActivity(), Grade::class.java)
                    startActivity(intent)
                }
        }



        val fab: FloatingActionButton? = view?.findViewById(R.id.fab)

        fab?.setOnClickListener {

            it.animate()
                .rotationBy(180f)
                .setDuration(500)
                .withEndAction {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(300).start()

                    val intent = Intent(requireActivity(), AdminRegistration::class.java)
                    startActivity(intent)
                }
        }
        return view

    }
}


