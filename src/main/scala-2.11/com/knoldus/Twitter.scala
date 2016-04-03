package com.knoldus

import java.io.{File, PrintWriter}
import java.net.InetAddress

import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin

import scala.io.Source

object Twitter {

  def getClient():Client ={

    val settings:Settings= Settings.settingsBuilder().put("cluster.name","sonu").build()
    val client: Client = TransportClient.builder().settings(settings).addPlugin(classOf[DeleteByQueryPlugin]).build()
      .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300))
    client
  }

  def readJsonAndInsertToIndex(file:String):Int={

    val allLines = Source.fromFile(file).getLines().toList
    val client = getClient()
    val result = client.prepareBulk()
    allLines map {
      line => result.add(client.prepareIndex("twitter","tweet").setSource(line))
    }
    val response = result.execute().actionGet()
    response.getItems.length
  }

  def readIndexAndInsertToJson():Unit={

    val allLines = getClient().prepareSearch("twitter").setTypes("tweet").execute().get()
    val file = new File("/home/knoldus/Desktop/ElasticSearchWithJson/outputJson.Json")
    val insert = new PrintWriter(file)
    insert.write(allLines.toString)
  }
}


