package com.knoldus

import org.scalatest.FunSuite

class TwitterTest extends FunSuite{

  test("Connection with the client"){

    val result = Twitter.getClient().toString
    assert("[org.elasticsearch.client.transport.TransportClient@5ac86ba5]"===result)
  }

  test("Insertion in index"){

    val result = Twitter.readJsonAndInsertToIndex("/home/knoldus/Desktop/ElasticSearchWithJson/inputJson.json")
    assert(result===270)
  }

  test("Insertion in the output file"){

    val result = Twitter.readIndexAndInsertToJson()
    assert(result!=null)
  }

}
