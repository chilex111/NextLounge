package merchant.com.our.nextlounge.listener

import merchant.com.our.nextlounge.model.OrderModel


interface MenuListener {
    fun menuListener(menuOrder: List<OrderModel>?, msg: String)
}
