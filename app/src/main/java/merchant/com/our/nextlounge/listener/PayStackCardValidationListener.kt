package merchant.com.our.nextlounge.listener

import android.text.Editable
import merchant.com.our.nextlounge.enums.CardValidity


interface PayStackCardValidationListener {
    fun afterChange(cardValidity: CardValidity, editable: Editable)

    fun paramIsValid(z: Boolean, cardValidity: CardValidity)
}
