package webshop.domain.repository

import webshop.domain.aggregate.Aggregate

trait Repository {
  val getDataRepository : Aggregate => Aggregate
}
