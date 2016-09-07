package org.dyne.danielsan.openblockchain

import java.util.Calendar

import org.dyne.danielsan.openblockchain.client.BitcoinClient
import org.dyne.danielsan.openblockchain.data.database.ChainDatabase

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Try

/**
  * Created by dan_mi_sun on 13/03/2016.
  */
object Driver {

  val client = new BitcoinClient

  def main(args: Array[String]) {

    implicit val space = ChainDatabase.space
    implicit val session = ChainDatabase.session

    Await.result(ChainDatabase.autocreate().future, 10.seconds)

    while (true) {
      val startHeight = Try(args(0).toInt).getOrElse(0)

      println(s"$getTimeString scanning from block $startHeight...")
      scanBlock(startHeight)

      println(s"$getTimeString pausing 1 hour...")
      wait(1.hour.toMillis)
    }
  }

  def scanBlock(id: Int): Unit = {
    val rawBlock = client.getBlock(id)
    val transactions = client.getTransactions(rawBlock)
      .filter(_ != null)
      .map(tx => tx.copy(
        is_op_return = Some(tx.hasOpReturnVout),
        time = tx.time * 1000, // s -> ms
        locktime = tx.locktime * 1000, // s -> ms
        blocktime = tx.blocktime * 1000 // s -> ms
      ))

    val blockIsOpReturn = transactions.exists(_.is_op_return.get)
    val block = rawBlock.copy(
      is_op_return = Some(blockIsOpReturn),
      time = rawBlock.time * 1000 // s -> ms
    )

    Await.result(ChainDatabase.insertBlock(block), 10.seconds)
    Await.result(ChainDatabase.insertTransactions(transactions), 10.seconds)

    println(s"$getTimeString saved block ${block.height} with ${transactions.length} transactions")

    if (block.nextblockhash.isDefined && block.nextblockhash.get.nonEmpty) {
      scanBlock(block.height + 1)
    }
  }

  def getTimeString = {
    val cal = Calendar.getInstance()
    val hour = cal.get(Calendar.HOUR_OF_DAY)
    val min = cal.get(Calendar.MINUTE)
    val sec = cal.get(Calendar.SECOND)
    val ms = cal.get(Calendar.MILLISECOND)

    var time = ""
    time += hour
    time += ":"
    time += padLeft(min)
    time += ":"
    time += padLeft(sec)
    time += "."
    time += padRight(ms)
    time
  }

  def padLeft(num: Int): String = {
    if (num < 10) {
      "0" + num
    } else {
      num.toString
    }
  }

  def padRight(num: Int): String = {
    var s = num.toString
    while (s.length < 3) {
      s += "0"
    }
    s
  }

}
