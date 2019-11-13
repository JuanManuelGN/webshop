package webshop.infrastructure.dao

import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto}

trait DaoMock extends Dao {

  override val getDataDao: DaoDto => Either[String, DaoDto] = daoDto => {
    daoDto match {
      case d: CustomerDaoDto => Right(d)
      case _ => Left("help me")
    }
  }

}
