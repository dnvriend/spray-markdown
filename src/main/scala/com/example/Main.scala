package com.example

import spray.routing.{Route, Directives, SimpleRoutingApp}
import spray.http._
import org.pegdown.{Extensions, PegDownProcessor}
import akka.actor.{ActorRefFactory, ActorSystem}

trait MarkdownService extends Directives {
 implicit def actorRefFactory: ActorRefFactory

  def markdownRoute: Route =
   pathPrefix("css") {
     getFromResourceDirectory("css")
   } ~
   pathPrefix("doc") {
     mapHttpResponseEntity(markdown2Html) {
       getFromResourceDirectory("md")
     }
   }

  def markdown2Html(entity: HttpEntity): HttpEntity = {
    def body(html: String): String =
      """<html><head><link rel="stylesheet" type="text/css" href="/css/markdown.css"/></head><body>""" + html + "</body></html>"
    if(entity.isEmpty) entity else {
      val html = new PegDownProcessor(Extensions.ALL).markdownToHtml(entity.asString)
      HttpEntity(ContentType(MediaTypes.`text/html`, HttpCharsets.`UTF-8`), body(html))
    }
  }
}

object Main extends App with SimpleRoutingApp with MarkdownService{
  implicit val system = ActorSystem("my-system")

  val config = Config(system)

  startServer(interface = config.bindAddress, port = config.bindPort) {
    markdownRoute
  }

}
