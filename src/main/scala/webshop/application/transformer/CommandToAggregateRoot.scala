package webshop.application.transformer

import webshop.application.command._
import webshop.domain.aggregate._

trait CommandToAggregateRoot {

  val commandToAggregateRoot: Command => Aggregate =
    command => {
      command match {
        case AddProductToStoreCommand(id, price, description) =>
          ProductAggregateRoot(id, price, description)
        case AddCustomerCommand(name, email, account) =>
          CustomerAggregateRoot(name, email, account)
        case AddProductToShoppingCardCommand(productId, count, email) =>
          ShoppingAggregateRoot(productId, count, email)
        case PayCommand(email) => PayAggregateRoot(email)
      }
    }
}
