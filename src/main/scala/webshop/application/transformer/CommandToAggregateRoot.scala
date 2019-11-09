package webshop.application.transformer

import webshop.application.command.{Command, AddProductToStoreCommand}
import webshop.domain.aggregate.{Aggregate, ProductAggregateRoot}

trait CommandToAggregateRoot {

  val commandToAggregateRoot: Command => Aggregate =
    command => {
      command match {
        case AddProductToStoreCommand(id, price, description) => ProductAggregateRoot(id, price, description)
      }
    }
}
