package com.example.memorinak

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var firstCard: ImageView? = null
    private var openCardsCount = 0
    private var matchedPairs = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val board = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val rows = Array(ROW_COUNT) {
            LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f
                )
            }
        }

        val legions = arrayOf(
            "ultramarines", "death_guard", "emperors_children", "raven_guard",
            "salamanders", "thousand_sons", "white_scars", "world_eaters"
        )
        val drawables = mapOf(
            "ultramarines" to R.drawable.ultramarines,
            "death_guard" to R.drawable.death_guard,
            "emperors_children" to R.drawable.emperors_children,
            "raven_guard" to R.drawable.raven_guard,
            "salamanders" to R.drawable.salamanders,
            "thousand_sons" to R.drawable.thousand_sons,
            "white_scars" to R.drawable.white_scars,
            "world_eaters" to R.drawable.world_eaters
        )

        val deck = legions.flatMap { listOf(it, it) }.shuffled()

        deck.forEachIndexed { index, legion ->
            val card = ImageView(this).apply {
                setBackgroundResource(R.drawable.warhammer_logo)
                setImageResource(drawables.getValue(legion))
                imageAlpha = 0
                layoutParams = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f
                ).apply { setMargins(4, 4, 4, 4) }
                tag = legion
                setOnClickListener { onCardClick(it as ImageView) }
            }
            rows[index / CARDS_IN_ROW].addView(card)
        }

        rows.forEach { board.addView(it) }
        setContentView(board)
    }

    private fun onCardClick(card: ImageView) {
        if (openCardsCount == 2 || !card.isClickable) return

        showFace(card)
        card.isClickable = false

        if (openCardsCount == 0) {
            firstCard = card
            openCardsCount = 1
            return
        }

        val first = firstCard ?: return
        openCardsCount = 2

        if (first.tag == card.tag) {
            matchedPairs++
            card.postDelayed({
                hideMatched(first)
                hideMatched(card)
                resetTurn()
                if (matchedPairs == PAIRS_COUNT) {
                    Toast.makeText(this, "Все пары найдены!", Toast.LENGTH_SHORT).show()
                }
            }, MATCH_DELAY)
        } else {
            card.postDelayed({
                showBack(first)
                showBack(card)
                resetTurn()
            }, MISMATCH_DELAY)
        }
    }

    private fun resetTurn() {
        firstCard = null
        openCardsCount = 0
    }

    private fun showFace(card: ImageView) {
        card.background = null
        card.imageAlpha = 255
    }

    private fun showBack(card: ImageView) {
        card.setBackgroundResource(R.drawable.warhammer_logo)
        card.imageAlpha = 0
        card.isClickable = true
    }

    private fun hideMatched(card: ImageView) {
        card.visibility = View.INVISIBLE
        card.isClickable = false
    }

    companion object {
        private const val ROW_COUNT = 4
        private const val CARDS_IN_ROW = 4
        private const val PAIRS_COUNT = 8
        private const val MATCH_DELAY = 600L
        private const val MISMATCH_DELAY = 1000L
    }
}
