package com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.glideimagegetter.databinding.FragmentFirstBinding
import com.example.glideimagegetter.setTextFromHtml

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // small image 76 X 24
        binding.tv1.setTextFromHtml("Pao <b>₹1000</b>  <img src=\"https://user-images.githubusercontent.com/29930308/156538348-e1da3ab3-c62f-48f6-97e4-e0db43910806.png\" style=\"vertical-align: middle;\">  <b>cashback</b>, goodie bags everytime when your friend purchase a course worth ₹2499 & more.")


        // fit image 123 X 39
        binding.tv2.setTextFromHtml("Pao <b>₹1000</b>  <img src=\"https://user-images.githubusercontent.com/29930308/156516276-4fd07671-e152-4a79-adc1-e61b6310bc17.png\" style=\"vertical-align: middle;\">  <b>cashback</b>, goodie bags everytime when your friend purchase a course worth ₹2499 & more.")

        // 133 X 42
        binding.tv3.setTextFromHtml("Pao <b>₹1000</b>  <img src=\"https://user-images.githubusercontent.com/29930308/156516082-cd9e4fb9-5c93-48f3-a977-55a09bb255d5.png\" style=\"vertical-align: middle;\">  <b>cashback</b>, goodie bags everytime when your friend purchase a course worth ₹2499 & more.")


        // 1082 X 342
        binding.tv4.setTextFromHtml("Pao <b>₹1000</b>  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Paytm_Logo_%28standalone%29.svg/1200px-Paytm_Logo_%28standalone%29.svg.png\" style=\"vertical-align: middle;\">  <b>cashback</b>, goodie bags everytime when your friend purchase a course worth ₹2499 & more.")

        // 312 X 99
        binding.tv5.setTextFromHtml("Pao <b>₹1000</b>  <img src=\"https://user-images.githubusercontent.com/29930308/156545969-8d920586-67c1-4639-8277-47d9aa215adf.png\" style=\"vertical-align: middle;\">  <b>cashback</b>, goodie bags everytime when your friend purchase a course worth ₹2499 & more.")


        binding.tv6.setTextFromHtml("Pao <b>₹1000</b>  <img src=\"https://user-images.githubusercontent.com/29930308/156538348-e1da3ab3-c62f-48f6-97e4-e0db43910806.png\" style=\"vertical-align: middle;\">  <b>cashback</b>, goodie bags everytime when your friend purchase a course worth ₹2499 & more.")


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}