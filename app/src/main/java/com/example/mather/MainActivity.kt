    package com.example.mather

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.text.Editable
    import android.text.TextWatcher
    import android.widget.EditText
    import android.widget.SeekBar
    import android.widget.TextView
    import java.text.DecimalFormat

    class MainActivity : AppCompatActivity() {

        private lateinit var billAmountEditText: EditText
        private lateinit var tipPercentageSeekBar: SeekBar
        private lateinit var selectedTipPercentageTextView: TextView
        private lateinit var calculatedTipAmountTextView: TextView
        private lateinit var totalAmountTextView: TextView

        private var billAmount: Double = 0.0
        private var tipPercentage: Int = 15

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            billAmountEditText = findViewById(R.id.editText_billAmount)
            tipPercentageSeekBar = findViewById(R.id.seekBar_tipPercentage)
            selectedTipPercentageTextView = findViewById(R.id.textView_selectedTipPercentage)
            calculatedTipAmountTextView = findViewById(R.id.textView_calculatedTipAmount)
            totalAmountTextView = findViewById(R.id.textView_totalAmount)

            billAmountEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    try {
                        billAmount = s.toString().toDouble()
                    } catch (e: NumberFormatException) {
                        billAmount = 0.0
                    }
                    calculateTipAndTotalAmount()
                }

                override fun afterTextChanged(s: Editable?) {
                    // Do nothing
                }
            })

            tipPercentageSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    tipPercentage = progress
                    selectedTipPercentageTextView.text = "$tipPercentage%"
                    calculateTipAndTotalAmount()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // Do nothing
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    // Do nothing
                }
            })
        }

        private fun calculateTipAndTotalAmount() {
            val decimalFormat = DecimalFormat("#.##")
            val tipAmount = billAmount * tipPercentage / 100
            calculatedTipAmountTextView.text = "$" + decimalFormat.format(tipAmount)
            totalAmountTextView.text = "$" + decimalFormat.format(billAmount + tipAmount)
        }
    }
