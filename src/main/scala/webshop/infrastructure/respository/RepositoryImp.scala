package webshop.infrastructure.respository

import webshop.application.transformer.{AggregateRootToDaoDto, DaoDtoToAggregateRoot}
import webshop.domain.aggregate.Aggregate
import webshop.domain.repository.Repository
import webshop.infrastructure.dao.Dao

trait RepositoryImp extends Repository
  with AggregateRootToDaoDto with Dao with DaoDtoToAggregateRoot {

  val getDataRepository : Aggregate => Either[String, Aggregate] = aggregate => {
    daoDtoToAggregateRoot.compose(getDataDao).compose(aggregateToDaoDto)(aggregate)
  }

}
