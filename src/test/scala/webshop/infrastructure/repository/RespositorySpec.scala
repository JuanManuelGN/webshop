package webshop.infrastructure.repository

import webshop.BaseTest
import webshop.domain.aggregate.{CustomerAggregateRoot, ProductAggregateRoot}
import webshop.infrastructure.dao.DaoMock
import webshop.infrastructure.respository.RepositoryImp

class RepositorySpec extends BaseTest with RepositoryImp with DaoMock {

  test("Is Right") {
    val customerAggregateRoot = CustomerAggregateRoot("Mario", "mario@email.com", "ES900")
    val repositoryResponse = getDataRepository(customerAggregateRoot)

    assert(repositoryResponse.isRight)
  }

  test("Is Left") {
    val customerAggregateRoot = ProductAggregateRoot("orange", 2.8, "juice")
    val repositoryResponse = getDataRepository(customerAggregateRoot)

    assert(repositoryResponse.isLeft)
  }
}
