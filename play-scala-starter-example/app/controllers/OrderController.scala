package controllers

import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.ExecutionContext

class OrderController @Inject() (ws: WSClient, cc: ControllerComponents, ec: ExecutionContext) extends AbstractController(cc) {

  def order = Action.async { request =>

    val wsRequest: WSRequest = ws.url("https://api.shoppable.com/v2/unlv/token/nLT27X5TZLftKyNCf4af770704db811d/order/R723132727")
    wsRequest.addHttpHeaders("Referer" -> "https://www.tresemme.com/us/en/secure/shoppable/confirmation.html?bregion=US&bstate=AZ&bzip=12345&cart=740c85a5-ecf2-4bbf-b3da-a25cbf87f941&email=ssingh234%40sapient.com&oid=R723132727&qamount=&qcode=&qty=20&shipping=0.00&sregion=US&sstate=AZ&subtotal=98.80&szip=12345&tax=0.00&total=98.80")
    wsRequest.addHttpHeaders("Origin" -> "https://www.tresemme.com")
    wsRequest.addHttpHeaders("Accept" -> "application/json, text/javascript, */*; q=0.01")
    //wsRequest.addHttpHeaders("User-Agent" -> "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
    //wsRequest.addHttpHeaders("DNT" -> "1")
    
    wsRequest.get().map {
      response => Ok(response.body)
    }(ec)
  }
}